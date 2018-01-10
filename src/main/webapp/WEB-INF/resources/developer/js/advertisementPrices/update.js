/**
 * Created by Tomal on 1/8/2018.
 */
function submitData(){
    var price_ids = [];
    var prices = [];
    $("[name='price_id[]']").each(function(index){
        price_ids[index]= $(this).val();
    });

    $("[name='price[]']").each(function(index){
        prices[index]= $(this).val();
    });

    var location = $("[name='price']").val();
    var data = {
        ids: price_ids,
        price: prices,
    };
    $.ajax({
        url: BASEURL+"api/advertisementPrices/update/",
        type: "POST",
        data: data ,
        traditional:true,
        statusCode:{
            500: function(response) {
                console.log(response);
            }, 401: function(response) {
                console.log(response.responseJSON);
            }, 422: function(response) {
                $("#notification").html("<div class='alert alert-danger'>Check the values and try again(Values must be number)</div>");
                BindErrorsWithHtml("errorObj_",response.responseJSON);
            }
        },
        success: function(response) {
            $("#notification").html("<div class='alert alert-success'>Saved Successfully</div>");
        }
    });
}

function cancel(){
    window.location = BASEURL+"admin/venue/all";
}