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
                <h3 class="uni-header"><span>Edit Location</span></h3>

                <div class="col-md-12">
                    <div class="row">
                        <div class="btn-container-top">
                            <button class="btn btn-action-top" onclick="submitUpdatedData('save')">Save</button>
                            <button class="btn btn-action-top" onclick="submitUpdatedData('save_and_close')" >Save&nbsp;&&nbsp;Close</button>
                            <button class="btn btn-action-top" onclick="submitUpdatedData('save_and_new')">Save&nbsp;&&nbsp;New</button>
                            <button class="btn btn-action-top" onclick="locationAfterSaveActionUpdate('cancel')">Cancel</button>
                            <button class="btn btn-action-top" onclick="window.open('${frontEndAppBaseUrl}/user-panel/slideshow?locId=${location.id}','_blank')">Preview</button>

                            <br>
                            <span id="successMsg"></span>
                        </div>
                        <div class="form-group">
                            <div class="panel panel-default">
                                <div class="panel-body">

                                    <div class="form-group">
                                        <label>Location Name</label>
                                        <input id="name"  class="form-control" value="${location.name}">
                                    </div>

                                    <div class="form-group">
                                        <label>Address</label>
                                        <input id="address" class="form-control" value="${location.address}">
                                    </div>

                                    <div class="row clearfix">
                                        <div class="col-md-6 col-xs-12">
                                            <div class="form-group">
                                                <label>
                                                    <a href="#" style="color:#333;font-size: 14px;" data-toggle="modal" data-target="#add-new-state">State</a>
                                                </label>
                                                <select id="stateId" class="form-control">
                                                    <option value="">Select State</option>
                                                    <c:forEach var="state" items="${states}">
                                                        <c:set var="stateOption" value="" />
                                                        <c:if test="${location.state.id == state.id }">
                                                            <c:set var="stateOption" value="selected='selected'" />
                                                        </c:if>
                                                        <option ${stateOption} value="${state.id}" >${state.name}</option>
                                                    </c:forEach>

                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-6 col-xs-12">
                                            <div class="form-group">
                                                <label>
                                                    <a href="#" style="color:#333;font-size: 14px;" data-toggle="modal" data-target="#add-new-state">City</a>
                                                </label>
                                                <select id="cityId" class="form-control">
                                                    <c:forEach var="city" items="${cities}">
                                                        <c:set var="cityOption" value="" />
                                                        <c:if test="${location.city.id == city.id }">
                                                            <c:set var="cityOption" value="selected='selected'" />
                                                        </c:if>
                                                        <option ${cityOption} value="${city.id}" >${city.name}</option>
                                                    </c:forEach>

                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-6 col-xs-12">
                                            <div class="form-group">
                                                <label>Zip</label>
                                                <input id="zip" class="form-control" value="${location.zip}">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label>Phone Number</label>
                                        <input id="phone"  class="form-control" value="${location.phone}" >
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
                                            <div id="venueLogoImg">
                                                <div class="dz-default dz-message">
                                                    <div class="droper">
                                                        <p class="dropicon"><i class="fa fa-cloud-upload"></i> </p>
                                                        <p class="dropext">Click or Drop your file to change logo</p>
                                                    </div>
                                                    <p id="errorObj_profilePictureToken"></p>
                                                </div>
                                            </div>
                                            <c:set value="" var="logoImgSrc" />
                                            <c:choose>
                                                <c:when test="${location.locationLogo==null ||location.locationLogo.trim().equals('')}">
                                                    <c:set value="/resources/images/default_profile_pic.png" var="logoImgSrc" />
                                                </c:when>
                                                <c:otherwise>
                                                    <c:set value="/common/${location.locationLogo}" var="logoImgSrc" />
                                                </c:otherwise>
                                            </c:choose>
                                            <img id="logoImg" onerror="this.src='<c:url value="/resources/images/default_alternate.png" />'" src="<c:url value="${logoImgSrc}" /> " class="img-thumbnail" width="150">


                                            <p id="errorObj_locationLogo"  class="text-danger"></p>
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
                                        <div id="venueBgImg" class="panel-body">

                                            <div class="dz-default dz-message">
                                                <div class="droper">
                                                    <p class="dropicon"><i class="fa fa-cloud-upload"></i> </p>
                                                    <p class="dropext">Click or Drop your file here</p>
                                                </div>
                                                <p id="errorObj_bgTokens"></p>
                                            </div>
                                            <div class="file-tab panel-body">
                                                <c:forEach var="bgImg" items="${location.locationBackgroundImages}">
                                                    <c:set value="" var="logoImgSrc" />
                                                    <c:choose>
                                                        <c:when test="${bgImg.image==null || bgImg.image.trim().equals('')}">
                                                            <c:set value="/resources/images/default_profile_pic.png" var="logoImgSrc" />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:set value="/common/${bgImg.image}" var="logoImgSrc" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <div id="bgImg_div_${bgImg.id}" >

                                                        <img id="bgImg_${bgImg.id}" onerror="this.src='<c:url value="/resources/images/default_alternate.png" />'" src="<c:url value="${logoImgSrc}" /> " class="img-thumbnail" width="150">
                                                        <br>
                                                        <a href="javascript:void(0)" onclick="removeBgImage(${bgImg.id})">Remove</a>
                                                    </div>
                                                </c:forEach>

                                            </div>


                                        </div>


                                    </div>
                                    <div class="imageupload panel panel-default">
                                        <div id="slideShowSettingsBtnDiv" class="panel-heading clearfix">
                                            <h4 class="panel-title pull-left ">Slideshow Settings</h4>
                                            <div class="btn-group pull-right">
                                                <c:set var="slideShowActiveOn" value="" ></c:set>
                                                <c:set var="slideShowActiveOff" value="" ></c:set>
                                                <c:set var="slideShowSettingsShow" value="none" ></c:set>
                                                <c:choose>
                                                    <c:when test="${location.hasSlideshow}">
                                                        <c:set var="slideShowActiveOn" value="active" ></c:set>
                                                        <c:set var="slideShowSettingsShow" value="block" ></c:set>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:set var="slideShowActiveOff" value="active" ></c:set>
                                                        <c:set var="slideShowSettingsShow" value="none" ></c:set>
                                                    </c:otherwise>
                                                </c:choose>

                                                <button type="button" data-val="1" class="btn btn-default ${slideShowActiveOn}">On</button>
                                                <button type="button" data-val="0" class="btn btn-default ${slideShowActiveOff}">Off</button>
                                            </div>
                                        </div>
                                        <div id="slideShowSettings"  class="file-tab panel-body" style="display: ${slideShowSettingsShow};">
                                            <div class="col-md-6">
                                                <h3 style="text-align: left;color: #fff"> TRANSITIONS</h3>
                                                <p style="text-align: left;">Duration Speed</p>
                                                <div class="input-group" style="margin-bottom: 13px">
                                                    <fmt:parseNumber var = "durationSpeedVal" integerOnly = "true"
                                                                     type = "number" value ="${location.durationSpeed}" />
                                                    <input min="0" type="number" class="form-control" id="durationSpeed" placeholder=""  value="${durationSpeedVal}">
                                                    <div class="input-group-addon">sec</div>
                                                </div>
                                                <p class="text-danger" id="errorObj_durationSpeed"></p>
                                                <p style="text-align: left;">Ad Break Time</p>
                                                <div class="input-group">
                                                    <fmt:parseNumber var = "breakTimeVal" integerOnly = "true"
                                                                     type = "number" value ="${location.breakTime}" />
                                                    <input min="0" type="number" class="form-control" id="breakTime" placeholder="" value="${breakTimeVal}" >
                                                    <div class="input-group-addon">min</div>

                                                </div>
                                                <p class="text-danger" id="errorObj_breakTime"></p>

                                            </div>
                                            <div class="col-md-6">
                                                <h3 style="text-align: left;"> TRANSITIONS</h3>
                                                <p style="text-align: left">Fade In</p>
                                                <select id="fadeInTime" class="form-control" style="margin-bottom: 13px">
                                                    <c:forEach var="fadeIn" items="${fadeInList}">
                                                        <c:set var="fadeInOption" value="" />
                                                        <c:if test="${location.fadeInTime == fadeIn }">
                                                            <c:set var="fadeInOption" value="selected='selected'" />
                                                        </c:if>
                                                        <fmt:parseNumber var = "fadeInVal" integerOnly = "true"
                                                                         type = "number" value = "${fadeIn}" />
                                                        <option value="${fadeInVal}" ${fadeInOption}>${fadeInVal}s</option>
                                                    </c:forEach>
                                                </select>
                                                <p style="text-align: left">Fade Out</p>
                                                <select id="fadeOutTime" class="form-control" style="margin-bottom: 13px">
                                                    <c:forEach var="fadeOut" items="${fadeOutList}">
                                                        <c:set var="fadeOutOption" value="" />
                                                        <c:if test="${location.fadeOutTime == fadeOut }">
                                                            <c:set var="fadeOutOption" value="selected='selected'" />
                                                        </c:if>
                                                        <fmt:parseNumber var = "fadeOutVal" integerOnly = "true"
                                                                         type = "number" value ="${fadeOut}" />
                                                        <option value="${fadeOutVal}" ${fadeOutOption}>${fadeOutVal}s</option>
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
        <input type="hidden" id="locationId" value="${location.id}" />
        <input type="hidden" id="venueBgRemoveIds" value="" />
        <input type="hidden" id="venueLogoToken" value="" />
        <input type="hidden" id="venueBgImgTokens" value="" />
        <link href="<s:url value="/resources/css/dropzone.css"/>" rel="stylesheet">
        <script src="<s:url value="/resources/js/dropzone.min.js"/>"></script>

        <script src="<s:url value="/resources/developer/js/temp-file/common.js"/>"></script>
        <script src="<s:url value="/resources/developer/js/location/common.js"/>"></script>
        <script src="<s:url value="/resources/developer/js/location/update.js"/>"></script>
        <script src="<s:url value="/resources/developer/js/location/cities.js"/>"></script>
        <!-- dropzone -->
        <script>

        </script>
    </jsp:body>
</t:genericpage>