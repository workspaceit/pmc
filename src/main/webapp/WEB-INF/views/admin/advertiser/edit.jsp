<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
        <script src="<s:url value="/resources/developer/js/location/common.js"/>"></script>
        <script src="<s:url value="/resources/developer/js/location/create.js"/>"></script>
        <script src="<s:url value="/resources/developer/js/location/common-modal.js"/>"></script>
            <script src="<s:url value="/resources/developer/js/checkout/common.js"/>"></script>
            <script src="<s:url value="/resources/developer/js/checkout/create.js"/>"></script>
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
                    <a class="btn btn-action-top" href="<c:url value="/admin/advertiser/all"/>">Cancel</a>
                    <button class="btn btn-action-top" onclick="loadCheckoutInModal()">Checkout</button>
                    <button onclick="deleteEntity(${advertiser.id}, 'advertiser')" class="btn btn-action-top">Delete</button>
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
            <div class="modal fade" id="addLocation" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" >Add Location</h4>
                        </div>
                        <div class="modal-body">
                            <div class="btn-container-top">
                                <button class="btn btn-action-top" onclick="submitLocationDataFromModal()">Save</button>
                            </div>
                            <div class="form-group">
                                <%@  include file="/WEB-INF/views/admin/location/pertial/form-body-for-modal.jsp" %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
                <%-- After image add Dropzone Image preview --%>
            <%@  include file="/WEB-INF/views/admin/advertiser/partial/file-preview.jsp" %>
            <%@  include file="/WEB-INF/views/admin/advertiser/partial/file-preview-without-url.jsp" %>
        </div>



        <%--Check out Modal--%>
        <div class="modal fade in" id="checkout-content" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="display: none;">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Invoice</h4>
                        <div class="btn-container-top">
                            <button class="btn btn-action-top" onclick="printOrDownloadInvoice()">Prepare to download</button>
                            <button class="btn btn-action-top" onclick="loadSendInvoiceModal()">Mail Invoice</button>
                        </div>

                        <style>
                            #checkout-body .col-md-8{
                                width: 100% !important;
                            }
                        </style>
                    <div id="checkout-body" class="modal-body">
                    </div>
                </div>
            </div>
        </div>
        </div>
        <div class="modal fade in" id="invoice-mail-send" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             style="display: none;">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <form class="form">
                        <h4 class="modal-title">Email Invoice</h4>
                        <div class="modal-body">
                            <div class="form-group">
                                <input type="text" class="form-control" id="invoiceRecipientEmail" >
                            </div>
                            <div class="form-group">
                                <input type="button"  class="btn btn-success" value="Send" onclick="sendInvoiceToEmail()">
                            </div>
                        </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="../others/delete-modal.jsp"%>

        <%--Developer Hidden Field--%>
        <input type="hidden" id="advertiserId" value="${advertiser.id}" />
        <input type="hidden" id="galleryAdId" value="${galleryAd.id}" />
        <input type="hidden" id="slideshowAdId" value="${slideshowAd.id}" />
        <input type="hidden" id="popupSmsAdId" value="${popupAdSms.id}" />
        <input type="hidden" id="popupEmailAdId" value="${popupAdEmail.id}" />
        <input type="hidden" id="frontEndBaseUrl" value="${frontEndBaseUrl}" />
        <input type="hidden" id="advertiserCityId" value="${advertiser.city.id}" />


        <script src="<s:url value="/resources/developer/js/location/cities.js"/>"></script>

        <%--Edit helper--%>
        <script src="<s:url value="/resources/developer/js/helper/edit.helper.js"/>"></script>

    </jsp:body>

</t:genericpage>