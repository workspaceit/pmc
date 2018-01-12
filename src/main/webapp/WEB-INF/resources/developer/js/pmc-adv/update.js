/**
 * Created by mi_rafi on 1/10/18.
 * Function used storeToken,getToken from /js/tmp-file/common.js
 */
var ID_KEY = {
    _ADV_OTHER_IMAGE : "_ADV_OTHER_IMAGE",
    _GALLERY_TOP_BANNER : "_GALLERY_TOP_BANNER",
    _GALLERY_BOTTOM_BANNER : "_GALLERY_BOTTOM_BANNER",
    _SLIDE_SHOW_BANNER : "_SLIDE_SHOW_BANNER",
    _POPUP_SMS_BANNER : "_POPUP_SMS_BANNER",
    _POPUP_EMAIL_BANNER : "_POPUP_EMAIL_BANNER"
};
function addIdToRemove(elem,key,id){
    $(elem).parent().find(".img-thumbnail").hide();
    $(elem).hide();
    storeToken(key,id);
}
function getIdToRemove(key){
    return getToken(key);
}




/*Create advertiser and other adds */
function updateAdvertiser(){
    var id  = parseInt($("#advertiserId").val());
    var data = {};
    var advertiserData = getAdvertiserInfoData("advertiser");
    var galleryAdsData =getGalleryAddsData("galleryAds");
    var popupAdsData =getPopUpAdsData("popupAds");
    var slideShowAdsData =getSlideShowAdsData("slideShowAds");


    advertiserData["advertiser.removeOtherImageIds"]= getToken(ID_KEY._ADV_OTHER_IMAGE);

    data = $.extend({}, data, advertiserData);
    data = $.extend({}, data,galleryAdsData);
    data = $.extend({}, data,popupAdsData);
    data = $.extend({}, data, slideShowAdsData);

    console.log(data);
    $.ajax({
        url: BASEURL+"api/pmc-advsr/update-all/"+id,
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
            advertiserAfterSaveAction(globalBtnAction);
        }
    });
}