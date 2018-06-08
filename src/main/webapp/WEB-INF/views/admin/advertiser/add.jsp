<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:genericpage>
    <jsp:body>
        <div id="page-wrapper">
            <div class="container">
                <h3 class="uni-header"><span>Create Advertiser</span></h3>
                <!-- <h3 class="title-top text-uppercase"><span>Create Advertiser</span></h3> -->
                <div class="btn-container-top">
                    <button class="btn btn-action-top" onclick="initSubmitAdvertiserData('save','create')">Save</button>
                    <button class="btn btn-action-top" onclick="initSubmitAdvertiserData('save_and_close','create')">Save&nbsp;&&nbsp;Close</button>
                    <button class="btn btn-action-top" onclick="initSubmitAdvertiserData('save_and_new','create')">Save&nbsp;&&nbsp;New</button>
                    <a class="btn btn-action-top" href='<s:url value="/admin/advertiser/all"/>'>Cancel</a>
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
                            <%@  include file="/WEB-INF/views/admin/advertiser/partial/add/advertiser-info.jsp" %>
                            <%@  include file="/WEB-INF/views/admin/advertiser/partial/add/gallery-ads.jsp"  %>
                            <%@  include file="/WEB-INF/views/admin/advertiser/partial/add/slideshow-adds.jsp"  %>
                            <%@  include file="/WEB-INF/views/admin/advertiser/partial/add/popup-ads.jsp"  %>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Add Location Modal -->
            <div class="modal fade" id="addLocation" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <%@include file="/WEB-INF/views/admin/location/modal-inner-html/add-location.jsp" %>
            </div>


                <%-- After image add Dropzone Image preview --%>
            <%@  include file="/WEB-INF/views/admin/advertiser/partial/file-preview.jsp" %>
            <%@  include file="/WEB-INF/views/admin/advertiser/partial/file-preview-without-url.jsp" %>
        </div>

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
        <script src="<s:url value="/resources/developer/js/pmc-adv/create.js"/>"></script>

        <script src="<s:url value="/resources/developer/js/location/common.js"/>"></script>
        <script src="<s:url value="/resources/developer/js/location/create.js"/>"></script>
        <script src="<s:url value="/resources/developer/js/location/common-modal.js"/>"></script>
        <script src="<s:url value="/resources/developer/js/location/cities.js"/>"></script>

    </jsp:body>

</t:genericpage>