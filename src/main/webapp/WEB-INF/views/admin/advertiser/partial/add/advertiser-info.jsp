
<div class="tab-pane active" id="tab_default_1">
    <div class="row clearfix">
        <div class="col-md-6">
            <div class="panel-body">
                <div class="form-group">
                    <label>Name</label>
                    <input id="name" class="form-control" value="${advertiser.name}">
                </div>
                <div class="form-group">
                    <label>Address</label>
                    <input id="address" class="form-control" value="${advertiser.address}" >
                </div>
                <div class="row clearfix">
                    <div class="col-md-4 col-xs-12">
                        <div class="form-group">
                            <label>
                                <a href="#" style="color:#333;font-size: 14px;" data-toggle="modal" data-target="#add-new-city">City</a>
                            </label>
                            <div>
                                <select id="cityId" class="form-control">
                                    <c:set var="cityOptionSelected" value="" ></c:set>
                                    <c:forEach var="city" items="${cities}">
                                        <c:if test="${advertiser.city.id == city.id}">
                                            <c:set var="cityOptionSelected" value="selected=\"selected\"" ></c:set>
                                        </c:if>

                                        <option value="${city.id}" ${cityOptionSelected} >${city.name}</option>
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
                                <a href="#" style="color:#333;font-size: 14px;" data-toggle="modal" data-target="#add-new-state">State</a>
                                <select id="stateId" class="form-control" >
                                    <c:set var="stateOptionSelected" value="" ></c:set>
                                    <c:forEach var="state" items="${states}" >
                                        <c:if test="${state.id == advertiser.state.id }" >
                                            <c:set var="stateOptionSelected" value="selected=\"selected\"" ></c:set>
                                        </c:if>
                                        <option value="${state.id}" ${stateOptionSelected}>${state.name}</option>
                                    </c:forEach>
                                </select>
                            </label>
                        </div>
                    </div>
                    <div class="col-md-4 col-xs-12">
                        <div class="form-group">
                            <label>Zip</label>
                            <input id="zip" class="form-control" value="${advertiser.zip}">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label>Phone Number</label>
                    <input id="phone" class="form-control" value="${advertiser.phone}" >
                </div>
                <div class="form-group">
                    <label>Website URL</label>
                    <input id="website" class="form-control" value="${advertiser.website}" >
                </div>

                <div class="imageupload panel panel-default">
                    <div class="panel-heading clearfix">
                        <h4 class="panel-title pull-left">Other Image</h4>
                        <div class="btn-group pull-right">


                        </div>
                    </div>
                    <div id="advertiserOtherImages"  class="panel-body" >
                        <div class="dz-default dz-message">
                            <span>Click here to upload</span>
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
                        <input name="runtimeStarts" id="runtimeStarts" class="form-control" value="<fmt:formatDate  value="${advertiser.runtimeStarts}" pattern="dd/MM/yyyy"></fmt:formatDate>">
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label>End Date</label>
                        <input name="runtimeEnds" id="runtimeEnds" class="form-control" value="<fmt:formatDate  value="${advertiser.runtimeEnds}" pattern="dd/MM/yyyy"></fmt:formatDate>">
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
                            <c:set var="allEventSelectionChecked" value="" ></c:set>
                            <c:if test="${advertiser.allLocations}" >
                                <c:set var="allEventSelectionChecked" value="checked" ></c:set>
                            </c:if>
                            <input id="allLocationSelection" type="checkbox" value="1"  ${allEventSelectionChecked} />All Location
                        </div>
                        <div style="width: 100%">
                            <select id="locationIds" class="js-example-placeholder-multiple js-states form-control" multiple="multiple">
                                <c:forEach var="location" items="${locations}" >
                                    <c:set var="locationSelected" value="" ></c:set>
                                    <c:forEach var="advertiserLocation" items="${advertiser.locations}" >
                                        <c:if test="${advertiserLocation.id == location.id}">
                                            <c:set var="locationSelected" value="selected=\"selected\"" ></c:set>
                                        </c:if>
                                    </c:forEach>
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

                                <c:set var="allEventSelectionChecked" value="" ></c:set>
                                <c:if test="${advertiser.allEvents}" >
                                    <c:set var="allEventSelectionChecked" value="checked" ></c:set>
                                </c:if>

                                <input id="allEventSelection" type="checkbox" value="1"  ${allEventSelectionChecked} />All Event
                            </div>
                            <div>
                                <select id="eventIds" class="js-example-placeholder-multiple js-states form-control" multiple="multiple">
                                    <c:forEach var="event" items="${events}" >
                                        <c:set var="eventSelected" value="" ></c:set>
                                        <c:forEach var="advertiserEvent" items="${advertiser.events}" >
                                            <c:if test="${advertiserEvent.id == event.id}">
                                                <c:set var="eventSelected" value="selected=\"selected\"" ></c:set>
                                            </c:if>
                                        </c:forEach>
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