/**
 * Created by mi_rafi on 1/5/18.
 */
var errorFound = false;
function notifyUser(id,response,errorFound){
    if(errorFound){
        $("#"+id).html(response.responseJSON.length).show();
    }else{
        $("#"+id).html(0).hide();
    }
}
function submit(marker){
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
                createAdvertiser();
            break;
    }
}
function validateAll(){

    validateAdvertiser();
    validateGalleryAdds();
    validateSlideShowAds();
    validatePopUpAdsData();
}
function initSubmit(){
    UnBindErrors("errorObj_");
    errorFound = false;
    submit(1);
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
    var otherImage = $('#otherImage').val();
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
                submit(2);
            }
        },
        success: function(response) {
            notifyUser("advertiserInfoErrorCount",response,false);
            submit(2);
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
                submit(3);
            }
        },
        success: function(response) {
            notifyUser("galleryAdsErrorCount",response,false);
            submit(3);
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
                submit(4);
            }
        },
        success: function(response) {
            console.log(response);
            notifyUser("slideShowAdsErrorCount",response,false);
            submit(4);
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
                submit(5);
            }
        },
        success: function(response) {
            notifyUser("popUpAdsErrorCount",response,false);
            submit(5);
        }
    });
}

/*Create advertiser and other adds */
function createAdvertiser(){
    var data = {};
    var advertiserData = getAdvertiserInfoData("advertiser");
    var galleryAdsData =getGalleryAddsData("galleryAds");
    var popupAdsData =getPopUpAdsData("popupAds");
    var slideShowAdsData =getSlideShowAdsData("slideShowAds");
    data = $.extend({}, data, advertiserData);
    data = $.extend({}, data,galleryAdsData);
    data = $.extend({}, data,popupAdsData);
    data = $.extend({}, data, slideShowAdsData);

    console.log(data);
    $.ajax({
        url: BASEURL+"api/pmc-advsr/create-all",
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
                console.log("Error from Create");
                console.log(response);
                validateAll();
            }
        },
        success: function(response) {
            notifyUser("advertiserInfoErrorCount",response,false);
            window.location=BASEURL+"admin/advertiser/all";
        }
    });
}




$(function() {
    /**Advertiser Info Date picker*/
    $('#runtimeStarts').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true
    },function(start, end, label) {
        //var years = moment().diff(start, 'years');
        // alert("You are " + years + " years old.");
    });

    $('#runtimeEnds').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true
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
        var isChecked = $("#allLocationSelection:checked").length;
        if(isChecked){
            $("#locationIds").parent().hide();
        }else{
            $("#locationIds").parent().show();
        }
    });
    $("#allEventSelection").click(function(){
        var isChecked = $("#allEventSelection:checked").length;
        if(isChecked){
            $("#eventIds").parent().hide();
        }else{
            $("#eventIds").parent().show();
        }
    });


});
function hasAllLocationSelected(){
    var isChecked = $("#allLocationSelection:checked").length;
    return (isChecked==1)?true:false;
}
function hasAllEventSelected(){
    var isChecked = $("#allEventSelection:checked").length;
    return (isChecked==1)?true:false;
}