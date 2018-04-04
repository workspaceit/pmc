<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.workspaceit.pmc.constant.watermark.Placement" %>
<t:genericpage>
    <jsp:body>
        <!-- /#page-wrapper -->
        <div id="page-wrapper">
            <div class="container">

                <h3 class="uni-header"><span>Watermark Setting</span></h3>
                <div class="btn-container-top">
                    <button class="btn btn-action-top" onclick="submitWatermark('save')">Save</button>
                    <button class="btn btn-action-top" onclick="submitWatermark('save-close')">Save&nbsp;&&nbsp;Close</button>
                    <button class="btn btn-action-top" onclick="submitWatermark('save-new')">Save&nbsp;&&nbsp;New</button>
                    <a href="<c:url value="/admin/watermark/all"/>" class="btn btn-action-top">Cancel</a>
                </div>
                <div class="row clearfix">
                    <!-- Page Heading -->
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <div class="imageupload panel panel-default">
                            <div class="panel-heading clearfix">
                                <h4 class="panel-title pull-left" style="margin-top: 10px">Watermark Name</h4>

                            </div>
                            <input class="form-control" id="name" name="name" placeholder="Watermark Name">
                        </div>
                        <div id="watermarkSettings" class="imageupload panel panel-default">
                            <div class="panel-heading clearfix">
                                <h4 class="panel-title pull-left" style="margin-top: 10px">Choose Information type</h4>
                                <div id="waterMarkImg" class="btn-group pull-right">
                                    <button type="button" class="wm_tab btn btn-default active" data-name="image" >Image</button>
                                    <button type="button" class="wm_tab btn btn-default" data-name="text"  >Text</button>
                                </div>
                            </div>
                            <div id="waterMarkImgFile" class="file-tab panel-body">
                                <div class="col-md-12">
                                    <label>Logo</label>
                                    <div id="watermarkLogoImg" >
                                        <div class="dz-default dz-message">
                                            <div class="droper">
                                                <p class="dropicon"><i class="fa fa-cloud-upload"></i> </p>
                                                <p class="dropext">Click or Drop your file here</p>
                                            </div>
                                            <p class="text-danger" id="errorObj_logoImgToken"></p>
                                        </div>
                                    </div>
                                    <p id="errorObj_locationLogo"  class="text-danger"></p>
                                </div>
                            </div>
                            <div id="waterMarkImgUrl" class="url-tab panel-body" style="display:none;">
                                <div class="col-md-12 col-sm-12 col-xs-12">
                                    <div class="form-group timepick">
                                        <label>Watermark Text</label><br>
                                        <input class="form-control" id="txt_wm_text" name="txt_wm_text" placeholder="Watermark Text">
                                        <p class="text-danger" id="errorObj_watermarkText"></p>
                                    </div>
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <div class="form-group timepick">
                                        <label>Font</label><br>
                                        <select id="e1" class="txt_font" name="txt_font">
                                            <c:forEach var="font" items="${fonts}">
                                                <option value="${font.id}">${font.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <p class="text-danger" id="errorObj_fontId"></p>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group timepick">
                                        <label>Text Color</label>
                                        <input class="jscolor form-control" id="txt_color" name="txt_color" value="B3FF57">
                                        <p class="text-danger" id="errorObj_color"></p>
                                    </div>
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <div class="form-group timepick">
                                        <label>Font Size</label><br>
                                        <input type="number" class="form-control" id="fontSize"  value="50"  >
                                    </div>

                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-12" style="display: none;">
                                    <div class="form-group timepick">
                                        <label>Background Color</label><br>
                                        <input class="jscolor form-control" id="textBackgroundColor"  value="">
                                    </div>
                                </div>
                            </div>
                            <div  class="panel-body">
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <div class="form-group timepick">
                                        <label>Placement</label><br>
                                        <select id="e-placement" class="img_placement" name="img_placement" >

                                            <c:forEach var="placement" items="${Placement.values()}">
                                                <option value="${placement.name()}">${placement.getDisplayName()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <p class="text-danger" id="errorObj_placement"></p>
                                </div>
                                <div class="col-md-6  col-sm-6 col-xs-12">
                                    <div class="form-group timepick">
                                        <label>Smallest Size</label><br>
                                        <select id="e-size" class="img_font_size" name="img_font_size">
                                            <option value="thumb">Thumb</option>
                                            <option value="small">Small</option>
                                            <option value="medium">Medium</option>
                                            <option value="large">Large</option>
                                            <option value="x_large">X-Large</option>
                                        </select>
                                    </div>
                                    <p class="text-danger" id="errorObj_size"></p>
                                </div>
                                <div class="col-md-12  col-sm-12 col-xs-12">
                                    <label>Fade</label><br>
                                    <div class="range-slider">
                                        <input id="img_fade_range" class="range-slider__range" name="img_fade_range" type="range" value="25" min="0" max="50">
                                        <span class="range-slider__value">0</span>
                                    </div>
                                    <p class="text-danger" id="errorObj_fade"></p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <div class="imgpreview panel panel-default">
                            <div class="panel-heading clearfix">
                                <h4 class="panel-title pull-left" style="margin-top: 10px">Preview</h4>
                                <%--<div id="waterMarkImg" class="btn-group pull-right">--%>
                                    <%--<button type="button" class="wm_tab btn btn-default active" data-name="image">Image</button>--%>
                                    <%--<button type="button" class="wm_tab btn btn-default" data-name="text">Text</button>--%>
                                <%--</div>--%>
                            </div>
                            <div class="url-tab panel-body" >

                                <div class="col-md-12">
                                    <div class="form-group timepick timepick_btn">
                                        <button class="btn btn-action-top" onclick="previewWatermarkOnSample()" >Preview</button>
                                        <button id="changeSample" class="btn btn-action-top" >Change sample</button>
                                            <%--
                                                Dropzone needs html but it is kept hidden
                                                Because image is beging displayed in #watermarkPreviewOnSampleImg
                                             --%>
                                        <div  id="dummyForDropZone" style="display: none"></div>
                                        <img id="watermarkPreviewOnSampleImg" class="img-responsive"  src="<s:url value="/resources${previewSampleUri}"/>" />
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
                        </div>
                    </div>
                    <input type="hidden" id="venueLogoToken" value="" />
                </div>
            </div>
        </div>

        <script src="<s:url value="/resources/js/bootstrap-imageupload.js"/>"></script>
        <script src="<s:url value="/resources/js/select2.js"/>"></script>
        <script src="<s:url value="/resources/js/jscolor.js"/>"></script>
        <!-- dropzone -->
        <link href="<s:url value="/resources/css/dropzone.css"/>" rel="stylesheet">
        <script src="<s:url value="/resources/js/dropzone.min.js"/>"></script>
        <script src="<s:url value="/resources/developer/js/helper/url.helper.js"/>"></script>
        <script src="<s:url value="/resources/developer/js/temp-file/common.js"/>"></script>
        <script src="<s:url value="/resources/developer/js/watermark/common.js"/>"></script>
        <script src="<s:url value="/resources/developer/js/watermark/create.js"/>"></script>
    </jsp:body>
</t:genericpage>