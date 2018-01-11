<div class="tab-pane" id="tab_default_2">
    <div class="row clearfix">
        <div class="col-md-6">
            <div class="imageupload panel panel-default">
                <div class="panel-heading clearfix">
                    <h4 class="panel-title pull-left">Logo</h4>
                    <%--<div>
                        <button type="button" class="btn btn-default active">File</button>
                        <button type="button" class="btn btn-default">URL</button>
                    </div>--%>
                </div>
                <div>
                    <img onerror="this.src='/resources/images/default_alternate.png'" src="<s:url value="/common/${galleryAd.logo}"/>" class="img-thumbnail" width="150">
                </div>

                <div id="advLogo"  class="panel-body" >
                    <div class="dz-default dz-message">
                        <span>Click here to upload</span>
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
                    <img onerror="this.src='/resources/images/default_alternate.png'" src="<s:url value="/common/${galleryAd.backgroundImage}"/>" class="img-thumbnail" width="150">
                </div>
                <div id="advBackgroundImage"  class="panel-body" >

                    <div class="dz-default dz-message">
                        <span>Click here to upload</span>
                    </div>
                </div>
                <p class="text-danger" id="errorObj_bgImgTokens"></p>
                <div class="panel-footer text-right"><h4 style="font-weight: bold;">Price: <input type="text" class="form-control" value="$10" style="display:inline;width:100px;height:30px;"/></h4>
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


                            <div class="btn-group">
                                <button type="button" class="active btn btn-default btn-switch" id="regi1"><i class="fa fa-repeat"></i><span class="hidden-xs">&nbsp;&nbsp;Rotate</span></button>
                                <button type="button" class="btn btn-default btn-switch" id="regi2"><i class="fa fa-minus"></i><span class="hidden-xs">&nbsp;&nbsp;Static</span></button>
                            </div>

                        </div>
                        <div class="date-small pull-right">
                            <input id="topBannerExpiryDate" type="text" class="form-control"  />
                            <i class="fa fa-calendar"></i>
                        </div>
                    </div>
                    <div class="btn-group pull-right">
                    </div>
                </div>
                <c:forEach var="adTopBanner" items="${galleryAd.adsTopBanners}" >
                    <div>
                        <img onerror="this.src='/resources/images/default_alternate.png'" src="<s:url value="/common/${adTopBanner.image}"/>" class="img-thumbnail" width="150">                    </div>
                </c:forEach>
                <div id="advTopBannerImage"  class="panel-body" >
                    <div class="dz-default dz-message">
                        <span>Click here to upload</span>

                    </div>
                </div>
                <p id="errorObj_topBannerImgTokens" class="text-danger"></p>
                <p class="text-danger" id="errorObj_topBannerExpiryDate"></p>
                <div class="panel-footer text-right"><h4 style="font-weight: bold;">Price: <input type="text" class="form-control" value="$10" style="display:inline;width:100px;height:30px;"/></h4>
                </div>
            </div>
            <div class="imageupload panel panel-default">
                <div class="panel-heading clearfix">
                    <div class="col-md-6 col-xs-6" style="padding-top: 4px">
                        <h4 class="panel-title pull-left">Bottom Ad Banner</h4>
                    </div>

                    <div class="col-md-6 col-xs-6 pull-right">

                        <div class="" style="margin-left: auto;float:right;">
                            <!-- <div class="onoffswitch3">
                                <input type="checkbox" name="onoffswitch3" class="onoffswitch3-checkbox" id="myonoffswitch4" checked>
                                <label class="onoffswitch3-label" for="myonoffswitch4">
                                    <span class="onoffswitch3-inner">
                                        <span class="onoffswitch3-active"><span class="onoffswitch3-switch"><i class="fa fa-repeat"></i>&nbsp;Rotate</span></span>
                                        <span class="onoffswitch3-inactive"><span class="onoffswitch3-switch"><i class="fa fa-minus"></i>&nbsp;Static</span></span>
                                    </span>
                                </label>
                            </div> -->
                            <div class="btn-group">
                                <button type="button" class="active btn btn-default btn-switch" id="regi3"><i class="fa fa-repeat"></i><span class="hidden-xs">&nbsp;&nbsp;Rotate</span></button>
                                <button type="button" class="btn btn-default btn-switch" id="regi4"><i class="fa fa-minus"></i><span class="hidden-xs">&nbsp;&nbsp;Static</span></button>
                            </div>

                        </div>
                        <div class="date-small pull-right">
                            <input id="bottomBannerExpiryDate" type="text" class="form-control" />
                            <i class="fa fa-calendar"></i>
                        </div>
                    </div>



                </div>
                <c:forEach var="bottomBanner" items="${galleryAd.bottomBanners}" >
                    <div>
                        <img id="profilePic" onerror="this.src='/resources/images/default_alternate.png'" src="<s:url value="/common/${bottomBanner.image}" />" class="img-thumbnail" width="150">
                    </div>
                </c:forEach>
                <div id="advBottomBannerImage"   class="panel-body" >
                    <div class="dz-default dz-message">
                        <span>Click here to upload</span>
                    </div>
                </div>
                <p class="text-danger" id="errorObj_bottomBannerExpiryDate"></p>
                <p class="text-danger" id="errorObj_bottomBannerImgTokens" ></p>
                <div class="panel-footer text-right"><h4 style="font-weight: bold;">Price: <input type="text" class="form-control" value="$10" style="display:inline;width:100px;height:30px;"/></h4></div>
            </div>
        </div>
    </div>
</div>