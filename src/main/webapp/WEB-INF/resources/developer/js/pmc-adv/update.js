/**
 * Created by mi_rafi on 1/10/18.
 * Function used storeToken,getToken from /js/tmp-file/common.js
 * /js/pmc-adv/common.js is required
 * /resources/developer/js/helper/others.js is required
 */

if(!isScriptLoadedAtPage("/developer/js/pmc-adv/common.js")){
    console.log("/js/pmc-adv/common.js is required")
}

var ID_KEY = {
    _ADV_OTHER_IMAGE : "_ADV_OTHER_IMAGE",
    _GALLERY_LOGO : "_GALLERY_LOGO",
    _GALLERY_BACKGROUND : "_GALLERY_BACKGROUND",
    _GALLERY_TOP_BANNER : "_GALLERY_TOP_BANNER",
    _GALLERY_BOTTOM_BANNER : "_GALLERY_BOTTOM_BANNER",
    _SLIDE_SHOW_BANNER : "_SLIDE_SHOW_BANNER",
    _POPUP_SMS_BANNER : "_POPUP_SMS_BANNER",
    _POPUP_EMAIL_BANNER : "_POPUP_EMAIL_BANNER"
};

var SECTION_TYPE = {
    LOGO:"LOGO",
    BACKGROUND:"BACKGROUND",
    BANNER:"BANNER",
    TOP_BANNER:"TOP_BANNER",
    BOTTOM_BANNER:"BOTTOM_BANNER",
    VIDEO:"VIDEO"
};
function addIdToRemove(elem,key,id){
    $(elem).parent().hide().remove();
    storeToken(ID_KEY[key],id);
}
function getIdToRemove(key){
    return getToken(key);
}
function getSectionResourceForUpdate(id){
    var secResources = [];
    $("#"+id).find(".section-resource").find(".sec-res-url").each(function(){
        var secRes = {id:$(this).data("sec-res-id")};
        secRes.url = $(this).val();
        secResources.push(secRes);

    });
    $("#"+id).find(".section-resource").find(".static-selector").each(function(){
        var id = $(this).data("sec-res-id");
        var selectedStatic = $(this).is(":checked");

        for(var i=0;i<secResources.length;i++){
            var secRes = secResources[i];
            if(secRes.id === id){
                secRes.selectedStatic = selectedStatic;
                break;
            }
        }
    });
    return secResources;
}



