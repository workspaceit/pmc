/**
 * Created by mi_rafi on 1/4/18.
 */

var errorFound = false;
var globalBtnAction = "";
var globalSubmitAction = "";

/*DropZoneObject used below code*/
var dropZoneElements = {
     advertiserOtherImages : {},

     galleryLogoImage : {},
     galleryBackgroundImage : {},
     galleryTopBanner : {},
     galleryBottomBanner : {},

     slideShowBanner : {},
     slideShowVideo : {},

     popupEmailBanner : {},
     popupEmailVideo : {},

     popUpSmsBanner : {},
     popUpSmsVideo : {}
};




var RotationSettings={
    _GALLERY_BOTTOM:"galleryBottomRotationBtn",
    _GALLERY_TOP:"galleryTopRotationBtn",
    _SLIDE_SHOW_BANNER:"slideShowBannerRotationBtn",
    _SLIDE_SHOW_VIDEO:"slideShowVideoRotationBtn",
    _POP_UP_SMS:"popUpSmsRotationBtn",
    _POP_UP_EMAIL:"popUpEmailRotationBtn"
};
function notifyUser(id,response,errorFound){
    if(errorFound){
        $("#"+id).html(response.responseJSON.length).show();
    }else{
        $("#"+id).html(0).hide();
    }
}
$(document).ready(function(){
    injectHiddenTokenFieldsForAdvertiser();

    for(var key in RotationSettings){
        var id = RotationSettings[key];
        initRtationSettings(id);
    }
});

function getRotationSetting(id){
   var rotationSettingVal =  $("#"+id+" .active").data("val");
    rotationSettingVal = parseInt(rotationSettingVal);

    if(rotationSettingVal==1){
        return "ROTATE";
    }else{
        return "STATIC";
    }
}
function initRtationSettings(id){
    $("#"+id+" .btn").click(function(){
        $("#"+id+" .btn").removeClass("active");
        $(this).addClass("active");
    });
}

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

function addQuantity(){

}

function injectHiddenTokenFieldsForAdvertiser(){
    for(var index in ADV_IMG_TYPE){
        var tokenElem = $("<input>", {type:"hidden",id: ADV_IMG_TYPE[index], "value": ""});
        $("body").append(tokenElem);
    }
}


