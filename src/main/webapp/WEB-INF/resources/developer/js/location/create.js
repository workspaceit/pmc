/**
 * Created by mi_rafi on 1/3/18.
 */
function submitData(){
    var name = $('#name').val();
    var address = $('#address').val();
    var stateId = $('#stateId').val();
    var zip = $('#zip').val();
    var phone = $('#phone').val();
    var locationLogo = $('#locationLogo').val();
    var durationSpeed = $('#durationSpeed').val();
    var breakTime = $('#breakTime').val();
    var fadeInTime = $('#fadeInTime').val();
    var fadeOutTime = $('#fadeOutTime').val();
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
                BindErrorsWithHtml("errorObj_",response.responseJSON);
            }
        },
        success: function(response) {
            window.location = BASEURL+"admin/location/all";
        }
    });
}