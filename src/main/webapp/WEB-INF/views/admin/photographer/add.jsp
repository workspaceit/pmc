<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<t:genericpage>
    <jsp:body>
        <div id="page-wrapper" style="min-height: 563px !important;">
            <div class="container">
                <h3 class="uni-header"><span>Photographer Details</span></h3>
                <div class="col-md-12">

                    <div class="row clearfix">
                        <div class="btn-container-top">
                            <button class="btn btn-action-top" onclick="submitPhotographerData('save')" >Save</button>
                            <button class="btn btn-action-top" onclick="submitPhotographerData('save_and_close')" >Save&nbsp;&&nbsp;Close</button>
                            <button class="btn btn-action-top" onclick="submitPhotographerData('save_and_new')" >Save&nbsp;&&nbsp;New</button>
                            <a class="btn btn-action-top" href="<c:url value="/admin/photographer/all"/>" >Cancel</a>
                        </div>
                        <div class="form-group">
                            <label>Full Name</label>
                            <input id="fullName" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Phone Number</label>
                            <input id="phoneNumber" class="form-control" type="text">
                        </div>
                        <div class="form-group">
                            <label>User Name</label>
                            <input id=userName class="form-control">
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
                                <div id="profileImg" >

                                    <div class="dz-default dz-message">
                                        <div class="droper">
                                            <p class="dropicon"><i class="fa fa-cloud-upload"></i> </p>
                                            <p class="dropext">Click or Drop your file here</p>
                                        </div>
                                        <p id="errorObj_profilePictureToken"></p>
                                    </div>
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
        </div>
        
        <!-- /#wrapper -->
        <!-- dropzone -->
        <link href="<s:url value="/resources/css/dropzone.css"/>" rel="stylesheet">
        <script src="<s:url value="/resources/js/dropzone.min.js"/>"></script>

        <script src="<s:url value="/resources/developer/js/photographer/create.js"/>" ></script>
        
    </jsp:body>

</t:genericpage>