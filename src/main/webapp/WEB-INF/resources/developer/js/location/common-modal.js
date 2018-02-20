ifNotScriptLoadedAtPageThrowException('/developer/js/location/common.js','location/common-modal.js');
ifNotScriptLoadedAtPageThrowException('/developer/js/temp-file/common.js','location/common-modal.js');

$('#locationModalSaveBtn').on("click",function(){
    submitLocationDataFromModal();
});

function overrideSubmitLocation(afterSuccessFn){
    $('#locationModalSaveBtn').unbind("click");
    $('#locationModalSaveBtn').on("click",function(){
        submitLocationDataFromModal(afterSuccessFn);
    });
}

function submitLocationDataFromModal(afterSuccessFn){
    var name = $('#location_name').val();
    var address = $('#location_address').val();
    var stateId = $('#location_stateId').val();
    var cityId = $('#location_cityId').val();
    var zip = $('#location_zip').val();
    var phone = $('#location_phone').val();
    var locationLogo = $('#location_locationLogo').val();
    var durationSpeed = $('#location_durationSpeed').val();
    var breakTime = $('#location_breakTime').val();
    var fadeInTime = $('#location_fadeInTime').val();
    var fadeOutTime = $('#location_fadeOutTime').val();
    var logoImgToken = getVenueLogoToken();
    var bgTokens = getVenueBgImgTokens();
    var hasSlideshow = getSlideShowSettingsStatus();
    var data = {
        name: name,
        address: address,
        stateId: stateId,
        cityId:cityId,
        zip: zip,
        phone: phone,
        locationLogo: locationLogo,
        hasSlideshow: hasSlideshow,
        durationSpeed: durationSpeed,
        breakTime: breakTime,
        fadeInTime: fadeInTime,
        fadeOutTime: fadeOutTime,
        logoImgToken:logoImgToken,
        bgTokens:bgTokens
    };
    console.log(data);
    $.ajax({
        url: BASEURL+"api/location/create",
        type: "POST",
        data: data ,
        traditional:true,
        statusCode:{
            500: function(response) {
                console.log(response);
            }, 401: function(response) {
                console.log(response.responseJSON);
            }, 422: function(response) {
                BindErrorsWithHtml("errorObjLocation_",response.responseJSON,false,"#locationFormBody");
            }
        },
        success: function(response) {
            $("#addLocation").modal("hide");

            emptyVenueLogoToken();
            emptyBgImgTokens();
            $("#addLocation").find("input:text,:input[type=number]").val("");
            $("#addLocation").find("select").each(function(){
                $(this).val($(this).find("option:first").val());
            });

            GLOBAL_venueLogoImgDropZone.removeAllFiles();
            GLOBAL_venueBgImgDropZone.removeAllFiles();
            BindErrorsWithHtml("errorObjLocation_",response.responseJSON,false,"#locationFormBody");

            if(afterSuccessFn!= undefined && typeof  afterSuccessFn == 'function'){
                afterSuccessFn(response);
            }else{

                var locationId = response.id;
                var locationName = response.name;
                $('<option value="'+locationId+'">'+locationName+'</option>').appendTo("#locationIds");
            }
        }
    });
}