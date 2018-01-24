function submitCheckoutData(){
    var transactionId = $('#transactionId').val();
    var checkoutAmounts = calculateAdvertisementPriceTotal()
    var advertiserId = $('#advertiserId').val();
    var subTotal = checkoutAmounts.subtotal;
    var discount = checkoutAmounts.discount;
    var total = checkoutAmounts.total;

    var data = {
        name: name,
        advertiserId: advertiserId,
        subTotal: subTotal,
        discount: discount,
        total: total
    };
    console.log(data);
    $.ajax({
        url: BASEURL+"api/pmc-advsr/checkout/"+transactionId+"/"+advertiserId,
        type: "POST",
        data: data ,
        traditional:true,
        statusCode:{
            500: function(response) {
                console.log(response);
            }, 401: function(response) {
                console.log(response.responseJSON);
            }, 422: function(response) {
                BindErrorsWithHtml("errorObj_",response.responseJSON);
            }
        },
        success: function(response) {
           window.location = BASEURL+"admin/advertiser/all";
        }
    });
    
}