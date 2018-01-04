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
function getToken(elemId){
    var tokens=[];
    try{
        var tokenStr =  $("#"+elemId).val().trim();
        tokens = JSON.parse(tokenStr==""?"[]":tokenStr);

    }catch(ex) {
        console.log(ex);
        tokens = [];
    }
    return tokens;
}
function storeToken(elemId,token){
    var tokens = getToken(elemId);
    if(tokens.indexOf(token)<0){
        tokens.push(token);
    }
    $("#"+elemId).val(JSON.stringify(tokens));
}
function removeToken(elemId,token){
    var tokens=getToken(elemId);
    var index = tokens.indexOf(token);
    if(index>0){
        tokens.splice(index,1);
    }
    $("#"+elemId).val(JSON.stringify(tokens));
}