$(document).ready(function(){
    dropZoneElements.advertiserOtherImages  = configAdvertBdImgDropZone("advertiserOtherImages","other-images",6,1,function(response){
        storeToken(ADV_IMG_TYPE._ADVERTISER_OTHER_IMAGES_TOKEN,response.token);
    },function(response){
        removeToken(ADV_IMG_TYPE._LOGO_TOKEN,response.token);
    });

    /*Gallery Ads*/
    dropZoneElements.galleryLogoImage = configAdvertBdImgDropZone("advLogo","logo-image",1,1,function(response){
        emptyToken(ADV_IMG_TYPE._LOGO_TOKEN);
        storeToken(ADV_IMG_TYPE._LOGO_TOKEN,response.token);
    },function(response){
        removeToken(ADV_IMG_TYPE._LOGO_TOKEN,response.token);
    });

    dropZoneElements.galleryBackgroundImage = configAdvertBdImgDropZone("advBackgroundImage","background-image",5,1,function(response){
        storeToken(ADV_IMG_TYPE._BACKGROUND_IMAGE,response.token);
    },function(response){
        removeToken(ADV_IMG_TYPE._BACKGROUND_IMAGE,response.token);
    });

    dropZoneElements.galleryTopBanner = configAdvertBdImgDropZone("advTopBannerImage","top-banner",6,1,function(response){
        storeToken(ADV_IMG_TYPE._TOP_BANNER_TOKEN,response.token);
    },function(response){
        removeToken(ADV_IMG_TYPE._TOP_BANNER_TOKEN,response.token);
    });

    dropZoneElements.galleryBottomBanner = configAdvertBdImgDropZone("advBottomBannerImage","bottom-banner",6,1,function(response){
        storeToken(ADV_IMG_TYPE._BOTTOM_BANNER_TOKEN,response.token);
    },function(response){
        removeToken(ADV_IMG_TYPE._BOTTOM_BANNER_TOKEN,response.token);
    });
    /*Slide show Ads*/
    dropZoneElements.slideShowBanner = configAdvertBdImgDropZone("advSlideShowBanner","slide-show-banner",6,1,function(response){
        storeToken(ADV_IMG_TYPE._SLIDESHOW_BANNER_TOKEN,response.token);
    },function(response){
        removeToken(ADV_IMG_TYPE._SLIDESHOW_BANNER_TOKEN,response.token);
    });

    dropZoneElements.slideShowVideo = configAdvertBdImgDropZone("advSlideShowVideo","slide-show-video",1,3,function(response){
        emptyToken(ADV_IMG_TYPE._SLIDESHOW_VIDEO_TOKEN,response.token);
        storeToken(ADV_IMG_TYPE._SLIDESHOW_VIDEO_TOKEN,response.token);
    },function(response){
        removeToken(ADV_IMG_TYPE._SLIDESHOW_VIDEO_TOKEN,response.token);
    });
    /*PopUp Ads*/
    dropZoneElements.popupEmailBanner = configAdvertBdImgDropZone("advEmailPopUpBanner","email-popup-banner",6,1,function(response){
        storeToken(ADV_IMG_TYPE._EMAIL_POPUP_BANNER_TOKEN,response.token);
    },function(response){
        removeToken(ADV_IMG_TYPE._EMAIL_POPUP_VIDEO_TOKEN,response.token);
    });

    dropZoneElements.popupEmailVideo = configAdvertBdImgDropZone("advEmailPopUpVideo","email-popup-video",1,3,function(response){
        emptyToken(ADV_IMG_TYPE._EMAIL_POPUP_VIDEO_TOKEN);
        storeToken(ADV_IMG_TYPE._EMAIL_POPUP_VIDEO_TOKEN,response.token);
    },function(response){
        removeToken(ADV_IMG_TYPE._EMAIL_POPUP_VIDEO_TOKEN,response.token);
    });

    dropZoneElements.popUpSmsBanner = configAdvertBdImgDropZone("advSmsPopUpBanner","sms-popup-banner",6,1,function(response){
        storeToken(ADV_IMG_TYPE._SMS_POPUP_BANNER_TOKEN,response.token);
    },function(response){
        removeToken(ADV_IMG_TYPE._SMS_POPUP_VIDEO_TOKEN,response.token);
    });

    dropZoneElements.popUpSmsVideo = configAdvertBdImgDropZone("advSmsPopUpVideo","sms-popup-video",1,3,function(response){
        emptyToken(ADV_IMG_TYPE._SMS_POPUP_VIDEO_TOKEN);
        storeToken(ADV_IMG_TYPE._SMS_POPUP_VIDEO_TOKEN,response.token);
    },function(response){
        removeToken(ADV_IMG_TYPE._SMS_POPUP_VIDEO_TOKEN,response.token);
    });


    /**Advertiser Info Date picker*/
    var runtimeStartsDate = ( $('#runtimeStarts').val().trim()=="" )?moment().format('MM/D/YYYY'):$('#runtimeStarts').val();
    var runtimeEndsStartDate = ( $('#runtimeEnds').val().trim()=="" )?moment().format('MM/D/YYYY'):$('#runtimeEnds').val();
    $('#runtimeStarts').daterangepicker({
        locale: {
            format: 'MM/DD/YYYY'
        },
        singleDatePicker: true,
        showDropdowns: true,
        startDate: runtimeStartsDate
    },function(start, end, label) {
        //var years = moment().diff(start, 'years');
        // alert("You are " + years + " years old.");
    });

    $('#runtimeEnds').daterangepicker({
        locale: {
            format: 'MM/DD/YYYY'
        },
        singleDatePicker: true,
        showDropdowns: true,
        startDate:runtimeEndsStartDate
    },function(start, end, label) {
        /*var years = moment().diff(start, 'years');
         alert("You are " + years + " years old.");*/
    });
    /**Gallery ads Date picker*/
    var topBannerExpiryDate = ( $('#topBannerExpiryDate').val().trim()=="" )?moment().format('MM/D/YYYY'):$('#topBannerExpiryDate').val();
    var bottomBannerExpiryDate = ( $('#bottomBannerExpiryDate').val().trim()=="" )?moment().format('MM/D/YYYY'):$('#bottomBannerExpiryDate').val();
    $('#topBannerExpiryDate').daterangepicker({
        locale: {
            format: 'MM/DD/YYYY'
        },
        singleDatePicker: true,
        showDropdowns: true,
        startDate:topBannerExpiryDate
    },function(start, end, label) {
        console.log(start, end, label);
        $("#topBannerExpiryDateLbl").html(start.format('MMM D, YYYY'));
        /*var years = moment().diff(start, 'years');
         alert("You are " + years + " years old.");*/
    });
    $('#bottomBannerExpiryDate').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true,
        startDate:bottomBannerExpiryDate
    },function(start, end, label) {
        $("#bottomBannerExpiryDateLbl").html(start.format('MMM D, YYYY'));
        /*var years = moment().diff(start, 'years');
         alert("You are " + years + " years old.");*/
    });

    /*Slide show*/
    var slideShowBannerExpiryDate = ( $('#slideShowBannerExpiryDate').val().trim()=="" )?moment().format('MM/D/YYYY'):$('#slideShowBannerExpiryDate').val();
    var slideShowVideoExpiryDate= ( $('#slideShowVideoExpiryDate').val().trim()=="" )?moment().format('MM/D/YYYY'):$('#slideShowVideoExpiryDate').val();

    $('#slideShowBannerExpiryDate').daterangepicker({
        locale: {
            format: 'MM/DD/YYYY'
        },
        singleDatePicker: true,
        showDropdowns: true,
        startDate:slideShowBannerExpiryDate
    },function(start, end, label) {
        $("#slideShowBannerExpiryDateLbl").html(start.format('MMM D, YYYY'));
        /*var years = moment().diff(start, 'years');
         alert("You are " + years + " years old.");*/
    });
    $('#slideShowVideoExpiryDate').daterangepicker({
        locale: {
            format: 'MM/DD/YYYY'
        },
        singleDatePicker: true,
        showDropdowns: true,
        startDate:slideShowVideoExpiryDate
    },function(start, end, label) {
        $("#slideShowVideoExpiryDateLbl").html(start.format('MMM D, YYYY'));
        /*var years = moment().diff(start, 'years');
         alert("You are " + years + " years old.");*/
    });
    /*Popup ads*/
    var smsExpiryDate = ( $('#smsExpiryDate').val().trim()=="" )?moment().format('MM/D/YYYY'):$('#smsExpiryDate').val();
    var emailExpiryDate = ( $('#emailExpiryDate').val().trim()=="" )?moment().format('MM/D/YYYY'):$('#emailExpiryDate').val();

    $('#smsExpiryDate').daterangepicker({
        locale: {
            format: 'MM/DD/YYYY'
        },
        singleDatePicker: true,
        showDropdowns: true,
        startDate:smsExpiryDate
    },function(start, end, label) {
        $("#smsExpiryDateLbl").html(start.format('MMM D, YYYY'));
        /*var years = moment().diff(start, 'years');
         alert("You are " + years + " years old.");*/
    });
    $('#emailExpiryDate').daterangepicker({
        locale: {
            format: 'MM/DD/YYYY'
        },
        singleDatePicker: true,
        showDropdowns: true,
        startDate:emailExpiryDate
    },function(start, end, label) {
        $("#emailExpiryDateLbl").html(start.format('MMM D, YYYY'));
        /*var years = moment().diff(start, 'years');
         alert("You are " + years + " years old.");*/
    });

    $('input[name="starttime"]').timepicker({
        timeFormat: 'hh:mm tt'
    });

    $(".js-example-placeholder-multiple").select2({
        placeholder: "",
        allowClear: true
    });

    $("#allLocationSelection").click(function(){
        showHideLocationSelectBox();
    });
    $("#allEventSelection").click(function(){
        showHideEventSelectBox();
    });

    showHideLocationSelectBox();
    showHideEventSelectBox();

});
function showHideLocationSelectBox(){
    var isChecked = $("#allLocationSelection:checked").length;
    if(isChecked==1){
        $("#locationIds").parent().hide();
    }else{
        $("#locationIds").parent().show();
    }
}
function showHideEventSelectBox(){
    var isChecked = $("#allEventSelection:checked").length;
    if(isChecked==1){
        $("#eventIds").parent().hide();
    }else{
        $("#eventIds").parent().show();
    }
}
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

