<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<t:genericpage>

    <jsp:body>
        <!-- /#page-wrapper -->
        <div id="page-wrapper">

            <div class="container">
                <h3 class="uni-header"><span>Add New Location</span></h3>

                <div class="col-md-12">
                    <div class="row">
                        <div class="btn-container-top">
                            <button class="btn btn-action-top" onclick="submitLocationData('save')">Save</button>
                            <button class="btn btn-action-top" onclick="submitLocationData('save_and_close')">Save&nbsp;&&nbsp;Close</button>
                            <button class="btn btn-action-top" onclick="submitLocationData('save_and_new')">Save&nbsp;&&nbsp;New</button>
                            <button class="btn btn-action-top" onclick="locationAfterSaveAction('cancel')">Cancel</button>
                        </div>
                        <div class="form-group">
                            <div id="locationFormBody" class="panel panel-default">
                                <div class="panel-body">

                                    <div class="form-group">
                                        <label>Event Location</label>
                                        <input id="name"  class="form-control">
                                    </div>

                                    <div class="form-group">
                                        <label>Address</label>
                                        <input id="address" class="form-control">
                                    </div>

                                    <div class="row clearfix">
                                        <!-- <div class="col-md-3">
                                            <div class="form-group">
                                            <label>
                                            <a href="#" style="color:#333;font-size: 14px;" data-toggle="modal" data-target="#add-new-city">City</a>
                                            </label>
                                            <select class="js-example-placeholder-multiple js-states form-control" multiple="multiple">
                                                <option>Miami</option>
                                                <option>Florida</option>
                                                <option>California</option>
                                            </select>
                                            </div>
                                        </div> -->
                                        <!-- <div class="col-md-3">
                                            <div class="form-group">
                                            <label>
                                            <a href="#" style="color:#333;font-size: 14px;" data-toggle="modal" data-target="#add-new-venue">Venue</a>
                                            </label>
                                            <select class="js-example-placeholder-multiple js-states form-control" multiple="multiple">
                                                <option>Los Angeles</option>
                                                <option>Houston</option>
                                                <option>Las Vegas</option>
                                            </select>
                                        </div>
                                        </div> -->

                                        <div class="col-md-6 col-xs-12">
                                            <div class="form-group">
                                                <label>
                                                    <a href="#" style="color:#333;font-size: 14px;" data-toggle="modal" data-target="#add-new-state">State</a>
                                                </label>
                                                <select id="stateId" class="form-control">
                                                    <c:forEach var="state" items="${states}">
                                                        <option value="${state.id}" >${state.name}</option>
                                                    </c:forEach>

                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-6 col-xs-12">
                                            <div class="form-group">
                                                <label>Zip</label>
                                                <input id="zip" class="form-control">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label>Phone Number</label>
                                        <input id="phone"  class="form-control">
                                    </div>
                                    <div class="imageupload panel panel-default">
                                        <div class="panel-heading clearfix">
                                            <h4 class="panel-title pull-left ">Venue logo</h4>
                                                <%--<div class="btn-group pull-right">
                                                    <button type="button" class="btn btn-default active">File</button>
                                                    <button type="button" class="btn btn-default">URL</button>
                                                </div>--%>
                                        </div>
                                        <div class="file-tab panel-body">
                                                <%--<label class="btn btn-primary btn-file btn-sm-new">
                                                    <span>Browse</span>
                                                    <!-- The file is stored here. -->
                                                    <input type="file" name="image-file">
                                                </label>
                                                <button type="button" class="btn btn-danger btn-sm-new">Delete image</button>
                            --%>
                                            <div id="venueLogoImg" >

                                                <div class="dz-default dz-message">
                                                    <span>Drop files here to upload</span>
                                                    <p id="errorObjLocation_profilePictureToken"></p>
                                                </div>
                                            </div>

                                            <p id="errorObjLocation_locationLogo"  class="text-danger"></p>
                                        </div>
                                        <div class="url-tab panel-body" style="display:none;">
                                            <div class="input-group">
                                                <input type="text" class="form-control hasclear" placeholder="Image URL">
                                                <div class="input-group-btn">
                                                    <button type="button" class="btn btn-default" style="margin-left: 5px;">Submit</button>
                                                </div>
                                            </div>
                                            <button type="button" class="btn btn-default btn-sm-new">Remove</button>
                                            <!-- The URL is stored here. -->
                                            <input type="hidden" name="image-url">
                                        </div>
                                    </div>
                                    <div class="imageupload panel panel-default">
                                        <div class="panel-heading clearfix">
                                            <h4 class="panel-title pull-left">Background Images</h4>
                                            <div class="btn-group pull-right">

                                            </div>
                                        </div>
                                        <div id="venueBgImg" >

                                            <div class="dz-default dz-message">
                                                <span>Drop files here to upload</span>
                                                <p id="errorObjLocation_profilePictureToken"></p>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="imageupload panel panel-default">
                                        <div class="panel-heading clearfix">
                                            <h4 class="panel-title pull-left ">Slideshow Settings</h4>
                                            <div  id="slideShowSettingsBtnDiv" class="btn-group pull-right">
                                                <button type="button" data-val="1" class="btn btn-default active">On</button>
                                                <button type="button" data-val="0" class="btn btn-default">Off</button>
                                            </div>
                                        </div>
                                        <div  id="slideShowSettings" class="file-tab panel-body">
                                            <div class="col-md-6">
                                                <h3 style="text-align: left;color: #fff"> TRANSITIONS</h3>
                                                <p style="text-align: left;">Duration Speed</p>
                                                <div class="input-group" style="margin-bottom: 13px">
                                                    <input type="number" min="0" class="form-control" id="durationSpeed" placeholder="">
                                                    <div class="input-group-addon">sec</div>
                                                </div>
                                                <p class="text-danger" id="errorObjLocation_durationSpeed"></p>
                                                <p style="text-align: left;">Ad Break Time</p>
                                                <div class="input-group">
                                                    <input type="number" min="0" class="form-control" id="breakTime" placeholder="">
                                                    <div class="input-group-addon">min</div>

                                                </div>
                                                <p class="text-danger" id="errorObjLocation_breakTime"></p>

                                            </div>
                                            <div class="col-md-6">
                                                <h3 style="text-align: left;"> TRANSITIONS</h3>
                                                <p style="text-align: left">Fade In</p>
                                                <select id="fadeInTime" class="form-control" style="margin-bottom: 13px">
                                                    <c:forEach var="fadeIn" items="${fadeInList}">
                                                        <fmt:parseNumber var = "fadeInVal" integerOnly = "true"
                                                                         type = "number" value = "${fadeIn}" />
                                                        <option value="${fadeInVal}" >${fadeInVal}s</option>
                                                    </c:forEach>
                                                </select>
                                                <p style="text-align: left">Fade Out</p>
                                                <select id="fadeOutTime" class="form-control" style="margin-bottom: 13px">
                                                    <c:forEach var="fadeOut" items="${fadeOutList}">
                                                        <fmt:parseNumber var = "fadeOutVal" integerOnly = "true"
                                                                         type = "number" value ="${fadeOut}" />
                                                        <option value="${fadeOutVal}" >${fadeOutVal}s</option>
                                                    </c:forEach>
                                                </select>
                                            </div>


                                        </div>
                                        <div class="url-tab panel-body" style="display:none;">


                                        </div>
                                    </div>


                                </div>
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
        </div>

        <%--Developer Hidden Field--%>
        <%--<input type="hidden" id="venueLogoToken" value="" />
        <input type="hidden" id="venueBgImgTokens" value="" />--%>


        <link href="<s:url value="/resources/css/dropzone.css"/>" rel="stylesheet">
        <script src="<s:url value="/resources/js/dropzone.min.js"/>"></script>
        <script src="<s:url value="/resources/developer/js/temp-file/common.js"/>"></script>
        <script src="<s:url value="/resources/developer/js/location/common.js"/>"></script>
        <script src="<s:url value="/resources/developer/js/location/create.js"/>"></script>


    </jsp:body>

</t:genericpage>