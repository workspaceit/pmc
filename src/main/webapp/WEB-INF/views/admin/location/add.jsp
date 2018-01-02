<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<t:genericpage>
    <jsp:body>
        <!-- /#page-wrapper -->
        <div id="page-wrapper">

            <div class="container">
                <h3 class="uni-header"><span>Add New Location</span></h3>

                <div class="col-md-12">
                    <div class="row">
                        <div class="btn-container-top">
                            <button class="btn btn-action-top" onclick="submitData()">Save</button>
                            <button class="btn btn-action-top">Save&nbsp;&&nbsp;Close</button>
                            <button class="btn btn-action-top">Save&nbsp;&&nbsp;New</button>
                            <button class="btn btn-action-top">Cancel</button>
                        </div>
                        <div class="form-group">
                            <div class="panel panel-default">
                                <div class="panel-body">

                                    <div class="form-group">
                                        <label>Event Location</label>
                                        <input id="name"  class="form-control">
                                    </div>

                                    <div class="form-group">
                                        <label>Address</label>
                                        <input id="address" class="form-control">
                                    </div>

                                    <div class="row clearfix">
                                        <!-- <div class="col-md-3">
                                            <div class="form-group">
                                            <label>
                                            <a href="#" style="color:#333;font-size: 14px;" data-toggle="modal" data-target="#add-new-city">City</a>
                                            </label>
                                            <select class="js-example-placeholder-multiple js-states form-control" multiple="multiple">
                                                <option>Miami</option>
                                                <option>Florida</option>
                                                <option>California</option>
                                            </select>
                                            </div>
                                        </div> -->
                                        <!-- <div class="col-md-3">
                                            <div class="form-group">
                                            <label>
                                            <a href="#" style="color:#333;font-size: 14px;" data-toggle="modal" data-target="#add-new-venue">Venue</a>
                                            </label>
                                            <select class="js-example-placeholder-multiple js-states form-control" multiple="multiple">
                                                <option>Los Angeles</option>
                                                <option>Houston</option>
                                                <option>Las Vegas</option>
                                            </select>
                                        </div>
                                        </div> -->

                                        <div class="col-md-6 col-xs-12">
                                            <div class="form-group">
                                                <label>
                                                    <a href="#" style="color:#333;font-size: 14px;" data-toggle="modal" data-target="#add-new-state">State</a>
                                                </label>
                                                <select id="stateId" class="form-control">
                                                    <c:forEach var="state" items="${states}">
                                                        <option value="${state.id}" >${state.name}</option>
                                                    </c:forEach>

                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-6 col-xs-12">
                                            <div class="form-group">
                                                <label>Zip</label>
                                                <input id="zip" class="form-control">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label>Phone Number</label>
                                        <input id="phone"  class="form-control">
                                    </div>
                                    <div class="imageupload panel panel-default">
                                        <div class="panel-heading clearfix">
                                            <h4 class="panel-title pull-left ">Venue logo</h4>
                                            <%--<div class="btn-group pull-right">
                                                <button type="button" class="btn btn-default active">File</button>
                                                <button type="button" class="btn btn-default">URL</button>
                                            </div>--%>
                                        </div>
                                        <div class="file-tab panel-body">
                                            <%--<label class="btn btn-primary btn-file btn-sm-new">
                                                <span>Browse</span>
                                                <!-- The file is stored here. -->
                                                <input type="file" name="image-file">
                                            </label>
                                            <button type="button" class="btn btn-danger btn-sm-new">Delete image</button>
