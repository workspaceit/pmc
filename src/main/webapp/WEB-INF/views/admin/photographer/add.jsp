<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<t:genericpage>
    <jsp:body>
        <div id="page-wrapper" style="min-height: 563px !important;">
            <div class="container">
                <h3 class="uni-header"><span>Account Details</span></h3>
                <div class="col-md-12">

                    <div class="row clearfix">
                        <div class="btn-container-top">
                            <button class="btn btn-action-top" onclick="submitPhotographerData()">Save</button>
                            <button class="btn btn-action-top">Save&nbsp;&&nbsp;Close</button>
                            <button class="btn btn-action-top">Save&nbsp;&&nbsp;New</button>
                            <button class="btn btn-action-top">Cancel</button>
                        </div>
                        <div class="form-group">
                            <label>Full Name</label>
                            <input id="fullName" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Phone Number</label>
                            <input id="phoneNumber" class="form-control" type="Number">
                        </div>
                        <div class="form-group">
                            <label>User Name</label>
                            <input id=userName class="form-control">
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
                            <input id="confirmPassword" class="form-control" type="password" placeholder="******">
                        </div>
                        <div class="imageupload panel panel-default">
                            <div class="panel-heading clearfix">
                                <h4 class="panel-title pull-left">Profile Image</h4>
                                <div class="btn-group pull-right">
                                </div>
                            </div>
                            <div class="file-tab panel-body">
                                <div id="profileImg" >

                                    <div class="dz-default dz-message">
                                        <span>Drop files here to upload</span>
                                        <p id="errorObj_profilePictureToken"></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- 
                        <div class="form-group">
                        	<label>Require Password reset</label>
	                        <div class="btn-group choose-btn">
	                            <button type="button" class="active btn btn-default" id="regi1">Yes</button>
	                            <button type="button" class="btn btn-default" id="regi3">No</button>
	                        </div>
                        </div> 
                        
                        -->

                    </div>

                </div>
            </div>
            <%-- After image add Dropzone Image preview --%>
            <div id="dropZonePreview" style="display: none">
                <div class="dz-preview dz-file-preview">
                    <div class="dz-details">
                        <div class="dz-filename"><span data-dz-name></span></div>
                        <div class="dz-size" data-dz-size></div>
                        <img data-dz-thumbnail />
                    </div>
                    <div class="dz-progress"><span class="dz-upload" data-dz-uploadprogress></span></div>
                    <%--<div class="dz-success-mark"><span>✔</span></div>
                    <div class="dz-error-mark"><span>✘</span></div>--%>
                    <div class="dz-error-message">
                        <span data-dz-errormessage></span>
                    </div>

                </div>
            </div>




        </div>
        <script>
        function submitPhotographerData(){
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
                    window.location.href =  BASEURL+'photographer/all';
                }
            });
            console.log(data)
           
        }
        
        
        </script>
        
        
        
        <!-- /#wrapper -->

        <link href="<s:url value="/resources/css/dropzone.css"/>" rel="stylesheet">
        <script src="<s:url value="/resources/js/dropzone.min.js"/>"></script>


        <!-- dropzone -->
        <script>
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
                                            removeImageByToken(file.token);
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

            function removeImageByToken(token){
                if(token == undefined){
                    return;
                }
                $.ajax({
                    url: BASEURL+'file/remove',
                    data:{"token":token},
                    type: 'POST',
                    statusCode: {
                        401: function (response) {
                            console.log(response);
                        },
                        422: function (response) {
                            console.log(response);
                        }
                    },success: function(data){
                        console.log(data);
                    }
                });
            }
        </script>


        
    </jsp:body>





</t:genericpage>