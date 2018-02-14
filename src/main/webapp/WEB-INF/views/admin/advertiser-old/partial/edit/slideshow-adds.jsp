<%@ page import="com.workspaceit.pmc.constant.advertisement.AdvertiseRotationSettings" %>
<%@page import="com.workspaceit.pmc.constant.advertisement.SlideshowAdsConstant" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="tab-pane" id="tab_default_3">
    <div class="imageupload panel panel-default">
        <div class="panel-heading clearfix">
            <div class="col-md-6 col-xs-6" style="padding-top: 4px">
                <h4 class="panel-title pull-left">Slideshow banner ad Upload</h4>
            </div>

            <div class="col-md-6 col-xs-6 pull-right">

                <div class="" style="margin-left: auto;float:right;">
                    <span id="slideShowBannerExpiryDateLbl" class="date_view"><fmt:formatDate value="${slideshowAd.bannerExpiryDate}" type="date" ></fmt:formatDate></span>
                    <c:set var="slideShowBannerRotateActive" value="" ></c:set>
                    <c:set var="slideShowBannerStaticActive" value="" ></c:set>
                    <c:choose>
                        <c:when test="${slideshowAd.bannerRotate == AdvertiseRotationSettings.ROTATE }" >
                            <c:set var="slideShowBannerRotateActive" value="active" ></c:set>
                        </c:when>
                        <c:when test="${slideshowAd.bannerRotate == AdvertiseRotationSettings.STATIC}">
                            <c:set var="slideShowBannerStaticActive" value="active" ></c:set>
                        </c:when>
                    </c:choose>
                    <div id="slideShowBannerRotationBtn" class="btn-group">
                        <button type="button" disabled="disabled" class="${slideShowBannerRotateActive} btn btn-default btn-switch" id="regi6"><i class="fa fa-repeat"></i><span class="hidden-xs">&nbsp;&nbsp;Rotate</span></button>
                        <button type="button" disabled="disabled" class="${slideShowBannerStaticActive} btn btn-default btn-switch" id="regi7"><i class="fa fa-minus"></i><span class="hidden-xs">&nbsp;&nbsp;Static</span></button>
                    </div>
                </div>
                <div class="date-small pull-right" style="display: none;">
                    <input id="slideShowBannerExpiryDate" type="text" class="form-control" value="<fmt:formatDate value="${slideshowAd.bannerExpiryDate}" pattern="MM/dd/yyyy" ></fmt:formatDate>"/>
                    <i class="fa fa-calendar"></i>
                </div>


            </div>
            <div class="btn-group pull-right">

            </div>
        </div>
        <c:forEach var="slideshowBannerImage" items="${slideshowAd.slideshowBannerImages}" >
            <div>
                <img onerror="this.src='/resources/images/default_alternate.png'" src="/common/${slideshowBannerImage.image}" class="img-thumbnail" width="150">
                <br>
                    <%--ID_KEY._SLIDE_SHOW_BANNER is global vaiable from update.js --%>
                <a href="javascript:void(0)" onclick="addIdToRemove(this,ID_KEY._SLIDE_SHOW_BANNER,${slideshowBannerImage.id})" style="display: none;" >Delete</a>

            </div>
        </c:forEach>


        <div id="advSlideShowBanner"  class="panel-body" style="display: none;" >
            <div class="dz-default dz-message">
                <span>Click here to upload</span>

            </div>
            <p id="errorObj_slideShowAdsBannerTokens"></p>
        </div>
        <div class="panel-footer clearfix">
                              <span class="pull-left" style="font-weight: bold;">Duration:
                                <select id="slideShowBannerDuration" class="form-control" style="display:inline;width:100px;height:35px;" disabled="disabled" >

                                    <c:forEach var="duration" items="${durations}" >
                                        <c:set var="durationOptions" value=""></c:set>
                                        <c:if test="${slideshowAd.bannerDuration == duration}">
                                            <c:set var="durationOptions" value="selected=\"selected\""></c:set>
                                        </c:if>
                                        <option value="${duration}" ${durationOptions} >${duration}s</option>
                                    </c:forEach>
                              </select>
                              </span>
            <span class="pull-right " style="font-weight: bold;">


                Price: $ <input type="text" class="form-control"
                                value="${slideshowAd.quantityPrice.get(SlideshowAdsConstant.BANNER).price}"
                                style="display:inline;width:100px;height:35px;"
                                disabled="disabled" /></span>
        </div>
    </div>
    <div class="imageupload panel panel-default">
        <div class="panel-heading clearfix">
            <div class="col-md-6 col-xs-6" style="padding-top: 4px">
                <h4 class="panel-title pull-left">Slideshow Video Ad upload</h4>
            </div>

            <div class="col-md-6 col-xs-6 pull-right">
                <span id="slideShowVideoExpiryDateLbl" class="date_view"><fmt:formatDate value="${slideshowAd.videoExpiryDate}" type="date" ></fmt:formatDate></span>

                <div class="" style="margin-left: auto;float:right;">
                    <c:set var="slideShowVideoRotateActive" value="" ></c:set>
                    <c:set var="slideShowVideoStaticActive" value="" ></c:set>
                    <c:choose>
                        <c:when test="${slideshowAd.videoRotate == AdvertiseRotationSettings.ROTATE }" >
                            <c:set var="slideShowVideoRotateActive" value="active" ></c:set>
                        </c:when>
                        <c:when test="${slideshowAd.videoRotate == AdvertiseRotationSettings.STATIC}">
                            <c:set var="slideShowVideoStaticActive" value="active" ></c:set>
                        </c:when>
                    </c:choose>
                    <div id="slideShowVideoRotationBtn" class="btn-group">
                        <button type="button" disabled="disabled" class="active btn btn-default btn-switch" data-val="1" ><i class="fa fa-repeat"></i><span class="hidden-xs">&nbsp;&nbsp;Rotate</span></button>
                        <button type="button" disabled="disabled" class="btn btn-default btn-switch" data-val="0" ><i class="fa fa-minus"></i><span class="hidden-xs">&nbsp;&nbsp;Static</span></button>
                    </div>
                </div>
                <div class="date-small pull-right" style="display: none;" >
                    <input id="slideShowVideoExpiryDate" type="text" class="form-control" value="<fmt:formatDate value="${slideshowAd.videoExpiryDate}" pattern="MM/dd/yyyy" />"/>
                    <i class="fa fa-calendar"></i>
                </div>
            </div>
            <div class="btn-group pull-right">

            </div>
        </div>
        <c:if test="${slideshowAd.video!=null && !slideshowAd.video.equals('')}">
            <video width="400" controls>
                <source src="<s:url value="/common/${slideshowAd.video}" />"  type="${slideshowAd.videoType}" >
                Your browser does not support HTML5 video.
            </video>
        </c:if>

        <div id="advSlideShowVideo"  class="panel-body" style="display: none;" >
            <div class="dz-default dz-message">
                <span>Click here to upload</span>
            </div>
            <p class="text-danger" id="errorObj_slideShowAdsVideoToken" style="display: block;"></p>
            <p class="text-danger" id="errorObj_advImg" style="display: block;"></p>

        </div>
        <div class="panel-footer clearfix">
                              <span class="pull-left" style="font-weight: bold;">Duration:
                               <select id="slideShowVideoDuration" class="form-control" style="display:inline;width:100px;height:35px;" disabled="disabled" >

                                    <c:forEach var="duration" items="${durations}" >
                                        <c:set var="durationOptions" value=""></c:set>
                                        <c:if test="${slideshowAd.videoDuration == duration}">
                                            <c:set var="durationOptions" value="selected=\"selected\""></c:set>
                                        </c:if>
                                        <option value="${duration}" ${durationOptions} >${duration}s</option>
                                    </c:forEach>
                              </select>
                              </span>
            <span class="pull-right " style="font-weight: bold;">
                Price: $ <input type="text" class="form-control"
                                value="${slideshowAd.quantityPrice.get(SlideshowAdsConstant.VIDEO).price}"
                                style="display:inline;width:100px;height:35px;" disabled="disabled" /></span>
        </div>
    </div>
</div>