/**
 * Created by mi_rafi on 1/3/18.
 */
function submitAdminUserData(){
    var fullName = $('#fullName').val();
    var phoneNumber = $('#phoneNumber').val();
    var userName = $('#userName').val();
    var email = $('#email').val();
    var password = $('#password').val();
    var confirmPassword = $('#confirmPassword').val();


    var data = {
        fullName: fullName,
        phoneNumber: phoneNumber,
        userName: userName,
        email: email,
        password: password,
        confirmPassword: confirmPassword
    };
    console.log(data);
    $.ajax({
        url: BASEURL+"api/admin/create",
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
            window.location = BASEURL+"admin/user/all";
        }
    });
}