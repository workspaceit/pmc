<%@ page import="com.workspaceit.pmc.constant.advertisement.ADVERTISEMENT_ROTATION_SETTINGS" %>
<%@page import="com.workspaceit.pmc.constant.advertisement.SECTION_TYPE" %>
<%@page import="com.workspaceit.pmc.constant.advertisement.FILE_TYPE" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="tab-pane" id="tab_default_3">
    <div class="btn-container-top">
        <button class="btn btn-action-top"  onclick="redirectToAdvPreview(${slideshowAd.id},'slideshow')" >Preview</button>
    </div>
    <div class="imageupload panel panel-default">
        <div class="panel-heading clearfix">
            <div class="col-md-6 col-xs-6" style="padding-top: 4px">
                <h4 class="panel-title pull-left">Slideshow banner Upload / video </h4>
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
                        <button type="button" class="${slideShowBannerRotateActive} btn btn-default btn-switch" data-val="1" ><i class="fa fa-repeat"></i><span class="hidden-xs">&nbsp;&nbsp;Rotate</span></button>
                        <button type="button" class="${slideShowBannerStaticActive} btn btn-default btn-switch" data-val="0" ><i class="fa fa-minus"></i><span class="hidden-xs">&nbsp;&nbsp;Static</span></button>
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
        <div class="image-previewer">
            <c:forEach var="secResource" items="${slideshowAd.sections.get(SECTION_TYPE.TOP_BANNER).sectionResource}" >
                <c:choose>
                    <c:when test="${secResource.fileType.equals(FILE_TYPE.IMAGE)}" >
                        <jsp:include page="partial/sectionResource.jsp">
                            <jsp:param name="key" value="_SLIDE_SHOW_BANNER"/>

                            <jsp:param name="id" value="${secResource.id}"/>
                            <jsp:param name="sectionId" value="${secResource.sectionId}"/>
                            <jsp:param name="fileName" value="${secResource.fileName}"/>
                            <jsp:param name="fileType" value="${secResource.fileType}"/>
                            <jsp:param name="mimeType" value="${secResource.mimeType}"/>
                            <jsp:param name="url" value="${secResource.url}"/>
                        </jsp:include>
                    </c:when>
                    <c:when test="${secResource.fileType.equals(FILE_TYPE.VIDEO)}" >
                        <video width="400" controls>
                            <source src="<s:url value="/common/${secResource.fileName}" />"  type="${secResource.mimeType}" >
                            Your browser does not support HTML5 video.
                        </video>
                    </c:when>
                </c:choose>

            </c:forEach>
        </div>


        <div id="advSlideShowBanner"  class="panel-body">
            <div class="dz-default dz-message">
                <div class="droper">
                    <p class="dropicon"><i class="fa fa-cloud-upload"></i> </p>
                    <p class="dropext">Click or Drop your banner images or video here</p>
                </div>
            </div>
            <div id="advSlideShowBannerPreviewsContainer" class="image-previewer"></div>
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
</div>