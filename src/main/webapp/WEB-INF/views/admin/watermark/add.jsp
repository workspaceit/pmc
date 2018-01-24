<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<t:genericpage>
    <jsp:body>
        <!-- /#page-wrapper -->
        <div id="page-wrapper">
        <div class="container">
            <h3 class="uni-header"><span>Watermark Setting</span></h3>
            <div class="btn-container-top">
                <button class="btn btn-action-top" onclick="submitWatermark('save')">Save</button>
                <button class="btn btn-action-top" onclick="submitWatermark('save-close')">Save&nbsp;&&nbsp;Close</button>
                <button class="btn btn-action-top" onclick="submitWatermark('save-new')">Save&nbsp;&&nbsp;New</button>
                <a href="<c:url value="/admin/watermark/all"/>" class="btn btn-action-top">Cancel</a>
            </div>
            <!-- Page Heading -->



            <div class="imageupload panel panel-default">
                <div class="panel-heading clearfix">
                    <h4 class="panel-title pull-left" style="margin-top: 10px">Watermark Title</h4>

                </div>
                <input class="form-control" id="name" name="name" placeholder="Watermark Title">

            </div>


            <div class="imageupload panel panel-default">
                <div class="panel-heading clearfix">
                    <h4 class="panel-title pull-left" style="margin-top: 10px">Choose Information type</h4>
                    <div id="waterMarkImg" class="btn-group pull-right">
                        <button type="button" class="wm_tab btn btn-default active" data-name="image" >Image</button>
                        <button type="button" class="wm_tab btn btn-default" data-name="text"  >Text</button>
                    </div>
                </div>

                <div id="waterMarkImgFile" class="file-tab panel-body">
                    <div class="file-tab panel-body">
                        <div id="watermarkLogoImg" >
                            <div class="dz-default dz-message">
                                <div class="droper">
                                    <p class="dropicon"><i class="fa fa-cloud-upload"></i> </p>
                                    <p class="dropext">Click or Drop your file here</p>
                                </div>
                                <p id="errorObj_profilePictureToken"></p>
                            </div>
                        </div>

                        <p id="errorObj_locationLogo"  class="text-danger"></p>
                    </div>
                    <div class="col-md-6">
                        <label>Logo Name</label>
                        <input class="form-control" id="img_logo_name" name="img_logo_name" placeholder="Enter Text Here">
                    </div>
                    <div class="col-md-4">
                        <div class="form-group timepick">
                            <label>Placement</label><br>
                            <select id="e-placement" class="img_placement" name="img_placement">
                                <option value="tl">Top Left</option>
                                <option value="tc">Top Center</option>
                                <option value="tr">Top Right</option>
                                <option value="bl">Bottom Left</option>
                                <option value="br">Bottom Right</option>
                                <option value="bc">Bottom Center</option>

                            </select>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group timepick">
                            <label>Smallest Size</label><br>
                            <select id="e-size" class="img_font_size" name="img_font_size">
                                <option value="thumb">Thumb</option>
                                <option value="small">Small</option>
                                <option value="medium">Medium</option>
                                <option value="large">Large</option>
                                <option value="x_large">X-Large</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <label>Fade</label><br>
                        <div class="range-slider">
                            <input id="img_fade_range" class="range-slider__range" name="img_fade_range" type="range" value="25" min="0" max="50">
                            <span class="range-slider__value">0</span>
                        </div>
                    </div>



                </div>
                <div id="waterMarkImgUrl" class="url-tab panel-body" style="display:none;">
                    <div class="col-md-3">
                        <div class="form-group timepick">
                            <label>Logo Name</label><br>
                            <input class="form-control" id="txt_logo_name" name="txt_logo_name" placeholder="New Watemark">
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group timepick">
                            <label>Text Watermark</label><br>
                            <input class="form-control" id="txt_wm_text" name="txt_wm_text" placeholder="enter your text watermark">
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group timepick">
                            <label>Font</label><br>
                            <select id="e1" class="txt_font" name="txt_font">
                                <option value="1">Myriad Pro</option>
                                <option value="2">Times New Roman</option>
                                <option value="3">Roboto</option>
                                <option value="4">Bebas Nueu</option>
                                <option value="5">Aerial</option>
                                <option value="6">Gothic Pro</option>
                                <option value="7">Fira</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group timepick">
                            <label>Text Colors</label>
                            <input class="jscolor form-control" id="txt_color" name="txt_color" value="B3FF57">
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div id="dropZonePreview" style="display: none">
            <div class="dz-preview dz-file-preview">
                <div class="dz-details">
                    <div class="dz-filename"><span data-dz-name></span></div>
                    <div class="dz-size" data-dz-size></div>
                    <img data-dz-thumbnail />
                </div>
                <div class="dz-progress"><span class="dz-upload" data-dz-uploadprogress></span></div>
                    <%--<div class="dz-success-mark"><span>✔</span></div>
                    <div class="dz-error-mark"><span>✘</span></div>--%>
                <div class="dz-error-message">
                    <span data-dz-errormessage></span>
                </div>

            </div>
        </div>


        <input type="hidden" id="venueLogoToken" value="" />

        <script src="<s:url value="/resources/js/bootstrap-imageupload.js"/>"></script>
        <script src="<s:url value="/resources/js/select2.js"/>"></script>
        <script src="<s:url value="/resources/js/jscolor.js"/>"></script>
        <!-- dropzone -->
        <link href="<s:url value="/resources/css/dropzone.css"/>" rel="stylesheet">
        <script src="<s:url value="/resources/js/dropzone.min.js"/>"></script>
        <script src="<s:url value="/resources/developer/js/temp-file/common.js"/>"></script>
        <script src="<s:url value="/resources/developer/js/watermark/common.js"/>"></script>



        <script>
            function submitWatermark(action) {
                var name = $('#name').val();
                var type=$('.wm_tab.active').attr("data-name");
                var logoImgToken='';
                var logoName='';
                var size='';
                var fade='';
                var watermarkText='';
                var fontId='';
                var placement='';
                var color='';

                if(type === "image"){
                    logoImgToken= getwatermarkLogoToken();
                    logoName=$("input[name=img_logo_name]").val();
                    placement=$(".img_placement").val();
                    size=$(".img_font_size").val();
                    fade=$("input[name=img_fade_range]").val();


                    if(name === '' || logoName===''||logoImgToken===''||placement===''||size===''||fade===''){
                        alert("Please fill all the field");
                        return false;
                    }

                }else{
                    logoName=$("input[name=txt_logo_name]").val();
                    watermarkText=$("input[name=txt_wm_text]").val();
                    fontId=$(".txt_font").val();
                    color=$("input[name=txt_color]").val();

                    if(name=== '' || logoName===''||watermarkText===''||fontId===''||color===''){
                        alert("Please fill all the field");
                        return false;
                    }

                }
                var data = {
                    name: name,
                    type:type,
                    logoImgToken:logoImgToken,
                    logoName: logoName,
                    placement: placement,
                    size: size,
                    fade: fade,
                    watermarkText: watermarkText,
                    fontId: fontId,
                    color: color
                };
                console.log(data)
                $.ajax({
                    url: BASEURL+"api/watermark/create",
                    type: "POST",
                    data: data ,
                    traditional:true,
                    statusCode:{
                        500: function(response) {
                            console.log(response);
                        }, 401: function(response) {
                            console.log(response.responseJSON);
                        }, 422: function(response) {
                            BindErrorsWithHtml("errorObj_",response.responseJSON);
                        }
                    },
                    success: function(response) {
                        if(action === "save" || action === "save-close") {
                            window.location = BASEURL+"admin/watermark/all";
                        }
                        else if(action === "save-new"){
                            window.location = BASEURL+"admin/watermark/add";
                        }
                    }
                });
            }





        </script>

    </jsp:body>
</t:genericpage>