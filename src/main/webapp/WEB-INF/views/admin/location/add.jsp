<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
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
                                                <input id="stateId" class="form-control">
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
                                        <input  class="form-control">
                                    </div>
                                    <div class="imageupload panel panel-default">
                                        <div class="panel-heading clearfix">
                                            <h4 class="panel-title pull-left ">Venue logo</h4>
                                            <div class="btn-group pull-right">
                                                <button type="button" class="btn btn-default active">File</button>
                                                <button type="button" class="btn btn-default">URL</button>
                                            </div>
                                        </div>
                                        <div class="file-tab panel-body">
                                            <label class="btn btn-primary btn-file btn-sm-new">
                                                <span>Browse</span>
                                                <!-- The file is stored here. -->
                                                <input type="file" name="image-file">
                                            </label>
                                            <button type="button" class="btn btn-danger btn-sm-new">Delete image</button>
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
                                        <div class="file-tab panel-body">
                                            <form action="/upload-target" class="dropzone"></form>
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
                                                    <input type="text" class="form-control" id="exampleInputAmount" placeholder="">
                                                    <div class="input-group-addon">sec</div>
                                                </div>
                                                <p style="text-align: left;">Ad Break Time</p>
                                                <div class="input-group">
                                                    <input type="text" class="form-control" id="exampleInputAmount" placeholder="">
                                                    <div class="input-group-addon">min</div>
                                                </div>

                                            </div>
                                            <div class="col-md-6">
                                                <h3 style="text-align: left;"> TRANSITIONS</h3>
                                                <p style="text-align: left">Fade In</p>
                                                <select class="form-control" style="margin-bottom: 13px">
                                                    <option value="">1s</option>
                                                    <option value="">2s</option>
                                                    <option value="">3s</option>
                                                    <option value="">4s</option>
                                                </select>
                                                <p style="text-align: left">Fade Out</p>
                                                <select class="form-control" style="margin-bottom: 13px">
                                                    <option value="">1s</option>
                                                    <option value="">2s</option>
                                                    <option value="">3s</option>
                                                    <option value="">4s</option>
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
        </div>
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
                    fadeOutTime: fadeOutTime
                };
                console.log(data);
                $.ajax({
                    url: BASEURL+"api/location/create",
                    type: "POST",
                    data: data ,
                    500: function(response) {
                        console.log(response);
                    }, 401: function(response) {
                        console.log(response.responseJSON);
                    }, 422: function(response) {
                        console.log(response.responseJSON);
                    }, success: function(response) {
                        console.log(response);
                    }
                });
            }
        </script>
    </jsp:body>
</t:genericpage>