function initValidationForUpdate(btnAction,submitType){
    globalBtnAction = btnAction;
    UnBindErrors("errorObj_");
    errorFound = false;
    submitAdvertiserData(submitType,true,1);
}
function initSubmitAdvertiserData(btnAction,submitType){
    globalBtnAction = btnAction;
    UnBindErrors("errorObj_");
    errorFound = false;
    submitAdvertiserData(submitType,false,1);
}
function advertiserAfterSaveAction(btnAction,id){
    if(id==undefined){
        id=-1;
    }
    var urlStr =BASEURL+"admin";
    switch(btnAction){
        case "save":
            urlStr += "/advertiser/checkout/"+id;
            break;
        case "save_and_close":
            urlStr += "/advertiser/all";
            break;
        case "save_and_new":
            urlStr += "/advertiser/add";
            break;
        case "cancel":
            urlStr += "/advertiser/all";
            break;
    }

    window.location =urlStr;
}
function submitAdvertiserData(submitType,forUpdate,marker){
    switch (marker){
        case 1:
            // set last parementer  `marker` of submitAdvertiserData function
            // to 2 to update all
            // Only advertiser can be updated as per discussion with Tahsin vhai
            validateAdvertiser(function(response){
                notifyUser("advertiserInfoErrorCount",response,false);
                submitAdvertiserData(submitType,forUpdate,5);
            },function(response){
                BindErrorsWithHtml("errorObj_",response.responseJSON,true);
                notifyUser("advertiserInfoErrorCount",response,true);
                errorFound = true;
                submitAdvertiserData(submitType,forUpdate,5);
            });
            break;
        case 2:
            validateGalleryAdds(forUpdate,function(response){
                notifyUser("galleryAdsErrorCount",response,false);
                submitAdvertiserData(submitType,forUpdate,3);
            },function(response){
                BindErrorsWithHtml("errorObj_",response.responseJSON,true);
                notifyUser("galleryAdsErrorCount",response,true);
                errorFound = true;
                submitAdvertiserData(submitType,forUpdate,3);
            });
            break;
        case 3:
            validateSlideShowAds(forUpdate,function(response){
                notifyUser("slideShowAdsErrorCount",response,false);
                submitAdvertiserData(submitType,forUpdate,4);
            },function(response){
                BindErrorsWithHtml("errorObj_",response.responseJSON,true);
                notifyUser("slideShowAdsErrorCount",response,true);
                errorFound = true;
                submitAdvertiserData(submitType,forUpdate,4);
            });
            break;
        case 4:
            validatePopUpAdsData(forUpdate,function(response){
                notifyUser("popUpAdsErrorCount",response,false);
                submitAdvertiserData(submitType,forUpdate,5);
            },function(response){
                BindErrorsWithHtml("errorObj_",response.responseJSON,true);
                notifyUser("popUpAdsErrorCount",response,true);
                errorFound = true;
                submitAdvertiserData(submitType,forUpdate,5);
            });
            break;
        case 5:
            if(!errorFound){
                submitCreateOrUpdate(submitType);
            }

            break;
    }
}
function submitCreateOrUpdate(btnAction){
       switch (btnAction){
           case "create":
               createAdvertiser(function(response){
                   notifyUser("advertiserInfoErrorCount",response,false);
                   advertiserAfterSaveAction(globalBtnAction,response.id);
               });
               break;
           case "update":
               updateAdvertiser();
               break;
       }
}
/*Advertiser */
function getAdvertiserInfoData(prefix){
    if(prefix==undefined){
        prefix = "";
    }else{
        prefix += "."
    }
    var name = $('#name').val();
    var address = $('#address').val();
    var cityId = $('#cityId').val();
    var eventIds = $('#eventIds').val();
    var stateId = $('#stateId').val();
    var zip = $('#zip').val();
    var phone = $('#phone').val();
    var website = $('#website').val();
    var runtimeStarts = $('#runtimeStarts').data('daterangepicker').startDate.format("MM/DD/YYYY");
    var runtimeEnds = $('#runtimeEnds').data('daterangepicker').startDate.format("MM/DD/YYYY");
    var locationIds = $('#locationIds').val();
    var allLocationSelected = hasAllLocationSelected();
    var allEventSelected = hasAllEventSelected();
    var otherImage = getToken(ADV_IMG_TYPE._ADVERTISER_OTHER_IMAGES_TOKEN);

    var data={};

    data[prefix+"name"]= name;
    data[prefix+"address"]= address;
    data[prefix+"cityId"]= cityId;
    data[prefix+"eventIds"]= eventIds;
    data[prefix+"stateId"]= stateId;
    data[prefix+"zip"]= zip;
    data[prefix+"phone"]= phone;
    data[prefix+"website"]= website;
    data[prefix+"otherImage"]= otherImage;
    data[prefix+"runtimeStarts"]= runtimeStarts;
    data[prefix+"runtimeEnds"]= runtimeEnds;
    data[prefix+"locationIds"]= locationIds;
    data[prefix+"isAllLocationSelected"]=allLocationSelected;
    data[prefix+"isAllEventSelected"]=allEventSelected;
    data[prefix+"otherImage"]=otherImage;


    return data;
}

