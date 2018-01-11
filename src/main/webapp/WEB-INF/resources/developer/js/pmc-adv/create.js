/**
 * Created by mi_rafi on 1/5/18.
 */




function validateAll(){

    validateAdvertiser();
    validateGalleryAdds();
    validateSlideShowAds();
    validatePopUpAdsData();
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
            advertiserAfterSaveAction(globalBtnAction);
        }
    });
}