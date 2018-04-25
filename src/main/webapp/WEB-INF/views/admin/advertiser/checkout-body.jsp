<%@ page import="com.workspaceit.pmc.constant.advertisement.GalleryAdsConstant" %>
<%@ page import="com.workspaceit.pmc.constant.advertisement.SlideshowAdsConstant" %>
<%@ page import="com.workspaceit.pmc.constant.advertisement.PopupAdConstant" %>
<%@page import="com.workspaceit.pmc.constant.advertisement.SECTION_TYPE" %>


<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div role="tabpanel" class="tab-pane" id="last">
    <div class="col-md-12" style="padding-left: 0px;background-color: #ffffff">
        <div class="row" style="background-color: #ffffff;margin-top: 10px;">
            <div class="col-xs-12 col-md-8" style="float:none;margin:10px auto;background:#241d40;padding-top:20px;">
                <div class="invoice-title">
                    <img src="<c:url value="/resources/images/logo2.png" />" />
                </div>
                <hr>
                <div class="row" style="color:#fff;font-size:16px;padding:5px;">
                    <div class="col-xs-6">
                        <address>
                            <strong>Billed To:</strong><br>
                            ${advertiser.name}<br>
                            ${advertiser.address}<br>
                            ${advertiser.city.name}<br>
                            ${advertiser.state.name}
                        </address>
                    </div>

                </div>
                <div class="row" style="color:#fff;font-size:16px;">
                    <div class="col-xs-6">
                        <address>
                            <strong>Payment Method:</strong><br>
                            Visa ending **** 4242<br>
                            jsmith@email.com
                        </address>
                    </div>
                    <fmt:formatDate var="orderDate" value="${advertiser.createdAt}" pattern="MMM d, yyyy" />
                    <div class="col-xs-6 text-right">
                        <address>
                            <strong>Order Date:</strong><br>
                            ${orderDate}<br><br>
                        </address>
                    </div>
                </div>
            </div>
        </div>

        <div class="row" style="background-color: #ffffff">
            <div class="col-md-8" style="float:none;margin:0px auto;padding:0px;margin-top:-10px;">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title"><strong>Order summary</strong></h3>
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-condensed">
                                <thead>
                                <tr>
                                    <td><strong>Item</strong></td>
                                    <td class="text-center"><strong>Price</strong></td>
                                    <td class="text-center"><strong>Quantity</strong></td>
                                    <td class="text-right"><strong>Totals</strong></td>
                                </tr>
                                </thead>
                                <tbody>
                                <!-- foreach ($order->lineItems as $line) or some such thing here -->
                                <tr>
                                    <td>Background Image</td>
                                    <td class="text-center price-td" data-price="${prices.get(GalleryAdsConstant.BACKGROUND_IMAGE)}" >
                                        <fmt:formatNumber
                                                value="${prices.get(GalleryAdsConstant.BACKGROUND_IMAGE)}"
                                                type="currency"
                                                currencyCode="${currencyCode}"
                                                currencySymbol="${currencySymbol}"
                                                maxFractionDigits="${maxFractionDigits}" />
                                    </td>
                                    <td class="text-center quantity-td" data-quantity="${quantities.get(GalleryAdsConstant.BACKGROUND_IMAGE)}" >${quantities.get(GalleryAdsConstant.BACKGROUND_IMAGE)}</td>
                                    <td class="text-right">
                                        <fmt:formatNumber
                                                value="${prices.get(GalleryAdsConstant.BACKGROUND_IMAGE)*quantities.get(GalleryAdsConstant.BACKGROUND_IMAGE)}"
                                                type="currency"
                                                currencyCode="${currencyCode}"
                                                currencySymbol="${currencySymbol}"
                                                maxFractionDigits="${maxFractionDigits}" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Gallery Top Ad Banner</td>
                                    <td class="text-center price-td"  data-price="${prices.get(GalleryAdsConstant.TOP_AD_BANNER)}" >
                                        <fmt:formatNumber
                                                value="${prices.get(GalleryAdsConstant.TOP_AD_BANNER)}"
                                                type="currency"
                                                currencyCode="${currencyCode}"
                                                currencySymbol="${currencySymbol}"
                                                maxFractionDigits="${maxFractionDigits}" />

                                    </td>
                                    <td class="text-center quantity-td" data-quantity="${quantities.get(GalleryAdsConstant.TOP_AD_BANNER)}" >${quantities.get(GalleryAdsConstant.TOP_AD_BANNER)}</td>
                                    <td class="text-right">
                                        <fmt:formatNumber
                                                value="${prices.get(GalleryAdsConstant.TOP_AD_BANNER)*quantities.get(GalleryAdsConstant.TOP_AD_BANNER)}"
                                                type="currency"
                                                currencyCode="${currencyCode}"
                                                currencySymbol="${currencySymbol}"
                                                maxFractionDigits="${maxFractionDigits}" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Gallery Bottom Ad Banner</td>
                                    <td class="text-center price-td" data-price="${prices.get(GalleryAdsConstant.BOTTOM_AD_BANNER)}" >
                                        <fmt:formatNumber
                                                value="${prices.get(GalleryAdsConstant.BOTTOM_AD_BANNER)}"
                                                type="currency"
                                                currencyCode="${currencyCode}"
                                                currencySymbol="${currencySymbol}"
                                                maxFractionDigits="${maxFractionDigits}" />
                                    </td>
                                    <td class="text-center quantity-td" data-quantity="${quantities.get(GalleryAdsConstant.BOTTOM_AD_BANNER)}" >${quantities.get(GalleryAdsConstant.BOTTOM_AD_BANNER)}</td>
                                    <td class="text-right">
                                        <fmt:formatNumber
                                                value="${prices.get(GalleryAdsConstant.BOTTOM_AD_BANNER)*quantities.get(GalleryAdsConstant.BOTTOM_AD_BANNER)}"
                                                type="currency"
                                                currencyCode="${currencyCode}"
                                                currencySymbol="${currencySymbol}"
                                                maxFractionDigits="${maxFractionDigits}" />
                                    </td>
                                </tr>

                                <tr>
                                    <td>Slide show Ad Banner</td>
                                    <td class="text-center price-td" data-price="${prices.get(SlideshowAdsConstant.BANNER)}">
                                        <fmt:formatNumber
                                                value="${prices.get(SlideshowAdsConstant.BANNER)}"
                                                type="currency"
                                                currencyCode="${currencyCode}"
                                                currencySymbol="${currencySymbol}"
                                                maxFractionDigits="${maxFractionDigits}" />
                                    </td>
                                    <td class="text-center quantity-td" data-quantity="${quantities.get(SlideshowAdsConstant.BANNER)}" >${quantities.get(SlideshowAdsConstant.BANNER)}</td>
                                    <td class="text-right">
                                        <fmt:formatNumber
                                                value="${prices.get(SlideshowAdsConstant.BANNER)*quantities.get(SlideshowAdsConstant.BANNER)}"
                                                type="currency"
                                                currencyCode="${currencyCode}"
                                                currencySymbol="${currencySymbol}"
                                                maxFractionDigits="${maxFractionDigits}" />

                                    </td>
                                </tr>
                                <tr>
                                    <td>Slide show Ad Video</td>
                                    <td class="text-center price-td" data-price="${prices.get(SlideshowAdsConstant.VIDEO)}" >
                                        <fmt:formatNumber
                                                value="${prices.get(SlideshowAdsConstant.VIDEO)}"
                                                type="currency"
                                                currencyCode="${currencyCode}"
                                                currencySymbol="${currencySymbol}"
                                                maxFractionDigits="${maxFractionDigits}" />
                                    </td>
                                    <td class="text-center quantity-td" data-quantity="${quantities.get(SlideshowAdsConstant.VIDEO)}" >${quantities.get(SlideshowAdsConstant.VIDEO)}</td>
                                    <td class="text-right">
                                        <fmt:formatNumber
                                                value="${quantities.get(SlideshowAdsConstant.VIDEO)*quantities.get(SlideshowAdsConstant.VIDEO)}"
                                                type="currency"
                                                currencyCode="${currencyCode}"
                                                currencySymbol="${currencySymbol}"
                                                maxFractionDigits="${maxFractionDigits}" />
                                    </td>
                                </tr>

                                <tr>
                                    <td>Popup Sms Ad</td>
                                    <td class="text-center price-td" data-price="${prices.get(PopupAdConstant.SMS)}" >
                                        <fmt:formatNumber
                                                value="${prices.get(PopupAdConstant.SMS)}"
                                                type="currency"
                                                currencyCode="${currencyCode}"
                                                currencySymbol="${currencySymbol}"
                                                maxFractionDigits="${maxFractionDigits}" />
                                    </td>
                                    <td class="text-center quantity-td" data-quantity="${quantities.get(PopupAdConstant.SMS)}" >${quantities.get(PopupAdConstant.SMS)}</td>
                                    <td class="text-right">
                                        <fmt:formatNumber
                                                value="${prices.get(PopupAdConstant.SMS)*quantities.get(PopupAdConstant.SMS)}"
                                                type="currency"
                                                currencyCode="${currencyCode}"
                                                currencySymbol="${currencySymbol}"
                                                maxFractionDigits="${maxFractionDigits}" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Popup Email Ad</td>
                                    <td class="text-center price-td" data-price="${prices.get(PopupAdConstant.EMAIL)}" >
                                        <fmt:formatNumber
                                                value="${prices.get(PopupAdConstant.EMAIL)}"
                                                type="currency"
                                                currencyCode="${currencyCode}"
                                                currencySymbol="${currencySymbol}"
                                                maxFractionDigits="${maxFractionDigits}" />
                                    </td>
                                    <td class="text-center quantity-td" data-quantity="${quantities.get(PopupAdConstant.EMAIL)}" >${quantities.get(PopupAdConstant.EMAIL)}</td>
                                    <td class="text-right">
                                        <fmt:formatNumber
                                                value="${prices.get(PopupAdConstant.EMAIL)*quantities.get(PopupAdConstant.EMAIL)}"
                                                type="currency"
                                                currencyCode="${currencyCode}"
                                                currencySymbol="${currencySymbol}"
                                                maxFractionDigits="${maxFractionDigits}" />
                                    </td>
                                </tr>

                                <tr>
                                    <td class="thick-line" data-price="" ></td>
                                    <td class="thick-line"></td>
                                    <td class="thick-line text-center"><strong>Subtotal</strong></td>
                                    <td class="thick-line text-right">
                                        <fmt:formatNumber
                                                value="${totalPrice}"
                                                type="currency"
                                                currencyCode="${currencyCode}"
                                                currencySymbol="${currencySymbol}"
                                                maxFractionDigits="${maxFractionDigits}" /></td>
                                </tr>
                                <tr style="overflow:hidden;">
                                    <td class="no-line"></td>
                                    <td class="no-line"></td>
                                    <td class="no-line text-center"><strong>Discount</strong></td>
                                    <td class="no-line text-left">
                                        <input  id="discountTxtField" onchange="printCheckAmount(printTotalInHtml,printDueInHtml)" type="number" min="0" class="form-control" placeholder="" value="${discount}" aria-describedby="sizing-addon2" style="text-align: right;width:40%;height: 32px;float:right;">
                                    </td>

                                </tr>
                                <tr style="overflow:hidden;">
                                    <td class="no-line"></td>
                                    <td class="no-line"></td>
                                    <td class="no-line text-center"><strong></strong></td>
                                    <td class="no-line">
                                        <p class="text-danger text-right" id="errorObj_discount" />
                                    </td>

                                </tr>
                                <tr>
                                    <td class="no-line"></td>
                                    <td class="no-line"></td>
                                    <td class="no-line text-center"><strong>Total</strong></td>
                                    <td class="no-line text-right" id="totalCheckoutPrice"  >
                                        <fmt:formatNumber
                                                value="${totalPrice-discount}"
                                                type="currency"
                                                currencyCode="${currencyCode}"
                                                currencySymbol="${currencySymbol}"
                                                maxFractionDigits="${maxFractionDigits}" />
                                    </td>
                                </tr>
                                <tr style="overflow:hidden;">
                                    <td class="no-line"></td>
                                    <td class="no-line"></td>
                                    <td class="no-line text-center"><strong>Due</strong></td>
                                    <td class="thin-line text-right" id="totalDuePrice" data-price="${totalDuePrice}"  ><fmt:formatNumber
                                            value="${totalDuePrice}"
                                            type="currency"
                                            currencyCode="${currencyCode}"
                                            currencySymbol="${currencySymbol}"
                                            maxFractionDigits="${maxFractionDigits}" /></td>

                                </tr>
                                <tr style="overflow:hidden;">
                                    <td class="no-line"></td>
                                    <td class="no-line"></td>
                                    <td class="no-line text-center"><strong>Previously Paid</strong></td>
                                    <td class="no-line text-right" id="prevPaidPrice" data-price="${totalPayedPrice}">
                                        <fmt:formatNumber
                                                value="${totalPayedPrice}"
                                                type="currency"
                                                currencyCode="${currencyCode}"
                                                currencySymbol="${currencySymbol}"
                                                maxFractionDigits="${maxFractionDigits}" /></td>
                                </tr>
                                <c:set var="checkoutBtnDisable" value="" />
                                <c:if test="${amountReturn>0}">
                                    <c:set var="checkoutBtnDisable" value="disabled='disabled'" />
                                    <tr style="overflow:hidden;">
                                        <td class="no-line"></td>
                                        <td class="no-line"></td>
                                        <td class="no-line text-center text-danger"><strong>Amount payable</strong></td>
                                        <td class="no-line text-right text-danger" id="amountReturn" data-price="${amountReturn}">
                                            <b>
                                                <fmt:formatNumber
                                                        value="${amountReturn}"
                                                        type="currency"
                                                        currencyCode="${currencyCode}"
                                                        currencySymbol="${currencySymbol}"
                                                        maxFractionDigits="${maxFractionDigits}" />
                                            </b>
                                        </td>
                                    </tr>
                                </c:if>



                                <tr style="overflow:hidden;">
                                    <td class="no-line"></td>
                                    <td class="no-line"></td>
                                    <td class="no-line text-center"><strong>Pay</strong></td>
                                    <td class="no-line text-left">
                                        <input  id="currentPaymentTxtField" onchange="printCheckAmount(printDueInHtml);"
                                                type="number" min="0" max="${totalPrice}" class="form-control" placeholder="" value="0.0" aria-describedby="sizing-addon2" style="text-align: right;width:40%;height: 32px;float:right;">
                                    </td>
                                </tr>

                                <tr style="overflow:hidden;">
                                    <td class="no-line"></td>
                                    <td class="no-line"></td>
                                    <td class="no-line text-center"><strong></strong></td>
                                    <td class="no-line">
                                        <p class="text-danger text-right" id="errorObj_totalPaidAmount" />
                                    </td>
                                </tr>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row text-center" style="background-color: #ffffff;margin-bottom:20px" >
            <button ${checkoutBtnDisable} onclick="submitCheckoutData()" class="btn btn-success btn-lg">Checkout</button>
        </div>
    </div>

</div>

<%-- Developer Hidden field --%>
<input type="hidden" id="advertiserId" value="${advertiser.id}" />
<input type="hidden" id="transactionId" value="${transactionId}" />
