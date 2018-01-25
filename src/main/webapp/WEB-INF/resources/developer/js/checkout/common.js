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


    var totalAfterDiscount = total-discount;
    var totalPaidAmount = getCurrentPayment();
    var prevPaidAmount = getPrevPaidPrice();
    var dueAmount = totalAfterDiscount - (prevPaidAmount+totalPaidAmount);

   return {
       subTotal:total,
       discount:discount,
       totalAfterDiscount:totalAfterDiscount,
       dueAmount:dueAmount,
       totalPaidAmount:totalPaidAmount,
       prevPaidAmount:prevPaidAmount
   };
}
function getPrevPaidPrice(){
    return parseFloat($("#prevPaidPrice").data("price"));
}
function getCurrentPayment(){
    var paymentStr = $("#currentPaymentTxtField").val();
    paymentStr = (paymentStr==null || paymentStr.trim()=="")?"0":paymentStr;

    return parseFloat(paymentStr);
}

function printTotalInHtml(){
    validateAndCorrect();
    var checkAmount  = calculateAdvertisementPriceTotal();
    $("#totalCheckoutPrice").html(numeral(checkAmount.totalAfterDiscount).format('$0,0.00'));
}
function printDueInHtml(){
    validateAndCorrect();
    var checkAmount  = calculateAdvertisementPriceTotal();

    setDueAmount(checkAmount.dueAmount);
}
function setDueAmount(dueAmount){
    $("#totalDuePrice").html(numeral(dueAmount).format('$0,0.00'));
    $("#totalDuePrice").data("price",dueAmount);
}
function setDiscountAmount(discountAmount){
    $("#discountTxtField").val(discountAmount);
}
function setCurrentPaymentAmount(amount){
    $("#currentPaymentTxtField").val(amount);
}
function validateAndCorrect(){
    var checkAmount  = calculateAdvertisementPriceTotal();

    if(checkAmount.discount>checkAmount.subTotal){
        checkAmount.discount = checkAmount.subTotal- checkAmount.prevPaidAmount;
        setDiscountAmount(checkAmount.discount);
        checkAmount  = calculateAdvertisementPriceTotal();
    }

    var balance = checkAmount.totalAfterDiscount - (checkAmount.totalPaidAmount+checkAmount.prevPaidAmount) ;
    if(balance<0){
        checkAmount.totalPaidAmount = checkAmount.totalAfterDiscount - checkAmount.prevPaidAmount;
        setCurrentPaymentAmount(checkAmount.totalPaidAmount);
    }

}

function printPaidInHtml(){
    var totalPayment = getCurrentPayment();
    $("#totalPayedPrice").html(numeral(totalPayment).format('$0,0.00'));
}
function getCheckoutData(){

    var checkoutAmount = calculateAdvertisementPriceTotal();

    return {
        discount: checkoutAmount.discount,
        totalPaidAmount:checkoutAmount.totalPaidAmount,
        totalDue:checkoutAmount.dueAmount
    };
}