/**
 * Created by mi_rafi on 1/3/18.
 */
$("#stateId").change(function(){
    var zipId = this.value;
    $.ajax({
        url: BASEURL+"api/cities/getByZip/"+zipId,
        type: "GET",
        traditional:true,
        statusCode:{
            500: function(response) {
                console.log(response);
            }, 401: function(response) {
                console.log(response.responseJSON);
            }, 422: function(response) {
                BindErrorsWithHtml("errorObjLocation_",response.responseJSON,false,"#locationFormBody");
            }
        },
        success: function(response) {
            $("#cityId").html("");
            $(response).each(function( key, value ){
               $("#cityId").append("<option value='"+value.id+"'>"+value.name+"</option>");
            });
        }
    });
});


$("#location_stateId").change(function(){
    var zipId = this.value;
    $.ajax({
        url: BASEURL+"api/cities/getByZip/"+zipId,
        type: "GET",
        traditional:true,
        statusCode:{
            500: function(response) {
                console.log(response);
            }, 401: function(response) {
                console.log(response.responseJSON);
            }, 422: function(response) {
                BindErrorsWithHtml("errorObjLocation_",response.responseJSON,false,"#locationFormBody");
            }
        },
        success: function(response) {
            $("#location_cityId").html("");
            $(response).each(function( key, value ){
                $("#location_cityId").append("<option value='"+value.id+"'>"+value.name+"</option>");
            });
        }
    });
});