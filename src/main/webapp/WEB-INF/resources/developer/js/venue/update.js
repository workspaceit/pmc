/**
 * Created by Tomal on 1/8/2018.
 */
function submitData(state){
    var name = $('#name').val();
    var location = $('#location').val();
    var venueId = $('#venueId').val();
    var data = {
        name: name,
        location_id: location,
    };
    console.log(data);
    $.ajax({
        url: BASEURL+"api/venue/update/"+venueId,
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
            if(state=="save")
                window.location = BASEURL+"admin/venue/all";
            else if(state=="saveClose")
                window.location = BASEURL+"admin/venue/all";
            else if(state=="saveNew")
                window.location = BASEURL+"admin/venue/add";
        }
    });
}

function cancel(){
    window.location = BASEURL+"admin/venue/all";
}