<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:genericpage>
    <jsp:body>
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
                                            <td>Top Ad Banner</td>
                                            <td class="text-center">$10.99</td>
                                            <td class="text-center">1</td>
                                            <td class="text-right">$10.99</td>
                                        </tr>
                                        <tr>
                                            <td>Bottom Ad Banner</td>
                                            <td class="text-center">$20.00</td>
                                            <td class="text-center">3</td>
                                            <td class="text-right">$60.00</td>
                                        </tr>
                                        <tr>
                                            <td>Pop Up Ad Banner</td>
                                            <td class="text-center">$600.00</td>
                                            <td class="text-center">1</td>
                                            <td class="text-right">$600.00</td>
                                        </tr>
                                        <tr>
                                            <td>Slide show Ad Banner</td>
                                            <td class="text-center">$600.00</td>
                                            <td class="text-center">1</td>
                                            <td class="text-right">$600.00</td>
                                        </tr>
                                        <tr>
                                            <td>Background Image</td>
                                            <td class="text-center">$50.00</td>
                                            <td class="text-center">2</td>
                                            <td class="text-right">$100.00</td>
                                        </tr>
                                        <tr>
                                            <td class="thick-line"></td>
                                            <td class="thick-line"></td>
                                            <td class="thick-line text-center"><strong>Subtotal</strong></td>
                                            <td class="thick-line text-right">$670.99</td>
                                        </tr>
                                        <tr style="overflow:hidden;">
                                            <td class="no-line"></td>
                                            <td class="no-line"></td>
                                            <td class="no-line text-center"><strong>Discount</strong></td>
                                            <td class="no-line text-left">
                                                <input type="text" class="form-control" placeholder="" aria-describedby="sizing-addon2" style="text-align: right;width:40%;height: 32px;float:right;">
                                            </td>

                                        </tr>

                                        <tr>
                                            <td class="no-line"></td>
                                            <td class="no-line"></td>
                                            <td class="no-line text-center"><strong>Total</strong></td>
                                            <td class="no-line text-right">$685.99</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row text-center" style="background-color: #ffffff;margin-bottom:20px" >
                    <button class="btn btn-success btn-lg">Checkout</button>
                </div>
            </div>

        </div>
    </jsp:body>

</t:genericpage>