/*Gallery Ads */
function getGalleryAddsData(prefix){
    if(prefix==undefined){
        prefix = "";
    }else{
        prefix += "."
    }
    var galleryId = ($('#galleryAdId').length>0)?parseInt($('#galleryAdId').val()):0;
    var advertiserId = ($('#galleryAdId').length>0)?parseInt($('#advertiserId').val()):null;
    var logoToken = getToken(ADV_IMG_TYPE._LOGO_TOKEN);

    var bgImgTokens =  getToken(ADV_IMG_TYPE._BACKGROUND_IMAGE);
    var topBannerImgTokens = getToken(ADV_IMG_TYPE._TOP_BANNER_TOKEN);
    var bottomBannerImgTokens = getToken(ADV_IMG_TYPE._BOTTOM_BANNER_TOKEN);

    var topBannerExpiryDate = $('#topBannerExpiryDate').data('daterangepicker').startDate.format("MM/DD/YYYY");
    var bottomBannerExpiryDate = $('#bottomBannerExpiryDate').data('daterangepicker').startDate.format("MM/DD/YYYY");

    var topBannerRotation = getRotationSetting(RotationSettings._GALLERY_TOP);
    var bottomBannerRotation = getRotationSetting(RotationSettings._GALLERY_BOTTOM);

    var galleryAdBgPrice = $('#galleryAdBgPrice').val();
    var galleryAdTopBannerPrice = $('#galleryAdTopBannerPrice').val();
    var galleryAdBottomBannerPrice = $('#galleryAdBottomBannerPrice').val();

    var data={};
    if(galleryId>0)data[prefix+"id"]= galleryId;

    data[prefix+"advertiserId"]= advertiserId;
    data[prefix+"logoToken"]= logoToken;
    data[prefix+"bgImgTokens"]= bgImgTokens;
    data[prefix+"topBannerImgTokens"]=topBannerImgTokens;
    data[prefix+"bottomBannerImgTokens"]=bottomBannerImgTokens;
    data[prefix+"topBannerExpiryDate"]= topBannerExpiryDate;
    data[prefix+"bottomBannerExpiryDate"]= bottomBannerExpiryDate;
    data[prefix+"topBannerRotation"]= topBannerRotation;
    data[prefix+"bottomBannerRotation"]= bottomBannerRotation;

    data[prefix+"bgPrice"]=galleryAdBgPrice;
    data[prefix+"topBannerPrice"] = galleryAdTopBannerPrice;
    data[prefix+"bottomBannerPrice"] = galleryAdBottomBannerPrice;


    return data;
}
/*Slideshow Ads */
function getSlideShowAdsData(prefix){
    if(prefix==undefined){
        prefix="";
    }else{
        prefix += "."
    }

    var slideShowAdsId = ($("#slideshowAdId").length>0)?$("#slideshowAdId").val():0;
    var slideShowAdsBannerTokens = getToken(ADV_IMG_TYPE._SLIDESHOW_BANNER_TOKEN);
    var slideShowAdsVideoToken = getToken(ADV_IMG_TYPE._SLIDESHOW_VIDEO_TOKEN);
    var slideShowBannerDuration = $("#slideShowBannerDuration").val();
    var slideShowVideoDuration =  $("#slideShowVideoDuration").val();

    var videoExpiryDate  =  $('#slideShowVideoExpiryDate').data('daterangepicker').startDate.format("MM/DD/YYYY");
    var bannerExpiryDate = $('#slideShowBannerExpiryDate').data('daterangepicker').startDate.format("MM/DD/YYYY");

    var bannerRotation = getRotationSetting(RotationSettings._SLIDE_SHOW_BANNER);
    var videoRotation = getRotationSetting(RotationSettings._SLIDE_SHOW_VIDEO);

    var bannerPrice = $('#slideshowAdBannerPrice').val();
    var videoPrice = $('#slideshowAdVideoPrice').val();


    var data = {};
    if(slideShowAdsId>0) data[prefix+"id"] = slideShowAdsId;

    data[prefix+"slideShowAdsBannerTokens"] = slideShowAdsBannerTokens;
    data[prefix+"slideShowAdsBannerTokens"] = slideShowAdsBannerTokens;
    data[prefix+"slideShowAdsVideoToken"] = slideShowAdsVideoToken;
    data[prefix+"slideShowBannerDuration"] = slideShowBannerDuration;
    data[prefix+"slideShowVideoDuration"] = slideShowVideoDuration;
    data[prefix+"videoExpiryDate"] = videoExpiryDate;
    data[prefix+"bannerExpiryDate"] = bannerExpiryDate;
    data[prefix+"bannerRotation"] = bannerRotation;
    data[prefix+"videoRotation"] = videoRotation;
    data[prefix+"bannerPrice"] = bannerPrice;
    data[prefix+"videoPrice"] = videoPrice;
    return data;
}
/*PopUp Ads */
function getPopUpAdsData(prefix){
    if(prefix==undefined){
        prefix="";
    }else{
        prefix += "."
    }
    var smsPopupId = $("#popupSmsAdId").length>0? parseInt($("#popupSmsAdId").val()):0;
    var smsPopupBanner = getToken(ADV_IMG_TYPE._SMS_POPUP_BANNER_TOKEN);
    var smsPopupVideo = getToken(ADV_IMG_TYPE._SMS_POPUP_VIDEO_TOKEN);
    var smsPopupVideoDuration = $("#smsPopupVideoDuration").val();
    var smsExpiryDate = $('#smsExpiryDate').data('daterangepicker').startDate.format("MM/DD/YYYY");

    var emailPopupId = $("#popupEmailAdId").length>0? parseInt($("#popupEmailAdId").val()):0;
    var emailPopupVideo = getToken(ADV_IMG_TYPE._EMAIL_POPUP_VIDEO_TOKEN);
    var emailPopupBanner = getToken(ADV_IMG_TYPE._EMAIL_POPUP_BANNER_TOKEN);
    var emailPopupVideoDuration = $("#emailPopupVideoDuration").val();
    var emailExpiryDate =  $('#emailExpiryDate').data('daterangepicker').startDate.format("MM/DD/YYYY");

    var smsRotation = getRotationSetting(RotationSettings._POP_UP_SMS);
    var emailRotation = getRotationSetting(RotationSettings._POP_UP_EMAIL);

    var smsAdPrice = $("#popUpAdSmsPrice").val();
    var emailAdPrice = $("#popUpAdEmailPrice").val();


    var data = {};
    if(smsPopupId>0)data[prefix+"smsId"]=smsPopupId;
    if(emailPopupId>0)data[prefix+"emailId"]=emailPopupId;

    data[prefix+"smsPopupBanner"]=smsPopupBanner;
    data[prefix+"smsPopupVideo"]= smsPopupVideo;
    data[prefix+"emailPopupBanner"]= emailPopupBanner;
    data[prefix+"emailPopupVideo"]= emailPopupVideo;
    data[prefix+"emailPopupVideoDuration"]= emailPopupVideoDuration;
    data[prefix+"smsPopupVideoDuration"]= smsPopupVideoDuration;
    data[prefix+"smsExpiryDate"]= smsExpiryDate;
    data[prefix+"emailExpiryDate"]= emailExpiryDate;
    data[prefix+"smsRotation"]= smsRotation;
    data[prefix+"emailRotation"]= emailRotation;
    data[prefix+"smsAdPrice"]= smsAdPrice;
    data[prefix+"emailAdPrice"]= emailAdPrice;

    return data;
}

