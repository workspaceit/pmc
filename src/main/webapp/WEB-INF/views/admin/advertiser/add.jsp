<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:genericpage>
    <jsp:body>
        <div id="page-wrapper">
            <div class="container">
                <h3 class="uni-header"><span>Create Advertiser</span></h3>
                <!-- <h3 class="title-top text-uppercase"><span>Create Advertiser</span></h3> -->
                <div class="btn-container-top">
                    <button class="btn btn-action-top" onclick="initSubmit()">Save</button>
                    <button class="btn btn-action-top">Save&nbsp;&&nbsp;Close</button>
                    <button class="btn btn-action-top">Save&nbsp;&&nbsp;New</button>
                    <button class="btn btn-action-top">Cancel</button>
                </div>

                <div class="tabbable-panel clearfix">
                    <div class="tabbable-line">
                        <ul class="nav nav-tabs ">
                            <li class="active">
                                <a href="#tab_default_1" data-toggle="tab">
                                    Advertiser Info<span id="advertiserInfoErrorCount"></span></a>
                            </li>
                            <li>
                                <a href="#tab_default_2" data-toggle="tab">
                                    Gallery Ads</a>
                            </li>
                            <li>
                                <a href="#tab_default_3" data-toggle="tab">
                                    Slideshow Ads</a>
                            </li>
                            <li>
                                <a href="#tab_default_4" data-toggle="tab">
                                    Popup Ads</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="tab_default_1">
                                <div class="row clearfix">
                                    <div class="col-md-6">
                                        <div class="panel-body">
                                            <div class="form-group">
                                                <label>Name</label>
                                                <input id="name" class="form-control">
                                            </div>
                                            <div class="form-group">
                                                <label>Address</label>
                                                <input id="address" class="form-control">
                                            </div>
                                            <div class="row clearfix">
                                                <div class="col-md-4 col-xs-12">
                                                    <div class="form-group">
                                                        <label>
                                                            <a href="#" style="color:#333;font-size: 14px;" data-toggle="modal" data-target="#add-new-city">City</a>
                                                        </label>
                                                        <div>
                                                            <select id="cityId" class="form-control">
                                                                <c:forEach var="city" items="${cities}">
                                                                    <option value="${city.id}">${city.name}</option>
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
                                                                <c:forEach var="state" items="${states}" >
                                                                    <option value="${state.id}">${state.name}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </label>
                                                    </div>
                                                </div>
                                                <div class="col-md-4 col-xs-12">
                                                    <div class="form-group">
                                                        <label>Zip</label>
                                                        <input id="zip" class="form-control">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label>Phone Number</label>
                                                <input id="phone" class="form-control">
                                            </div>
                                            <div class="form-group">
                                                <label>Website URL</label>
                                                <input id="website" class="form-control">
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
                                                    <input name="runtimeStarts" id="runtimeStarts" class="form-control">
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>End Date</label>
                                                    <input name="runtimeEnds" id="runtimeEnds" class="form-control">
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
                                                        <input id="allLocationSelection" type="checkbox" value="1"  />All Location
                                                    </div>
                                                    <div style="width: 100%">
                                                        <select id="locationIds" class="js-example-placeholder-multiple js-states form-control" multiple="multiple">

                                                            <c:forEach var="location" items="${locations}" >
                                                                <option value="${location.id}">${location.name}</option>

                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <button type="button" class="btn btn-primary btn-sm-new"  data-toggle="modal" data-target="#addLocation" style="margin-top: 15px;"><i class="fa fa-plus" aria-hidden="true"></i>  Add Location</button>
                                                </div>
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
                                                                    <option value="${event.id}">${event.name}</option>
                                                                </c:forEach>

                                                            </select>
                                                        </div>

                                                    </div>
                                                    <!-- <button type="button" class="btn btn-primary btn-sm-new"  data-toggle="modal" data-target="#addEvent" style="margin-top: 15px;"><i class="fa fa-plus" aria-hidden="true"></i>  Add Event</button> -->
                                                </div>
                                            </div>
                                        </div>





                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane" id="tab_default_2">
                                <div class="row clearfix">
                                    <div class="col-md-6">
                                        <div class="imageupload panel panel-default">
                                            <div class="panel-heading clearfix">
                                                <h4 class="panel-title pull-left">Logo</h4>
                                                <%--<div>
                                                    <button type="button" class="btn btn-default active">File</button>
                                                    <button type="button" class="btn btn-default">URL</button>
                                                </div>--%>
                                            </div>
                                            <div id="advLogo"  class="panel-body" >
                                                <div class="dz-default dz-message">
                                                    <span>Click here to upload</span>
                                                    <p id="errorObj_"></p>
                                                </div>
                                            </div>
                                            <div class="url-tab panel-body" style="display:none;">
                                                <div class="input-group">
                                                    <input type="text" class="form-control hasclear" placeholder="Image URL">
                                                    <div class="input-group-btn">
                                                        <button type="button" class="btn btn-default" style="margin-left: 5px">Submit</button>
                                                    </div>
                                                </div>
                                                <button type="button" class="btn btn-default" style="display: none;">Remove</button>
                                                <!-- The URL is stored here. -->
                                                <input type="hidden" name="image-url" value="">
                                            </div>
                                        </div>
                                        <div class="imageupload panel panel-default">
                                            <div class="panel-heading clearfix">
                                                <h4 class="panel-title pull-left">Background Image</h4>
                                                <!-- <div class="btn-group pull-right">
                                                    <button type="button" class="btn btn-default active">File</button>
                                                    <button type="button" class="btn btn-default">URL</button>
                                                </div> -->
                                            </div>
                                            <div id="advBackgroundImage"  class="panel-body" >

                                                <div class="dz-default dz-message">
                                                    <span>Click here to upload</span>
                                                    <p id="errorObj_"></p>
                                                </div>
                                            </div>
                                            <div class="panel-footer text-right"><h4 style="font-weight: bold;">Price: <input type="text" class="form-control" value="$10" style="display:inline;width:100px;height:30px;"/></h4>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="imageupload panel panel-default">
                                            <div class="panel-heading clearfix">
                                                <div class="col-md-6 col-xs-6" style="padding-top: 4px">
                                                    <h4 class="panel-title pull-left">Top Ad Banner</h4>
                                                </div>

                                                <div class="col-md-6 col-xs-6 pull-right">

                                                    <div class="" style="margin-left: auto;float:right;">


                                                        <div class="btn-group">
                                                            <button type="button" class="active btn btn-default btn-switch" id="regi1"><i class="fa fa-repeat"></i><span class="hidden-xs">&nbsp;&nbsp;Rotate</span></button>
                                                            <button type="button" class="btn btn-default btn-switch" id="regi2"><i class="fa fa-minus"></i><span class="hidden-xs">&nbsp;&nbsp;Static</span></button>
                                                        </div>

                                                    </div>
                                                    <div class="date-small pull-right">
                                                        <input type="text" class="form-control" name="startdate" />
                                                        <i class="fa fa-calendar"></i>
                                                    </div>
                                                </div>
                                                <div class="btn-group pull-right">

                return data;
                                                </div>
                                            </div>
                                            <div id="advTopBannerImage"  class="panel-body" >
                                                <div class="dz-default dz-message">
                                                    <span>Click here to upload</span>
                                                    <p id="errorObj_"></p>
                                                </div>
                                            </div>

                                            <div class="panel-footer text-right"><h4 style="font-weight: bold;">Price: <input type="text" class="form-control" value="$10" style="display:inline;width:100px;height:30px;"/></h4>
                                            </div>
                                        </div>
                                        <div class="imageupload panel panel-default">
                                            <div class="panel-heading clearfix">
                                                <div class="col-md-6 col-xs-6" style="padding-top: 4px">
                                                    <h4 class="panel-title pull-left">Bottom Ad Banner</h4>
                                                </div>

                                                <div class="col-md-6 col-xs-6 pull-right">

                                                    <div class="" style="margin-left: auto;float:right;">
                                                        <!-- <div class="onoffswitch3">
                                                            <input type="checkbox" name="onoffswitch3" class="onoffswitch3-checkbox" id="myonoffswitch4" checked>
                                                            <label class="onoffswitch3-label" for="myonoffswitch4">
                                                                <span class="onoffswitch3-inner">
                                                                    <span class="onoffswitch3-active"><span class="onoffswitch3-switch"><i class="fa fa-repeat"></i>&nbsp;Rotate</span></span>
                                                                    <span class="onoffswitch3-inactive"><span class="onoffswitch3-switch"><i class="fa fa-minus"></i>&nbsp;Static</span></span>
                                                                </span>
                                                            </label>
                                                        </div> -->
                                                        <div class="btn-group">
                                                            <button type="button" class="active btn btn-default btn-switch" id="regi3"><i class="fa fa-repeat"></i><span class="hidden-xs">&nbsp;&nbsp;Rotate</span></button>
                                                            <button type="button" class="btn btn-default btn-switch" id="regi4"><i class="fa fa-minus"></i><span class="hidden-xs">&nbsp;&nbsp;Static</span></button>
                                                        </div>

                                                    </div>
                                                    <div class="date-small pull-right">
                                                        <input type="text" class="form-control" name="startdate" />
                                                        <i class="fa fa-calendar"></i>
                                                    </div>
                                                    <!-- <div class="" style="margin-left: auto;float:right;margin-right:5px;">
                                                    <label style="font-size: 13px;font-weight: 600">Duration : </label>
                                                    <select class="form-control" style="padding: 0px 12px 0px 12px;height: 28px;width: 75px;display: inline;">

                                                      <option value="">1s</option>
                                                      <option value="">2s</option>
                                                      <option value="">3s</option>
                                                      <option value="">4s</option>
                                                    </select>
                                                    </div> -->
                                                </div>



                                            </div>
                                            <div id="advBottomBannerImage"   class="panel-body" >
                                                <div class="dz-default dz-message">
                                                    <span>Click here to upload</span>
                                                    <p id="errorObj_"></p>
                                                </div>
                                            </div>
                                            <div class="panel-footer text-right"><h4 style="font-weight: bold;">Price: <input type="text" class="form-control" value="$10" style="display:inline;width:100px;height:30px;"/></h4></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane" id="tab_default_3">
                                <div class="imageupload panel panel-default">
                                    <div class="panel-heading clearfix">
                                        <div class="col-md-6 col-xs-6" style="padding-top: 4px">
                                            <h4 class="panel-title pull-left">Slideshow banner ad Upload</h4>
                                        </div>

                                        <div class="col-md-6 col-xs-6 pull-right">

                                            <div class="" style="margin-left: auto;float:right;">
                                                <!-- <div class="onoffswitch3">
                                                    <input type="checkbox" name="onoffswitch3" class="onoffswitch3-checkbox" id="myonoffswitch5" checked>
                                                    <label class="onoffswitch3-label" for="myonoffswitch5">
                                                        <span class="onoffswitch3-inner">
                                                            <span class="onoffswitch3-active"><span class="onoffswitch3-switch"><i class="fa fa-repeat"></i>&nbsp;Rotate</span></span>
                                                            <span class="onoffswitch3-inactive"><span class="onoffswitch3-switch"><i class="fa fa-minus"></i>&nbsp;Static</span></span>
                                                        </span>
                                                    </label>
                                                </div> -->
                                                <div class="btn-group">
                                                    <button type="button" class="active btn btn-default btn-switch" id="regi6"><i class="fa fa-repeat"></i><span class="hidden-xs">&nbsp;&nbsp;Rotate</span></button>
                                                    <button type="button" class="btn btn-default btn-switch" id="regi7"><i class="fa fa-minus"></i><span class="hidden-xs">&nbsp;&nbsp;Static</span></button>
                                                </div>
                                            </div>
                                            <div class="date-small pull-right">
                                                <input type="text" class="form-control" name="startdate" />
                                                <i class="fa fa-calendar"></i>
                                            </div>

                                            <!-- <div class="" style="margin-left: auto;float:right;margin-right:5px;">
                                            <label style="font-size: 13px;font-weight: 600">Duration : </label>
                                            <select class="form-control" style="padding: 0px 12px 0px 12px;height: 28px;width: 75px;display: inline;">

                                              <option value="">1s</option>
                                              <option value="">2s</option>
                                              <option value="">3s</option>
                                              <option value="">4s</option>
                                            </select>
                                            </div> -->
                                        </div>
                                        <div class="btn-group pull-right">

                                        </div>
                                    </div>
                                    <div id="advSlideShowBanner"  class="panel-body" >
                                        <div class="dz-default dz-message">
                                            <span>Click here to upload</span>
                                            <p id="errorObj_"></p>
                                        </div>
                                    </div>
                                    <div class="panel-footer clearfix">
                          <span class="pull-left" style="font-weight: bold;">Duration:
                            <select class="form-control" style="display:inline;width:100px;height:35px;" >
                            <option>
                              2s
                            </option>
                            <option>
                              3s
                            </option>
                            <option>
                              4s
                            </option>
                          </select>
                          </span>
                                        <span class="pull-right " style="font-weight: bold;">Price: <input type="text" class="form-control" value="$10" style="display:inline;width:100px;height:35px;"/></span>
                                    </div>
                                </div>
                                <div class="imageupload panel panel-default">
                                    <div class="panel-heading clearfix">
                                        <div class="col-md-6 col-xs-6" style="padding-top: 4px">
                                            <h4 class="panel-title pull-left">Slideshow Video Ad upload</h4>
                                        </div>

                                        <div class="col-md-6 col-xs-6 pull-right">

                                            <div class="" style="margin-left: auto;float:right;">
                                                <!-- <div class="onoffswitch3">
                                                    <input type="checkbox" name="onoffswitch3" class="onoffswitch3-checkbox" id="myonoffswitch6" checked>
                                                    <label class="onoffswitch3-label" for="myonoffswitch6">
                                                        <span class="onoffswitch3-inner">
                                                            <span class="onoffswitch3-active"><span class="onoffswitch3-switch"><i class="fa fa-repeat"></i>&nbsp;Rotate</span></span>
                                                            <span class="onoffswitch3-inactive"><span class="onoffswitch3-switch"><i class="fa fa-minus"></i>&nbsp;Static</span></span>
                                                        </span>
                                                    </label>
                                                </div> -->
                                                <div class="btn-group">
                                                    <button type="button" class="active btn btn-default btn-switch" id="regi8"><i class="fa fa-repeat"></i><span class="hidden-xs">&nbsp;&nbsp;Rotate</span></button>
                                                    <button type="button" class="btn btn-default btn-switch" id="regi9"><i class="fa fa-minus"></i><span class="hidden-xs">&nbsp;&nbsp;Static</span></button>
                                                </div>
                                            </div>
                                            <div class="date-small pull-right">
                                                <input type="text" class="form-control" name="startdate" />
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <!-- <div class="" style="margin-left: auto;float:right;margin-right:5px;">
                                            <label style="font-size: 13px;font-weight: 600">Duration : </label>
                                            <select class="form-control" style="padding: 0px 12px 0px 12px;height: 28px;width: 75px;display: inline;">

                                              <option value="">1s</option>
                                              <option value="">2s</option>
                                              <option value="">3s</option>
                                              <option value="">4s</option>
                                            </select>
                                            </div> -->
                                        </div>
                                        <div class="btn-group pull-right">

                                        </div>
                                    </div>
                                    <div id="advSlideShowVideo"  class="panel-body" >
                                        <div class="dz-default dz-message">
                                            <span>Click here to upload</span>
                                            <p id="errorObj_"></p>
                                        </div>
                                    </div>
                                    <div class="panel-footer clearfix">
                          <span class="pull-left" style="font-weight: bold;">Duration:
                            <select class="form-control" style="display:inline;width:100px;height:35px;" >
                            <option>
                              2s
                            </option>
                            <option>
                              3s
                            </option>
                            <option>
                              4s
                            </option>
                          </select>
                          </span>
                                        <span class="pull-right " style="font-weight: bold;">Price: <input type="text" class="form-control" value="$10" style="display:inline;width:100px;height:35px;"/></span>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane" id="tab_default_4">
                                <div class="imageupload panel panel-default">
                                    <div class="panel-heading clearfix">
                                        <div class="col-md-6 col-xs-6" style="padding-top: 4px">
                                            <h4 class="panel-title pull-left">SMS pop up banner/Video upload</h4>
                                        </div>

                                        <div class="col-md-6 col-xs-6 pull-right">

                                            <div class="" style="margin-left: auto;float:right;">
                                                <!-- <div class="onoffswitch3">
                                                    <input type="checkbox" name="onoffswitch3" class="onoffswitch3-checkbox" id="myonoffswitch7" checked>
                                                    <label class="onoffswitch3-label" for="myonoffswitch7">
                                                        <span class="onoffswitch3-inner">
                                                            <span class="onoffswitch3-active"><span class="onoffswitch3-switch"><i class="fa fa-repeat"></i>&nbsp;Rotate</span></span>
                                                            <span class="onoffswitch3-inactive"><span class="onoffswitch3-switch"><i class="fa fa-minus"></i>&nbsp;Static</span></span>
                                                        </span>
                                                    </label>
                                                </div> -->
                                                <div class="btn-group">
                                                    <button type="button" class="active btn btn-default btn-switch" id="regi10"><i class="fa fa-repeat"></i><span class="hidden-xs">&nbsp;&nbsp;Rotate</span></button>
                                                    <button type="button" class="btn btn-default btn-switch" id="regi11"><i class="fa fa-minus"></i><span class="hidden-xs">&nbsp;&nbsp;Static</span></button>
                                                </div>
                                            </div>
                                            <div class="date-small pull-right">
                                                <input type="text" class="form-control" name="startdate" />
                                                <i class="fa fa-calendar"></i>
                                            </div>

                                            <!-- <div class="" style="margin-left: auto;float:right;margin-right:5px;">
                                            <label style="font-size: 13px;font-weight: 600">Duration : </label>
                                            <select class="form-control" style="padding: 0px 12px 0px 12px;height: 28px;width: 75px;display: inline;">

                                              <option value="">1s</option>
                                              <option value="">2s</option>
                                              <option value="">3s</option>
                                              <option value="">4s</option>
                                            </select>
                                            </div> -->
                                        </div>
                                        <div class="btn-group pull-right">

                                        </div>
                                    </div>
                                    <div id="advSmsPopUpVideo"  class="panel-body" >
                                        <div class="dz-default dz-message">
                                            <span>Click here to upload</span>
                                            <p id="errorObj_"></p>
                                        </div>
                                    </div>
                                    <div class="panel-footer clearfix">
                          <span class="pull-left" style="font-weight: bold;">Duration:
                            <select class="form-control" style="display:inline;width:100px;height:35px;" >
                            <option>
                              2s
                            </option>
                            <option>
                              3s
                            </option>
                            <option>
                              4s
                            </option>
                          </select>
                          </span>
                                        <span class="pull-right " style="font-weight: bold;">Price: <input type="text" class="form-control" value="$10" style="display:inline;width:100px;height:35px;"/></span>
                                    </div>
                                </div>
                                <div class="imageupload panel panel-default">
                                    <div class="panel-heading clearfix">
                                        <div class="col-md-6 col-xs-6" style="padding-top: 4px">
                                            <h4 class="panel-title pull-left">Email pop up banner/Video upload</h4>
                                        </div>

                                        <div class="col-md-6 col-xs-6 pull-right">

                                            <div class="" style="margin-left: auto;float:right;">
                                                <!-- <div class="onoffswitch3">
                                                    <input type="checkbox" name="onoffswitch3" class="onoffswitch3-checkbox" id="myonoffswitch8" checked>
                                                    <label class="onoffswitch3-label" for="myonoffswitch8">
                                                        <span class="onoffswitch3-inner">
                                                            <span class="onoffswitch3-active"><span class="onoffswitch3-switch"><i class="fa fa-repeat"></i>&nbsp;Rotate</span></span>
                                                            <span class="onoffswitch3-inactive"><span class="onoffswitch3-switch"><i class="fa fa-minus"></i>&nbsp;Static</span></span>
                                                        </span>
                                                    </label>
                                                </div> -->
                                                <div class="btn-group">
                                                    <button type="button" class="active btn btn-default btn-switch" id="regi12"><i class="fa fa-repeat"></i><span class="hidden-xs">&nbsp;&nbsp;Rotate</span></button>
                                                    <button type="button" class="btn btn-default btn-switch" id="regi13"><i class="fa fa-minus"></i><span class="hidden-xs">&nbsp;&nbsp;Static</span></button>
                                                </div>
                                            </div>
                                            <div class="date-small pull-right">
                                                <input type="text" class="form-control" name="startdate" />
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <!-- <div class="" style="margin-left: auto;float:right;margin-right:5px;">
                                            <label style="font-size: 13px;font-weight: 600">Duration : </label>
                                            <select class="form-control" style="padding: 0px 12px 0px 12px;height: 28px;width: 75px;display: inline;">

                                              <option value="">1s</option>
                                              <option value="">2s</option>
                                              <option value="">3s</option>
                                              <option value="">4s</option>
                                            </select>
                                            </div> -->
                                        </div>
                                        <div class="btn-group pull-right">

                                        </div>
                                    </div>
                                    <div id="advEmailPopUpVideo"  class="panel-body" >
                                        <div class="dz-default dz-message">
                                            <span>Click here to upload</span>
                                            <p id="errorObj_"></p>
                                        </div>
                                    </div>
                                    <div class="panel-footer clearfix">
                          <span class="pull-left" style="font-weight: bold;">Duration:
                            <select class="form-control" style="display:inline;width:100px;height:35px;" >
                            <option>
                              2s
                            </option>
                            <option>
                              3s
                            </option>
                            <option>
                              4s
                            </option>
                          </select>
                          </span>
                                        <span class="pull-right " style="font-weight: bold;">Price: <input type="text" class="form-control" value="$10" style="display:inline;width:100px;height:35px;"/></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- /.container-fluid -->

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
        <script type="text/javascript" src="http://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
        <!-- select2 js -->
        <!-- Select2 css -->
        <link href="<s:url value="/resources/css/select2.css"/>" rel="stylesheet"/>
        <link href="<s:url value="/resources/css/daterangepicker.css"/>" rel="stylesheet"/>

        <script src="<s:url value="/resources/js/select2.js"/>"></script>
        <script type="text/javascript" src="http://cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.8.1/jquery.timepicker.min.js"></script>
        <%--<link rel="stylesheet" type="text/css" href="http://cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.css" />--%>

        <link href="<s:url value="/resources/css/dropzone.css"/>" rel="stylesheet">
        <script src="<s:url value="/resources/js/dropzone.min.js"/>"></script>
        <script src="<s:url value="/resources/developer/js/temp-file/common.js"/>"></script>
        <script src="<s:url value="/resources/developer/js/pmc-adv/common.js"/>"></script>


        <script>
            function notifyUser(id,response,errorFound){
                if(errorFound){
                    $("#"+id).html("( "+response.responseJSON.length+" )");
                }
            }
            function submit(marker){
                validateAdvertiser();
            }
            function initSubmit(){
                UnBindErrors("errorObj_");
                submit(0);
            }
            function getAdvertiserInfoData(){
                var name = $('#name').val();
                var address = $('#address').val();
                var cityId = $('#cityId').val();
                var eventIds = $('#eventIds').val();
                var stateId = $('#stateId').val();
                var zip = $('#zip').val();
                var phone = $('#phone').val();
                var website = $('#website').val();
                var otherImage = $('#otherImage').val();
                var runtimeStarts = $('#runtimeStarts').val();
                var runtimeEnds = $('#runtimeEnds').val();
                var locationIds = $('#locationIds').val();
                var createdAt = $('#createdAt').val();
                var updatedAt = $('#updatedAt').val();
                var createdBy = $('#createdBy').val();
                var allLocationSelected = hasAllLocationSelected();
                var allEventSelected = hasAllEventSelected();

                return  {
                            name: name,
                            address: address,
                            cityId: cityId,
                            eventIds: eventIds,
                            stateId: stateId,
                            zip: zip,
                            phone: phone,
                            website: website,
                            otherImage: otherImage,
                            runtimeStarts: runtimeStarts,
                            runtimeEnds: runtimeEnds,
                            locationIds: locationIds,
                            isAllLocationSelected:allLocationSelected,
                            isAllEventSelected:allEventSelected
                        };
            }
            function validateAdvertiser(){
                var data = getAdvertiserInfoData();
                console.log(data);
                $.ajax({
                    url: BASEURL+"api/pmc-adv/validate-create",
                    type: "POST",
                    data: data,
                    statusCode: {
                        500: function(response) {
                            console.log(response);
                        },
                        401: function(response) {
                            console.log(response.responseJSON);
                        },
                        422: function(response) {
                            console.log(response.responseJSON);
                            BindErrorsWithHtml("errorObj_",response.responseJSON,true);
                            notifyUser("advertiserInfoErrorCount",response,true);
                        }
                    },
                    success: function(response) {
                        console.log(response);
                    }
                });
            }
            $(document).ready(function(){
                var advertiserOtherImages  = configAdvertBdImgDropZone("advertiserOtherImages","other-images",1,1,function(response){
                    storeToken(ADV_IMG_TYPE._ADVERTISER_OTHER_IMAGES_TOKEN,response.token);
                },function(){
                    removeToken(ADV_IMG_TYPE._LOGO_TOKEN);
                });
                var logoBackgroundImage = configAdvertBdImgDropZone("advLogo","logo-image",1,1,function(response){
                    storeToken(ADV_IMG_TYPE._LOGO_TOKEN,response.token);
                },function(){
                    removeToken(ADV_IMG_TYPE._LOGO_TOKEN);
                });

                var backgroundImage = configAdvertBdImgDropZone("advBackgroundImage","background-image",1,1,function(response){
                    storeToken(ADV_IMG_TYPE._BACKGROUND_IMAGE,response.token);
                },function(){
                    removeToken(ADV_IMG_TYPE._BACKGROUND_IMAGE);
                });

                var topBanner = configAdvertBdImgDropZone("advTopBannerImage","top-banner",1,1,function(response){
                    storeToken(ADV_IMG_TYPE._TOP_BANNER_TOKEN,response.token);
                },function(){
                    removeToken(ADV_IMG_TYPE._TOP_BANNER_TOKEN);
                });

                var bottomBanner = configAdvertBdImgDropZone("advBottomBannerImage","bottom-banner",1,1,function(response){
                    storeToken(ADV_IMG_TYPE._BOTTOM_BANNER_TOKEN,response.token);
                },function(){
                    removeToken(ADV_IMG_TYPE._BOTTOM_BANNER_TOKEN);
                });

                var slideShowBanner = configAdvertBdImgDropZone("advSlideShowBanner","slide-show-banner",1,1,function(response){
                    storeToken(ADV_IMG_TYPE._SLIDESHOW_BANNER_TOKEN,response.token);
                },function(){
                    removeToken(ADV_IMG_TYPE._SLIDESHOW_BANNER_TOKEN);
                });

                var slideShowVideo = configAdvertBdImgDropZone("advSlideShowVideo","slide-show-video",1,3,function(response){
                    storeToken(ADV_IMG_TYPE._SLIDESHOW_VIDEO_TOKEN,response.token);
                },function(){
                    removeToken(ADV_IMG_TYPE._SLIDESHOW_VIDEO_TOKEN);
                });

                var emailPopUpVideo = configAdvertBdImgDropZone("advEmailPopUpVideo","email-popup-video",1,3,function(response){
                    storeToken(ADV_IMG_TYPE._EMAIL_POPUP_VIDEO_TOKEN,response.token);
                },function(){
                    removeToken(ADV_IMG_TYPE._EMAIL_POPUP_VIDEO_TOKEN);
                });

                var smsPopUpVideo = configAdvertBdImgDropZone("advSmsPopUpVideo","sms-popup-video",1,3,function(response){
                    storeToken(ADV_IMG_TYPE._SMS_POPUP_VIDEO_TOKEN,response.token);
                },function(){
                    removeToken(ADV_IMG_TYPE._SMS_POPUP_VIDEO_TOKEN);
                });

            });
            function configAdvertBdImgDropZone(elementId,param,maxFile,maxFileSize,fnSuccess,fnError){
                var advImgDropZone = new Dropzone("#"+elementId,
                    {
                        url: BASEURL+"file/upload/adv/"+param,
                        method:"post",
                        paramName:"advImg",
                        maxFilesize: maxFileSize,
                        maxFiles:maxFile,
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
                                    removeImageByToken(file.token,fnError);
                                    advImgDropZone.removeFile(file);
                                });
                            });

                        },
                        error:function(file,response){
                            var msg = (typeof response == "object")?((response.length>0)?response[0].msg:""):response;
                            $("#profileImg").find(".dz-error-message span").html(msg);
                        },
                        success:function(file,response){

                            file.token = response.token;
                            console.log(file);
                            fnSuccess(response);
                        }
                    }
                );
                return advImgDropZone;
            }


            $(function() {
                $('#runtimeStarts').daterangepicker({
                        singleDatePicker: true,
                        showDropdowns: true
                    },

                    function(start, end, label) {
                        //var years = moment().diff(start, 'years');
                        // alert("You are " + years + " years old.");
                    });
                $('#runtimeEnds').daterangepicker({
                        singleDatePicker: true,
                        showDropdowns: true
                    },

                    function(start, end, label) {
                        /*var years = moment().diff(start, 'years');
                        alert("You are " + years + " years old.");*/
                    });

                $('input[name="starttime"]').timepicker({
                    timeFormat: 'hh:mm tt'
                });

                $(".js-example-placeholder-multiple").select2({
                    placeholder: "",
                    allowClear: true
                });

                $("#allLocationSelection").click(function(){
                    var isChecked = $("#allLocationSelection:checked").length;
                    if(isChecked){
                        $("#locationIds").parent().hide();
                    }else{
                        $("#locationIds").parent().show();
                    }
                });
                $("#allEventSelection").click(function(){
                    var isChecked = $("#allEventSelection:checked").length;
                    if(isChecked){
                        $("#eventIds").parent().hide();
                    }else{
                        $("#eventIds").parent().show();
                    }
                });


            });
            function hasAllLocationSelected(){
                var isChecked = $("#allLocationSelection:checked").length;
                return (isChecked==1)?true:false;
            }
            function hasAllEventSelected(){
                var isChecked = $("#allEventSelection:checked").length;
                return (isChecked==1)?true:false;
            }
        </script>

    </jsp:body>

</t:genericpage>