--%>
                                            <div id="venueLogoImg" >

                                                <div class="dz-default dz-message">
                                                    <span>Drop files here to upload</span>
                                                    <p id="errorObj_profilePictureToken"></p>
                                                </div>
                                            </div>

                                            <p id="errorObj_locationLogo"  class="text-danger"></p>
                                        </div>
                                        <div class="url-tab panel-body" style="display:none;">
                                            <div class="input-group">
                                                <input type="text" class="form-control hasclear" placeholder="Image URL">
                                                <div class="input-group-btn">
                                                    <button type="button" class="btn btn-default" style="margin-left: 5px;">Submit</button>
                                                </div>
                                            </div>
                                            <button type="button" class="btn btn-default btn-sm-new">Remove</button>
                                            <!-- The URL is stored here. -->
                                            <input type="hidden" name="image-url">
                                        </div>
                                    </div>
                                    <div class="imageupload panel panel-default">
                                        <div class="panel-heading clearfix">
                                            <h4 class="panel-title pull-left">Background Images</h4>
                                            <div class="btn-group pull-right">

                                            </div>
                                        </div>
                                        <div id="venueBgImg" >

                                            <div class="dz-default dz-message">
                                                <span>Drop files here to upload</span>
                                                <p id="errorObj_profilePictureToken"></p>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="imageupload panel panel-default">
                                        <div class="panel-heading clearfix">
                                            <h4 class="panel-title pull-left ">Slideshow Settings</h4>
                                            <div class="btn-group pull-right">
                                                <button type="button" class="btn btn-default active">On</button>
                                                <button type="button" class="btn btn-default">Off</button>
                                            </div>
                                        </div>
                                        <div class="file-tab panel-body">
                                            <div class="col-md-6">
                                                <h3 style="text-align: left;color: #fff"> TRANSITIONS</h3>
                                                <p style="text-align: left;">Duration Speed</p>
                                                <div class="input-group" style="margin-bottom: 13px">
                                                    <input type="text" class="form-control" id="durationSpeed" placeholder="">
                                                    <div class="input-group-addon">sec</div>
                                                </div>
                                                <p class="text-danger" id="errorObj_durationSpeed"></p>
                                                <p style="text-align: left;">Ad Break Time</p>
                                                <div class="input-group">
                                                    <input type="text" class="form-control" id="breakTime" placeholder="">
                                                    <div class="input-group-addon">min</div>

                                                </div>
                                                <p class="text-danger" id="errorObj_breakTime"></p>

                                            </div>
                                            <div class="col-md-6">
                                                <h3 style="text-align: left;"> TRANSITIONS</h3>
                                                <p style="text-align: left">Fade In</p>
                                                <select id="fadeInTime" class="form-control" style="margin-bottom: 13px">
                                                    <option value="1">1s</option>
                                                    <option value="2">2s</option>
                                                    <option value="3">3s</option>
                                                    <option value="4">4s</option>
                                                </select>
                                                <p style="text-align: left">Fade Out</p>
                                                <select id="fadeOutTime" class="form-control" style="margin-bottom: 13px">
                                                    <option value="1">1s</option>
                                                    <option value="2">2s</option>
                                                    <option value="3">3s</option>
                                                    <option value="4">4s</option>
                                                </select>
                                            </div>


                                        </div>
                                        <div class="url-tab panel-body" style="display:none;">


                                        </div>
                                    </div>


                                </div>
                            </div>
                        </div>
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

        <%--Developer Hidden Field--%>
        <input type="hidden" id="venueLogoToken" value="" />
        <input type="hidden" id="venueBgImgTokens" value="" />

        <script>
            function submitData(){
                var name = $('#name').val();
                var address = $('#address').val();
                var stateId = $('#stateId').val();
                var zip = $('#zip').val();
                var phone = $('#phone').val();
                var locationLogo = $('#locationLogo').val();
                var hasSlideshow = $('#hasSlideshow').val();
                var durationSpeed = $('#durationSpeed').val();
                var breakTime = $('#breakTime').val();
                var fadeInTime = $('#fadeInTime').val();
                var fadeOutTime = $('#fadeOutTime').val();
                var logoImgToken = getVenueLogoToken();
                var bgTokens = getVenueBgImgTokens();
                var hasSlideshow = true;
                var data = {
                    name: name,
                    address: address,
                    stateId: stateId,
                    zip: zip,
                    phone: phone,
                    locationLogo: locationLogo,
                    hasSlideshow: hasSlideshow,
                    durationSpeed: durationSpeed,
                    breakTime: breakTime,
                    fadeInTime: fadeInTime,
                    fadeOutTime: fadeOutTime,
                    logoImgToken:logoImgToken,
                    bgTokens:bgTokens,
                    hasSlideshow:hasSlideshow
                };
                console.log(data);
                $.ajax({
                    url: BASEURL+"api/location/create",
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
                        console.log(response);
                    }
                });
            }





        </script>
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
                        configVenueLogoDropZone();
                        configVenueBdImgDropZone();

                    });
                }
            };
            function storeVenueLogoToken(token){
                $("#venueLogoToken").val(token);
            }
            function getVenueLogoToken(){
                var token=0;
                try{
                    token = parseInt($("#venueLogoToken").val());
                    if(isNaN(token)){
                        token = 0;
                    }
                }catch(ex) {
                    console.log(ex);
                    token = 0;
                }
                return token;
            }
            function storeVenueBgImgToken(token){
                var tokens = getVenueBgImgTokens();
                if(tokens.indexOf(token)<0){
                    tokens.push(token);
                }
                $("#venueBgImgTokens").val(JSON.stringify(tokens));
            }
            function getVenueBgImgTokens(){
                var tokens=[];
                try{
                    var venueLogoTokenStr =  $("#venueBgImgTokens").val().trim();
                    tokens = JSON.parse(venueLogoTokenStr==""?"[]":venueLogoTokenStr);

                }catch(ex) {
                    console.log(ex);
                    tokens = [];
                }
                return tokens;
            }
            function removeBgImgTokens(token){
                var tokens=getVenueBgImgTokens();
                var index = tokens.indexOf(token);
                if(index>0){
                    tokens.splice(index,1);
                }
            }
            function removeVenueLogoToken(token){
                var venueLogoToken = getVenueLogoToken();
                if(venueLogoToken==token){
                    $("#venueLogoToken").val("");
                }

            }
            function configVenueLogoDropZone(){
                var venueLogoImgDropZone = new Dropzone("div#venueLogoImg",
                    {
                        url: BASEURL+"file/upload/venue-logo-image",
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
                                    removeImageByToken(file.token,function (data) {
                                        removeVenueLogoToken(data.token);
                                    });
                                    profilePictureToken = 0;
                                    var _ref;
                                    venueLogoImgDropZone.removeFile(file);
                                });
                            });

                        },
                        error:function(file,response){
                            var msg = (typeof response == "object")?((response.length>0)?response[0].msg:""):response;
                            $("#profileImg").find(".dz-error-message span").html(msg);
                        },
                        success:function(file,response){

                            file.token = response.token;
                            storeVenueLogoToken(response.token);
                            console.log(file);
                        }
                    }
                );
            }
            function configVenueBdImgDropZone(){
                var venueBgImgDropZone = new Dropzone("div#venueBgImg",
                    {
                        url: BASEURL+"file/upload/venue-background-image",
                        method:"post",
                        paramName:"profileImg",
                        maxFilesize: 1,
                        maxFiles:5,
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
                                    removeImageByToken(file.token,function(data){
                                        removeBgImgTokens(data.token);
                                    });
                                    profilePictureToken = 0;
                                    var _ref;
                                    venueBgImgDropZone.removeFile(file);
                                });
                            });

                        },
                        error:function(file,response){
                            var msg = (typeof response == "object")?((response.length>0)?response[0].msg:""):response;
                            $("#profileImg").find(".dz-error-message span").html(msg);
                        },
                        success:function(file,response){

                            file.token = response.token;
                            storeVenueBgImgToken(response.token);
                            console.log(file);
                        }
                    }
                );
            }
            function removeImageByToken(token,fn){
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
                        fn(data);
                    }
                });
            }
        </script>
    </jsp:body>
</t:genericpage>