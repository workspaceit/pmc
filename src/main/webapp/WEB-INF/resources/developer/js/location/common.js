/**
 * Created by mi_rafi on 1/3/18.
 */
Dropzone.autoDiscover = false;
var profilePictureToken = 0;


// alternative to DOMContentLoaded
$(document).ready(function(){
    //Drop Zone Binding
    configVenueLogoDropZone();
    configVenueBdImgDropZone();

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
    var venueLogoTokenElement = $("<input>", {type:"hidden",id: "venueLogoToken", "value": ""});
    var venueBgImgTokensElement = $("<input>", {type:"hidden",id: "venueBgImgTokens", "value": ""});

    $("body").append(venueLogoTokenElement);
    $("body").append(venueBgImgTokensElement);
}
function getVenueLogoToken(){
    var token=0;
    try{
        token = parseInt($("#venueLogoToken").val());
        if(isNaN(token)){
            token = 0;
        }
    }catch(ex) {
        console.log(ex);
        token = 0;
    }
    return token;
}
function storeVenueLogoToken(token){
    $("#venueLogoToken").val(token);
}
function removeVenueLogoToken(token){
    var venueLogoToken = getVenueLogoToken();
    if(venueLogoToken==token){
        $("#venueLogoToken").val("");
    }

}

function getVenueBgImgTokens(){
    var tokens=[];
    try{
        var venueLogoTokenStr =  $("#venueBgImgTokens").val().trim();
        tokens = JSON.parse(venueLogoTokenStr==""?"[]":venueLogoTokenStr);

    }catch(ex) {
        console.log(ex);
        tokens = [];
    }
    return tokens;
}
function storeVenueBgImgToken(token){
    var tokens = getVenueBgImgTokens();
    if(tokens.indexOf(token)<0){
        tokens.push(token);
    }
    $("#venueBgImgTokens").val(JSON.stringify(tokens));
}
function removeBgImgTokens(token){
    var tokens=getVenueBgImgTokens();
    var index = tokens.indexOf(token);
    if(index>0){
        tokens.splice(index,1);
    }
}


function configVenueLogoDropZone(){
    var venueLogoImgDropZone = new Dropzone("div#venueLogoImg",
        {
            url: BASEURL+"file/upload/venue-logo-image",
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
                            removeVenueLogoToken(data.token);
                        });
                        profilePictureToken = 0;
                        var _ref;
                        venueLogoImgDropZone.removeFile(file);
                    });
                });

            },
            error:function(file,response){
                var msg = (typeof response == "object")?((response.length>0)?response[0].msg:""):response;
                $("#profileImg").find(".dz-error-message span").html(msg);
            },
            success:function(file,response){

                file.token = response.token;
                storeVenueLogoToken(response.token);
                console.log(file);
            }
        }
    );
}
function configVenueBdImgDropZone(){
    var venueBgImgDropZone = new Dropzone("div#venueBgImg",
        {
            url: BASEURL+"file/upload/venue-background-image",
            method:"post",
            paramName:"profileImg",
            maxFilesize: 1,
            maxFiles:5,
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
                        removeImageByToken(file.token,function(data){
                            removeBgImgTokens(data.token);
                        });
                        profilePictureToken = 0;
                        var _ref;
                        venueBgImgDropZone.removeFile(file);
                    });
                });

            },
            error:function(file,response){
                var msg = (typeof response == "object")?((response.length>0)?response[0].msg:""):response;
                $("#profileImg").find(".dz-error-message span").html(msg);
            },
            success:function(file,response){

                file.token = response.token;
                storeVenueBgImgToken(response.token);
                console.log(file);
            }
        }
    );
}
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
function getSlideShowSettingsStatus(){
    var onOfVal = $("#slideShowSettingsBtnDiv .active").first().data("val");
    parseInt(onOfVal);
    if(isNaN(onOfVal)){
        onOfVal = 0;
    }
    return (onOfVal==1)?true:false;
}
// $("#slideShowSettingsBtnDiv .")
$(document).ready(function(){
     $("#slideShowSettingsBtnDiv .btn").click(function(){
        $("#slideShowSettingsBtnDiv .active").removeClass("active");
        $(this).addClass("active");
        var onOfVal = parseInt($(this).data("val"));
        if(isNaN(onOfVal)){
            onOfVal = 0;
        }
        if(onOfVal==1){
            $("#slideShowSettings").show();
        }else{
            $("#slideShowSettings").hide();
        }

    })
});