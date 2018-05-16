function deleteEntity(id, type) {
    bindDeleteModalButtonAction(function(){
        deleteEntityFromServer(id, type);
    }, false);
}

function bindDeleteModalButtonAction(yesBtnFn){
    $("#delete-content-yes").unbind("click");
    $("#delete-content-no").unbind("click");
    $("#delete-content-yes").click(function(){
        yesBtnFn();
        closeDeleteEntityModal();
    });
    $("#delete-content-no").click(function(){
        closeDeleteEntityModal();
    });
    $('#delete-content .modal-title').html("Are you sure you want to delete this item?");
    $("#delete-content").modal("show");
}
function closeDeleteEntityModal(){
    $("#delete-content").modal("hide");
    $("#delete-content-no").unbind("click");
    $("#delete-content-yes").unbind("click");
}

function deleteEntityFromServer(id, type){

    var data = {
        'id': id,
        'type': type
    };
    console.log("hi");
    $.ajax({
        url: BASEURL+'api/common/delete-entity',
        data: data,
        type: 'POST',
        traditional: true,
        statusCode: {
            401: function (response) {
            },
            422: function (response) {
                showServerErrorMessage(title,response.responseJSON.msg);
            }
        },
        success: function(data){
            window.location = BASEURL + 'admin/' + type + '/all';
        }
    });
}
