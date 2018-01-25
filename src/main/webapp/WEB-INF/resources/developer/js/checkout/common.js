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
function printCheckAmount(fn1,fn2,fn3){
    console.log("sd");
    validateAndCorrect();
    if(fn1!=undefined && typeof fn1 == "function")fn1();
    if(fn2!=undefined && typeof fn2 == "function")fn2();
    if(fn3!=undefined && typeof fn3 == "function")fn3();
}
function printTotalInHtml(){
    var checkAmount  = calculateAdvertisementPriceTotal();
    $("#totalCheckoutPrice").html(numeral(checkAmount.totalAfterDiscount).format('$0,0.00'));
}
function printDueInHtml(){
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
    var totalPayable = checkAmount.subTotal- checkAmount.prevPaidAmount;

    if(checkAmount.discount<0){
        setDiscountAmount(0);
        checkAmount  = calculateAdvertisementPriceTotal();
    }

    if(checkAmount.totalPaidAmount<0){
        setCurrentPaymentAmount(0);
        checkAmount  = calculateAdvertisementPriceTotal();
    }
    /**Discount can't be greater then payable amount*/
    if(checkAmount.discount>totalPayable){
        checkAmount.discount = totalPayable;
        setDiscountAmount(checkAmount.discount);
        checkAmount  = calculateAdvertisementPriceTotal();
    }

    var balance = checkAmount.totalAfterDiscount - (checkAmount.totalPaidAmount+checkAmount.prevPaidAmount) ;
    /**Payment can't be greater then payable amount*/
    if(balance<0){
        checkAmount.totalPaidAmount = checkAmount.totalAfterDiscount - checkAmount.prevPaidAmount;
        setCurrentPaymentAmount(checkAmount.totalPaidAmount);
    }

}

function getCheckoutData(){

    var checkoutAmount = calculateAdvertisementPriceTotal();

    return {
        discount: checkoutAmount.discount,
        totalPaidAmount:checkoutAmount.totalPaidAmount,
        totalDue:checkoutAmount.dueAmount
    };
}