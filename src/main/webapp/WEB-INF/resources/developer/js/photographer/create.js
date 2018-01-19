/**
 * Created by mi_rafi on 1/10/18.
 */

Dropzone.autoDiscover = false;
var profilePictureToken = 0;
var banerImagesToken = [];

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
                    addRemoveLinks: true,
                    previewTemplate:$("#dropZonePreview").html(),
                    init:function(){

                        this.on("maxfilesexceeded", function(file) {
                            this.removeAllFiles();
                            this.addFile(file);
                        });
                        this.on("addedfile", function(file) {
                            file._removeLink.addEventListener("click", function() {
                                console.log(file);
                                removeFileByToken(file.token);
                                profilePictureToken = 0;
                                var _ref;
                                profileImgDropzone.removeFile(file);
                            });
                        });

                    },
                    error:function(file,response){
                        var msg = (typeof response == "object")?((response.length>0)?response[0].msg:""):response;
                        $("#profileImg").find(".dz-error-message span").html(msg);
                    },
                    success:function(file,response){

                        file.token = response.token;
                        profilePictureToken = response.token;
                        console.log(file);
                    }
                }
            );

        });
    }
};


function submitPhotographerData(btnAction){
    var fullName = $("#fullName").val();
    var phoneNumber = $("#phoneNumber").val();
    var email = $("#email").val();
    var userName = $("#userName").val();
    var password =  $("#password").val();
    var confirmPassword = 	$("#confirmPassword").val();

    var data = {
        "fullName":fullName,
        "phoneNumber":phoneNumber,
        "email":email,
        "userName":userName,
        "password":password,
        "confirmPassword":confirmPassword,
        "profilePictureToken":profilePictureToken
    };
    $.ajax({
        url: BASEURL+'api/photographer/create',
        data:data,
        type: 'POST',
        statusCode: {
            401: function (response) {
                console.log(response);
            },
            422: function (response) {

                BindErrorsWithHtml("errorObj_",response.responseJSON);
                console.log(response);

            }
        },success: function(data){
            UnBindErrors("errorObj_");
            photographerAfterSaveActionCreate(btnAction);
        }
    });
    console.log(data)

}
function photographerAfterSaveActionCreate(btnAction){
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