/*Create advertiser and other adds */
function updateAdvertiser(){
    var id  = parseInt($("#advertiserId").val());
    var data = {advertiser:{}, galleryAds:{}, popupAds:{}, slideShowAds:{},urlsUpdate:[]};


    var advertiserData = getAdvertiserInfoData();
    var galleryAdsData =getGalleryAddsData();
    var popupAdsData =getPopUpAdsData();
    var slideShowAdsData =getSlideShowAdsData();

    advertiserData["removeOtherImageIds"]= getToken(ID_KEY._ADV_OTHER_IMAGE);

    galleryAdsData["removeLogoId"]= (getToken(ID_KEY._GALLERY_LOGO).length>0)?getToken(ID_KEY._GALLERY_LOGO)[0]:null;
    galleryAdsData["removeBackgroundIds"]= getToken(ID_KEY._GALLERY_BACKGROUND);
    galleryAdsData["removeTopBannerIds"]= getToken(ID_KEY._GALLERY_TOP_BANNER);
    galleryAdsData["removeBottomBannerIds"]= getToken(ID_KEY._GALLERY_BOTTOM_BANNER);

    slideShowAdsData["removeBannerIds"]= getToken(ID_KEY._SLIDE_SHOW_BANNER);

    popupAdsData["removeSmsBannerIds"]= getToken(ID_KEY._POPUP_SMS_BANNER);
    popupAdsData["removeEmailBannerIds"]= getToken(ID_KEY._POPUP_EMAIL_BANNER);


    data.advertiser = advertiserData;
    data.galleryAds = galleryAdsData;
    data.popupAds = popupAdsData;
    data.slideShowAds = slideShowAdsData;


    var gallerySection={};
    gallerySection[SECTION_TYPE.LOGO]= getSectionResourceForUpdate("galleryLogoSectionResource");
    gallerySection[SECTION_TYPE.BACKGROUND]= getSectionResourceForUpdate("galleryBackgroundSectionResource");
    gallerySection[SECTION_TYPE.TOP_BANNER]= getSectionResourceForUpdate("galleryTopSectionResource");
    gallerySection[SECTION_TYPE.BOTTOM_BANNER]= getSectionResourceForUpdate("galleryBottomSectionResource");


    var slideShowSection={};
    slideShowSection[SECTION_TYPE.BANNER]= getSectionResourceForUpdate("slideShowBannerSectionResource");

    var popUpSmsSection ={};
    popUpSmsSection[SECTION_TYPE.BANNER] = getSectionResourceForUpdate("popUpSmsBannerSectionResource");

    var popUpEmailSection= {};
    popUpEmailSection[SECTION_TYPE.BANNER] =  getSectionResourceForUpdate("popUpEmailBannerSectionResource");

    data.galleryAds.updateSectionResources = gallerySection;

    data.slideShowAds.updateSectionResources = slideShowSection;

    data.popupAds.updateSmsSectionResources = popUpSmsSection;
    data.popupAds.updateEmailSectionResources = popUpEmailSection;

    console.log(data);
    $.ajax({
        url: BASEURL+"api/pmc-advsr/update-all/"+id,
        type: "POST",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(data),
        statusCode: {
            500: function(response) {
                console.log(response);
            },
            401: function(response) {
                console.log(response.responseJSON);
            },
            422: function(response) {
                notifyUser("advertiserInfoErrorCount",response,false);
                console.log(response);
            }
        },
        success: function(response) {
            notifyUser("advertiserInfoErrorCount",response,false);
            advertiserAfterSaveAction(globalBtnAction,id);
        }
    });
}
function redirectToAdvPreview(id,type){
     var frontEndBaseUrl = $("#frontEndBaseUrl").val();
     var uriWithQueryParam = "/user-panel";
     if(type=="gallery"){
         uriWithQueryParam += "/gallery?galleryId="+id;
     } else if(type=="popup"){
         uriWithQueryParam += "/gallery?popUpId="+id;
     }else if(type=="slideshow"){
         /**
          * Main Slide show
          * */
         uriWithQueryParam += "/slideshow?pmcadv="+id;
     }
    window.open(frontEndBaseUrl+uriWithQueryParam, '_blank');
}
function getAdIdBySelectedSectionTab(){
    var galleryTab =  $("#tab_default_2").hasClass("active");
    var slideshowTab = $("#tab_default_3").hasClass("active");
    var popupTab = $("#tab_default_4").hasClass("active");

}
function loadCheckoutInModal(){

    $("#checkout-content").modal("show");
    var id  = parseInt($("#advertiserId").val());
    $.ajax({
        url: BASEURL+"admin/invoice/checkout/ajax/"+id,
        type: "GET",
        statusCode: {
            500: function(response) {
                console.log(response);
            },
            401: function(response) {
                console.log(response.responseJSON);
            },
            422: function(response) {
                notifyUser("advertiserInfoErrorCount",response,false);
                console.log(response);
            }
        },
        success: function(response) {
            $("#checkout-body").html(response);
        }
    });
}
function hideCheckoutInModal(){
    $("#checkout-content").modal("hide");
}
function loadSendInvoiceModal(){
    hideCheckoutInModal();
    $("#invoice-mail-send").modal("show");
}
function hideSendInvoiceModal(){
    $("#invoice-mail-send").modal("hide");
}
function printOrDownloadInvoice() {
    var id = parseInt($("#advertiserId").val());
    var w = window.open(BASEURL + "admin/invoice/checkout/page/" + id);
    w.print();

}
function sendInvoiceToEmail(){
    var id = parseInt($("#advertiserId").val());
    var email = $("#invoiceRecipientEmail").val();

    $.ajax({
        url: BASEURL+"api/invoice/mail/" + id,
        type: "POST",
        data: {"email":email},
        traditional:true,
        statusCode: {
            500: function(response) {
                console.log(response);
            },
            401: function(response) {
                console.log(response.responseJSON);
            },
            422: function(response) {

            }
        },
        success: function(response) {
            hideSendInvoiceModal();
        }
    });
}
function addSectionResourceForUpdate(secResId,key){
    /*
    var id = PREFIX.SEC_RES.URL._UPDATE+secResId;
    var url =$("#"+id).val();
    var selectedStatic = isStaticSelectorSelectedCheckBySecResId(secResId);
    var allToken = getToken(ID_KEY[key]);

    /!**
     * Carry unique object
     * Uniqueness defined by Id
     * *!/
    for(var i=0;i<allToken.length;i++){
        if( allToken[i].id === secResId){
            allToken.splice(i,1);
        }
    }
    emptyToken(ID_KEY[key]);
    for(var i=0;i<allToken.length;i++){
        allToken[i].selectedStatic = isStaticSelectorSelectedCheckBySecResId(allToken[i].id);
        storeToken( ID_KEY[key],allToken[i]);
    }

    var urlObj = {id:secResId,url:url,selectedStatic:selectedStatic};
    storeToken( ID_KEY[key],urlObj);*/

}


$(document).ready(function(){
    /**
     * Get Cities by state
     * */
    var stateId = $("#stateId").val();
    fetchCityOnStateChange(stateId,function(response) {
        var selectedCityId = $("#advertiserCityId").val();
        $("#cityId").html("");
        $(response).each(function( key, value ){

            var selectedHtml = (selectedCityId == value.id)?"selected='selected'":"";
            var html = "<option value='"+value.id+"' "+selectedHtml+">"+value.name+"</option>";
            $("#cityId").append(html);
        });
    },'locationFormBody');


    /**
     * Show static selected radio show
     * */
    for(var key in RotationSettings){
        var id = RotationSettings[key];
        var isRotationStatic = isRotationSettingsStatic(id);
        if(isRotationStatic){

            showStaticSectionRadio(id,true);
        }
    }
});