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
                    <button class="btn btn-action-top">Save</button>
                    <button class="btn btn-action-top">Save&nbsp;&&nbsp;Close</button>
                    <button class="btn btn-action-top">Save&nbsp;&&nbsp;New</button>
                    <button class="btn btn-action-top">Cancel</button>
                </div>

                <div class="tabbable-panel clearfix">
                    <div class="tabbable-line">
                        <ul class="nav nav-tabs ">
                            <li class="active">
                                <a href="#tab_default_1" data-toggle="tab">
                                    Advertiser Info</a>
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
                                                <input class="form-control">
                                            </div>
                                            <div class="form-group">
                                                <label>Address</label>
                                                <input class="form-control">
                                            </div>
                                            <div class="row clearfix">
                                                <div class="col-md-4 col-xs-12">
                                                    <div class="form-group">
                                                        <label>
                                                            <a href="#" style="color:#333;font-size: 14px;" data-toggle="modal" data-target="#add-new-city">City</a>
                                                        </label>
                                                        <div>
                                                            <select class="js-example-placeholder-multiple js-states form-control" multiple="multiple">
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
                                                            <select class="js-example-placeholder-multiple js-states form-control" multiple="multiple">
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
                                                        <input class="form-control">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label>Phone Number</label>
                                                <input class="form-control">
                                            </div>
                                            <div class="form-group">
                                                <label>Website URL</label>
                                                <input class="form-control">
                                            </div>

                                            <div class="imageupload panel panel-default">
                                                <div class="panel-heading clearfix">
                                                    <h4 class="panel-title pull-left">Other Image</h4>
                                                    <div class="btn-group pull-right">


                                                    </div>
                                                </div>
                                                <div class="file-tab panel-body">
                                                    <form action="/upload-target" class="dropzone dz-clickable"><div class="dz-default dz-message"><span>Drop files here to upload</span></div></form>
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
                                                    <input name="startdate" id="startDate" class="form-control">
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>End Date</label>
                                                    <input name="enddate" id="endDate" class="form-control">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel-body">
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">Choose Location</h4>
                                                </div>
                                                <div class="panel-body">
                                                    <div style="width: 100%">
                                                        <select class="form-control">
                                                            <option value="0">All Locations</option>
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
                                                        <select class="form-control">
                                                            <option value="0">All Event</option>
                                                            <c:forEach var="event" items="${events}" >
                                                                <option value="${event.id}">${event.name}</option>
                                                            </c:forEach>

                                                        </select>
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
                                                    <p id="errorObj_profilePictureToken"></p>
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

            $(document).ready(function(){

                var logoBackgroundImage = configAdvertBdImgDropZone("advLogo","logo-image",1,1);
                var backgroundImage = configAdvertBdImgDropZone("advBackgroundImage","background-image",1,1);
                var topBanner = configAdvertBdImgDropZone("advTopBannerImage","top-banner",1,1);
                var bottomBanner = configAdvertBdImgDropZone("advBottomBannerImage","bottom-banner",1,1);

                var slideShowBanner = configAdvertBdImgDropZone("advSlideShowBanner","slide-show-banner",1,1);
                var slideShowVideo = configAdvertBdImgDropZone("advSlideShowVideo","slide-show-video",1,3);

                var emailPopUpVideo = configAdvertBdImgDropZone("advEmailPopUpVideo","email-popup-video",1,3);
                var smsPopUpVideo = configAdvertBdImgDropZone("advSmsPopUpVideo","sms-popup-video",1,3);

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
                                    removeImageByToken(file.token,fnError(response));
                                   /* */
                                    profilePictureToken = 0;

                                    var _ref;
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
                            fnSuccess(file,response);
                        }
                    }
                );
                return advImgDropZone;
            }


            $(function() {
                $('input[name="startdate"]').daterangepicker({
                        singleDatePicker: true,
                        showDropdowns: true
                    },

                    function(start, end, label) {
                        var years = moment().diff(start, 'years');
                        // alert("You are " + years + " years old.");
                    });
                $('input[name="enddate"]').daterangepicker({
                        singleDatePicker: true,
                        showDropdowns: true
                    },

                    function(start, end, label) {
                        var years = moment().diff(start, 'years');
                        alert("You are " + years + " years old.");
                    });

                $('input[name="starttime"]').timepicker({
                    timeFormat: 'hh:mm tt'
                });

                $(".js-example-placeholder-multiple").select2({
                    placeholder: "",
                    allowClear: true
                });
            });

        </script>

    </jsp:body>

</t:genericpage>