function submitWatermark(action) {
    var id  = $("#watermarkId").val();
    var data = getWatermarkData();


    $.ajax({
        url: BASEURL+"api/watermark/update/"+id,
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
            if(action === "save") {
                $.growl.notice({title: 'Success!', message: "Watermark saved"});
            }
            else if(action === "save-close") {
                window.location = BASEURL+"admin/watermark/all";
            }
            else if(action === "save-new"){
                window.location = BASEURL+"admin/watermark/add";
            }
        }
    });
}


$(document).ready(function () {
    setTimeout(function(){previewWatermarkOnSampleEdit(); },1000);
});

