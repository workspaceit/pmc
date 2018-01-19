<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="tab-pane" id="tab_default_4">
    <div class="imageupload panel panel-default">
        <div class="panel-heading clearfix">
            <div class="col-md-6 col-xs-6" style="padding-top: 4px">
                <h4 class="panel-title pull-left">SMS pop up banner/Video upload</h4>
            </div>

            <div class="col-md-6 col-xs-6 pull-right">
                <span id="smsExpiryDateLbl" class="date_view"><fmt:formatDate value="${popupAdSms.expiryDate}" type="date" ></fmt:formatDate></span>

                <div class="" style="margin-left: auto;float:right;">
                    <div id="popUpSmsRotationBtn" class="btn-group">
                        <c:set var="popSmsRotateActive" value="" ></c:set>
                        <c:set var="popSmsStaticActive" value="" ></c:set>
                        <c:choose>
                            <c:when test="${popupAdSms.adRotate == AdvertiseRotationSettings.ROTATE }" >
                                <c:set var="popSmsRotateActive" value="active" ></c:set>
                            </c:when>
                            <c:when test="${popupAdSms.adRotate == AdvertiseRotationSettings.STATIC}">
                                <c:set var="popSmsStaticActive" value="active" ></c:set>
                            </c:when>
                        </c:choose>
                        <button type="button" class="${popSmsRotateActive} btn btn-default btn-switch" data-val="1"><i class="fa fa-repeat"></i><span class="hidden-xs">&nbsp;&nbsp;Rotate</span></button>
                        <button type="button" class="${popSmsStaticActive} btn btn-default btn-switch" data-val="0"><i class="fa fa-minus"></i><span class="hidden-xs">&nbsp;&nbsp;Static</span></button>
                    </div>
                </div>
                <div class="date-small pull-right">
                    <input id="smsExpiryDate" type="text" class="form-control" value="<fmt:formatDate value="${popupAdSms.expiryDate}" pattern="MM/dd/yyyy" ></fmt:formatDate>" />
                    <i class="fa fa-calendar"></i>
                </div>

            </div>
            <div class="btn-group pull-right">

            </div>
        </div>

        <c:forEach var="popupBannerImage" items="${popupAdSms.popupBannerImages}" >
            <div>
                <img onerror="this.src='/resources/images/default_alternate.png'" src="/common/${popupBannerImage.image}" class="img-thumbnail" width="150">

                <br>
                    <%--ID_KEY._POPUP_SMS_BANNER is global vaiable update.js --%>
                <a href="javascript:void(0)" onclick="addIdToRemove(this,ID_KEY._POPUP_SMS_BANNER,${popupBannerImage.id})" >Delete</a>

            </div>

        </c:forEach>
        <div id="advSmsPopUpBanner"  class="panel-body" >
            <div class="dz-default dz-message">
                <span>Click here to upload banner image</span>

            </div>
        </div>
        <p class="text-danger" id="errorObj_smsPopupBanner"></p>
        <c:if test="${popupAdSms.video!=null && !popupAdSms.video.equals('')}">
            <video width="400" controls>
                <source src="<s:url value="/common/${popupAdSms.video}" />"  type="${popupAdSms.videoType}" >
                Your browser does not support HTML5 video.
            </video>
        </c:if>
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
                                        <c:set var="durationOptionsSelected" value=""></c:set>
                                        <c:if test="${popupAdSms.duration == duration}">
                                            <c:set var="durationOptionsSelected" value="selected=\"selected\""></c:set>
                                        </c:if>
                                        <option value="${duration}" ${durationOptionsSelected}>${duration}s</option>
                                    </c:forEach>
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
                    <span id="emailExpiryDateLbl" class="date_view"><fmt:formatDate value="${popupAdEmail.expiryDate}" type="date" ></fmt:formatDate></span>

                    <div id="popUpEmailRotationBtn" class="btn-group">
                        <c:set var="popEmailRotateActive" value="" ></c:set>
                        <c:set var="popEmailStaticActive" value="" ></c:set>
                        <c:choose>
                            <c:when test="${popupAdEmail.adRotate == AdvertiseRotationSettings.ROTATE }" >
                                <c:set var="popEmailRotateActive" value="active" ></c:set>
                            </c:when>
                            <c:when test="${popupAdEmail.adRotate == AdvertiseRotationSettings.STATIC}">
                                <c:set var="popEmailStaticActive" value="active" ></c:set>
                            </c:when>
                        </c:choose>
                        <button type="button" class="${popEmailRotateActive} btn btn-default btn-switch" data-val="1" ><i class="fa fa-repeat"></i><span class="hidden-xs">&nbsp;&nbsp;Rotate</span></button>
                        <button type="button" class="${popEmailStaticActive} btn btn-default btn-switch" data-val="0" ><i class="fa fa-minus"></i><span class="hidden-xs">&nbsp;&nbsp;Static</span></button>
                    </div>
                </div>
                <div class="date-small pull-right">
                    <input id="emailExpiryDate" type="text" class="form-control" value="<fmt:formatDate value="${popupAdEmail.expiryDate}" pattern="MM/dd/yyyy" ></fmt:formatDate>" />
                    <i class="fa fa-calendar"></i>
                </div>
            </div>
            <div class="btn-group pull-right">

            </div>
        </div>
        <c:forEach var="popupBannerImage" items="${popupAdEmail.popupBannerImages}" >
            <div>
                <img onerror="this.src='/resources/images/default_alternate.png'" src="/common/${popupBannerImage.image}" class="img-thumbnail" width="150">
                <br>
                    <%--ID_KEY._POPUP_EMAIL_BANNER is global vaiable update.js --%>
                <a href="javascript:void(0)" onclick="addIdToRemove(this,ID_KEY._POPUP_EMAIL_BANNER,${popupBannerImage.id})" >Delete</a>

            </div>

        </c:forEach>
        <div id="advEmailPopUpBanner"  class="panel-body" >
            <div class="dz-default dz-message">
                <span>Click here to upload banner image</span>
            </div>
        </div>
        <p class="text-danger" id="errorObj_emailPopupBanner"></p>

        <c:if test="${popupAdEmail.video!=null && !popupAdEmail.video.equals('')}">
            <video width="400" controls>
                <source src="<s:url value="/common/${popupAdEmail.video}" />"  type="${popupAdEmail.videoType}" >
                Your browser does not support HTML5 video.
            </video>
        </c:if>

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
                                    <c:set var="durationOptionsSelected" value=""></c:set>
                                    <c:if test="${popupAdEmail.duration == duration}">
                                        <c:set var="durationOptionsSelected" value="selected=\"selected\""></c:set>
                                    </c:if>
                                    <option value="${duration}" ${durationOptionsSelected} >${duration}s</option>
                                </c:forEach>
                          </select>
                          </span>
            <span class="pull-right " style="font-weight: bold;">Price: <input type="text" class="form-control" value="$10" style="display:inline;width:100px;height:35px;"/></span>
        </div>
    </div>
</div>