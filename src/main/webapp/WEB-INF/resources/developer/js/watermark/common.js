
WATERMARK_TOKEN_KEY = {_SAMPLE:"sampleToken"}


$(document).ready(function()
{
    $("#e9").select2( {maximumSelectionSize: 3});
    $("#e1").select2();
    $("#e2").select2();
    $("#e-placement").select2();
    $("#e-size").select2();
});

$(document).ready(function(){
    $("#waterMarkImg .btn").click(function(){
        $("#waterMarkImg .active").removeClass("active");
        $(this).addClass("active");
        var onOfVal = $(this).data("name");

        if(onOfVal=="image"){

            $("#waterMarkImgFile").show();
            $("#waterMarkImgUrl").hide();
        }else if(onOfVal=="text"){
            $("#waterMarkImgFile").hide();
            $("#waterMarkImgUrl").show();
        }

    });
});


var rangeSlider = function(){
    var slider = $('.range-slider'),
        range = $('.range-slider__range'),
        value = $('.range-slider__value');

    slider.each(function(){

        value.each(function(){
            var value = $(this).prev().attr('value');
            $(this).html(value);
        });

        range.on('input', function(){
            $(this).next(value).html(this.value);
        });
    });
};

rangeSlider();




$(document).ready( function() {
    $(document).on('change', '.btn-file :file', function() {
        var input = $(this),
            label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
        input.trigger('fileselect', [label]);
    });

    $('.btn-file :file').on('fileselect', function(event, label) {

        var input = $(this).parents('.input-group').find(':text'),
            log = label;

        if( input.length ) {
            input.val(log);
        } else {
            if( log ) alert(log);
        }

    });
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#img-upload').attr('src', e.target.result);
            }

            reader.readAsDataURL(input.files[0]);
        }
    }

    $("#imgInp").change(function(){
        readURL(this);
    });
});



$(document).ready(function(){
    //Drop Zone Binding
    configwatermarkLogoDropZone();

    //Injecting necessary hidden elements
    // to store uploaded file token
    injectHiddenTokenFields();
});

function injectHiddenTokenFields(){
    var watermarkLogoTokenElement = $("<input>", {type:"hidden",id: "watermarkLogoToken", "value": ""});
    $("body").append(watermarkLogoTokenElement);

}
function getwatermarkLogoToken(){
    var token=0;
    try{
        token = parseInt($("#watermarkLogoToken").val());
        if(isNaN(token)){
            token = 0;
        }
    }catch(ex) {
        console.log(ex);
        token = 0;
    }
    return token;
}
function storewatermarkLogoToken(token){
    $("#watermarkLogoToken").val(token);
}
function removewatermarkLogoToken(token){
    var watermarkLogoToken = getwatermarkLogoToken();
    if(watermarkLogoToken==token){
        $("#watermarkLogoToken").val("");
    }

}

