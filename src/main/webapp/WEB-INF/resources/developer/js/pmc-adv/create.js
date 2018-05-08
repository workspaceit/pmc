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
function createAdvertiser(fnSuccess){
    var data = {advertiser:{}, galleryAds:{}, popupAds:{}, slideShowAds:{}};
    var advertiserData = getAdvertiserInfoData();
    var galleryAdsData =getGalleryAddsData();
    var popupAdsData =getPopUpAdsData();
    var slideShowAdsData =getSlideShowAdsData();

    data.advertiser = advertiserData;
    data.galleryAds = galleryAdsData;
    data.popupAds = popupAdsData;
    data.slideShowAds = slideShowAdsData;

    console.log(data);
    $.ajax({
        url: BASEURL+"api/pmc-advsr/create-all",
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
                console.log("Error from Create");
                console.log(response);
                validateAll();
            }
        },
        success: function(response) {
            console.log(response);
            if(typeof fnSuccess=="function"){
                fnSuccess(response);
            }
        }
    });
}

$(document).ready(function(){
    /**
     * Get Cities by state
     * */
    $("#stateId").trigger("change");
});