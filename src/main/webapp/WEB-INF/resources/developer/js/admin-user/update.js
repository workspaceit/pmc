/**
 * Created by mi_rafi on 1/3/18.
 */
function submitUpdatedAdminUserData(action){
    var id = $('#admin_id').val();
    var fullName = $('#fullName').val();
    var phoneNumber = $('#phoneNumber').val();
    var userName = $('#userName').val();
    var email = $('#email').val();
    var password = $('#password').val();
    var confirmPassword = $('#confirmPassword').val();
    var profilePictureToken=  getVenueLogoToken();


    var data = {
        fullName: fullName,
        phoneNumber: phoneNumber,
        userName: userName,
        email: email,
        password: password,
        confirmPassword: confirmPassword,
        profilePictureToken: profilePictureToken
    };
    console.log(data);
    $.ajax({
        url: BASEURL+"api/admin/update/"+id,
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
            if(action === "save" || action === "save-close") {
                window.location = BASEURL + "admin/user/all";
            }
            else if(action === "save-new"){
                window.location = BASEURL + "admin/user/add";
            }notifyAdminUpdateStatus();
        }
    });
}