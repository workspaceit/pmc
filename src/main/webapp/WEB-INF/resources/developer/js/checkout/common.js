function calculateAdvertisementPriceTotal(){
    var discountStr = ($("#discountTxtField").val());
    discountStr = (discountStr==null || discountStr.trim()=="")?"0":discountStr;

    var total = 0;
    var discount = parseFloat(discountStr);

    $(".price-td").each(function(){
        var price = parseFloat($(this).data("price"));
        var quantity = parseFloat($(this).parent().find(".quantity-td").data("quantity"));
        total +=(price*quantity);
    });
   return {subtotal:total,discount:discount,total:total-discount};
}
function getTotalPayment(){
    var paymentStr = ($("#paymentTxtField").val());
    paymentStr = (paymentStr==null || paymentStr.trim()=="")?"0":paymentStr;

    return parseFloat(paymentStr);
}
function printTotalInHtml(){
    var checkAmount  = calculateAdvertisementPriceTotal();
    $("#totalCheckoutPrice").html(numeral(checkAmount.total).format('$0,0.00'));
}
function printDueInHtml(){
    var checkAmount  = calculateAdvertisementPriceTotal();
    var totalPayment = getTotalPayment();
    var dueAmount = checkAmount.total-totalPayment;
    $("#totalDuePrice").html(numeral(dueAmount).format('$0,0.00'));
}