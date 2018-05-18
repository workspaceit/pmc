var $stateModalSaveBtn = $('#stateModalSaveBtn');

$('#add-new-state').on('shown.bs.modal', initializeStateForm).on('hidden.bs.modal', initializeStateForm);

function initializeStateForm() {
    console.log("initi");
    var $stateFormBody = $('#stateFormBody');
    $stateFormBody.find('#stateName').val('');
    $stateFormBody.find('#code').val('');
    UnBindErrors("errorObjState_");
}

$stateModalSaveBtn.on('click', function () {
    console.log("save clicked");
    var $stateFormBody = $('#stateFormBody');
    var stateName = $stateFormBody.find('#stateName').val();
    var stateCode = $stateFormBody.find('#code').val();
    var data = {
        'stateName': stateName,
        'code': stateCode
    };
    $.ajax({
        url: BASEURL+"api/state/create",
        type: "POST",
        data: data ,
        traditional:true,
        statusCode:{
            500: function(response) {
                console.log(response);
            }, 401: function(response) {
                console.log(response.responseJSON);
            }, 422: function(response) {
                BindErrorsWithHtml("errorObjState_", response.responseJSON, false, "#stateFormBody");
            }
        },
        success: function(state) {
            $('#add-new-state').modal('hide');
            var optionHtml = '<option value="' + state.id + '">'+ state.name +'</option>';
            var $stateSelect = $('#stateId');
            $stateSelect.append(optionHtml);
            $stateSelect.val(state.id);

            var $cityModalStateId = $('#cityStateId');
            $cityModalStateId.append(optionHtml);
            initializeStateForm();

            /**
             * Get Cities by state
             * */
            var stateId = state.id;
            fetchCityOnStateChange(stateId, function(response) {
                var selectedCityId = $("#advertiserCityId").val();
                $("#cityId").html("");
                $(response).each(function( key, value ){

                    var selectedHtml = (selectedCityId == value.id)?"selected='selected'":"";
                    var html = "<option value='"+value.id+"' "+selectedHtml+">"+value.name+"</option>";
                    $("#cityId").append(html);
                });
            },'locationFormBody');

        }
    });
});