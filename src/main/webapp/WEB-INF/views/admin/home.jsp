<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<t:genericpage>
    <jsp:body>
        <div id="page-wrapper">

            <div class="container">

                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <!-- <ol class="breadcrumb">
                            <li class="active">
                                <i class="fa fa-dashboard"></i> Dashboard
                            </li>
                        </ol> -->
                    </div>
                </div>
                <!-- /.row -->
                <br>


                <div class="row" style="">
                    <div class="col-lg-7">
                        <div class="wrapper_graph">
                            <h3 class="uni-header"><span>Photos Uploaded</span></h3>
                            <div class="canvas-holder">
                                    <%--<canvas class="for-mobile" id='c'></canvas>--%>
                                <canvas class="for-mobile" id="canvas"></canvas>
                            </div>
                            <div class="label">text</div>
                        </div>
                        <%--<p style="margin-top: 20px;font-size: 20px">Please mouse over the dots</p>--%>
                    </div>
                    <div class="col-lg-5" style="margin-top: 20px;margin-bottom: 2opx">
                        <div class="col-lg-6" style="text-align: center;">
                            <p class="p_another">Total Photos Uploaded</p>
                            <h4 class="huge_another" style="color: #455578 !important">${totalImages}</h4>
                        </div>
                        <div class="col-lg-6" style="text-align: center;">
                            <p class="p_another">Total Photos Uploaded in Last week</p>
                            <h4 class="huge_another" style="color: #5cb85c !important">${totalImagesInLastWeek}</h4>
                        </div>
                    </div>
                    <div class="col-lg-5" style="margin-top: 20px;margin-bottom: 2opx">
                        <div class="col-lg-6" style="text-align: center;">
                            <p class="p_another">Total Events</p>
                            <h4 class="huge_another" style="color: #f0ad4e !important">${totalEvents}</h4>
                        </div>
                        <div class="col-lg-6" style="text-align: center;">
                            <p class="p_another">Total Events In Last Week</p>
                            <h4 class="huge_another" style="color: #d9534f !important">${totalEventsInLastWeek}</h4>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-12" style="margin-top: 70px;">
                        <h3 class="uni-header"><span>Most Recent Events</span></h3>
                        <div class="table-responsive">
                            <table class="table table-hover cstm-table table-bordered">
                                <thead>
                                <tr>
                                    <th>NAME</th>
                                    <th style="text-align: center;">CREATED</th>
                                    <th style="text-align: center;">STATUS</th>
                                    <th style="text-align: center;">TOTAL PHOTOS</th>
                                    <th style="text-align: center;">ACTION</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${topActiveEvents}" var="event">
                                    <tr>
                                        <td><a href="events.html">${event.name}</a></td>
                                        <td style="text-align: center;">
                                            <fmt:formatDate value="${event.createAt}" pattern="yyyy/MM/dd"/>
                                        </td>
                                        <td style="text-align: center;">
                                            <c:choose>
                                                <c:when test="${event.status}">
                                                    Active
                                                </c:when>
                                                <c:otherwise>
                                                    Inactive
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td style="text-align: center;">${event.noOfImages}</td>
                                        <td style="text-align:center;"><a
                                                href="<c:url value="/admin/event/gallery/${event.id}" />"
                                                class="btns btns1">View Gallery</a></td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                            </table>
                        </div>

                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div class="col-lg-12">
                        <br/>
                        <h3 class="uni-header"><span>Notification panel</span></h3>
                        <div class="list-group">
                            <c:forEach var="notification" items="${notifications}" >
                                <fmt:formatDate var="createdDate" value="${notification.createdAt}" pattern="yyyy-MM-dd H:m:s" />
                                <a href="javascript:void(0)" class="list-group-item" style="cursor: default">
                                    ${notification.details}
                                        <span class="badge badge-success " >
                                          <time class="timeago" datetime="${createdDate}" ></time>
                                        </span>
                                </a>
                            </c:forEach>
                        </div>
                    </div>
                </div>

                <!-- newwst upload -->
                    <%--<section class="new upload">--%>
                    <%--<div class="row" style="padding:0px;">--%>
                    <%--<div class="col-md-12">--%>
                    <%--<h3 class="uni-header"><span>Newest Uploads</span></h3>--%>
                    <%--<div class="row clearfix">--%>
                    <%--<div class="col-lg-3" class="box_padding_img">--%>
                    <%--<a href="gallery2.html"><img src="<c:url value="/resources/images/img_01.jpg"/>" class="img-responsive event-img" style="height: 160px;"></a>--%>
                    <%--<p style="font-size: 20px;margin-top: 10px;">4 days ago</p>--%>
                    <%--</div>--%>
                    <%--<div class="col-lg-3" class="box_padding_img">--%>
                    <%--<a href="gallery2.html"><img src="<c:url value="/resources/images/img_02.jpg"/>" class="img-responsive event-img" style="height: 160px;"></a>--%>
                    <%--<p style="font-size: 20px;margin-top: 10px;">12 hours ago</p>--%>
                    <%--</div>--%>
                    <%--<div class="col-lg-3" class="box_padding_img">--%>
                    <%--<a href="gallery2.html"><img src="<c:url value="/resources/images/img_03.jpg"/>" class="img-responsive event-img" style="height: 160px;"></a>--%>
                    <%--<p style="font-size: 20px;margin-top: 10px;">1 days ago</p>--%>
                    <%--</div>--%>
                    <%--<div class="col-lg-3" class="box_padding_img">--%>
                    <%--<a href="gallery2.html"><img src="<c:url value="/resources/images/img_04.jpg"/>" class="img-responsive event-img" style="height: 160px;"></a>--%>
                    <%--<p style="font-size: 20px;margin-top: 10px;">7 days ago</p>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--</div>--%>

                    <%--</section>--%>
                <!-- newwst upload -->

                <!-- /.row -->

            </div>
            <!-- /.container-fluid -->

        </div>
        <script>

            $(document).ready(function () {
                $("time.timeago").timeago();

                $.ajax({
                    url:BASEURL+'api/event/monthwise-event-image-count',
                    type:'GET',
                    success:function (data) {
                        console.log(data);
                        renderGraph(data);
                    }
                });
            });

            function renderGraph(data){
                var MONTHS = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
                var config = {
                    type: 'line',
                    data: {
                        labels: MONTHS,
                        datasets: [
                            {
                                label: 'Photo Uploaded',
                                fill: false,
                                backgroundColor: window.chartColors.blue,
                                borderColor: window.chartColors.blue,
                                data: data
                            }]
                    },
                    options: {
                        responsive: true,
                        title: {
                            display: false,
                            text: 'Photo Uploaded'
                        },
                        tooltips: {
                            mode: 'index',
                            intersect: false,
                        },
                        hover: {
                            mode: 'nearest',
                            intersect: true
                        },
                        scales: {
                            xAxes: [{
                                display: true,
                                scaleLabel: {
                                    display: false,
                                    labelString: 'Month'
                                }
                            }],
                            yAxes: [{
                                display: true,
                                scaleLabel: {
                                    display: false,
                                    labelString: 'Photos'
                                }
                            }]
                        }
                    }
                };
                var ctx =$('#canvas');
                var myChart = new Chart(ctx,config);
            }
        </script>
        <script src="<s:url value="/resources/js/Chart.js"/>"></script>
        <script src="<s:url value="/resources/js/chart-utils.js"/>"></script>
        <script src="<s:url value="/resources/js/timeago.js" />"></script>
    </jsp:body>
</t:genericpage>