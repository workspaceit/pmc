/**
 * Created by mi_rafi on 1/4/18.
 */
var errorFound = false;
var globalBtnAction = "";
var globalSubmitAction = "";
function notifyUser(id,response,errorFound){
    if(errorFound){
        $("#"+id).html(response.responseJSON.length).show();
    }else{
        $("#"+id).html(0).hide();
    }
}
$(document).ready(function(){
    injectHiddenTokenFieldsForAdvertiser();
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
function injectHiddenTokenFieldsForAdvertiser(){
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

    console.log("RUNTIME");

    /**Advertiser Info Date picker*/
    var runtimeStartsDate = ( $('#runtimeStarts').val().trim()=="" )?moment().format('MM/D/YYYY'):$('#runtimeStarts').val();
    var runtimeEndsStartDate = ( $('#runtimeEnds').val().trim()=="" )?moment().format('MM/D/YYYY'):$('#runtimeEnds').val();
    $('#runtimeStarts').daterangepicker({
        locale: {
            format: 'DD/MM/YYYY'
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
            format: 'DD/MM/YYYY'
        },
        singleDatePicker: true,
        showDropdowns: true,
        startDate:runtimeEndsStartDate
    },function(start, end, label) {
        /*var years = moment().diff(start, 'years');
         alert("You are " + years + " years old.");*/
    });
    /**Gallery ads Date picker*/
    $('#topBannerExpiryDate').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true
    },function(start, end, label) {
        /*var years = moment().diff(start, 'years');
         alert("You are " + years + " years old.");*/
    });
    $('#bottomBannerExpiryDate').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true
    },function(start, end, label) {
        /*var years = moment().diff(start, 'years');
         alert("You are " + years + " years old.");*/
    });
    /*Popup ads*/
    $('#smsExpiryDate').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true
    },function(start, end, label) {
        /*var years = moment().diff(start, 'years');
         alert("You are " + years + " years old.");*/
    });
    $('#emailExpiryDate').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true
    },function(start, end, label) {
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

function initSubmitAdvertiserData(btnAction,submitAction){
    globalBtnAction=btnAction;
    globalSubmitAction = submitAction;
    UnBindErrors("errorObj_");
    errorFound = false;
    submitAdvertiserData(1);
}
function advertiserAfterSaveAction(btnAction){
    var urlStr ="";
    switch(btnAction){
        case "save":
            urlStr = "admin/advertiser/all";
            break;
        case "save_and_close":
            urlStr = "admin/advertiser/all";
            break;
        case "save_and_new":
            urlStr = "admin/advertiser/add";
            break;
        case "cancel":
            urlStr = "admin/advertiser/all";
            break;
    }
    window.location =BASEURL+urlStr;
}
function submitAdvertiserData(marker){
    switch (marker){
        case 1:
            validateAdvertiser();
            break;
        case 2:
            validateGalleryAdds();
            break;
        case 3:
            validateSlideShowAds();
            break;
        case 4:
            validatePopUpAdsData();
            break;
        case 5:
            if(!errorFound)
                submitCreateOrUpdate();
            break;
    }
}
function submitCreateOrUpdate(){
       switch (globalSubmitAction){
           case "create":
               createAdvertiser();
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
    var runtimeStarts = $('#runtimeStarts').val();
    var runtimeEnds = $('#runtimeEnds').val();
    var locationIds = $('#locationIds').val();
    var createdAt = $('#createdAt').val();
    var updatedAt = $('#updatedAt').val();
    var createdBy = $('#createdBy').val();
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
function validateAdvertiser(){
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
                BindErrorsWithHtml("errorObj_",response.responseJSON,true);
                notifyUser("advertiserInfoErrorCount",response,true);
                errorFound = true;
                submitAdvertiserData(2);
            }
        },
        success: function(response) {
            notifyUser("advertiserInfoErrorCount",response,false);
            submitAdvertiserData(2);
        }
    });
}
/*Gallery Ads */
function getGalleryAddsData(prefix){
    if(prefix==undefined){
        prefix = "";
    }else{
        prefix += "."
    }
    var advertiserId = $('#advertiserId').val();
    var logoToken = getToken(ADV_IMG_TYPE._LOGO_TOKEN);

    var bgImgTokens =  getToken(ADV_IMG_TYPE._BACKGROUND_IMAGE);
    var topBannerImgTokens = getToken(ADV_IMG_TYPE._TOP_BANNER_TOKEN);
    var bottomBannerImgTokens = getToken(ADV_IMG_TYPE._BOTTOM_BANNER_TOKEN);

    var topBannerExpiryDate = $('#topBannerExpiryDate').val();
    var bottomBannerExpiryDate = $('#bottomBannerExpiryDate').val();
    var slideShowVideoDuration = $('#slideShowVideoDuration').val();

    var data={};

    data[prefix+"advertiserId"]= advertiserId;
    data[prefix+"logoToken"]= logoToken;
    data[prefix+"bgImgTokens"]= bgImgTokens;
    data[prefix+"topBannerImgTokens"]=topBannerImgTokens;
    data[prefix+"bottomBannerImgTokens"]=bottomBannerImgTokens;
    data[prefix+"topBannerExpiryDate"]= topBannerExpiryDate;
    data[prefix+"bottomBannerExpiryDate"]= bottomBannerExpiryDate;
    data[prefix+"slideShowVideoDuration"] = slideShowVideoDuration;
    return data;
}
function validateGalleryAdds(){
    var data = getGalleryAddsData();
    $.ajax({
        url: BASEURL+"api/pmc-adv/gallery-create-validation",
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
                BindErrorsWithHtml("errorObj_",response.responseJSON,true);
                notifyUser("galleryAdsErrorCount",response,true);

                errorFound = true;
                submitAdvertiserData(3);
            }
        },
        success: function(response) {
            notifyUser("galleryAdsErrorCount",response,false);
            submitAdvertiserData(3);
        }
    });
}
/*Slideshow Ads */
function getSlideShowAdsData(prefix){
    if(prefix==undefined){
        prefix="";
    }else{
        prefix += "."
    }

    var slideShowAdsBannerTokens = getToken(ADV_IMG_TYPE._SLIDESHOW_BANNER_TOKEN);
    var slideShowAdsVideoToken = getToken(ADV_IMG_TYPE._SLIDESHOW_VIDEO_TOKEN);
    var slideShowBannerDuration = $("#slideShowBannerDuration").val();
    var slideShowVideoDuration =  $("#slideShowVideoDuration").val();

    var data = {};
    data[prefix+"slideShowAdsBannerTokens"] = slideShowAdsBannerTokens;
    data[prefix+"slideShowAdsVideoToken"] = slideShowAdsVideoToken;
    data[prefix+"slideShowBannerDuration"] = slideShowBannerDuration;
    data[prefix+"slideShowVideoDuration"] = slideShowVideoDuration;
    return data;
}
function validateSlideShowAds(){
    var data = getSlideShowAdsData();
    $.ajax({
        url: BASEURL+"api/pmc-adv/slideshow-create-validation",
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
                BindErrorsWithHtml("errorObj_",response.responseJSON,true);
                notifyUser("slideShowAdsErrorCount",response,true);
                errorFound = true;
                submitAdvertiserData(4);
            }
        },
        success: function(response) {
            console.log(response);
            notifyUser("slideShowAdsErrorCount",response,false);
            submitAdvertiserData(4);
        }
    });
}
/*PopUp Ads */
function getPopUpAdsData(prefix){
    if(prefix==undefined){
        prefix="";
    }else{
        prefix += "."
    }
    var smsPopupBanner = getToken(ADV_IMG_TYPE._SMS_POPUP_BANNER_TOKEN);
    var smsPopupVideo = getToken(ADV_IMG_TYPE._SMS_POPUP_VIDEO_TOKEN);
    var emailPopupBanner = getToken(ADV_IMG_TYPE._EMAIL_POPUP_BANNER_TOKEN);
    var emailPopupVideo = getToken(ADV_IMG_TYPE._EMAIL_POPUP_VIDEO_TOKEN);
    var emailPopupVideoDuration = $("#emailPopupVideoDuration").val();
    var smsPopupVideoDuration = $("#smsPopupVideoDuration").val();
    var smsExpiryDate = $("#smsExpiryDate").val();
    var emailExpiryDate = $("#emailExpiryDate").val();

    var data = {};
    data[prefix+"smsPopupBanner"]=smsPopupBanner;
    data[prefix+"smsPopupVideo"]= smsPopupVideo;
    data[prefix+"emailPopupBanner"]= emailPopupBanner;
    data[prefix+"emailPopupVideo"]= emailPopupVideo;
    data[prefix+"emailPopupVideoDuration"]= emailPopupVideoDuration;
    data[prefix+"smsPopupVideoDuration"]= smsPopupVideoDuration;

    data[prefix+"smsExpiryDate"]= smsExpiryDate;
    data[prefix+"emailExpiryDate"]= emailExpiryDate;
    return data;
}
function validatePopUpAdsData() {
    var data = getPopUpAdsData();
    $.ajax({
        url: BASEURL+"api/pmc-adv/popup-create-validation",
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
                BindErrorsWithHtml("errorObj_",response.responseJSON,true);
                notifyUser("popUpAdsErrorCount",response,true);
                errorFound = true;
                submitAdvertiserData(5);
            }
        },
        success: function(response) {
            notifyUser("popUpAdsErrorCount",response,false);
            submitAdvertiserData(5);
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