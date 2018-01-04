/**
 * Created by mi_rafi on 1/4/18.
 */
$(document).ready(function(){
    injectHiddenTokenFields();
});

var ADV_IMG_TYPE = {
    _LOGO_TOKEN :"logoToken",
    _BACKGROUND_IMAGE:"backgroundImageToken",
    _TOP_BANNER_TOKEN:"topBannerToken",
    _BOTTOM_BANNER_TOKEN:"bottomBannerToken",
    _SLIDESHOW_BANNER_TOKEN:"slideShowBannerToken",
     _SLIDESHOW_VIDEO_TOKEN:"slideShowVideoToken",
    _EMAIL_POPUP_VIDEO_TOKEN:"emailPopUpVideoToken",
    _SMS_POPUP_VIDEO_TOKEN:"smsPopUpVideo"
};
function injectHiddenTokenFields(){
    for(var index in ADV_IMG_TYPE){
        var tokenElem = $("<input>", {type:"hidden",id: ADV_IMG_TYPE[index], "value": ""});
        $("body").append(tokenElem);
    }
}
function getVenueBgImgTokens(elemId){
    var tokens=[];
    try{
        var venueLogoTokenStr =  $("#"+elemId).val().trim();
        tokens = JSON.parse(venueLogoTokenStr==""?"[]":venueLogoTokenStr);

    }catch(ex) {
        console.log(ex);
        tokens = [];
    }
    return tokens;
}
function storeVenueBgImgToken(elemId,token){
    var tokens = getVenueBgImgTokens(elemId);
    if(tokens.indexOf(token)<0){
        tokens.push(token);
    }
    $("#"+elemId).val(JSON.stringify(tokens));
}
function removeBgImgTokens(elemId,token){
    var tokens=getVenueBgImgTokens(elemId);
    var index = tokens.indexOf(token);
    if(index>0){
        tokens.splice(index,1);
    }
    $("#"+elemId).val(JSON.stringify(tokens));
}