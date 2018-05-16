var $body = $('body');
var type = $('#type').val();


function getCheckedIds(){
    return $('.select-checkbox:checked').map(function(){return $(this).val();}).get();
}

$(document).ready(function() {

    function clearSelectionAfterOperation() {
        $('.select-checkbox:checked').prop('checked', false);
        $('#select-all-checkbox').prop('checked', false);
        enableOrDisableEditButton();
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

        if(checkedIds.length > 0) {
            $('#activate-selected-btn').prop('disabled', false);
            $('#deactivate-selected-btn').prop('disabled', false);
            $('#delete-selected-btn').prop('disabled', false);
        }
        else {
            $('#activate-selected-btn').prop('disabled', true);
            $('#deactivate-selected-btn').prop('disabled', true);
            $('#delete-selected-btn').prop('disabled', true);
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
        console.log("POS");
        bindDeleteModalButtonAction(function(){
            var ids = getCheckedIds();
            console.log(ids);
            for(var i= 0; i < ids.length; i++) {
                var id = ids[i];
                deleteEntityFromServer(id, false);
            }
        }, true);
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
                    clearSelectionAfterOperation();
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
                    clearSelectionAfterOperation();
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

function deleteEntityFromServer(id,  multiple){

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
            window.location.reload();
        }
    });
}
function deleteEntity(id,  multiple) {
    bindDeleteModalButtonAction(function(){
        console.log("YES "+id);
        deleteEntityFromServer(id,  multiple);
    }, false);
}
function bindDeleteModalButtonAction(yesBtnFn, multiple){
    $("#delete-content-yes").unbind("click");
    $("#delete-content-no").unbind("click");

    $("#delete-content-yes").click(function(){
        yesBtnFn();
        closeDeleteEntityModal();
    });
    $("#delete-content-no").click(function(){
        closeDeleteEntityModal();
    });
    var ids = getCheckedIds();
    if(multiple) {
        $('#delete-content .modal-title').html("Are you sure you want to delete these items?");
    }
    else {
        $('#delete-content .modal-title').html("Are you sure you want to delete this item?");
    }
    $("#delete-content").modal("show");
}
function closeDeleteEntityModal(){
    $("#delete-content").modal("hide");
    $("#delete-content-no").unbind("click");
    $("#delete-content-yes").unbind("click");
}