/**
 * Created by mi_rafi on 1/3/18.
 */
$("#stateId").change(function(){
    var stateId = this.value;
    fetchCityOnStateChange(stateId,'locationFormBody');
});


$("#location_stateId").change(function(){
    var stateId = this.value;
    fetchCityOnStateChange(stateId,function(response) {
        $("#location_cityId").html("");
        $(response).each(function( key, value ){
            $("#location_cityId").append("<option value='"+value.id+"'>"+value.name+"</option>");
        });
    },'locationFormBody');

});

function fetchCityOnStateChange(stateId,successFn,locationFormBodyId){
    $.ajax({
        url: BASEURL+"api/cities/getByZip/"+stateId,
        type: "GET",
        traditional:true,
        statusCode:{
            500: function(response) {
                console.log(response);
            }, 401: function(response) {
                console.log(response.responseJSON);
            }, 422: function(response) {
                if(locationFormBodyId !== undefined && $("#"+locationFormBodyId).length>0){
                    BindErrorsWithHtml("errorObjLocation_",response.responseJSON,false,"#"+locationFormBodyId);
                }else{
                    BindErrorsWithHtml("errorObjLocation_",response.responseJSON,false);
                }

            }
        },
        success: function(response) {
            if(successFn !== undefined && typeof successFn == "function"){
                successFn(response);
                return;
            }

            $("#cityId").html("");
            $(response).each(function( key, value ){
                $("#cityId").append("<option value='"+value.id+"'>"+value.name+"</option>");
            });

        }
    });
}