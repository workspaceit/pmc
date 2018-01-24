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
    var paymentStr = $("#paymentTxtField").val();
    paymentStr = (paymentStr==null || paymentStr.trim()=="")?"0":paymentStr;

    return parseFloat(paymentStr);
}
function getDuePayment(){
    var paymentStr = $("#totalDuePrice").data("price");
    paymentStr = (paymentStr==null || paymentStr=="")?"0":paymentStr;

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
    setDueAmount();
}
function setDueAmount(){
    var totalPayment = getTotalPayment();
    $("#totalDuePrice").data("price",totalPayment);
}
function printPaidInHtml(){
    var totalPayment = getTotalPayment();
    $("#totalPayedPrice").html(numeral(totalPayment).format('$0,0.00'));
}
function getCheckoutData(){

    var checkoutAmounts = calculateAdvertisementPriceTotal();


    var subTotal = checkoutAmounts.subtotal;
    var discount = checkoutAmounts.discount;
    var total = checkoutAmounts.total;
    var totalPaidAmount = getTotalPayment();
    var totalDue = getDuePayment();

    return {
        name: name,
        subTotal: subTotal,
        discount: discount,
        total: total,
        totalPaidAmount:totalPaidAmount,
        totalDue:totalDue
    };
}