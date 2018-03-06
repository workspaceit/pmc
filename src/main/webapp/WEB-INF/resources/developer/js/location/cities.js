/**
 * Created by mi_rafi on 1/3/18.
 */
$("#stateId").change(function(){
    var zipId = this.value;
    fetchCityOnStateChange(zipId,function(response) {
        $("#cityId").html("");
        $(response).each(function( key, value ){
            $("#cityId").append("<option value='"+value.id+"'>"+value.name+"</option>");
        });
    },'locationFormBody');
});


$("#location_stateId").change(function(){
    var zipId = this.value;
    fetchCityOnStateChange(zipId,function(response) {
        $("#location_cityId").html("");
        $(response).each(function( key, value ){
            $("#location_cityId").append("<option value='"+value.id+"'>"+value.name+"</option>");
        });
    },'locationFormBody');

});

function fetchCityOnStateChange(zipId,successFn,locationFormBodyId){
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
                if(locationFormBodyId !== undefined){
                    BindErrorsWithHtml("errorObjLocation_",response.responseJSON,false,"#"+locationFormBodyId);
                }else{
                    BindErrorsWithHtml("errorObjLocation_",response.responseJSON,false);
                }

            }
        },
        success: function(response) {
            successFn(response);
        }
    });
}