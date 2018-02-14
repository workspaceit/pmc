<%@page import="com.workspaceit.pmc.constant.advertisement.AdvertiseRotationSettings" %>
<%@page import="com.workspaceit.pmc.constant.advertisement.SECTION_TYPE" %>
<div class="tab-pane" id="tab_default_2">
    <div class="row clearfix">
        <div class="col-md-6">
            <div class="imageupload panel panel-default">
                <div class="panel-heading clearfix">
                    <h4 class="panel-title pull-left">Logo</h4>
                </div>
                <div>
                    <c:forEach var="secResource" items="${galleryAd
                                                            .sections
                                                            .get(SECTION_TYPE.LOGO).sectionResource}" >
                        <c:if test="${!secResource.fileName.trim().equals('')}" >
                            <img onerror="this.src='/resources/images/default_alternate.png'" src="<s:url value="/common/${secResource.fileName}"/>" class="img-thumbnail" width="150">
                        </c:if>
                  </c:forEach>
                </div>

                <div id="advLogo"  class="panel-body"  >
                    <div class="dz-default dz-message">
                        <div class="droper">
                            <p class="dropicon"><i class="fa fa-cloud-upload"></i> </p>
                            <p class="dropext">Click or Drop your files here</p>
                        </div>
                    </div>
                </div>
                <p class="text-danger" id="errorObj_logoToken"></p>
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
                <div>
                    <c:forEach var="secResource" items="${galleryAd
                                                            .sections
                                                            .get(SECTION_TYPE.BACKGROUND)
                                                            .sectionResource}" >
                        <c:if test="${!secResource.fileName.trim().equals('')}" >
                            <img onerror="this.src='/resources/images/default_alternate.png'" src="<s:url value="/common/${secResource.fileName}"/>" class="img-thumbnail" width="150">

                        </c:if>

                    </c:forEach>
                </div>

                <div id="advBackgroundImage"  class="panel-body"    >
                    <div class="dz-default dz-message">
                        <div class="droper">
                            <p class="dropicon"><i class="fa fa-cloud-upload"></i> </p>
                            <p class="dropext">Click or Drop your files here</p>
                        </div>
                    </div>
                </div>
                <p class="text-danger" id="errorObj_bgImgTokens"></p>
                <div class="panel-footer text-right"><h4 style="font-weight: bold;">
                    Price: $ <input id="galleryAdBgPrice" type="text" class="form-control"
                                  value="${galleryAd.sections.get(SECTION_TYPE.BACKGROUND).price}" style="display:inline;width:100px;height:30px;"/>
                </h4>
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

                            <span id="topBannerExpiryDateLbl" class="date_view">
                                <c:if test="${galleryAd.sections.get(SECTION_TYPE.TOP_BANNER).expireDate!=null}" >
                                    <fmt:formatDate value="${galleryAd.sections.get(SECTION_TYPE.TOP_BANNER).expireDate}" type="date" />
                                </c:if>
                            </span>

                            <c:set var="topBannerRotateActive" value="" ></c:set>
                            <c:set var="topBannerStaticActive" value="" ></c:set>
                            <c:choose>
                                <c:when test="${galleryAd.sections.get(SECTION_TYPE.TOP_BANNER).rotation == AdvertiseRotationSettings.ROTATE }" >
                                    <c:set var="topBannerRotateActive" value="active" ></c:set>
                                </c:when>
                                <c:when test="${galleryAd.sections.get(SECTION_TYPE.TOP_BANNER).rotation == AdvertiseRotationSettings.STATIC}">
                                    <c:set var="topBannerStaticActive" value="active" ></c:set>
                                </c:when>
                            </c:choose>

                            <div id="galleryTopRotationBtn" class="btn-group">
                                <button disabled="disabled" type="button" class="${topBannerRotateActive} btn btn-default btn-switch" data-val="1"><i class="fa fa-repeat"></i><span class="hidden-xs">&nbsp;&nbsp;Rotate</span></button>
                                <button disabled="disabled" type="button" class="${topBannerStaticActive} btn btn-default btn-switch" data-val="0" ><i class="fa fa-minus"></i><span class="hidden-xs">&nbsp;&nbsp;Static</span></button>
                            </div>

                        </div>

                        <div class="date-small pull-right" >
                            <c:set var="tpExpDate" value=""/>
                            <c:if test="${galleryAd.sections.get(SECTION_TYPE.TOP_BANNER).expireDate!=null}" >
                                <fmt:formatDate var="tpExpDate" value="${galleryAd.sections.get(SECTION_TYPE.TOP_BANNER).expireDate}" pattern="MM/dd/yyyy" />
                            </c:if>

                            <input id="topBannerExpiryDate" type="text" class="form-control" value="${tpExpDate}" />

                            <i class="fa fa-calendar"></i>
                        </div>
                    </div>
                    <div class="btn-group pull-right">
                    </div>
                </div>

                <c:forEach var="secResource" items="${galleryAd.sections.get(SECTION_TYPE.TOP_BANNER).sectionResource}" >
                    <div>
                        <img onerror="this.src='/resources/images/default_alternate.png'" src="<s:url value="/common/${secResource.fileName}"/>" class="img-thumbnail" width="150">
                        <br>
                            <%--ID_KEY._GALLERY_TOP_BANNER is global vaiable update.js --%>
                        <a href="javascript:void(0)" onclick="addIdToRemove(this,ID_KEY._GALLERY_TOP_BANNER,${secResource.id})" >Delete</a>
                    </div>
                </c:forEach>
                <div id="advTopBannerImage"  class="panel-body"    >
                    <div class="dz-default dz-message">
                        <div class="droper">
                            <p class="dropicon"><i class="fa fa-cloud-upload"></i> </p>
                            <p class="dropext">Click or Drop your files here</p>
                        </div>
                    </div>
                </div>
                <p id="errorObj_topBannerImgTokens" class="text-danger"></p>
                <p class="text-danger" id="errorObj_topBannerExpiryDate"></p>
                <div class="panel-footer text-right">
                    <h4 style="font-weight: bold;">
                        Price: $ <input id="galleryAdTopBannerPrice" type="text"
                                      class="form-control" value="${galleryAd.sections.get(SECTION_TYPE.TOP_BANNER).price}" style="display:inline;width:100px;height:30px;"/>

                      </h4>
                </div>
            </div>
            <div class="imageupload panel panel-default">
                <div class="panel-heading clearfix">
                    <div class="col-md-6 col-xs-6" style="padding-top: 4px">
                        <h4 class="panel-title pull-left">Bottom Ad Banner</h4>
                    </div>

                    <div class="col-md-6 col-xs-6 pull-right">

                        <div class="" style="margin-left: auto;float:right;">
                            <span id="bottomBannerExpiryDateLbl" class="date_view">
                                 <c:if test="${galleryAd.sections.get(SECTION_TYPE.BOTTOM_BANNER).expireDate!=null}" >
                                     <fmt:formatDate value="${galleryAd.sections.get(SECTION_TYPE.BOTTOM_BANNER).expireDate}" type="date" />
                                 </c:if>
                            </span>

                            <c:set var="bottomBannerRotateActive" value="" ></c:set>
                            <c:set var="bottomBannerStaticActive" value="" ></c:set>
                            <c:choose>

                                <c:when test="${galleryAd.sections.get(SECTION_TYPE.TOP_BANNER).rotation == AdvertiseRotationSettings.ROTATE }" >
                                    <c:set var="bottomBannerRotateActive" value="active" ></c:set>
                                </c:when>
                                <c:when test="${galleryAd.sections.get(SECTION_TYPE.TOP_BANNER).rotation == AdvertiseRotationSettings.STATIC}">
                                    <c:set var="bottomBannerStaticActive" value="active" ></c:set>
                                </c:when>
                            </c:choose>
                            <div id="galleryBottomRotationBtn" class="btn-group">
                                <button disabled="disabled" type="button" class="${bottomBannerRotateActive} btn btn-default btn-switch" data-val="1" ><i class="fa fa-repeat"></i><span class="hidden-xs">&nbsp;&nbsp;Rotate</span></button>
                                <button disabled="disabled" type="button" class="${bottomBannerStaticActive} btn btn-default btn-switch" data-val="0" ><i class="fa fa-minus"></i><span class="hidden-xs">&nbsp;&nbsp;Static</span></button>
                            </div>

                        </div>
                        <div class="date-small pull-right" >
                            <c:set var="bbExpDate" />
                            <c:if test="${galleryAd.sections.get(SECTION_TYPE.BOTTOM_BANNER).expireDate!=null}" >
                                <fmt:formatDate var="bbExpDate" value="${galleryAd.sections.get(SECTION_TYPE.BOTTOM_BANNER).expireDate}" pattern="MM/dd/yyyy" />
                            </c:if>
                            <input id="bottomBannerExpiryDate" type="text" class="form-control" value="${bbExpDate}" />
                            <i class="fa fa-calendar"></i>
                        </div>
                    </div>

                </div>
                <c:forEach var="secResource" items="${galleryAd.sections.get(SECTION_TYPE.BOTTOM_BANNER).sectionResource}" >
                    <div>
                        <img  onerror="this.src='/resources/images/default_alternate.png'" src="<s:url value="/common/${secResource.fileName}" />" class="img-thumbnail" width="150">
                        <br>
                            <%--ID_KEY._GALLERY_BOTTOM_BANNER is global vaiable from update.js --%>
                        <a href="javascript:void(0)" onclick="addIdToRemove(this,ID_KEY._GALLERY_BOTTOM_BANNER,${secResource.id})" >Delete</a>
                    </div>
                </c:forEach>
                <div id="advBottomBannerImage"   class="panel-body"  >
                    <div class="dz-default dz-message">
                        <div class="droper">
                            <p class="dropicon"><i class="fa fa-cloud-upload"></i> </p>
                            <p class="dropext">Click or Drop your files here</p>
                        </div>
                    </div>
                </div>
                <p class="text-danger" id="errorObj_bottomBannerExpiryDate"></p>
                <p class="text-danger" id="errorObj_bottomBannerImgTokens" ></p>
                <div class="panel-footer text-right">
                    <h4 style="font-weight: bold;">
                        Price: $ <input id="galleryAdBottomBannerPrice" type="text" class="form-control" value="${galleryAd.sections.get(SECTION_TYPE.BOTTOM_BANNER).price}" style="display:inline;width:100px;height:30px;"/>

                     </h4>
                </div>
            </div>
        </div>
    </div>
</div>