<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:genericpage>
    <jsp:body>
        <div id="page-wrapper" style="min-height: 563px !important;">
            <div class="container">
                <h3 class="uni-header"><span>Edit photographer info</span></h3>
                <div class="col-md-12">

                    <div class="row clearfix">
                        <div class="btn-container-top">
                            <button class="btn btn-action-top" onclick="initUpdate()">Save</button>
                            <button class="btn btn-action-top">Save&nbsp;&&nbsp;Close</button>
                            <button class="btn btn-action-top">Save&nbsp;&&nbsp;New</button>
                            <button class="btn btn-action-top">Cancel</button>
                        </div>


                        <div class="form-group">
                            <label>Full Name</label>
                            <input id="fullName" class="form-control" value="${photographer.fullName}">
                        </div>
                        <div class="form-group">
                            <label>Phone Number</label>
                            <input id="phoneNumber" class="form-control" type="Number" value="${photographer.phoneNumber}" >
                        </div>
                        <div class="form-group">
                            <label>User Name</label>
                            <input id=userName class="form-control" value="${photographer.userName}" >
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <input id="email" class="form-control" value="${photographer.email}" >
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
                            <div class="file-tab panel-body">

                                <div id="profileImg" >
                                    <div class="dz-default dz-message">
                                        <span>Change Profile Picture</span>
                                        <p id="errorObj_profilePictureToken"></p>
                                    </div>
                                </div>
                                <div>
                                    <c:set value="" var="imgSrc" />
                                    <c:choose>
                                        <c:when test="${photographer.profilePhoto==null || photographer.profilePhoto.trim().equals('')}">
                                            <c:set value="/resources/images/default_profile_pic.png" var="imgSrc" />
                                        </c:when>
                                        <c:otherwise>
                                            <c:set value="/photographer-profile-img/${photographer.profilePhoto}" var="imgSrc" />
                                        </c:otherwise>
                                    </c:choose>
                                    <img id="profilePic" onerror="this.src='<c:url value="/resources/images/default_alternate.png" />'" src="<c:url value="${imgSrc}" /> " class="img-thumbnail" width="150">

                                </div>
                            </div>
                        </div>
                        <!-- 
                        <div class="form-group">
                        	<label>Require Password reset</label>
	                        <div class="btn-group choose-btn">
	                            <button type="button" class="active btn btn-default" id="regi1">Yes</button>
	                            <button type="button" class="btn btn-default" id="regi3">No</button>
	                        </div>
                        </div> 
                        
                        -->

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

            <%--Developer's Hidden Fields--%>
            <input id="photographer_id" type="hidden" value="${photographer.id}" />


        </div>
        <script>
            var updateCount = 0;
            function notifyUpdateStatus(uCount){

                location.reload();
            }
            function initUpdate(){
                UnBindErrors("errorObj_");
                update(1);
            }
        function update(count){

            var password =  $("#password").val();
            updateCount =0;
            switch(count){
                case 1:
                    updateBasicInfo();
                    break;
                case 2:
                    if(password!=null && password.trim()!=""){
                        updatePassword();
                    }else{
                        update(3);
                    }
                    break;
                case 3:
                    if(profilePictureToken>0){
                        updateProfilePicture(profilePictureToken);
                    }else{
                        update(4);
                    }
                    break;
                case 4:
                    notifyUpdateStatus();
            }



        }
        function updateBasicInfo(){
            var photographerId = $("#photographer_id").val();
        	var fullName = $("#fullName").val();
        	var phoneNumber = $("#phoneNumber").val();
            var email = $("#email").val();
            var userName = $("#userName").val();
            
            var data = {
            			"fullName":fullName,
            			"phoneNumber":phoneNumber,
            			"email":email,
            			"userName":userName,
                        "profilePictureToken":profilePictureToken
            		};
            $.ajax({
                url: BASEURL+'api/photographer/update/profile-info/'+photographerId,
                data:data,
                type: 'POST',
                statusCode: {
                    401: function (response) {
                        console.log(response);
                    },
                    422: function (response) {

                        BindErrorsWithHtml("errorObj_",response.responseJSON,true);
                        console.log(response);
                    }
                },success: function(data){
                    update(2);
                }
            });
            console.log(data)
           
        }
        function updatePassword(){
            var photographerId = $("#photographer_id").val();
            var password =  $("#password").val();
            var confirmPassword = 	$("#confirmPassword").val();

            var data = {
                "password":password,
                "confirmPassword":confirmPassword,
                "profilePictureToken":profilePictureToken
            };
            $.ajax({
                url: BASEURL+'api/photographer/update/profile-password/'+photographerId,
                data:data,
                type: 'POST',
                statusCode: {
                    401: function (response) {
                        console.log(response);
                    },
                    422: function (response) {

                        BindErrorsWithHtml("errorObj_",response.responseJSON,true);
                        console.log(response);
                    }
                },success: function(data){
                    update(3);
                }
            });
            console.log(data)

        }
        function updateProfilePicture(token){
            var photographerId = $("#photographer_id").val();
            var data = {
                "profilePictureToken":token
            };
            $.ajax({
                url: BASEURL+'api/photographer/update/profile-picture/'+photographerId,
                data:data,
                type: 'POST',
                statusCode: {
                    401: function (response) {
                        console.log(response);
                    },
                    422: function (response) {

                        BindErrorsWithHtml("errorObj_",response.responseJSON,true);
                        console.log(response);
                    }
                },success: function(data){
                    update(4);
                    /**
                     * Update global token
                     * Once token is used it is deleted from server
                    * */
                    profilePictureToken = 0;
                }
            });
            console.log(data)

        }
        
        </script>
        
        
        
        <!-- /#wrapper -->

        <link href="<s:url value="/resources/css/dropzone.css"/>" rel="stylesheet">
        <script src="https://rawgit.com/enyo/dropzone/master/dist/min/dropzone.min.js"></script>


        <!-- dropzone -->
        <script>

            Dropzone.autoDiscover = false;
            var profilePictureToken = 0;
            var otherImagesToken = [];

            // alternative to DOMContentLoaded
            document.onreadystatechange = function () {
                if (document.readyState == "interactive") {
                    $(function() {
                        var profileImgDropzone = new Dropzone("div#profileImg",
                            {
                                url: BASEURL+"file/upload/photographer-profile-image",
                                method:"post",
                                paramName:"profileImg",
                                maxFilesize: 1,
                                maxFiles:1,
                                //addRemoveLinks: true,
                                previewTemplate:$("#dropZonePreview").html(),
                                init:function(){

                                    this.on("maxfilesexceeded", function(file) {
                                        console.log("maxfilesexceeded");
                                        this.removeAllFiles();
                                        this.addFile(file);
                                    });
                                    this.on("addedfile",function(file){
                                        $("#profilePic").hide();
                                    });
                                },
                                error:function(file,response){
                                    var msg = (typeof response == "object")?((response.length>0)?response[0].msg:""):response;
                                    $("#profileImg").find(".dz-error-message span").html(msg);
                                    $("#profilePic").hide();

                                },
                                success:function(file,response){

                                    file.token = response.token;
                                    profilePictureToken = response.token;
                                }
                            }
                        );

                    });
                }
            }

            function removeImageByToken(token){
                if(token == undefined){
                    return;
                }
                $.ajax({
                    url: BASEURL+'file/remove/photographer-profile-image',
                    data:{"token":token},
                    type: 'POST',
                    statusCode: {
                        401: function (response) {
                            console.log(response);
                        },
                        422: function (response) {
                            console.log(response);
                        }
                    },success: function(data){
                        console.log(data);
                    }
                });
            }
        </script>


        
    </jsp:body>





</t:genericpage>