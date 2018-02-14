/**
 * Created by mi_rafi on 1/3/18.
 */
Dropzone.autoDiscover = false;
var profilePictureToken = 0;


function locationAfterSaveAction(btnAction){
    var urlStr ="";
    switch(btnAction){
        case "save":
            urlStr = "admin/location/all";
            break;
        case "save_and_close":
            urlStr = "admin/location/all";
            break;
        case "save_and_new":
            urlStr = "admin/location/add";
            break;
        case "cancel":
            urlStr = "admin/location/all";
            break;
    }
    window.location =BASEURL+urlStr;
}
var GLOBAL_venueLogoImgDropZone;
var GLOBAL_venueBgImgDropZone;
// alternative to DOMContentLoaded
$(document).ready(function(){
    //Drop Zone Binding
    GLOBAL_venueLogoImgDropZone = configVenueLogoDropZone();
    GLOBAL_venueBgImgDropZone = configVenueBdImgDropZone();

    //Injecting necessary hidden elements
    // to store uploaded file token
    injectHiddenTokenFieldsForLocation();
});

function injectHiddenTokenFieldsForLocation(){
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
function emptyVenueLogoToken(){

    try{
        $("#venueLogoToken").val("");
    }catch(ex) {
        console.log(ex);

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
function emptyBgImgTokens(){
    $("#venueBgImgTokens").val("");
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
                        removeFileByToken(file.token,function (data) {
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
                $("#venueLogoImg").find(".dz-error-message span").html(msg).addClass("text-danger");

            },
            success:function(file,response){

                file.token = response.token;
                storeVenueLogoToken(response.token);
                console.log(file);
            }
        }
    );

    return venueLogoImgDropZone;
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
                        removeFileByToken(file.token,function(data){
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
                $("#venueBgImg").find(".dz-error-message span").html(msg).addClass("text-danger");;
            },
            success:function(file,response){

                file.token = response.token;
                storeVenueBgImgToken(response.token);
                console.log(file);
            }
        }
    );

    return venueBgImgDropZone;
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

    });
});