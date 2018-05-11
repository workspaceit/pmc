/**
 * Created by mi_rafi on 1/10/18.
 * Function used storeToken,getToken from /js/tmp-file/common.js
 * /js/pmc-adv/common.js is required
 * /resources/developer/js/helper/others.js is required
 */

if(!isScriptLoadedAtPage("/developer/js/pmc-adv/common.js")){
    console.log("/js/pmc-adv/common.js is reuired")
}

var ID_KEY = {
    _ADV_OTHER_IMAGE : "_ADV_OTHER_IMAGE",
    _GALLERY_TOP_BANNER : "_GALLERY_TOP_BANNER",
    _GALLERY_BOTTOM_BANNER : "_GALLERY_BOTTOM_BANNER",
    _SLIDE_SHOW_BANNER : "_SLIDE_SHOW_BANNER",
    _POPUP_SMS_BANNER : "_POPUP_SMS_BANNER",
    _POPUP_EMAIL_BANNER : "_POPUP_EMAIL_BANNER"
};
function addIdToRemove(elem,key,id){
    $(elem).parent().hide();
    storeToken(key,id);
}
function getIdToRemove(key){
    return getToken(key);
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
    galleryAdsData["removeTopBannerIds"]= getToken(ID_KEY._GALLERY_TOP_BANNER);
    galleryAdsData["removeBottomBannerIds"]= getToken(ID_KEY._GALLERY_BOTTOM_BANNER);
    slideShowAdsData["removeBannerIds"]= getToken(ID_KEY._SLIDE_SHOW_BANNER);

    popupAdsData["removeSmsBannerIds"]= getToken(ID_KEY._POPUP_SMS_BANNER);
    popupAdsData["removeEmailBannerIds"]= getToken(ID_KEY._POPUP_EMAIL_BANNER);


    data.advertiser = advertiserData;
    data.galleryAds = galleryAdsData;
    data.popupAds = popupAdsData;
    data.slideShowAds = slideShowAdsData;
    data.urlsUpdate = getToken(PREFIX._IMAGE_UPDATE_URL);

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
function addImageUrlForUpdate(id){
    var key = PREFIX._IMAGE_UPDATE_URL+id;
    var url =$("#"+key).val();
    var allToken = getToken(PREFIX._IMAGE_UPDATE_URL);
    console.log(allToken);
    /**
     * Carry unique object
     * Uniqueness defined by Id
     * */
    for(var i=0;i<allToken.length;i++){
        if( allToken[i].id === id){
            allToken.splice(i,1);
        }
    }
    emptyToken(PREFIX._IMAGE_UPDATE_URL);
    for(var i=0;i<allToken.length;i++){
        storeToken( PREFIX._IMAGE_UPDATE_URL,allToken[i]);
    }

    var urlObj = {id:id,url:url};
    storeToken( PREFIX._IMAGE_UPDATE_URL,urlObj);

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
});