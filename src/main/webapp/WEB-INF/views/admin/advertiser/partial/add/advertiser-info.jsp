
<div class="tab-pane active" id="tab_default_1">
    <div class="row clearfix">
        <div class="col-md-6">
            <div class="panel-body">
                <div class="form-group">
                    <label>Name</label>
                    <input id="name" class="form-control" value="">
                </div>
                <div class="form-group">
                    <label>Address</label>
                    <input id="address" class="form-control" value="" >
                </div>
                <div class="row clearfix">
                    <div class="col-md-4 col-xs-12">
                        <div class="form-group">
                            <label>City</label>
                            <div>
                                <select id="cityId" class="form-control">
                                    <c:forEach var="city" items="${cities}">
                                        <option value="${city.id}"  >${city.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <!-- <div class="col-md-3 col-xs-12">
                        <div class="form-group">
                        <label>
                        <a href="#" style="color:#333;font-size: 14px;" data-toggle="modal" data-target="#add-new-venue">Venue</a>
                        </label>
                        <div>
                          <select class="js-example-placeholder-multiple js-states form-control" multiple="multiple">
                              <option>Los Angeles</option>
                              <option>Houston</option>
                              <option>Las Vegas</option>
                          </select>
                        </div>
                    </div>
                    </div> -->
                    <div class="col-md-4 col-xs-12">
                        <div class="form-group">
                            <label style="width:100%;">
                                <label>State</label>
                                <select id="stateId" class="form-control" >
                                    <c:forEach var="state" items="${states}" >
                                        <option value="${state.id}" >${state.name}</option>
                                    </c:forEach>
                                </select>
                            </label>
                        </div>
                    </div>
                    <div class="col-md-4 col-xs-12">
                        <div class="form-group">
                            <label>Zip</label>
                            <input id="zip" class="form-control" value="">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label>Phone Number</label>
                    <input id="phone" class="form-control" value="" >
                </div>
                <div class="form-group">
                    <label>Website URL</label>
                    <input id="website" class="form-control" value="" >
                </div>

                <div class="imageupload panel panel-default">
                    <div class="panel-heading clearfix">
                        <h4 class="panel-title pull-left">Other Image</h4>
                        <div class="btn-group pull-right">


                        </div>
                    </div>
                    <div id="advertiserOtherImages"  class="panel-body" >
                        <div class="dz-default dz-message">
                            <div class="droper">
                                <p class="dropicon"><i class="fa fa-cloud-upload"></i> </p>
                                <p class="dropext">Click or Drop your files here</p>
                            </div>
                            <p id="errorObj_"></p>
                        </div>
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
        <div class="col-md-6">
            <div class="panel-body clearfix">
                <h4 class="text-center ar-title">Advertisement Runtime</h4>
                <div class="col-md-6">
                    <div class="form-group">
                        <label>Start Date</label>
                        <input name="runtimeStarts" id="runtimeStarts" class="form-control" value="">
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label>End Date</label>
                        <input name="runtimeEnds" id="runtimeEnds" class="form-control" value="">
                    </div>
                </div>
            </div>
            <div class="panel-body">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">Choose Location</h4>
                    </div>
                    <div class="panel-body">
                        <div>
                            <input id="allLocationSelection" type="checkbox" value="1" />All Location
                        </div>
                        <div style="width: 100%">
                            <select id="locationIds" class="js-example-placeholder-multiple js-states form-control" multiple="multiple">
                                <c:forEach var="location" items="${locations}" >
                                    <option value="${location.id}" ${locationSelected} >${location.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button type="button" class="btn btn-primary btn-sm-new"  data-toggle="modal" data-target="#addLocation" style="margin-top: 15px;"><i class="fa fa-plus" aria-hidden="true"></i>  Add Location</button>
                    </div>
                    <p class="text-danger" id="errorObj_locationIds" style="display: block;"></p>
                </div>
            </div>
            <div class="panel-body">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">Choose Event</h4>
                    </div>
                    <div class="panel-body">
                        <div style="width: 100%">
                            <div>


                                <input id="allEventSelection" type="checkbox" value="1"  />All Event
                            </div>
                            <div>
                                <select id="eventIds" class="js-example-placeholder-multiple js-states form-control" multiple="multiple">
                                    <c:forEach var="event" items="${events}" >
                                        <option value="${event.id}" ${eventSelected} >${event.name}</option>
                                    </c:forEach>

                                </select>
                            </div>
                            <p class="text-danger" id="errorObj_eventIds" style="display: block;"></p>
                        </div>
                        <!-- <button type="button" class="btn btn-primary btn-sm-new"  data-toggle="modal" data-target="#addEvent" style="margin-top: 15px;"><i class="fa fa-plus" aria-hidden="true"></i>  Add Event</button> -->
                    </div>
                </div>
            </div>





        </div>
    </div>
</div>