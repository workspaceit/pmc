

$(".choose-btn > .btn").click(function(){
    $(".choose-btn > .btn").removeClass("active");
    $(this).addClass("active");
});


Dropzone.autoDiscover = false;
var profilePictureToken = 0;


// alternative to DOMContentLoaded
$(document).ready(function(){
    //Drop Zone Binding
    configVenueLogoDropZone();


    //Injecting necessary hidden elements
    // to store uploaded file token
    injectHiddenTokenFields();
});

function notifyAdminUpdateStatus() {
    $("#successMsg").html("Successfully updated").fadeIn(500).delay(1000).fadeIn(500, function () {
        location.reload();
    });
}

function injectHiddenTokenFields(){
    var profilePicTokenElement = $("<input>", {type:"hidden",id: "profilePicToken", "value": ""});


    $("body").append(profilePicTokenElement);

}
function getVenueLogoToken(){
    var token=0;
    try{
        token = parseInt($("#profilePicToken").val());
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
    $("#profilePicToken").val(token);
}
function removeVenueLogoToken(token){
    var profilePicToken = getVenueLogoToken();
    if(profilePicToken==token){
        $("#profilePicToken").val("");
    }

}





function configVenueLogoDropZone(){
    var profilePicImgDropZone = new Dropzone("div#profilePicImg",
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
                        profilePicImgDropZone.removeFile(file);
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
function redirectFromAdminUser(btnAction){
    var url = BASEURL+"admin/user";
    switch(btnAction){
        case "save":
            url += "/all";
            break;
        case "save-new":
            url +="/add";
            break;
        case "save-close":
            url += "/all";
            break;
        case "cancel":
            url +="/all";
            break;
    }
    window.location = url;
}