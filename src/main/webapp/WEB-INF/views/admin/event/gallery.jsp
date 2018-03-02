<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<t:genericpage>
    <jsp:body>
        <div id="page-wrapper">

        <div class="container">
            <div class="button-actions">
                <button class="btn-edit btn-uni" id="editEvent"><i class="fa fa-pencil"></i>&nbsp;&nbsp;Edit</button>
                <button class="btn-edit btn-uni" id="deleteTrash"><i class="fa fa-trash"></i>&nbsp;&nbsp;Delete</button>
                <button class="btn-edit btn-uni" id="doneEdit"><i class="fa fa-check"></i>&nbsp;&nbsp;Done Editing</button>
            </div>
            <div class="row">

                <div class="col-lg-12 event-gallery">
                    <div class="event-logo">
                        <div class="togglerFace">
                            <img class="event-logo-image" src="<s:url value="/common/${event.eventPhoto}" />" />
                        </div>
                        <div class="toggler">
                            <img class="event-logo-image" src="<s:url value="/common/${event.eventPhoto}" />" />
                            <div class="uploader skinny-landscape" style="width:100%;"><div class="upload-title" style="position: absolute; top: 50%; margin-top: -14.5px;">Change Logo</div>
                            <input type="file" name="layoutOverlay" class="file-upload" title="Choose" accept="image/jpg,image/jpeg,image/png"></div>
                        </div>
                    </div>
                    <div class="desc-div">
                        <div class="noteditable-desc togglerFace">
                            <input type="hidden" id="event-id" value="${event.id}">
                            <h3 class="event-name-static">${event.name}</h3>
                            <p class="e-date">
                                Date: <fmt:formatDate value="${event.startsAt}" pattern="yyyy/MM/dd" />
                            </p>
                            <p class="e-image-no">
                                <span class="image-count">${imageCount}</span> Images
                            </p>
                        </div>
                        <div class="editable-desc toggler">
                            <input type="text" class="event-name" id="event-name" value="${event.name}" />
                            <p class="e-date">
                                Date: <fmt:formatDate value="${event.startsAt}" pattern="yyyy/MM/dd" />
                            </p>
                            <p class="e-image-no">
                               <span class="image-count">${imageCount}</span> Images
                            </p>
                        </div>
                    </div>
                    <c:forEach items="${eventImages}" var="eventImage" varStatus="status">
                        <div class="col-lg-3 col-md-4 col-xs-6 thumb" id="event-image${eventImage.id}">
                            <div class="chk-div toggler">
                                <div class="checkboxFive">
                                    <input type="checkbox" class="event-image" data-value="${eventImage.id}" value="1" id="checkbox${eventImage.id}" name=""/>
                                    <label for="checkbox${eventImage.id}">
                                    </label>
                                </div>
                            </div>
                            <a class="thumbnail" data-index="${status.index}" href="#" data-image-id="" data-toggle="modal" data-title="This is my title" data-caption="Some lovely red flowers">
                                <img class="img-responsive" src="<s:url value="/event-images/web/${eventImage.image}" />" alt="Image">
                            </a>
                        </div>
                    </c:forEach>



                <div class="modal fade" id="image-gallery" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                                <h4 class="modal-title" id="image-gallery-title"></h4>
                            </div>
                            <div class="modal-body clearfix">
                                <div class="col-md-12" style="padding:0px;">
                                    <img id="image-gallery-image" class="img-responsive" src="">
                                </div>
                            </div>
                            <div class="modal-footer">

                                <div class="col-md-2 text-left">
                                    <button type="button" class="btn btn-primary btn-lg btn-action" id="show-previous-image">Previous</button>
                                </div>

                                <div class="col-md-8 text-center">
                                    <div class="action-container">
                                        <a href="#"><img src="<s:url value="/resources/images/11.png"/>" /></a>
                                        <a href="#"><img src="<s:url value="/resources/images/22.png" />" /></a>
                                        <a href="#"><img src="<s:url value="/resources/images/33.png" />" /></a>
                                        <a href="#"><img src="<s:url value="/resources/images/44.png" />" /></a>
                                        <a href="#"><img src="<s:url value="/resources/images/55.png" />" /></a>
                                    </div>
                                </div>

                                <div class="col-md-2">
                                    <button type="button" id="show-next-image" class="btn btn-default btn-lg btn-action">Next</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        </div>
        <%--<script>--%>
            <%--$(document).ready(function(){--%>

                <%--loadGallery(true, 'a.thumbnail');--%>

                <%--//This function disables buttons when needed--%>
                <%--function disableButtons(counter_max, counter_current){--%>
                    <%--$('#show-previous-image, #show-next-image').show();--%>
                    <%--if(counter_max == counter_current){--%>
                        <%--$('#show-next-image').hide();--%>
                    <%--} else if (counter_current == 1){--%>
                        <%--$('#show-previous-image').hide();--%>
                    <%--}--%>
                <%--}--%>

                <%--/**--%>
                 <%--*--%>
                 <%--* @param setIDs        Sets IDs when DOM is loaded. If using a PHP counter, set to false.--%>
                 <%--* @param setClickAttr  Sets the attribute for the click handler.--%>
                 <%--*/--%>

                <%--function loadGallery(setIDs, setClickAttr){--%>
                    <%--var current_image,--%>
                        <%--selector,--%>
                        <%--counter = 0;--%>

                    <%--$('#show-next-image, #show-previous-image').click(function(){--%>
                        <%--if($(this).attr('id') == 'show-previous-image'){--%>
                            <%--current_image--;--%>
                        <%--} else {--%>
                            <%--current_image++;--%>
                        <%--}--%>

                        <%--selector = $('[data-image-id="' + current_image + '"]');--%>
                        <%--updateGallery(selector);--%>
                    <%--});--%>

                    <%--function updateGallery(selector) {--%>
                        <%--var $sel = selector;--%>
                        <%--current_image = $sel.data('image-id');--%>
                        <%--$('#image-gallery-caption').text($sel.data('caption'));--%>
                        <%--$('#image-gallery-title').text($sel.data('title'));--%>
                        <%--$('#image-gallery-image').attr('src', $sel.data('image'));--%>
                        <%--disableButtons(counter, $sel.data('image-id'));--%>
                    <%--}--%>

                    <%--if(setIDs == true){--%>
                        <%--$('[data-image-id]').each(function(){--%>
                            <%--counter++;--%>
                            <%--$(this).attr('data-image-id',counter);--%>
                        <%--});--%>
                    <%--}--%>
                    <%--$(setClickAttr).on('click',function(){--%>
                        <%--updateGallery($(this));--%>
                    <%--});--%>
                <%--}--%>
            <%--});--%>
        <%--</script>--%>

        <script>
            $(document).ready(function(){
                $("#doneEdit").hide();
                $('#deleteTrash').hide();
                $("#editEvent").click(function(){
                    $(this).hide();
                    $(".toggler").show();
                    $(".togglerFace").hide();
                    $("#doneEdit").show();
                    $("#deleteTrash").show();

                });
                $("#doneEdit").click(function(){
                    $(this).hide();
                    $(".toggler").hide();
                    $(".togglerFace").show();
                    $("#editEvent").show();
                    $("#deleteTrash").hide();

                    console.log("here");
                    var formData = new FormData();
                    var files = $('.file-upload').prop('files');
                    if(files.length!=0){
                        var file = files[0];
                        formData.append("file",file);
                    }
                    formData.append("eventName",$('#event-name').val());
                    formData.append("eventId",$('#event-id').val());
                    $.ajax({
                        url:BASEURL+'api/event/update-name-logo',
                        type:"POST",
                        data:formData,
                        processData: false,
                        contentType: false,
                        success:function (res) {
                            $('.event-logo-image').prop('src','/common/'+res.eventPhoto);
                            $('.event-name-static').html(res.name);
                            $('#event-image').val(res.name);
                        }
                    })
                });
                $('#deleteTrash').click(function () {
                    var eventIds = [];
                    $('.event-image').each(function () {
                        if($(this).prop('checked')){
                            eventIds.push($(this).attr('data-value'));
                        }
                    });
                    $.ajax(
                        {
                            url:BASEURL+'api/event/delete/images',
                            type:'POST',
                            dataType:'JSON',
                            data:{
                                eventId:$('#event-id').val(),
                                imageIds:eventIds,
                            },
                            success:function (res) {
                                var obj = JSON.parse(res);

                                eventIds.forEach(function (item) {
                                    $('#event-image'+item).remove();
                                    $.growl.notice({ message: "Image removed successfully" });
                                });

                                $('.image-count').html(obj);

                            }
                        }
                    );


                });
                var currentNode;
                var currentNum=0;
                $('.thumbnail').click(function () {
                    currentNode = $(this);
                    currentNum = parseInt($(this).attr('data-index'));
                    display_next_prev(currentNum);
                    var imageSrc = $(this).find('.img-responsive').prop('src');
                    $('#image-gallery').find('#image-gallery-image').attr('src',imageSrc);
                    $('#image-gallery').modal('show');
                });
                
                $('#show-next-image').click(function () {
                    var total_img = parseInt($('.image-count').html());

                    if(currentNode.closest('.thumb').next().find('.thumbnail')){
                        if(currentNum<total_img){
                            currentNum = currentNum+1;
                        }
                        display_next_prev(currentNum)
                        var next_image = currentNode.closest('.thumb').next().find('.img-responsive').prop('src');
                        $('#image-gallery').find('#image-gallery-image').attr('src',next_image);
                        currentNode =currentNode.closest('.thumb').next().find('.thumbnail');

                    }

                });

                $('#show-previous-image').click(function () {
                    if(currentNode.closest('.thumb').prev().find('.thumbnail')){
                        if(currentNum>0){
                            currentNum = currentNum-1;
                        }
                        display_next_prev(currentNum);
                        var next_image = currentNode.closest('.thumb').prev().find('.img-responsive').prop('src');
                        $('#image-gallery').find('#image-gallery-image').attr('src',next_image);
                        currentNode =currentNode.closest('.thumb').prev().find('.thumbnail');
                    }



                });

                function display_next_prev(index){
                    var total_img = parseInt($('.image-count').html());
                    $('#show-previous-image').show();
                    $('#show-next-image').show();
                    if(index==0){
                        $('#show-previous-image').hide();
                    }
                    if(index==total_img-1){
                        $('#show-next-image').hide();
                    }


                }
            });
        </script>
    </jsp:body>
</t:genericpage>

