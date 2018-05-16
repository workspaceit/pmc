<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.workspaceit.pmc.constant.advertisement.SECTION_TYPE" %>
<%@page import="com.workspaceit.pmc.constant.advertisement.FILE_TYPE" %>
<%@page import="com.workspaceit.pmc.constant.advertisement.ADVERTISEMENT_ROTATION_SETTINGS" %>



<div class="tab-pane" id="tab_default_4">



    <div class="imageupload panel panel-default">
        <div class="btn-container-top" >
            <button class="btn btn-action-top"  onclick="redirectToAdvPreview(${popupAdSms.id},'popup')" >Preview</button>
        </div>
        <div class="panel-heading clearfix">
            <div class="col-md-6 col-xs-6" style="padding-top: 4px">
                <h4 class="panel-title pull-left">SMS pop up banner/Video upload</h4>
            </div>
            <div class="col-md-6 col-xs-6 pull-right">
                <span id="smsExpiryDateLbl" class="date_view">
                    <c:if test="${popupAdSms.sections.get(SECTION_TYPE.BANNER).expireDate!=null}" >
                        <fmt:formatDate value="${popupAdSms.sections.get(SECTION_TYPE.BANNER).expireDate}" type="date" />
                    </c:if>
                </span>

                <div class="" style="margin-left: auto;float:right;">
                    <div id="popUpSmsRotationBtn" class="btn-group">
                        <c:set var="popSmsRotateActive" value="" ></c:set>
                        <c:set var="popSmsStaticActive" value="" ></c:set>
                        <c:choose>
                            <c:when test="${popupAdSms.sections.get(SECTION_TYPE.BANNER).rotation.equals(ADVERTISEMENT_ROTATION_SETTINGS.ROTATE)}" >
                                <c:set var="popSmsRotateActive" value="active" ></c:set>
                            </c:when>
                            <c:when test="${popupAdSms.sections.get(SECTION_TYPE.BANNER).rotation.equals( ADVERTISEMENT_ROTATION_SETTINGS.STATIC)}">
                                <c:set var="popSmsStaticActive" value="active" ></c:set>
                            </c:when>
                        </c:choose>
                        <button type="button"  class="${popSmsRotateActive} btn btn-default btn-switch" data-val="1"><i class="fa fa-repeat"></i><span class="hidden-xs">&nbsp;&nbsp;Rotate</span></button>
                        <button type="button"  class="${popSmsStaticActive} btn btn-default btn-switch" data-val="0"><i class="fa fa-minus"></i><span class="hidden-xs">&nbsp;&nbsp;Static</span></button>
                    </div>
                </div>
                <div class="date-small pull-right" >
                    <c:set var="smsExpDate" value=""/>
                    <c:if test="${popupAdSms.sections.get(SECTION_TYPE.BANNER).expireDate!=null}" >
                        <fmt:formatDate var="smsExpDate" value="${popupAdSms.sections.get(SECTION_TYPE.BANNER).expireDate}" type="date" pattern="MM/dd/yyyy"  />
                    </c:if>

                    <input id="smsExpiryDate"
                           type="text"
                           class="form-control"
                           value="${smsExpDate}"

                    />
                    <i class="fa fa-calendar"></i>
                </div>

            </div>
            <div class="btn-group pull-right">

            </div>
        </div>

        <div class="image-previewer">
            <c:forEach var="secResource" items="${popupAdSms.sections.get(SECTION_TYPE.BANNER).sectionResource}" >
                <c:choose>
                    <c:when test="${secResource.fileType.equals(FILE_TYPE.IMAGE)}">
                        <jsp:include page="partial/sectionResource.jsp">
                            <jsp:param name="key" value="_POPUP_SMS_BANNER"/>

                            <jsp:param name="id" value="${secResource.id}"/>
                            <jsp:param name="sectionId" value="${secResource.sectionId}"/>
                            <jsp:param name="fileName" value="${secResource.fileName}"/>
                            <jsp:param name="fileType" value="${secResource.fileType}"/>
                            <jsp:param name="mimeType" value="${secResource.mimeType}"/>
                            <jsp:param name="url" value="${secResource.url}"/>
                        </jsp:include>

                    </c:when>
                    <c:when test="${secResource.fileType.equals(FILE_TYPE.VIDEO)}">
                        <video width="400" controls>
                            <source src="<s:url value="/common/${secResource.fileName}" />"  type="${secResource.mimeType}" >
                            Your browser does not support HTML5 video.
                        </video>
                    </c:when>

                </c:choose>
            </c:forEach>
        </div>
        <div id="advSmsPopUpBanner"  class="panel-body"   >
            <div class="dz-default dz-message">
                <div class="droper">
                    <p class="dropicon"><i class="fa fa-cloud-upload"></i> </p>
                    <p class="dropext">Click or Drop your files here</p>
                </div>
            </div>
            <div id="advSmsPopUpBannerPreviewsContainer" class="image-previewer"></div>
        </div>
        <p class="text-danger" id="errorObj_smsPopupBanner"></p>

        <div class="panel-footer clearfix">
                          <span class="pull-left" style="font-weight: bold;">Duration:
                            <select id="smsPopupVideoDuration" class="form-control" style="display:inline;width:100px;height:35px;"  >
                                    <c:forEach var="duration" items="${durations}" >
                                        <c:set var="durationOptionsSelected" value=""></c:set>
                                        <c:if test="${popupAdSms.sections.get(SECTION_TYPE.BANNER).duration == duration}">
                                            <c:set var="durationOptionsSelected" value="selected=\"selected\""></c:set>
                                        </c:if>
                                        <option value="${duration}" ${durationOptionsSelected}>${duration}s</option>
                                    </c:forEach>
                              </select>
                          </span>
            <span class="pull-right " style="font-weight: bold;">
                Price: $ <input id="popUpAdSmsPrice" type="text" class="form-control"
                                value="${popupAdSms.sections.get(SECTION_TYPE.BANNER).price}"
                                style="display:inline;width:100px;height:35px;"  /></span>
        </div>
    </div>

    <div class="imageupload panel panel-default">
        <div class="btn-container-top" >
            <button class="btn btn-action-top"  onclick="redirectToAdvPreview(${popupAdEmail.id},'popup')" >Preview</button>
        </div>
        <div class="panel-heading clearfix">
           <%-- <button class="btn" value="Preview" onclick="redirectToAdvPreview(${popupAdEmail.id},'popup')" />--%>
            <div class="col-md-6 col-xs-6" style="padding-top: 4px">
                <h4 class="panel-title pull-left">Email pop up banner/Video upload</h4>
            </div>

            <div class="col-md-6 col-xs-6 pull-right">



                <div class="" style="margin-left: auto;float:right;">
                    <span id="emailExpiryDateLbl" class="date_view">
                        <c:if test="${popupAdEmail.sections.get(SECTION_TYPE.BANNER).expireDate!=null}" >
                            <fmt:formatDate value="${popupAdEmail.sections.get(SECTION_TYPE.BANNER).expireDate}" type="date" />
                        </c:if>
                    </span>

                    <div id="popUpEmailRotationBtn" class="btn-group">
                        <c:set var="popEmailRotateActive" value="" ></c:set>
                        <c:set var="popEmailStaticActive" value="" ></c:set>
                        <c:choose>
                            <c:when test="${popupAdEmail.sections.get(SECTION_TYPE.BANNER).rotation.equals(ADVERTISEMENT_ROTATION_SETTINGS.ROTATE)}" >
                                <c:set var="popEmailRotateActive" value="active" ></c:set>
                            </c:when>
                            <c:when test="${popupAdEmail.sections.get(SECTION_TYPE.BANNER).rotation.equals(ADVERTISEMENT_ROTATION_SETTINGS.STATIC)}">
                                <c:set var="popEmailStaticActive" value="active" ></c:set>
                            </c:when>
                        </c:choose>
                        <button type="button"  class="${popEmailRotateActive} btn btn-default btn-switch" data-val="1" ><i class="fa fa-repeat"></i><span class="hidden-xs">&nbsp;&nbsp;Rotate</span></button>
                        <button type="button"  class="${popEmailStaticActive} btn btn-default btn-switch" data-val="0" ><i class="fa fa-minus"></i><span class="hidden-xs">&nbsp;&nbsp;Static</span></button>
                    </div>
                </div>
                <div class="date-small pull-right" >

                    <c:set var="emailExpDate" value=""/>
                    <c:if test="${popupAdEmail.sections.get(SECTION_TYPE.BANNER).expireDate!=null}" >
                        <fmt:formatDate var="emailExpDate" value="${popupAdEmail.sections.get(SECTION_TYPE.BANNER).expireDate}" type="date" pattern="MM/dd/yyyy"  />
                    </c:if>
                    <input id="emailExpiryDate"
                           type="text" class="form-control"
                           value="${emailExpDate}"/>

                    <i class="fa fa-calendar"></i>
                </div>
            </div>
            <div class="btn-group pull-right">

            </div>
        </div>
        <div class="image-previewer">
            <c:forEach var="secResource" items="${popupAdEmail.sections.get(SECTION_TYPE.BANNER).sectionResource}" >
                <c:choose>
                    <c:when test="${secResource.fileType.equals(FILE_TYPE.IMAGE)}" >
                        <jsp:include page="partial/sectionResource.jsp">
                            <jsp:param name="key" value="_POPUP_EMAIL_BANNER"/>

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
        <div id="advEmailPopUpBanner"  class="panel-body"  >
            <div class="dz-default dz-message">
                <div class="droper">
                    <p class="dropicon"><i class="fa fa-cloud-upload"></i> </p>
                    <p class="dropext">Click or Drop your files here</p>
                </div>
            </div>
            <div id="advEmailPopUpBannerPreviewsContainer" class="image-previewer"></div>
        </div>
        <p class="text-danger" id="errorObj_emailPopupBanner"></p>
        <p class="text-danger" id="errorObj_emailPopupVideo"></p>
        <div class="panel-footer clearfix">
                          <span class="pull-left" style="font-weight: bold;">Duration:
                            <select id="emailPopupVideoDuration" class="form-control" style="display:inline;width:100px;height:35px;">
                                <c:forEach var="duration" items="${durations}" >
                                    <c:set var="durationOptionsSelected" value=""></c:set>
                                    <c:if test="${popupAdEmail.sections.get(SECTION_TYPE.BANNER).duration == duration}">
                                        <c:set var="durationOptionsSelected" value="selected=\"selected\""></c:set>
                                    </c:if>
                                    <option value="${duration}" ${durationOptionsSelected} >${duration}s</option>
                                </c:forEach>
                          </select>
                          </span>
            <span class="pull-right " style="font-weight: bold;">
                Price: $ <input id="popUpAdEmailPrice"  type="text" class="form-control"
                                value="${popupAdEmail.sections.get(SECTION_TYPE.BANNER).price}"
                                style="display:inline;width:100px;height:35px;" /></span>
        </div>
    </div>
</div>