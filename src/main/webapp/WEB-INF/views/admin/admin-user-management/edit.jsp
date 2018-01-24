<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:genericpage>
    <jsp:body>
        <div id="page-wrapper" style="min-height: 563px !important;">
            <div class="container">
                <h3 class="uni-header"><span>Edit admin info</span></h3>
                <div class="col-md-12">

                    <div class="row clearfix">
                        <div class="btn-container-top">

                            <button class="btn btn-action-top" onclick="submitUpdatedAdminUserData('save')">Save</button>
                            <button class="btn btn-action-top" onclick="submitUpdatedAdminUserData('save-close')">Save&nbsp;&&nbsp;Close</button>
                            <button class="btn btn-action-top" onclick="submitUpdatedAdminUserData('save-new')">Save&nbsp;&&nbsp;New</button>
                            <a href="<c:url value="/admin/user/all"/>" class="btn btn-action-top">Cancel</a>

                            <br>
                            <span id="successMsg"></span>
                        </div>


                        <div class="form-group">
                            <label>Full Name</label>
                            <input id="fullName" class="form-control" value="${admin.name}">
                        </div>
                        <div class="form-group">
                            <label>Phone Number</label>
                            <input id="phoneNumber" class="form-control" type="Number" value="${admin.phoneNumber}" >
                        </div>
                        <div class="form-group">
                            <label>User Name</label>
                            <input id="userName" class="form-control"  value="${admin.userName}" disabled="disabled" >
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <input id="email" class="form-control" value="${admin.email}" disabled="disabled" >

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
                            <div>
                                <c:set value="" var="imgSrc" />
                                <c:choose>
                                    <c:when test="${admin.image==null || admin.image.trim().equals('')}">
                                        <c:set value="/resources/images/default_profile_pic.png" var="imgSrc" />
                                    </c:when>
                                    <c:otherwise>
                                        <c:set value="/common/${admin.image}" var="imgSrc" />
                                    </c:otherwise>
                                </c:choose>
                                <img id="profilePic" onerror="this.src='<c:url value="/resources/images/default_alternate.png" />'" src="<c:url value="${imgSrc}" /> " class="img-thumbnail" width="150">

                            </div>
                            <div class="file-tab panel-body">

                                <div id="profilePicImg" >

                                    <div class="dz-default dz-message">
                                        <div class="droper">
                                            <p class="dropicon"><i class="fa fa-cloud-upload"></i> </p>
                                            <p class="dropext">Click or Drop your file here</p>
                                        </div>
                                        <p id="errorObj_profilePictureToken"></p>
                                    </div>
                                </div>

                                <p id="errorObj_locationLogo"  class="text-danger"></p>
                            </div>
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

            <%--Developer's Hidden Fields--%>
            <input id="admin_id" type="hidden" value="${admin.id}" />






        <!-- /#wrapper -->

        <link href="<s:url value="/resources/css/dropzone.css"/>" rel="stylesheet">
        <script src="https://rawgit.com/enyo/dropzone/master/dist/min/dropzone.min.js"></script>

        <script src="<s:url value="/resources/developer/js/temp-file/common.js"/>"></script>
        <script src="<s:url value="/resources/developer/js/admin-user/common.js"/>"></script>
        <script src="<s:url value="/resources/developer/js/admin-user/update.js"/>"></script>



    </jsp:body>





</t:genericpage>