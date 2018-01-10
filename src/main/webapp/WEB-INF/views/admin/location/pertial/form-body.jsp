<%--
  Created by IntelliJ IDEA.
  User: mi_rafi
  Date: 1/9/18
  Time: 2:58 PM
  To change this template use File | Settings | File Templates.
--%>
<div id="locationFormBody" class="panel panel-default">
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
                            <p id="errorObjLocation_profilePictureToken"></p>
                        </div>
                    </div>

                    <p id="errorObjLocation_locationLogo"  class="text-danger"></p>
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
                        <p id="errorObjLocation_profilePictureToken"></p>
                    </div>
                </div>

            </div>
            <div class="imageupload panel panel-default">
                <div class="panel-heading clearfix">
                    <h4 class="panel-title pull-left ">Slideshow Settings</h4>
                    <div  id="slideShowSettingsBtnDiv" class="btn-group pull-right">
                        <button type="button" data-val="1" class="btn btn-default active">On</button>
                        <button type="button" data-val="0" class="btn btn-default">Off</button>
                    </div>
                </div>
                <div  id="slideShowSettings" class="file-tab panel-body">
                    <div class="col-md-6">
                        <h3 style="text-align: left;color: #fff"> TRANSITIONS</h3>
                        <p style="text-align: left;">Duration Speed</p>
                        <div class="input-group" style="margin-bottom: 13px">
                            <input type="number" class="form-control" id="durationSpeed" placeholder="">
                            <div class="input-group-addon">sec</div>
                        </div>
                        <p class="text-danger" id="errorObjLocation_durationSpeed"></p>
                        <p style="text-align: left;">Ad Break Time</p>
                        <div class="input-group">
                            <input type="number" class="form-control" id="breakTime" placeholder="">
                            <div class="input-group-addon">min</div>

                        </div>
                        <p class="text-danger" id="errorObjLocation_breakTime"></p>

                    </div>
                    <div class="col-md-6">
                        <h3 style="text-align: left;"> TRANSITIONS</h3>
                        <p style="text-align: left">Fade In</p>
                        <select id="fadeInTime" class="form-control" style="margin-bottom: 13px">
                            <c:forEach var="fadeIn" items="${fadeInList}">
                                <fmt:parseNumber var = "fadeInVal" integerOnly = "true"
                                                 type = "number" value = "${fadeIn}" />
                                <option value="${fadeInVal}" >${fadeInVal}s</option>
                            </c:forEach>
                        </select>
                        <p style="text-align: left">Fade Out</p>
                        <select id="fadeOutTime" class="form-control" style="margin-bottom: 13px">
                            <c:forEach var="fadeOut" items="${fadeOutList}">
                                <fmt:parseNumber var = "fadeOutVal" integerOnly = "true"
                                                 type = "number" value ="${fadeOut}" />
                                <option value="${fadeOutVal}" >${fadeOutVal}s</option>
                            </c:forEach>
                        </select>
                    </div>


                </div>
                <div class="url-tab panel-body" style="display:none;">


                </div>
            </div>


        </div>
    </div>