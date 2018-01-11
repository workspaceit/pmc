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
function updateAdvertiser(){

}