function configwatermarkLogoDropZone(){
    var watermarkLogoImgDropZone = new Dropzone("div#watermarkLogoImg",
        {
            url: BASEURL+"file/upload/photographer-profile-image",
            method:"post",
            paramName:"profileImg",
            maxFilesize: 1,
            maxFiles:1,
            addRemoveLinks: true,
            previewTemplate:$("#dropZonePreview").html(),
            init:function(){

                this.on("maxfilesexceeded", function(file) {
                    this.removeAllFiles();
                    this.addFile(file);
                });
                this.on("addedfile", function(file) {
                    file._removeLink.addEventListener("click", function() {
                        console.log(file);

                        removeImageByToken(file.token,function (data) {
                            removewatermarkLogoToken(data.token);
                        });
                        profilePictureToken = 0;
                        var _ref;
                        watermarkLogoImgDropZone.removeFile(file);
                    });
                });

            },
            error:function(file,response){
                var msg = (typeof response == "object")?((response.length>0)?response[0].msg:""):response;
                $("#profileImg").find(".dz-error-message span").html(msg);
            },
            success:function(file,response){
                $("#logoImg").remove();
                file.token = response.token;
                storewatermarkLogoToken(response.token);
                console.log(file);
            }
        }
    );
}
function getAllWatermarkData(){
    var name = $('#name').val();
    var type=$('.wm_tab.active').attr("data-name");
    var logoImgToken= getwatermarkLogoToken();
    var logoName=$("input[name=img_logo_name]").val(); //$("input[name=txt_logo_name]").val();
    var placement=$(".img_placement").val();
    var size=$(".img_font_size").val();
    var fade=$("input[name=img_fade_range]").val();
    var watermarkText=$("input[name=txt_wm_text]").val();
    var fontId=$(".txt_font").val();
    var color=$("input[name=txt_color]").val();
    var sampleImgToken = getToken(WATERMARK_TOKEN_KEY._SAMPLE);


    var data = {
        name: name,
        type:type,
        logoImgToken:logoImgToken,
        sampleImgToken:sampleImgToken,
        logoName: logoName,
        placement: placement,
        size: size,
        fade: fade,
        watermarkText: watermarkText,
        fontId: fontId,
        color: color
    };
    return data;
}
function getWatermarkData(){
    var name = $('#name').val();
    var type=$('.wm_tab.active').attr("data-name");
    var logoImgToken= getwatermarkLogoToken();
    var logoName=$("input[name=img_logo_name]").val();
    var txtLogoName=$("#txt_logo_name").val();
    var size=$(".img_font_size").val();
    var fade=$("input[name=img_fade_range]").val();
    var watermarkText=$("input[name=txt_wm_text]").val();
    var fontId=$(".txt_font").val();
    var placement=$(".img_placement").val();
    var color=$("input[name=txt_color]").val();
    var sampleImgToken = getToken(WATERMARK_TOKEN_KEY._SAMPLE);

    var data = {
        name: name,
        type:type,
        logoImgToken:logoImgToken,
        sampleImgToken:sampleImgToken,
        logoName: logoName,
        txtLogoName:txtLogoName,
        placement: placement,
        size: size,
        fade: fade,
        watermarkText: watermarkText,
        fontId: fontId,
        color: color
    };
    return data;
}
function previewWatermarkOnSample() {
    var parameters = getAllWatermarkData();

    if(parameters.type=="image"){
        if(parameters.logoImgToken==null || parameters.logoImgToken<=0){
            alert("Logo Required");
            return;
        }
    }

    var url = getWatermarkOnSamplePreviewUrl(parameters);

    $("#watermarkPreviewOnSampleImg").attr("src",url);
}
function previewWatermarkOnSample() {
    var parameters = getAllWatermarkData();

    if(parameters.type=="image"){
        if(parameters.logoImgToken==null || parameters.logoImgToken<=0){
            alert("Logo Required");
            return;
        }
    }

    var url = getWatermarkOnSamplePreviewUrl(parameters);

    $("#watermarkPreviewOnSampleImg").attr("src",url);
}
function previewWatermarkOnSampleEdit() {
    var parameters = getAllWatermarkData();



    var url = getWatermarkOnSamplePreviewUrlWithId(parameters);

    $("#watermarkPreviewOnSampleImg").attr("src",url);
}
function getWatermarkOnSamplePreviewUrl(parameters) {

    var urlParams = getArrayToUriParams(parameters);
    var url = BASEURL+"img/watermarked-preview?"+urlParams;
    return url;
}

function getWatermarkOnSamplePreviewUrlWithId(parameters) {

    var id = $("#watermarkId").val();
    var urlParams = getArrayToUriParams(parameters);
    var url = BASEURL+"img/watermarked-preview/"+id+"?"+urlParams;
    return url;
}
commonDropZoneConfig({
        elementId:"dummyForDropZone",
        param:"watermark-sample-image",
        maxFile:1,
        maxFileSize:1,
        success:function(response,file){
            storeToken(WATERMARK_TOKEN_KEY._SAMPLE,response.token);
            $("#watermarkPreviewOnSampleImg").attr("src",file.dataURL);
        },
        afterServerFileRemove:function(response){
            removeToken(WATERMARK_TOKEN_KEY._SAMPLE,response.token);
        }
    });



$('#changeSample').click(function(){
    $("#dummyForDropZone").click();
});
