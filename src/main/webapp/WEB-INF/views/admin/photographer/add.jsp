<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericpage>
    <jsp:body>
        <div id="page-wrapper" style="min-height: 563px !important;">
            <div class="container">
                <h3 class="uni-header"><span>Account Details</span></h3>
                <div class="col-md-12">

                    <div class="row clearfix">
                        <div class="btn-container-top">
                            <button class="btn btn-action-top" onclick="submitProtographerData()">Save</button>
                            <button class="btn btn-action-top">Save&nbsp;&&nbsp;Close</button>
                            <button class="btn btn-action-top">Save&nbsp;&&nbsp;New</button>
                            <button class="btn btn-action-top">Cancel</button>
                        </div>
                        <div class="form-group">
                            <label>Full Name</label>
                            <input id="full_name" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Phone Number</label>
                            <input id="phone_number" class="form-control" type="Number">
                        </div>
                        <div class="form-group">
                            <label>User Name</label>
                            <input id=username class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <input id="email" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <input id="password" class="form-control" type="password" placeholder="******">
                        </div>
                        <div class="form-group">
                            <label>Confirm Password</label>
                            <input id="confirm_password" class="form-control" type="password" placeholder="******">
                        </div>
                        <div class="imageupload panel panel-default">
                            <div class="panel-heading clearfix">
                                <h4 class="panel-title pull-left">Profile Image</h4>
                                <div class="btn-group pull-right">
                                </div>
                            </div>
                            <div class="file-tab panel-body">
                                <form action="/upload-target" class="dropzone"></form>
                            </div>
                        </div>
                        <!-- <div class="form-group">
                        <label>Require Password reset</label>
                        <div class="btn-group choose-btn">
                            <button type="button" class="active btn btn-default" id="regi1">Yes</button>
                            <button type="button" class="btn btn-default" id="regi3">No</button>
                        </div>
                        </div> -->

                    </div>

                </div>
            </div>





        </div>
        <script>
        function submitProtographerData(){
        	var fullName = $("#full_name").val();
        	var phoneNumber = $("#phone_number").val();
            var email = $("#email").val();
            var userName = $("#username").val();
            var passoword =  $("#password").val();
            var confirmPassword = 	$("#confirm_password").val();
            
            var data = {
            			"fullName":fullName,
            			"phoneNumber":phoneNumber,
            			"email":email,
            			"userName":userName,
            			"passoword":passoword,
            			"confirmPassword":confirmPassword
            		};
            
            console.log(data)
           
        }
        
        
        </script>
    </jsp:body>
</t:genericpage>