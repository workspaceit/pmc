<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<t:genericpage>

    <jsp:body>
        <!-- dropzone css -->
        <!-- /#page-wrapper -->
        <div id="page-wrapper" style="min-height: 563px !important;">
            <div class="container">
                <h3 class="uni-header"><span>Personal Details</span></h3>
                <div class="col-md-12">
                    <div class="row">
                        <div class="btn-container-top">
                            <button class="btn btn-action-top"  onclick="submitAdminUserData('save')">Save</button>
                            <button class="btn btn-action-top" onclick="submitAdminUserData('save-close')">Save&nbsp;&&nbsp;Close</button>
                            <button class="btn btn-action-top" onclick="submitAdminUserData('save-new')">Save&nbsp;&&nbsp;New</button>
                            <a href="<c:url value="/admin/user/all"/>" class="btn btn-action-top">Cancel</a>
                        </div>
                        <div class="form-group">
                            <label>Full Name</label>
                            <input id="fullName" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Phone Number</label>
                            <input id="phoneNumber" class="form-control" type="Number">
                        </div>
                        <div class="form-group">
                            <label>User Name</label>
                            <input id="userName" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <input id="email" class="form-control">
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
                                    <%--<label class="btn btn-primary btn-file btn-sm-new">
                                        <span>Browse</span>
                                        <!-- The file is stored here. -->
                                        <input type="file" name="image-file">
                                    </label>
                                    <button type="button" class="btn btn-danger btn-sm-new">Delete image</button>
--%>
                                <div id="profilePicImg" >

                                    <div class="dz-default dz-message">
                                        <span>Drop files here to upload</span>
                                        <p id="errorObj_profilePictureToken"></p>
                                    </div>
                                </div>

                                <p id="errorObj_locationLogo"  class="text-danger"></p>
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

        <!-- dropzone -->
        <link href="<s:url value="/resources/css/dropzone.css"/>" rel="stylesheet">
        <script src="<s:url value="/resources/js/dropzone.min.js"/>"></script><!-- dropzone -->

        <script src="<s:url value="/resources/developer/js/temp-file/common.js"/>"></script>
        <script src="<s:url value="/resources/developer/js/admin-user/common.js"/>"></script>
        <script src="<s:url value="/resources/developer/js/admin-user/create.js"/>"></script>








        <!-- Morris Charts JavaScript -->
        <!--     <script src="js/plugins/morris/raphael.min.js"></script>
        <script src="js/plugins/morris/morris.min.js"></script>
        <script src="js/plugins/morris/morris-data.js"></script> -->





    </jsp:body>
</t:genericpage>