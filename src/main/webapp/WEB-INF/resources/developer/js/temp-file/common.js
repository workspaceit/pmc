/**
 * Created by mi_rafi on 1/4/18.
 */
function removeImageByToken(token,fn){
    if(token == undefined){
        return;
    }
    $.ajax({
        url: BASEURL+'file/remove',
        data:{"token":token},
        type: 'POST',
        statusCode: {
            401: function (response) {
                console.log(response);
            },
            422: function (response) {
                console.log(response);
            }
        },success: function(data){
            console.log(data);
            fn(data);
        }
    });
}