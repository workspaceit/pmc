/**
 * Created by mi_rafi on 1/3/18.
 */
$(document).ready(function(){
    injectHiddenBgRemoveIdField();
});
function locationAfterSaveActionUpdate(btnAction){
    var id = $('#locationId').val();
    var urlStr ="";
    switch(btnAction){
        case "save":
            // urlStr = "admin/location/update/" + id;
            $.growl.notice({title: 'Success!', message: "Location updated"});
            console.log("here");
            return;
            break;
        case "save_and_close":
            urlStr = "admin/location/all";
            break;
        case "save_and_new":
            urlStr = "admin/location/add";
            break;
        case "cancel":
            urlStr = "admin/location/all";
            break;
    }
    window.location =BASEURL+urlStr;
}
function submitUpdatedData(btnAction){
    var id = $('#locationId').val();
    var name = $('#name').val();
    var address = $('#address').val();
    var stateId = $('#stateId').val();
    var cityId = $('#cityId').val();
    var zip = $('#zip').val();
    var phone = $('#phone').val();
    var locationLogo = $('#locationLogo').val();
    var durationSpeed = $('#durationSpeed').val();
    var breakTime = $('#breakTime').val();
    var fadeInTime = $('#fadeInTime').val();
    var fadeOutTime = $('#fadeOutTime').val();
    var removeBgIds = getVenueBgRemoveIds();
    var logoImgToken = getVenueLogoToken(); // function from js/location/common.js
    var bgTokens = getVenueBgImgTokens(); // function from js/location/common.js
    var hasSlideshow = getSlideShowSettingsStatus(); // function from js/location/common.js

    var data = {
        name: name,
        address: address,
        stateId: stateId,
        cityId: cityId,
        zip: zip,
        phone: phone,
        locationLogo: locationLogo,
        hasSlideshow: hasSlideshow,
        durationSpeed: durationSpeed,
        breakTime: breakTime,
        fadeInTime: fadeInTime,
        fadeOutTime: fadeOutTime,
        removeBgIds:removeBgIds,
        logoImgToken:logoImgToken,
        bgTokens:bgTokens
    };
    console.log(data);
    $.ajax({
        url: BASEURL+"api/location/update/"+id,
        type: "POST",
        data: data ,
        traditional:true,
        statusCode:{
            500: function(response) {
                console.log(response);
            }, 401: function(response) {
                console.log(response.responseJSON);
            }, 422: function(response) {
                BindErrorsWithHtml("errorObj_",response.responseJSON);
            }
        },
        success: function(response) {
            // notifyUpdateStatus(function(){
            //     locationAfterSaveActionUpdate(btnAction);
            // });
            locationAfterSaveActionUpdate(btnAction);
        }
    });
}
function injectHiddenBgRemoveIdField(){
    var venueBgRemoveIdsElement = $("<input>", {type:"hidden",id: "venueBgRemoveIds", "value": ""});

    $("body").append(venueBgRemoveIdsElement);
}
function getVenueBgRemoveIds(){
    var ids=[];
    try{
        var venueLogoTokenStr =  $("#venueBgRemoveIds").val().trim();
        ids = JSON.parse(venueLogoTokenStr==""?"[]":venueLogoTokenStr);

    }catch(ex) {
        console.log(ex);
        ids = [];
    }
    return ids;
}
function storeVenueBgRemoveIds(id){
    var ids = getVenueBgRemoveIds();
    if(ids.indexOf(id)<0){
        ids.push(id);
    }
    $("#venueBgRemoveIds").val(JSON.stringify(ids));
}
function removeVenueBgRemoveIds(id){
    var ids=getVenueBgRemoveIds();
    var index = ids.indexOf(id);
    if(index>0){
        ids.splice(index,1);
    }
}
function removeBgImage(id){
    $("#bgImg_div_"+id).hide();
    storeVenueBgRemoveIds(id);
}