/*Validation */
function validateAdvertiser(fnSuccess,fnError){
    var data = getAdvertiserInfoData();
    console.log(data);
    $.ajax({
        url: BASEURL+"api/pmc-advsr/validate-create",
        type: "POST",
        data: data,
        traditional:true,
        statusCode: {
            500: function(response) {
                console.log(response);
            },
            401: function(response) {
                console.log(response.responseJSON);
            },
            422: function(response) {
                console.log(response.responseJSON);

                if(typeof fnError == "function"){
                    fnError(response)
                }

            }
        },
        success: function(response) {
            if(typeof fnSuccess == "function"){
                fnSuccess(response)
            }

        }
    });
}
function validateGalleryAdds(forUpdate,fnSuccess,fnError){
    var url = "";
    if(forUpdate==undefined || forUpdate==false){
        url = BASEURL+"api/pmc-adv/gallery-create-validation";
    }else{
        url = BASEURL+"api/pmc-adv/gallery-update-validation";
    }
    var data = getGalleryAddsData();
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        traditional:true,
        statusCode: {
            500: function(response) {
                console.log(response);
            },
            401: function(response) {
                console.log(response.responseJSON);
            },
            422: function(response) {
                if(typeof fnError == "function"){
                    fnError(response);
                }
            }
        },
        success: function(response) {
            if(typeof fnSuccess == "function"){
                fnSuccess(response);
            }
        }
    });
}
function validateSlideShowAds(forUpdate,fnSuccess,fnError){
    var url="";
    if(forUpdate==undefined ||forUpdate==false){
        url = BASEURL+"api/pmc-adv/slideshow-create-validation";
    }else{
        url = BASEURL+"api/pmc-adv/slideshow-update-validation";
    }
    var data = getSlideShowAdsData();
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        traditional:true,
        statusCode: {
            500: function(response) {
                console.log(response);
            },
            401: function(response) {
                console.log(response.responseJSON);
            },
            422: function(response) {
                if(typeof fnError == "function"){
                    fnError(response);
                }

            }
        },
        success: function(response) {
            console.log(response);
            if(typeof fnSuccess == "function"){
                fnSuccess(response);
            }

        }
    });
}
function validatePopUpAdsData(forUpdate,fnSuccess,fnError) {
    var url = "";
    if(forUpdate==undefined || forUpdate==false){
        url = BASEURL+"api/pmc-adv/popup-create-validation";
    }else{
        url = BASEURL+"api/pmc-adv/popup-update-validation";
    }
    var data = getPopUpAdsData();
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        traditional:true,
        statusCode: {
            500: function(response) {
                console.log(response);
            },
            401: function(response) {
                console.log(response.responseJSON);
            },
            422: function(response) {
                if(typeof fnError == "function"){
                    fnError(response);
                }

            }
        },
        success: function(response) {
            if(typeof fnSuccess == "function"){
                fnSuccess(response);
            }

        }
    });
}

