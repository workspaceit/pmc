function submitCheckoutData(){
    var transactionId = $('#transactionId').val();
    var advertiserId = $('#advertiserId').val();

    var data = getCheckoutData();
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