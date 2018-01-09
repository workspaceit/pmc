/**
 * Created by mi_rafi on 1/4/18.
 */
$(document).ready(function(){
    injectHiddenTokenFields();
});

var ADV_IMG_TYPE = {
    _ADVERTISER_OTHER_IMAGES_TOKEN:"otherImagesToken",
    _LOGO_TOKEN :"logoToken",
    _BACKGROUND_IMAGE:"backgroundImageToken",
    _TOP_BANNER_TOKEN:"topBannerToken",
    _BOTTOM_BANNER_TOKEN:"bottomBannerToken",
    _SLIDESHOW_BANNER_TOKEN:"slideShowBannerToken",
     _SLIDESHOW_VIDEO_TOKEN:"slideShowVideoToken",
    _EMAIL_POPUP_BANNER_TOKEN:"emailPopUpBannerToken",
    _EMAIL_POPUP_VIDEO_TOKEN:"emailPopUpVideoToken",
    _SMS_POPUP_BANNER_TOKEN:"smsPopUpBanner",
    _SMS_POPUP_VIDEO_TOKEN:"smsPopUpVideo"
};
function injectHiddenTokenFields(){
    for(var index in ADV_IMG_TYPE){
        var tokenElem = $("<input>", {type:"hidden",id: ADV_IMG_TYPE[index], "value": ""});
        $("body").append(tokenElem);
    }
}

$(document).ready(function(){
    var advertiserOtherImages  = configAdvertBdImgDropZone("advertiserOtherImages","other-images",6,1,function(response){
        storeToken(ADV_IMG_TYPE._ADVERTISER_OTHER_IMAGES_TOKEN,response.token);
    },function(response){
        removeToken(ADV_IMG_TYPE._LOGO_TOKEN,response.token);
    });

    /*Gallery Ads*/
    var logoBackgroundImage = configAdvertBdImgDropZone("advLogo","logo-image",1,1,function(response){
        emptyToken(ADV_IMG_TYPE._LOGO_TOKEN);
        storeToken(ADV_IMG_TYPE._LOGO_TOKEN,response.token);
    },function(response){
        removeToken(ADV_IMG_TYPE._LOGO_TOKEN,response.token);
    });

    var backgroundImage = configAdvertBdImgDropZone("advBackgroundImage","background-image",5,1,function(response){
        storeToken(ADV_IMG_TYPE._BACKGROUND_IMAGE,response.token);
    },function(response){
        removeToken(ADV_IMG_TYPE._BACKGROUND_IMAGE,response.token);
    });

    var topBanner = configAdvertBdImgDropZone("advTopBannerImage","top-banner",6,1,function(response){
        storeToken(ADV_IMG_TYPE._TOP_BANNER_TOKEN,response.token);
    },function(response){
        removeToken(ADV_IMG_TYPE._TOP_BANNER_TOKEN,response.token);
    });

    var bottomBanner = configAdvertBdImgDropZone("advBottomBannerImage","bottom-banner",6,1,function(response){
        storeToken(ADV_IMG_TYPE._BOTTOM_BANNER_TOKEN,response.token);
    },function(response){
        removeToken(ADV_IMG_TYPE._BOTTOM_BANNER_TOKEN,response.token);
    });
    /*Slide show Ads*/
    var slideShowBanner = configAdvertBdImgDropZone("advSlideShowBanner","slide-show-banner",6,1,function(response){
        storeToken(ADV_IMG_TYPE._SLIDESHOW_BANNER_TOKEN,response.token);
    },function(response){
        removeToken(ADV_IMG_TYPE._SLIDESHOW_BANNER_TOKEN,response.token);
    });

    var slideShowVideo = configAdvertBdImgDropZone("advSlideShowVideo","slide-show-video",1,3,function(response){
        emptyToken(ADV_IMG_TYPE._SLIDESHOW_VIDEO_TOKEN,response.token);
        storeToken(ADV_IMG_TYPE._SLIDESHOW_VIDEO_TOKEN,response.token);
    },function(response){
        removeToken(ADV_IMG_TYPE._SLIDESHOW_VIDEO_TOKEN,response.token);
    });
    /*PopUp Ads*/
    var emailPopUpBanner = configAdvertBdImgDropZone("advEmailPopUpBanner","email-popup-banner",6,1,function(response){
        storeToken(ADV_IMG_TYPE._EMAIL_POPUP_BANNER_TOKEN,response.token);
    },function(response){
        removeToken(ADV_IMG_TYPE._EMAIL_POPUP_VIDEO_TOKEN,response.token);
    });

    var emailPopUpVideo = configAdvertBdImgDropZone("advEmailPopUpVideo","email-popup-video",1,3,function(response){
        emptyToken(ADV_IMG_TYPE._EMAIL_POPUP_VIDEO_TOKEN);
        storeToken(ADV_IMG_TYPE._EMAIL_POPUP_VIDEO_TOKEN,response.token);
    },function(response){
        removeToken(ADV_IMG_TYPE._EMAIL_POPUP_VIDEO_TOKEN,response.token);
    });

    var smsPopUpBanner = configAdvertBdImgDropZone("advSmsPopUpBanner","sms-popup-banner",6,1,function(response){
        storeToken(ADV_IMG_TYPE._SMS_POPUP_BANNER_TOKEN,response.token);
    },function(response){
        removeToken(ADV_IMG_TYPE._SMS_POPUP_VIDEO_TOKEN,response.token);
    });

    var smsPopUpVideo = configAdvertBdImgDropZone("advSmsPopUpVideo","sms-popup-video",1,3,function(response){
        emptyToken(ADV_IMG_TYPE._SMS_POPUP_VIDEO_TOKEN);
        storeToken(ADV_IMG_TYPE._SMS_POPUP_VIDEO_TOKEN,response.token);
    },function(response){
        removeToken(ADV_IMG_TYPE._SMS_POPUP_VIDEO_TOKEN,response.token);
    });

});
function configAdvertBdImgDropZone(elementId,param,maxFile,maxFileSize,fnSuccess,fnError){
    var advImgDropZone = new Dropzone("#"+elementId,
        {
            url: BASEURL+"file/upload/adv/"+param,
            method:"post",
            paramName:"advImg",
            maxFilesize: maxFileSize,
            maxFiles:maxFile,
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
                        removeFileByToken(file.token,fnError);
                        advImgDropZone.removeFile(file);
                    });
                });

            },
            error:function(file,response){
                var msg = (typeof response == "object")?((response.length>0)?response[0].msg:""):response;
                $("#"+elementId).find(".dz-error-message span").html(msg);
            },
            success:function(file,response){

                file.token = response.token;
                console.log(file);
                fnSuccess(response);
            }
        }
    );
    return advImgDropZone;
}