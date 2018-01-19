/**
 * Created by mi_rafi on 1/10/18.
 */

Dropzone.autoDiscover = false;
var profilePictureToken = 0;
var otherImagesToken = [];

// alternative to DOMContentLoaded
document.onreadystatechange = function () {
    if (document.readyState == "interactive") {
        $(function() {
            var profileImgDropzone = new Dropzone("div#profileImg",
                {
                    url: BASEURL+"file/upload/photographer-profile-image",
                    method:"post",
                    paramName:"profileImg",
                    maxFilesize: 1,
                    maxFiles:1,
                    //addRemoveLinks: true,
                    previewTemplate:$("#dropZonePreview").html(),
                    init:function(){

                        this.on("maxfilesexceeded", function(file) {
                            console.log("maxfilesexceeded");
                            this.removeAllFiles();
                            this.addFile(file);
                        });
                        this.on("addedfile",function(file){
                            $("#profilePic").hide();
                        });
                    },
                    error:function(file,response){
                        var msg = (typeof response == "object")?((response.length>0)?response[0].msg:""):response;
                        $("#profileImg").find(".dz-error-message span").html(msg);
                        $("#profilePic").hide();

                    },
                    success:function(file,response){

                        file.token = response.token;
                        profilePictureToken = response.token;
                    }
                }
            );

        });
    }
};




var updateCount = 0;
var globalBtnAction = "";
function initUpdate(btnAction){
    globalBtnAction = btnAction;
    UnBindErrors("errorObj_");
    update(1);
}

function update(count){


    updateCount =0;
    switch(count){
        case 1:
            updateBasicInfo();
            break;
        case 2:
            var password =  $("#password").val();
            if(password!=null && password.trim()!=""){
                updatePassword();
            }else{
                update(3);
            }
            break;
        case 3:
            if(profilePictureToken>0){
                updateProfilePicture(profilePictureToken);
            }else{
                update(4);
            }
            break;
        case 4:
            photographerAfterSaveAction(globalBtnAction);
    }



}
function photographerAfterSaveAction(btnAction){
    var urlStr ="";
    switch(btnAction){
        case "save":
            urlStr = "admin/photographer/all";
            break;
        case "save_and_close":
            urlStr = "admin/photographer/all";
            break;
        case "save_and_new":
            urlStr = "admin/photographer/add";
            break;
        case "cancel":
            urlStr = "admin/photographer/all";
            break;
    }
    window.location =BASEURL+urlStr;
}
function updateBasicInfo(){
    var photographerId = $("#photographer_id").val();
    var fullName = $("#fullName").val();
    var phoneNumber = $("#phoneNumber").val();
    var email = $("#email").val();
    var userName = $("#userName").val();

    var data = {
        "fullName":fullName,
        "phoneNumber":phoneNumber,
        "email":email,
        "userName":userName,
        "profilePictureToken":profilePictureToken
    };
    $.ajax({
        url: BASEURL+'api/photographer/update/profile-info/'+photographerId,
        data:data,
        type: 'POST',
        statusCode: {
            401: function (response) {
                console.log(response);
            },
            422: function (response) {

                BindErrorsWithHtml("errorObj_",response.responseJSON,true);
                console.log(response);
            }
        },success: function(data){
            update(2);
        }
    });
    console.log(data)

}
function updatePassword(){
    var photographerId = $("#photographer_id").val();
    var password =  $("#password").val();
    var confirmPassword = 	$("#confirmPassword").val();

    var data = {
        "password":password,
        "confirmPassword":confirmPassword,
        "profilePictureToken":profilePictureToken
    };
    $.ajax({
        url: BASEURL+'api/photographer/update/profile-password/'+photographerId,
        data:data,
        type: 'POST',
        statusCode: {
            401: function (response) {
                console.log(response);
            },
            422: function (response) {

                BindErrorsWithHtml("errorObj_",response.responseJSON,true);
                console.log(response);
            }
        },success: function(data){
            update(3);
        }
    });
    console.log(data)

}
function updateProfilePicture(token){
    var photographerId = $("#photographer_id").val();
    var data = {
        "profilePictureToken":token
    };
    $.ajax({
        url: BASEURL+'api/photographer/update/profile-picture/'+photographerId,
        data:data,
        type: 'POST',
        statusCode: {
            401: function (response) {
                console.log(response);
            },
            422: function (response) {

                BindErrorsWithHtml("errorObj_",response.responseJSON,true);
                console.log(response);
            }
        },success: function(data){
            update(4);
            /**
             * Update global token
             * Once token is used it is deleted from server
             * */
            profilePictureToken = 0;
        }
    });
    console.log(data)

}