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
function submitAdvertiserData(submitType,forUpdate,marker){
    switch (marker){
        case 1:
            validateAdvertiser(function(response){
                notifyUser("advertiserInfoErrorCount",response,false);
                submitAdvertiserData(submitType,forUpdate,2);
            },function(response){
                BindErrorsWithHtml("errorObj_",response.responseJSON,true);
                notifyUser("advertiserInfoErrorCount",response,true);
                errorFound = true;
                submitAdvertiserData(submitType,forUpdate,2);
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
                   advertiserAfterSaveAction(globalBtnAction);
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
    var advertiserId = $('#advertiserId').val();
    var logoToken = getToken(ADV_IMG_TYPE._LOGO_TOKEN);

    var bgImgTokens =  getToken(ADV_IMG_TYPE._BACKGROUND_IMAGE);
    var topBannerImgTokens = getToken(ADV_IMG_TYPE._TOP_BANNER_TOKEN);
    var bottomBannerImgTokens = getToken(ADV_IMG_TYPE._BOTTOM_BANNER_TOKEN);

    var topBannerExpiryDate = $('#topBannerExpiryDate').data('daterangepicker').startDate.format("MM/DD/YYYY");
    var bottomBannerExpiryDate = $('#bottomBannerExpiryDate').data('daterangepicker').startDate.format("MM/DD/YYYY");

    var slideShowVideoDuration = $('#slideShowVideoDuration').val();

    var data={};
    if(galleryId>0)data[prefix+"id"]= galleryId;

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

    var data = {};
    if(slideShowAdsId>0) data[prefix+"id"] = slideShowAdsId;

    data[prefix+"slideShowAdsBannerTokens"] = slideShowAdsBannerTokens;
    data[prefix+"slideShowAdsBannerTokens"] = slideShowAdsBannerTokens;
    data[prefix+"slideShowAdsVideoToken"] = slideShowAdsVideoToken;
    data[prefix+"slideShowBannerDuration"] = slideShowBannerDuration;
    data[prefix+"slideShowVideoDuration"] = slideShowVideoDuration;
    data[prefix+"videoExpiryDate"] = videoExpiryDate;
    data[prefix+"bannerExpiryDate"] = bannerExpiryDate;

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