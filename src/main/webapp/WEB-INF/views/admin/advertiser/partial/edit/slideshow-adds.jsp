<%@ page import="com.workspaceit.pmc.constant.advertisement.ADVERTISEMENT_ROTATION_SETTINGS" %>
<%@page import="com.workspaceit.pmc.constant.advertisement.SECTION_TYPE" %>
<%@page import="com.workspaceit.pmc.constant.advertisement.FILE_TYPE" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="tab-pane" id="tab_default_3">
    <div class="imageupload panel panel-default">
        <div class="panel-heading clearfix">
            <div class="col-md-6 col-xs-6" style="padding-top: 4px">
                <h4 class="panel-title pull-left">Slideshow banner ad Upload</h4>
            </div>
            <div class="col-md-6 col-xs-6 pull-right">

                <div class="" style="margin-left: auto;float:right;">
                    <span id="slideShowBannerExpiryDateLbl" class="date_view">
                        <c:if test="${slideshowAd.sections.get(SECTION_TYPE.TOP_BANNER).expireDate!=null}" >
                            <fmt:formatDate value="${slideshowAd.sections.get(SECTION_TYPE.TOP_BANNER).expireDate}" type="date" />
                        </c:if>
                    </span>
                    <c:set var="slideShowBannerRotateActive" value="" ></c:set>
                    <c:set var="slideShowBannerStaticActive" value="" ></c:set>
                    <c:choose>
                        <c:when test="${slideshowAd.sections.get(SECTION_TYPE.TOP_BANNER).rotation == ADVERTISEMENT_ROTATION_SETTINGS.ROTATE }" >
                            <c:set var="slideShowBannerRotateActive" value="active" ></c:set>
                        </c:when>
                        <c:when test="${slideshowAd.sections.get(SECTION_TYPE.TOP_BANNER).rotation == ADVERTISEMENT_ROTATION_SETTINGS.STATIC}">
                            <c:set var="slideShowBannerStaticActive" value="active" ></c:set>
                        </c:when>
                    </c:choose>
                    <div id="slideShowBannerRotationBtn" class="btn-group">
                        <button type="button" class="${slideShowBannerRotateActive} btn btn-default btn-switch" id="regi6"><i class="fa fa-repeat"></i><span class="hidden-xs">&nbsp;&nbsp;Rotate</span></button>
                        <button type="button" class="${slideShowBannerStaticActive} btn btn-default btn-switch" id="regi7"><i class="fa fa-minus"></i><span class="hidden-xs">&nbsp;&nbsp;Static</span></button>
                    </div>
                </div>
                <div class="date-small pull-right" >
                    <c:set var="tbExpDate" value="" />
                    <c:if test="${slideshowAd.sections.get(SECTION_TYPE.TOP_BANNER).expireDate!=null}" >
                        <fmt:formatDate var="tbExpDate" value="${slideshowAd.sections.get(SECTION_TYPE.TOP_BANNER).expireDate}" pattern="MM/dd/yyyy" />
                    </c:if>
                    <input id="slideShowBannerExpiryDate" type="text" class="form-control" value="${tbExpDate}"/>
                    <i class="fa fa-calendar"></i>
                </div>


            </div>
            <div class="btn-group pull-right">

            </div>
        </div>
        <c:forEach var="secResource" items="${slideshowAd.sections.get(SECTION_TYPE.TOP_BANNER).sectionResource}" >
            <c:if test="${!secResource.fileName.equals('') && secResource.fileType.equals(FILE_TYPE.IMAGE)}" >
                <div>
                    <img onerror="this.src='/resources/images/default_alternate.png'" src="/common/${secResource.fileName}" class="img-thumbnail" width="150">
                    <br>
                        <%--ID_KEY._SLIDE_SHOW_BANNER is global vaiable from update.js --%>
                    <a href="javascript:void(0)" onclick="addIdToRemove(this,ID_KEY._SLIDE_SHOW_BANNER,${secResource.id})" >Delete</a>

                </div>
            </c:if>

        </c:forEach>


        <div id="advSlideShowBanner"  class="panel-body">
            <div class="dz-default dz-message">
                <div class="droper">
                    <p class="dropicon"><i class="fa fa-cloud-upload"></i> </p>
                    <p class="dropext">Click or Drop your files here</p>
                </div>
            </div>
        </div>
        <div class="panel-footer clearfix">
                              <span class="pull-left" style="font-weight: bold;">Duration:
                                <select id="slideShowBannerDuration" class="form-control" style="display:inline;width:100px;height:35px;" >

                                    <c:forEach var="duration" items="${durations}" >
                                        <c:set var="durationOptions" value=""></c:set>
                                        <c:if test="${slideshowAd.sections.get(SECTION_TYPE.TOP_BANNER).duration == duration}">
                                            <c:set var="durationOptions" value="selected=\"selected\""></c:set>
                                        </c:if>
                                        <option value="${duration}" ${durationOptions} >${duration}s</option>
                                    </c:forEach>
                              </select>
                              </span>
            <span class="pull-right " style="font-weight: bold;">


                Price: $ <input id="slideshowAdBannerPrice" type="text" class="form-control"
                                value="${slideshowAd.sections.get(SECTION_TYPE.TOP_BANNER).price}"
                                style="display:inline;width:100px;height:35px;" /></span>
        </div>
    </div>
    <div class="imageupload panel panel-default">
        <div class="panel-heading clearfix">
            <div class="col-md-6 col-xs-6" style="padding-top: 4px">
                <h4 class="panel-title pull-left">Slideshow Video Ad upload</h4>
            </div>

            <div class="col-md-6 col-xs-6 pull-right">
                <span id="slideShowVideoExpiryDateLbl" class="date_view">

                    <c:if test="${slideshowAd.sections.get(SECTION_TYPE.BOTTOM_BANNER).expireDate!=null}" >
                        <fmt:formatDate value="${slideshowAd.sections.get(SECTION_TYPE.BOTTOM_BANNER).expireDate}" type="date" />
                    </c:if>
                </span>

                <div class="" style="margin-left: auto;float:right;">
                    <c:set var="slideShowVideoRotateActive" value="" ></c:set>
                    <c:set var="slideShowVideoStaticActive" value="" ></c:set>
                    <c:choose>
                        <c:when test="${slideshowAd.sections.get(SECTION_TYPE.BOTTOM_BANNER).rotation.equals(ADVERTISEMENT_ROTATION_SETTINGS.ROTATE) }" >
                            <c:set var="slideShowVideoRotateActive" value="active" ></c:set>
                        </c:when>
                        <c:when test="${slideshowAd.sections.get(SECTION_TYPE.BOTTOM_BANNER).rotation.equals(ADVERTISEMENT_ROTATION_SETTINGS.STATIC)}">
                            <c:set var="slideShowVideoStaticActive" value="active" ></c:set>
                        </c:when>
                    </c:choose>
                    <div id="slideShowVideoRotationBtn" class="btn-group">
                        <button type="button" class="active btn btn-default btn-switch" data-val="1" ><i class="fa fa-repeat"></i><span class="hidden-xs">&nbsp;&nbsp;Rotate</span></button>
                        <button type="button" class="btn btn-default btn-switch" data-val="0" ><i class="fa fa-minus"></i><span class="hidden-xs">&nbsp;&nbsp;Static</span></button>
                    </div>
                </div>
                <div class="date-small pull-right" >
                    <c:set var="bbExpDate" value="" />
                    <c:if test="${slideshowAd.sections.get(SECTION_TYPE.BOTTOM_BANNER).expireDate!=null}" >
                        <fmt:formatDate var="bbExpDate" value="${slideshowAd.sections.get(SECTION_TYPE.BOTTOM_BANNER).expireDate}" pattern="MM/dd/yyyy" />
                    </c:if>

                    <input id="slideShowVideoExpiryDate" type="text" class="form-control" value="${bbExpDate}"/>
                    <i class="fa fa-calendar"></i>
                </div>
            </div>
            <div class="btn-group pull-right">

            </div>
        </div>
        <c:forEach var="secResource" items="${slideshowAd.sections.get(SECTION_TYPE.BOTTOM_BANNER).sectionResource}" >

            <c:if test="${!secResource.fileName.equals('') && secResource.fileType.equals(FILE_TYPE.VIDEO)}">
                <video width="400" controls>
                    <source src="<s:url value="/common/${secResource.fileName}" />"  type="${secResource.mimeType}" >
                    Your browser does not support HTML5 video.
                </video>
            </c:if>
        </c:forEach>


        <div id="advSlideShowVideo"  class="panel-body" >
            <div class="dz-default dz-message">
                <div class="droper">
                    <p class="dropicon"><i class="fa fa-cloud-upload"></i> </p>
                    <p class="dropext">Click or Drop your files here</p>
                </div>
            </div>

        </div>
        <div class="panel-footer clearfix">
                              <span class="pull-left" style="font-weight: bold;">Duration:
                               <select id="slideShowVideoDuration" class="form-control" style="display:inline;width:100px;height:35px;" >

                                    <c:forEach var="duration" items="${durations}" >
                                        <c:set var="durationOptions" value=""></c:set>
                                       <c:if test="${slideshowAd.sections.get(SECTION_TYPE.BOTTOM_BANNER).duration == duration}">
                                            <c:set var="durationOptions" value="selected=\"selected\""></c:set>
                                        </c:if>
                                        <option value="${duration}" ${durationOptions} >${duration}s</option>
                                    </c:forEach>
                              </select>
                              </span>
            <span class="pull-right " style="font-weight: bold;">
                Price: $ <input id="slideshowAdVideoPrice" type="text" class="form-control"
                                value="${slideshowAd.sections.get(SECTION_TYPE.BOTTOM_BANNER).price}"
                                style="display:inline;width:100px;height:35px;" /></span>
        </div>
    </div>
</div>