var $body = $('body');
var type = $('#type').val();

$(document).ready(function() {


    function getCheckedIds(){
        return $('.select-checkbox:checked').map(function(){return $(this).val();}).get();
    }

    $body.on('click', '#select-all-checkbox',function () {
        var isSelected = $(this).prop('checked');
        if(isSelected){
            $('.select-checkbox').prop('checked', true);
        }
        else {
            $('.select-checkbox').prop('checked', false);
        }
        enableOrDisableEditButton();
    });

    $body.on('click', '.select-checkbox', function () {
        enableOrDisableEditButton();
    });

    function enableOrDisableEditButton() {
        var checkedIds = getCheckedIds();
        if(checkedIds.length === 1){
            $('#edit-selected-btn').prop('disabled', false);
        }
        else{
            $('#edit-selected-btn').prop('disabled', true);
        }
    }

    $body.on('click', '#edit-selected-btn', function () {
        var id = getCheckedIds()[0];
        window.location = BASEURL + 'admin/' + type + '/update/' + id;
    });

    $body.on('click', '.activate-checkbox', function () {
        var id = $(this).val();
        var isSelected = $(this).prop('checked');
        if(isSelected){
            activateEntity(id, false);
        }
        else{
            deActivateEntity(id, false);
        }
    });

    $body.on('click', '#delete-selected-btn', function () {
        var ids = getCheckedIds();
        for(var i= 0; i < ids.length; i++) {
            var id = ids[i];
            deleteEntity(id, false);
        }
    });

    $body.on('click', '#activate-selected-btn', function () {
        var ids = getCheckedIds();
        for(var i= 0; i < ids.length; i++){
            activateEntity(ids[i], false);
        }
    });

    $body.on('click', '#deactivate-selected-btn', function () {
        var ids = getCheckedIds();
        for(var i= 0; i < ids.length; i++){
            deActivateEntity(ids[i], false);
        }
    });

    function deActivateEntity(id, multiple) {
        var data = {
            'id': id,
            'type': type
        };
        $.ajax({
            url: BASEURL+'api/common/deactivate-entity',
            data: data,
            type: 'POST',
            traditional: true,
            statusCode: {
                401: function (response) {
                    if(!multiple){
                        showErrorMessage(id);
                    }
                },
                422: function (response) {
                    if(!multiple){
                        showErrorMessage(id);
                    }
                }
            },
            success: function(data){
                if(!multiple){
                    showDisableMessage(id);
                }
            }
        });
    }

    function activateEntity(id,  multiple) {
        var data = {
            'id': id,
            'type': type
        };
        $.ajax({
            url: BASEURL+'api/common/activate-entity',
            data: data,
            type: 'POST',
            traditional: true,
            statusCode: {
                401: function (response) {
                    if(!multiple){
                        showErrorMessage(id);
                    }
                },
                422: function (response) {
                    if(!multiple){
                        showErrorMessage(id);
                    }
                }
            },
            success: function(data){
                if(!multiple){
                    showEnableMessage(id);
                }
            }
        });
    }


    function showEnableMessage(id){
        var title = $('#title-' + id).html();
        $.growl.notice({ title: title, message: "Enabled" });
        $('.activate-checkbox[value='+ id +']').prop('checked', true);
    }

    function showDisableMessage(id){
        var title = $('#title-' + id).html();
        $.growl.notice({ title: title, message: "Disabled" });
        $('.activate-checkbox[value='+ id +']').prop('checked', false);
    }

    function showErrorMessage(id){
        var title = $('#title-' + id).html();
        $.growl.error({ title: title, message: "Could not perform operation" });
    }

});


function deleteEntity(id,  multiple) {
    var data = {
        'id': id,
        'type': type
    };
    var title = $('#title-' + id).html();
    $.ajax({
        url: BASEURL+'api/common/delete-entity',
        data: data,
        type: 'POST',
        traditional: true,
        statusCode: {
            401: function (response) {
                if(!multiple){

                }
            },
            422: function (response) {

                if(!multiple){
                    showServerErrorMessage(title,response.responseJSON.msg);
                }
            }
        },
        success: function(data){
            $('#title-' + id).parents("tr").fadeOut(500,function(){
                $('#title-' + id).parents("tr").remove();
            });
            if(!multiple){
                showSuccessMessage(title,data.msg);
            }
        }
    });
}