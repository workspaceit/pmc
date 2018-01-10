/**
 * Created by mi_rafi on 1/3/18.
 */

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

function notifyUpdateStatus(){
    $("#successMsg").html("Successfully updated").fadeIn(500).delay( 1000 ).fadeIn(500,function(){
        location.reload();
    });

}
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