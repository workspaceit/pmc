function submitWatermark(action) {
    var id  = $("#watermarkId").val();
    var data = getWatermarkData();

    if(data.type=="image"){
        if(data.name === '' || data.logoName===''||data.logoImgToken===''||data.placement===''||data.size===''||data.fade===''){
            alert("Please fill all the field");
            return false;
        }

    }else{
        if(data.name === '' || data.logoName===''||data.watermarkText===''||data.fontId===''||data.color===''){
            alert("Please fill all the field");
            return false;
        }

    }



    console.log(data)
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
//                        alert("Updated successfully");
            if(action === "save" || action === "save-close") {
                window.location = BASEURL+"admin/watermark/all";
            }
            else if(action === "save-new"){
                window.location = BASEURL+"admin/watermark/add";
            }
        }
    });
}


