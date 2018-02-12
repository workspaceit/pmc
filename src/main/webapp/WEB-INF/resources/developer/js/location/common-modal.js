ifNotScriptLoadedAtPageThrowException('/developer/js/location/common.js','location/common-modal.js');
ifNotScriptLoadedAtPageThrowException('/developer/js/temp-file/common.js','location/common-modal.js');


function submitLocationDataFromModal(afterSuccessFn){
    var name = $('#location_name').val();
    var address = $('#location_address').val();
    var stateId = $('#location_stateId').val();
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
            emptyVenueLogoToken();
            emptyBgImgTokens();
            $("#addLocation").find("input:text").val("");
            $("#addLocation").find("select").each(function(){

                $(this).val($(this).find("option:first").val());
            });

            if(afterSuccessFn!= undefined && typeof  afterSuccessFn == 'function'){
                afterSuccessFn(response);
            }else{

                var locationId = 0;
                var locationName = name;
                $('<option value="'+locationId+'">'+locationName+'</option>').appendTo("#locationIds");
                $("#addLocation").modal("hide");
            }
        }
    });
}