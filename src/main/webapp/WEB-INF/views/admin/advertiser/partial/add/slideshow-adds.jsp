<%@ page import="com.workspaceit.pmc.constant.advertisement.SlideshowAdsConstant" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="tab-pane" id="tab_default_3">
    <div class="imageupload panel panel-default">
        <div class="panel-heading clearfix">
            <div class="col-md-6 col-xs-6" style="padding-top: 4px">
                <h4 class="panel-title pull-left">Slideshow banner ad Upload</h4>
            </div>

            <div class="col-md-6 col-xs-6 pull-right">

                <div class="" style="margin-left: auto;float:right;">
                    <div id="slideShowBannerRotationBtn" class="btn-group">
                        <button type="button" class="active btn btn-default btn-switch" data-val="1" ><i class="fa fa-repeat"></i><span class="hidden-xs">&nbsp;&nbsp;Rotate</span></button>
                        <button type="button" class="btn btn-default btn-switch"  data-val="0" ><i class="fa fa-minus"></i><span class="hidden-xs">&nbsp;&nbsp;Static</span></button>
                    </div>
                </div>
                <div class="date-small pull-right">
                    <input id="slideShowBannerExpiryDate" type="text" class="form-control" />
                    <i class="fa fa-calendar"></i>
                </div>

            </div>
            <div class="btn-group pull-right">

            </div>
        </div>
        <div id="advSlideShowBanner"  class="panel-body" >
            <div class="dz-default dz-message">
                <div class="droper">
                    <p class="dropicon"><i class="fa fa-cloud-upload"></i> </p>
                    <p class="dropext">Click or Drop your files here</p>
                </div>
            </div>
            <p id="errorObj_slideShowAdsBannerTokens"></p>
        </div>
        <div class="panel-footer clearfix">
                              <span class="pull-left" style="font-weight: bold;">Duration:
                                <select id="slideShowBannerDuration" class="form-control" style="display:inline;width:100px;height:35px;" >
                                    <c:forEach var="duration" items="${durations}" >
                                         <option value="${duration}">${duration}s</option>
                                    </c:forEach>
                              </select>
                              </span>
            <span class="pull-right " style="font-weight: bold;">Price: $ <input id="slideshowAdBannerPrice" type="text" class="form-control" value="${slideshowAdPrice.get(SlideshowAdsConstant.BANNER).price}" style="display:inline;width:100px;height:35px;"/></span>
        </div>
    </div>
    <div class="imageupload panel panel-default">
        <div class="panel-heading clearfix">
            <div class="col-md-6 col-xs-6" style="padding-top: 4px">
                <h4 class="panel-title pull-left">Slideshow Video Ad upload</h4>
            </div>

            <div class="col-md-6 col-xs-6 pull-right">

                <div class="" style="margin-left: auto;float:right;">
                    <div id="slideShowVideoRotationBtn" class="btn-group">
                        <button type="button" class="active btn btn-default btn-switch" data-val="1" ><i class="fa fa-repeat"></i><span class="hidden-xs">&nbsp;&nbsp;Rotate</span></button>
                        <button type="button" class="btn btn-default btn-switch" data-val="0" ><i class="fa fa-minus"></i><span class="hidden-xs">&nbsp;&nbsp;Static</span></button>
                    </div>
                </div>
                <div class="date-small pull-right">
                    <input id="slideShowVideoExpiryDate" type="text" class="form-control" />
                    <i class="fa fa-calendar"></i>
                </div>

            </div>
            <div class="btn-group pull-right">

            </div>
        </div>
        <div id="advSlideShowVideo"  class="panel-body" >
            <div class="dz-default dz-message">
                <div class="droper">
                    <p class="dropicon"><i class="fa fa-cloud-upload"></i> </p>
                    <p class="dropext">Click or Drop file to upload video</p>
                </div>
            </div>
            <p class="text-danger" id="errorObj_slideShowAdsVideoToken" style="display: block;"></p>
            <p class="text-danger" id="errorObj_advImg" style="display: block;"></p>

        </div>
        <div class="panel-footer clearfix">
          <span class="pull-left" style="font-weight: bold;">Duration:
               <select id="slideShowVideoDuration" class="form-control" style="display:inline;width:100px;height:35px;" >
                    <c:forEach var="duration" items="${durations}" >
                        <option value="${duration}">${duration}s</option>
                    </c:forEach>
              </select>
          </span>
            <span class="pull-right " style="font-weight: bold;">Price:$ <input id="slideshowAdVideoPrice" type="text" class="form-control" value="${slideshowAdPrice.get(SlideshowAdsConstant.VIDEO).price    }" style="display:inline;width:100px;height:35px;"/></span>
        </div>
    </div>
</div>