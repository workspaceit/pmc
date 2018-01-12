<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link href="http://vjs.zencdn.net/6.6.0/video-js.css" rel="stylesheet">

<!-- If you'd like to support IE8 -->
<script src="http://vjs.zencdn.net/ie8/1.1.2/videojs-ie8.min.js"></script>
<t:genericpage>
    <jsp:attribute name="developerScript">
        <script type="text/javascript" src="http://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
        <!-- select2 js -->
        <!-- Select2 css -->
        <link href="<s:url value="/resources/css/select2.css"/>" rel="stylesheet"/>
        <link href="<s:url value="/resources/css/daterangepicker.css"/>" rel="stylesheet"/>

        <script src="<s:url value="/resources/js/select2.js"/>"></script>
        <script type="text/javascript" src="http://cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.8.1/jquery.timepicker.min.js"></script>
        <%--<link rel="stylesheet" type="text/css" href="http://cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.css" />--%>

        <link href="<s:url value="/resources/css/dropzone.css"/>" rel="stylesheet">
        <script src="<s:url value="/resources/js/dropzone.min.js"/>"></script>
        <script src="<s:url value="/resources/developer/js/temp-file/common.js"/>"></script>
        <script src="<s:url value="/resources/developer/js/pmc-adv/common.js"/>"></script>
        <script src="<s:url value="/resources/developer/js/pmc-adv/update.js"/>"></script>
     </jsp:attribute>
    <jsp:body>
        <div id="page-wrapper">
            <div class="container">
                <h3 class="uni-header"><span>Create Advertiser</span></h3>
                <!-- <h3 class="title-top text-uppercase"><span>Create Advertiser</span></h3> -->
                <div class="btn-container-top">
                    <button class="btn btn-action-top" onclick="initValidationForUpdate('save','update')">Save</button>
                    <button class="btn btn-action-top" onclick="initValidationForUpdate('save_and_close','update')">Save&nbsp;&&nbsp;Close</button>
                    <button class="btn btn-action-top" onclick="initValidationForUpdate('save_and_new','update')">Save&nbsp;&&nbsp;New</button>
                    <button class="btn btn-action-top" onclick="advertiserAfterSaveAction('cancel')">Cancel</button>
                </div>

                <div class="tabbable-panel clearfix">
                    <div class="tabbable-line">
                        <ul class="nav nav-tabs ">
                            <li class="active">
                                <a href="#tab_default_1" data-toggle="tab">
                                    Advertiser Info
                                    <span id="advertiserInfoErrorCount" class="notify" style="display: none"></span>
                                </a>
                            </li>
                            <li>
                                <a href="#tab_default_2" data-toggle="tab">
                                    Gallery Ads
                                    <span id="galleryAdsErrorCount" class="notify" style="display: none"></span>
                                </a>
                            </li>
                            <li>
                                <a href="#tab_default_3" data-toggle="tab">
                                    Slideshow Ads
                                    <span id="slideShowAdsErrorCount" class="notify" style="display: none"></span>
                                </a>
                            </li>
                            <li>
                                <a href="#tab_default_4" data-toggle="tab">
                                    Popup Ads
                                    <span id="popUpAdsErrorCount" class="notify" style="display: none"></span>
                                </a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <%@include file="/WEB-INF/views/admin/advertiser/partial/edit/advertiser-info.jsp" %>
                            <%@include file="/WEB-INF/views/admin/advertiser/partial/edit/gallery-ads.jsp" %>
                            <%@include file="/WEB-INF/views/admin/advertiser/partial/edit/slideshow-adds.jsp" %>
                            <%@include file="/WEB-INF/views/admin/advertiser/partial/edit/popup-ads.jsp" %>
                        </div>
                    </div>
                </div>
            </div>

            <!-- /.container-fluid -->

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
        <input type="hidden" id="advertiserId" value="${advertiser.id}" />
        <input type="hidden" id="galleryAdId" value="${galleryAd.id}" />
        <input type="hidden" id="slideshowAdId" value="${slideshowAd.id}" />
        <input type="hidden" id="popupSmsAdId" value="${popupAdSms.id}" />
        <input type="hidden" id="popupEmailAdId" value="${popupAdEmail.id}" />
    </jsp:body>

</t:genericpage>