function hasAllLocationSelected(){
    var isChecked = $("#allLocationSelection:checked").length;
    return (isChecked==1)?true:false;
}
function hasAllEventSelected(){
    var isChecked = $("#allEventSelection:checked").length;
    return (isChecked==1)?true:false;
}

function submitLocationDataFromModal(){
    var name = $('#location_name').val();
    var address = $('#location_address').val();
    var stateId = $('#location_stateId').val();
    var zip = $('#location_zip').val();
    var phone = $('#location_phone').val();
    var locationLogo = $('#location_locationLogo').val();
    var durationSpeed = $('#location_durationSpeed').val();
    var breakTime = $('#location_breakTime').val();
    var fadeInTime = $('#location_fadeInTime').val();
    var fadeOutTime = $('#location_fadeOutTime').val();
    var logoImgToken = getVenueLogoToken();
    var bgTokens = getVenueBgImgTokens();
    var hasSlideshow = getSlideShowSettingsStatus();
    var data = {
        name: name,
        address: address,
        stateId: stateId,
        zip: zip,
        phone: phone,
        locationLogo: locationLogo,
        hasSlideshow: hasSlideshow,
        durationSpeed: durationSpeed,
        breakTime: breakTime,
        fadeInTime: fadeInTime,
        fadeOutTime: fadeOutTime,
        logoImgToken:logoImgToken,
        bgTokens:bgTokens
    };
    console.log(data);
    $.ajax({
        url: BASEURL+"api/location/create",
        type: "POST",
        data: data ,
        traditional:true,
        statusCode:{
            500: function(response) {
                console.log(response);
            }, 401: function(response) {
                console.log(response.responseJSON);
            }, 422: function(response) {
                BindErrorsWithHtml("errorObjLocation_",response.responseJSON,false,"#locationFormBody");
            }
        },
        success: function(response) {
            emptyVenueLogoToken();
            emptyBgImgTokens();
            $("#addLocation").find("input:text").val("");
            $("#addLocation").find("select").each(function(){

                $(this).val($(this).find("option:first").val());
            });
            var locationId = 0;
            var locationName = name;
            $('<option value="'+locationId+'">'+locationName+'</option>').appendTo("#locationIds");
            $("#addLocation").modal("hide");
        }
    });
}