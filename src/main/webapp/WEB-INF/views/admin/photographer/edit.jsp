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
                            <button class="btn btn-action-top" onclick="initUpdate('save')">Save</button>
                            <button class="btn btn-action-top" onclick="initUpdate('save_and_close')">Save&nbsp;&&nbsp;Close</button>
                            <button class="btn btn-action-top" onclick="initUpdate('save_and_new')">Save&nbsp;&&nbsp;New</button>
                            <button class="btn btn-action-top" onclick="photographerAfterSaveAction('cancel')">Cancel</button>
                            <button onclick="deleteEntity(${photographer.id},'photographer')" class="btn btn-action-top">Delete</button>
                            <br>
                            <span id="successMsg"></span>
                        </div>


                        <div class="form-group">
                            <label>Full Name</label>
                            <input id="fullName" class="form-control" value="${photographer.fullName}">
                        </div>
                        <div class="form-group">
                            <label>Phone Number</label>
                            <input id="phoneNumber" class="form-control" type="text" value="${photographer.phoneNumber}" >
                        </div>
                        <div class="form-group">
                            <label>User Name</label>
                            <input id=userName disabled="disabled" class="form-control" value="${photographer.userName}" >
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <input id="email" disabled="disabled" class="form-control" value="${photographer.email}" >
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

            <%@include file="../others/delete-modal.jsp"%>

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
        <!-- /#wrapper -->
        <link href="<s:url value="/resources/css/dropzone.css"/>" rel="stylesheet">
        <script src="https://rawgit.com/enyo/dropzone/master/dist/min/dropzone.min.js"></script>
        <script src="<s:url value="/resources/developer/js/photographer/update.js"/>" ></script>

        <%--Edit helper--%>
        <script src="<s:url value="/resources/developer/js/helper/edit.helper.js"/>"></script>
    </jsp:body>
</t:genericpage>