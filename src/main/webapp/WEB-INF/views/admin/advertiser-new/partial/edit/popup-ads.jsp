<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.workspaceit.pmc.constant.advertisement.SECTION_TYPE" %>
<%@page import="com.workspaceit.pmc.constant.advertisement.FILE_TYPE" %>
<%@page import="com.workspaceit.pmc.constant.advertisement.AdvertiseRotationSettings" %>



<div class="tab-pane" id="tab_default_4">
    <div class="imageupload panel panel-default">
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
                            <c:when test="${popupAdSms.sections.get(SECTION_TYPE.BANNER).rotation.equals(AdvertiseRotationSettings.ROTATE)}" >
                                <c:set var="popSmsRotateActive" value="active" ></c:set>
                            </c:when>
                            <c:when test="${popupAdSms.sections.get(SECTION_TYPE.BANNER).rotation.equals( AdvertiseRotationSettings.STATIC)}">
                                <c:set var="popSmsStaticActive" value="active" ></c:set>
                            </c:when>
                        </c:choose>
                        <button type="button" disabled="disabled" class="${popSmsRotateActive} btn btn-default btn-switch" data-val="1"><i class="fa fa-repeat"></i><span class="hidden-xs">&nbsp;&nbsp;Rotate</span></button>
                        <button type="button" disabled="disabled" class="${popSmsStaticActive} btn btn-default btn-switch" data-val="0"><i class="fa fa-minus"></i><span class="hidden-xs">&nbsp;&nbsp;Static</span></button>
                    </div>
                </div>
                <div class="date-small pull-right" style="display:none; ">
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

        <c:forEach var="secResource" items="${popupAdSms.sections.get(SECTION_TYPE.BANNER).sectionResource}" >
            <c:if test="${secResource.fileType.equals(FILE_TYPE.IMAGE)}">
                <div>
                    <img onerror="this.src='/resources/images/default_alternate.png'" src="/common/${secResource.fileName}" class="img-thumbnail" width="150">

                    <br>
                        <%--ID_KEY._POPUP_SMS_BANNER is global vaiable update.js --%>
                    <a href="javascript:void(0)" onclick="addIdToRemove(this,ID_KEY._POPUP_SMS_BANNER,${secResource.id})" style="display: none;" >Delete</a>

                </div>
            </c:if>


        </c:forEach>
        <div id="advSmsPopUpBanner"  class="panel-body" style="display: none"  >
            <div class="dz-default dz-message">
                <span>Click here to upload banner image</span>

            </div>
        </div>
        <p class="text-danger" id="errorObj_smsPopupBanner"></p>
        <c:forEach var="secResource" items="${popupAdSms.sections.get(SECTION_TYPE.BANNER).sectionResource}">
            <c:if test="${secResource.fileType.equals(FILE_TYPE.VIDEO)}">
                <video width="400" controls>
                    <source src="<s:url value="/common/${secResource.fileName}" />"  type="${secResource.mimeType}" >
                    Your browser does not support HTML5 video.
                </video>
            </c:if>
        </c:forEach>

        <p class="text-danger" id="errorObj_smsPopupVideo"></p>
        <div class="panel-footer clearfix">
                          <span class="pull-left" style="font-weight: bold;">Duration:
                            <select id="smsPopupVideoDuration" class="form-control" style="display:inline;width:100px;height:35px;" disabled="disabled" >
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
                Price: $ <input type="text" class="form-control"
                                value="${popupAdSms.sections.get(SECTION_TYPE.BANNER).price}"
                                style="display:inline;width:100px;height:35px;" disabled="disabled" /></span>
        </div>
    </div>
    <div class="imageupload panel panel-default">
        <div class="panel-heading clearfix">
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
                            <c:when test="${popupAdEmail.sections.get(SECTION_TYPE.BANNER).rotation.equals(AdvertiseRotationSettings.ROTATE)}" >
                                <c:set var="popEmailRotateActive" value="active" ></c:set>
                            </c:when>
                            <c:when test="${popupAdEmail.sections.get(SECTION_TYPE.BANNER).rotation.equals(AdvertiseRotationSettings.STATIC)}">
                                <c:set var="popEmailStaticActive" value="active" ></c:set>
                            </c:when>
                        </c:choose>
                        <button type="button" disabled="disabled" class="${popEmailRotateActive} btn btn-default btn-switch" data-val="1" ><i class="fa fa-repeat"></i><span class="hidden-xs">&nbsp;&nbsp;Rotate</span></button>
                        <button type="button" disabled="disabled" class="${popEmailStaticActive} btn btn-default btn-switch" data-val="0" ><i class="fa fa-minus"></i><span class="hidden-xs">&nbsp;&nbsp;Static</span></button>
                    </div>
                </div>
                <div class="date-small pull-right" style="display:none;">
                    <c:set var="emailExpDate" value=""/>
                    <c:if test="${popupAdEmail.sections.get(SECTION_TYPE.BANNER).expireDate!=null}" >
                        <fmt:formatDate var="emailExpDate" value="${popupAdEmail.sections.get(SECTION_TYPE.BANNER).expireDate}" type="date" pattern="MM/dd/yyyy"  />
                    </c:if>

                    <input id="emailExpiryDate"
                           type="text" class="form-control"
                           value="${emailExpDate}>"

                    />
                    <i class="fa fa-calendar"></i>
                </div>
            </div>
            <div class="btn-group pull-right">

            </div>
        </div>
        <c:forEach var="secResource" items="${popupAdEmail.sections.get(SECTION_TYPE.BANNER).sectionResource}" >
            <c:if test="${secResource.fileType.equals(FILE_TYPE.IMAGE)}" >
                <div>
                    <img onerror="this.src='/resources/images/default_alternate.png'" src="/common/${secResource.fileName}" class="img-thumbnail" width="150">
                    <br>
                        <%--ID_KEY._POPUP_EMAIL_BANNER is global vaiable update.js --%>
                    <a href="javascript:void(0)" onclick="addIdToRemove(this,ID_KEY._POPUP_EMAIL_BANNER,${secResource.id})" style="display: none;" >Delete</a>

                </div>
            </c:if>


        </c:forEach>
        <div id="advEmailPopUpBanner"  class="panel-body" style="display: none"  >
            <div class="dz-default dz-message">
                <span>Click here to upload banner image</span>
            </div>
        </div>
        <p class="text-danger" id="errorObj_emailPopupBanner"></p>
        <c:forEach var="secResource" items="${popupAdEmail.sections.get(SECTION_TYPE.BANNER).sectionResource}" >
            <c:if test="${secResource.fileType.equals(FILE_TYPE.VIDEO)}" >
                <video width="400" controls>
                    <source src="<s:url value="/common/${secResource.fileName}" />"  type="${secResource.mimeType}" >
                    Your browser does not support HTML5 video.
                </video>
            </c:if>
        </c:forEach>

        <p class="text-danger" id="errorObj_emailPopupVideo"></p>
        <div class="panel-footer clearfix">
                          <span class="pull-left" style="font-weight: bold;">Duration:
                            <select id="emailPopupVideoDuration" class="form-control" style="display:inline;width:100px;height:35px;" disabled="disabled" >
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
                Price: $ <input type="text" class="form-control"
                                value="${popupAdEmail.sections.get(SECTION_TYPE.BANNER).price}"
                                style="display:inline;width:100px;height:35px;" disabled="disabled" /></span>
        </div>
    </div>
</div>