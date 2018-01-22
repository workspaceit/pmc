<%@ page import="com.workspaceit.pmc.constant.advertisement.PopupAdConstant" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="tab-pane" id="tab_default_4">
    <div class="imageupload panel panel-default">
        <div class="panel-heading clearfix">
            <div class="col-md-6 col-xs-6" style="padding-top: 4px">
                <h4 class="panel-title pull-left">SMS pop up banner/Video upload</h4>
            </div>

            <div class="col-md-6 col-xs-6 pull-right">

                <div class="" style="margin-left: auto;float:right;">

                    <div id="popUpSmsRotationBtn" class="btn-group">
                        <button type="button" class="active btn btn-default btn-switch" data-val="1" ><i class="fa fa-repeat"></i><span class="hidden-xs">&nbsp;&nbsp;Rotate</span></button>
                        <button type="button" class="btn btn-default btn-switch" data-val="0" ><i class="fa fa-minus"></i><span class="hidden-xs">&nbsp;&nbsp;Static</span></button>
                    </div>
                </div>
                <div class="date-small pull-right">
                    <input id="smsExpiryDate" type="text" class="form-control" name="startdate" />
                    <i class="fa fa-calendar"></i>
                </div>
            </div>
            <div class="btn-group pull-right">

            </div>
        </div>
        <div id="advSmsPopUpBanner"  class="panel-body" >
            <div class="dz-default dz-message">
                <span>Click here to upload banner image</span>

            </div>
        </div>
        <p class="text-danger" id="errorObj_smsPopupBanner"></p>

        <div id="advSmsPopUpVideo"  class="panel-body" >
            <div class="dz-default dz-message">
                <span>Click here to upload video</span>

            </div>
        </div>
        <p class="text-danger" id="errorObj_smsPopupVideo"></p>
        <div class="panel-footer clearfix">
                          <span class="pull-left" style="font-weight: bold;">Duration:
                            <select id="smsPopupVideoDuration" class="form-control" style="display:inline;width:100px;height:35px;" >
                                    <c:forEach var="duration" items="${durations}" >
                                        <option value="${duration}">${duration}s</option>
                                    </c:forEach>
                              </select>
                          </span>
            <span class="pull-right " style="font-weight: bold;">Price: $ <input type="text" class="form-control" value="${popupAdPrice.get(PopupAdConstant.SMS).price}" style="display:inline;width:100px;height:35px;"/></span>
        </div>
    </div>
    <div class="imageupload panel panel-default">
        <div class="panel-heading clearfix">
            <div class="col-md-6 col-xs-6" style="padding-top: 4px">
                <h4 class="panel-title pull-left">Email pop up banner/Video upload</h4>
            </div>

            <div class="col-md-6 col-xs-6 pull-right">

                <div class="" style="margin-left: auto;float:right;">

                    <div id="popUpEmailRotationBtn" class="btn-group">
                        <button type="button" class="active btn btn-default btn-switch" data-val="1"><i class="fa fa-repeat"></i><span class="hidden-xs">&nbsp;&nbsp;Rotate</span></button>
                        <button type="button" class="btn btn-default btn-switch" data-val="0"><i class="fa fa-minus"></i><span class="hidden-xs">&nbsp;&nbsp;Static</span></button>
                    </div>
                </div>
                <div class="date-small pull-right">
                    <input id="emailExpiryDate" type="text" class="form-control" name="" />
                    <i class="fa fa-calendar"></i>
                </div>

            </div>
            <div class="btn-group pull-right">

            </div>
        </div>
        <div id="advEmailPopUpBanner"  class="panel-body" >
            <div class="dz-default dz-message">
                <span>Click here to upload banner image</span>
            </div>
        </div>
        <p class="text-danger" id="errorObj_emailPopupBanner"></p>
        <div id="advEmailPopUpVideo"  class="panel-body" >
            <div class="dz-default dz-message">
                <span>Click here to upload video</span>
            </div>
        </div>
        <p class="text-danger" id="errorObj_emailPopupVideo"></p>
        <div class="panel-footer clearfix">
                          <span class="pull-left" style="font-weight: bold;">Duration:
                            <select id="emailPopupVideoDuration" class="form-control" style="display:inline;width:100px;height:35px;" >
                                <c:forEach var="duration" items="${durations}" >
                                    <option value="${duration}">${duration}s</option>
                                </c:forEach>
                          </select>
                          </span>
            <span class="pull-right " style="font-weight: bold;">Price: $ <input type="text" class="form-control" value="${popupAdPrice.get(PopupAdConstant.EMAIL).price}" style="display:inline;width:100px;height:35px;"/></span>
        </div>
    </div>
</div>