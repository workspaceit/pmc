<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:genericpage>
    <jsp:body>
        <div id="page-wrapper" style="min-height: 563px !important;">
            <div class="container">
                <h3 class="uni-header"><span>Personal Details</span></h3>
                <div class="col-md-12">
                    <div class="row">
                        <div class="btn-container-top">
                            <button class="btn btn-action-top" onclick="submitProfileUpdateData('save')">Save</button>
                            <button class="btn btn-action-top" onclick="submitProfileUpdateData('save_and_close')">Save&nbsp;&&nbsp;Close</button>
                            <button class="btn btn-action-top" onclick="submitProfileUpdateData('save_and_new')">Save&nbsp;&&nbsp;New</button>
                            <button class="btn btn-action-top" onclick="submitProfileUpdateData('cancel')">Cancel</button>
                        </div>
                        <div class="form-group">
                            <label>Full Name</label>
                            <input id="fullName"  class="form-control" value="${user.name}">
                        </div>
                        <div class="form-group">
                            <label>Phone Number</label>
                            <input id="phoneNumber" class="form-control" type="text" value="${user.phoneNumber}" >
                        </div>
                        <div class="form-group">
                            <label>User Name</label>
                            <input disabled="disabled" class="form-control" value="${user.userName}" >
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <input disabled="disabled" class="form-control" value="${user.email}" >
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <input id="password" class="form-control" type="password" placeholder="******">
                        </div>
                        <div class="form-group">
                            <label>Confirm Password</label>
                            <input id="confirmPassword" class="form-control" type="password" placeholder="******">
                        </div>
                        <div class="imageupload panel panel-default">
                            <div class="panel-heading clearfix">
                                <h4 class="panel-title pull-left">Profile Image</h4>
                                <div class="btn-group pull-right">
                                </div>
                            </div>
                            <c:set value="" var="imgSrc" />
                            <c:choose>
                                <c:when test="${user.image==null || user.image.trim().equals('')}">
                                    <c:set value="/resources/images/default_profile_pic.png" var="imgSrc" />
                                </c:when>
                                <c:otherwise>
                                    <c:set value="/common/${user.image}" var="imgSrc" />
                                </c:otherwise>
                            </c:choose>
                            <img onerror="this.src='<c:url value="/resources/images/default_alternate.png" />'" src="<c:url value="${imgSrc}" />" class="img-thumbnail" width="150">

                            <div id="profileImageDiv"  class="panel-body" >
                                <div class="dz-default dz-message">
                                    <span>Click here to upload</span>
                                    <p id="errorObj_"></p>
                                </div>
                            </div>
                        </div>
                        <!-- <div class="form-group">
                        <label>Require Password reset</label>
                        <div class="btn-group choose-btn">
                            <button type="button" class="active btn btn-default" id="regi1">Yes</button>
                            <button type="button" class="btn btn-default" id="regi3">No</button>
                        </div>
                        </div> -->

                    </div>

                </div>
            </div>
        </div>
        <%-- After image add Dropzone Image preview --%>
        <div id="dropZonePreview" style="display: none">
            <div class="dz-preview dz-file-preview">
                <div class="dz-details">
                    <div class="dz-filename"><span data-dz-name></span></div>
                    <div class="dz-size" data-dz-size></div>
                    <img data-dz-thumbnail />
                </div>
                <div class="dz-progress"><span class="dz-upload" data-dz-uploadprogress></span></div>
                    <%--<div class="dz-success-mark"><span>✔</span></div>
                    <div class="dz-error-mark"><span>✘</span></div>--%>
                <div class="dz-error-message">
                    <span data-dz-errormessage></span>
                </div>
            </div>
        </div>

        <script src="<s:url value="/resources/developer/js/temp-file/common.js"/>"></script>
        <link href="<s:url value="/resources/css/dropzone.css"/>" rel="stylesheet">
        <script src="<s:url value="/resources/js/dropzone.min.js"/>"></script>
        <script src="<s:url value="/resources/developer/js/util/dropzone-common.js" />" ></script>
        <script>

            var PROFILE_IMG_KEY={_PROFILE_IMAGE_TOKEN:"_PROFILE_IMAGE_TOKE"};
            /*Gallery Ads*/
            var logoBackgroundImage = configDropZone("profileImageDiv","admin-profile-image",1,1,function(response){
                emptyToken(PROFILE_IMG_KEY._PROFILE_IMAGE_TOKEN);
                storeToken(PROFILE_IMG_KEY._PROFILE_IMAGE_TOKEN,response.token);
            },function(response){
                removeToken(PROFILE_IMG_KEY._PROFILE_IMAGE_TOKEN,response.token);
            });

            function submitProfileUpdateData(actionName){
                var fullName = $('#fullName').val();
                var phoneNumber = $('#phoneNumber').val();
                var password = $('#password').val();
                var confirmPassword = $('#confirmPassword').val();
                var profilePictureToken = getToken(PROFILE_IMG_KEY._PROFILE_IMAGE_TOKEN);
                var data = {
                    fullName: fullName,
                    phoneNumber: phoneNumber,
                    password: password,
                    confirmPassword:confirmPassword,
                    profilePictureToken:profilePictureToken
                };
                $.ajax({
                    url: BASEURL+"api/admin/update-profile",
                    type: "POST",
                    data: data,
                    traditional:true,
                    statusCode:{
                                500: function(response) {
                                    console.log(response);
                                }, 401: function(response) {
                                    console.log(response.responseJSON);
                                }, 422: function(response) {
                                    BindErrorsWithHtml("errorObj_",response.responseJSON,false);
                                }
                    },success: function(response) {
                        redirectFromProfileUpdate(actionName);

                    }
                });
            }
            function redirectFromProfileUpdate(actionStr){
                var url = BASEURL+"admin/user";
                switch(actionStr){
                    case "save":
                        url += "/all";
                        break;
                    case "save_and_new":
                        url += "/add";
                        break;
                    case "save_and_close":
                        url += "/all";
                        break;
                    case "cancel":
                        url += "/all";
                        break;
                }
                window.location = url;
            }
        </script>



    </jsp:body>

</t:genericpage>