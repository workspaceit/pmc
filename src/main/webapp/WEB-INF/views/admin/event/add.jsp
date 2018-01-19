<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:genericpage>
    <jsp:body>
        <div id="page-wrapper" style="min-height: 563px !important;">
            <div class="container">
                <h3 class="uni-header"><span>Create Event</span></h3>
                <div class="btn-container-top">
                    <button class="btn btn-action-top" id="save-watermark-btn">Save</button>
                    <button class="btn btn-action-top" id="save-close-watermark-btn">Save&nbsp;&&nbsp;Close</button>
                    <button class="btn btn-action-top" id="save-new-watermark-btn">Save&nbsp;&&nbsp;New</button>
                    <a href="<c:url value="/admin/event/all"/>" class="btn btn-action-top">Cancel</a>
                </div>
                <!-- Page Heading -->
                <div class="row cstm-tab tab-pane clearfix" style="margin:0px !important">
                    <form id="event-form" method="post">
                        <div class="col-md-8" style="padding-left: 0px">

                            <div class="form-group">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">Event Information</h4>
                                    </div>
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label>Event Name</label>
                                            <input type="text" id="eventName" name="name" class="form-control">
                                        </div>

                                        <div class="row clearfix">
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label>Start Date</label>
                                                    <input name="startDate" id="startDate" class="form-control">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group timepick">
                                                    <label>Start time</label><br>
                                                    <input type="time" value="12:45" id="startTime" name="startTime" class="form-control">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label>End Date</label>
                                                    <input name="endDate" id="endDate" class="form-control">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group timepick">
                                                    <label>End time</label><br>
                                                    <input type="time" value="16:45" id="endTime" name="endTime" class="form-control">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group clearfix">
                                            <div class="yes_no_container">
                                                <input id="input1" name="private" value="false" checked type="radio"/>
                                                <label for="input1">Public</label>
                                                <input id="input2" name="private" value="true" type="radio"/>
                                                <label for="input2">Private</label>
                                                <span class="slider"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>Event Venue</label>
                                            <select id="venueId" name="venueId" style="width: 100%;" tabindex="-1"
                                                    class="form-control select2-hidden-accessible" aria-hidden="true">
                                            </select>
                                            <button type="button" class="btn btn-primary btn-sm-new" data-toggle="modal"
                                                    data-target="#addLocation" style="margin-top: 15px;"><i
                                                    class="fa fa-plus" aria-hidden="true"></i>Add new Venue
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="row clearfix">
                                        <div class="col-md-6">
                                            <div class="form-group">

                                                <div class="panel panel-default">
                                                    <div class="panel-heading">
                                                        <h4 class="panel-title">Choose Photographer</h4>
                                                    </div>
                                                    <div class="panel-body">
                                                        <div style="width: 100%">
                                                            <select id="photographer-select2" name="photographerIds" multiple="true" style="width: 100%;" tabindex="-1"
                                                                    class="select2-hidden-accessible" aria-hidden="true">
                                                            </select>
                                                        </div>
                                                        <%--<button type="button" class="btn btn-primary btn-sm-new"--%>
                                                                <%--data-toggle="modal" data-target="#addPhotographer"--%>
                                                                <%--style="margin-top: 15px;">--%>
                                                            <%--<i class="fa fa-plus" aria-hidden="true"></i> Add New Photographer--%>
                                                        <%--</button>--%>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">

                                                <div class="panel panel-default">
                                                    <div class="panel-heading">
                                                        <h4 class="panel-title">Choose Advertiser</h4>
                                                    </div>
                                                    <div class="panel-body">
                                                        <div style="width: 100%">
                                                            <select id="advertiser-select2" name="advertiserIds" multiple="true" style="width: 100%;" tabindex="-1"
                                                                    class="select2-hidden-accessible" aria-hidden="true">
                                                            </select>
                                                        </div>

                                                        <!-- <button type="button" class="btn btn-primary btn-sm-new"  data-toggle="modal" data-target="#addAdvertiser" style="margin-top: 15px;"><i class="fa fa-plus" aria-hidden="true"></i>  Add new Advertiser</button> -->
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="col-md-4">
                            <div class="imageupload panel panel-default">
                                <div class="panel-heading clearfix">
                                    <h4 class="panel-title pull-left">Add Event Photo</h4>
                                    <div  class="btn-group pull-right">

                                    </div>
                                </div>
                                <div class="file-tab panel-body">
                                    <div id="eventImg">
                                        <div class="dz-default dz-message">
                                            <span>Drop file or click to select</span>
                                            <p id="errorObj_profilePictureToken"></p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <hr/>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">Choose Watermark</h4>
                                </div>
                                <div class="panel-body">
                                    <div style="width: 100%">
                                        <select id="watermark-select2" value="" name="watermarkIds" style="width: 100%;" tabindex="-1"
                                                class="select2-hidden-accessible" aria-hidden="true">
                                        </select>
                                    </div>

                                </div>
                            </div>
                            <div style="text-align: center;">
                                <label style="font-weight: 200;font-size: 22px;">view as</label>
                            </div>
                            <div class="col-md-12">
                                <a class="new-cstm-btn" style="width:100%;"
                                   href="http://163.53.151.2/pcm-dash/album-list.html" target="_blank">
                                    <i class="fa fa-camera"></i>&nbsp;&nbsp; Photographer View
                                </a>
                            </div>
                            <div class="col-md-12">
                                <a class="new-cstm-btn" style="width:100%;"
                                   href="http://163.53.151.2/pcm-dash/user-dash.html" target="_blank">
                                    <i class="fa fa-user"></i>&nbsp;&nbsp;Normal User View
                                </a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
                <%-- After image add Dropzone Image preview --%>
            <div id="dropZonePreview" style="display: none">
                <div class="dz-preview dz-file-preview">
                    <div class="dz-details">
                        <div class="dz-filename"><span data-dz-name></span></div>
                        <div class="dz-size" data-dz-size></div>
                        <img data-dz-thumbnail/>
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
        <link href="<s:url value="/resources/css/select2.css"/>" rel="stylesheet"/>
        <link href="<s:url value="/resources/css/daterangepicker.css"/>" rel="stylesheet"/>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.8.1/jquery.timepicker.min.css" rel="stylesheet"/>
        <script src="<s:url value="/resources/js/select2.js"/>"></script>
        <script type="text/javascript"
                src="http://cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.js"></script>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.8.1/jquery.timepicker.min.js"></script>
        <!-- /#wrapper -->
        <!-- dropzone -->
        <link href="<s:url value="/resources/css/dropzone.css"/>" rel="stylesheet">
        <script src="<s:url value="/resources/js/dropzone.min.js"/>"></script>
        <script src="<s:url value="/resources/developer/js/event/create.js"/>" ></script>
    </jsp:body>
</t:genericpage>