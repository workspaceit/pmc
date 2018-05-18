var $stateModalSaveBtn = $('#cityModalSaveBtn');

$('#add-new-city').on('shown.bs.modal', initializeCityForm).on('hidden.bs.modal', initializeCityForm);

function initializeCityForm() {
    console.log("initi1");
    var $stateFormBody = $('#cityFormBody');
    $stateFormBody.find('#cityName').val('');
    $stateFormBody.find('#cityStateId').val('');
    UnBindErrors("errorObjCity_");
}

$stateModalSaveBtn.on('click', function () {
    UnBindErrors("errorObjCity_");
    var $stateFormBody = $('#cityFormBody');
    var cityName = $stateFormBody.find('#cityName').val();
    var stateId = $stateFormBody.find('#cityStateId').val();
    var data = {
        'cityName': cityName,
        'cityStateId': stateId
    };
    $.ajax({
        url: BASEURL+"api/city/create",
        type: "POST",
        data: data ,
        traditional:true,
        statusCode:{
            500: function(response) {
                console.log(response);
            }, 401: function(response) {
                console.log(response.responseJSON);
            }, 422: function(response) {
                BindErrorsWithHtml("errorObjCity_", response.responseJSON, false, "#cityFormBody");
            }
        },
        success: function(city) {
            $('#add-new-city').modal('hide');
            var optionHtml = '<option value="' + city.id + '">'+ city.name +'</option>';
            var $citySelect = $('#cityId');
            $citySelect.append(optionHtml);
            $citySelect.val(city.id);
            var $stateSelect = $('#stateId');
            $stateSelect.val(city.stateId);
            initializeCityForm();
        }
    });
});