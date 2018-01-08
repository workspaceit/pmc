<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                                <canvas class="for-mobile" id='c'></canvas>
                            </div>
                            <div class="label">text</div>
                        </div>
                        <p style="margin-top: 20px;font-size: 20px">Please mouse over the dots</p>
                    </div>
                    <div class="col-lg-5" style="margin-top: 20px;margin-bottom: 2opx">
                        <div class="col-lg-6" style="text-align: center;">
                            <p class="p_another">Total Photos Uploaded</p>
                            <h4 class="huge_another" style="color: #455578 !important">2864</h4>
                        </div>
                        <div class="col-lg-6" style="text-align: center;">
                            <p class="p_another">Total Photos Uploaded</p>
                            <h4 class="huge_another" style="color: #5cb85c !important">872</h4>
                        </div>
                    </div>
                    <div class="col-lg-5" style="margin-top: 20px;margin-bottom: 2opx">
                        <div class="col-lg-6" style="text-align: center;">
                            <p class="p_another">Total Photos Uploaded</p>
                            <h4 class="huge_another" style="color: #f0ad4e !important">39</h4>
                        </div>
                        <div class="col-lg-6" style="text-align: center;">
                            <p class="p_another">Total Photos Uploaded</p>
                            <h4 class="huge_another" style="color: #d9534f !important">17</h4>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-12" style="margin-top: 70px;">
                        <h3 class="uni-header"><span>Most popular Galleries</span></h3>
                        <div class="table-responsive">
                            <table class="table table-hover cstm-table table-bordered">
                                <thead>
                                <tr>
                                    <th>NAME</th>
                                    <th style="text-align: center;">FIRST UPLOAD</th>
                                    <th style="text-align: center;">STATUS</th>
                                    <th style="text-align: center;">TOTAL PHOTOS</th>
                                    <th style="text-align: center;">ACTION</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td><a href="events.html">Southern Fire (June)</a></td>
                                    <td style="text-align: center;">04/16/2017</td>
                                    <td style="text-align: center;">Not Active</td>
                                    <td style="text-align: center;">279</td>
                                    <td style="text-align:center;"><a href="gallery2.html" class="btns btns1">View Gallery</a></td>
                                </tr>
                                <tr>
                                    <td><a href="events.html">Phantom Entertainment</a></td>
                                    <td style="text-align: center;">02/25/2017</td>
                                    <td style="text-align: center;">Not Active</td>
                                    <td style="text-align: center;">86</td>
                                    <td style="text-align:center;"><a href="gallery2.html" class="btns btns1">View Gallery</a></td>
                                </tr>
                                <tr>
                                    <td><a href="events.html">Nikis</a></td>
                                    <td style="text-align: center;">06/17/2017</td>
                                    <td style="text-align: center;">Not Active</td>
                                    <td style="text-align: center;">72</td>
                                    <td style="text-align:center;"><a href="gallery2.html" class="btns btns1">View Gallery</a></td>
                                </tr>
                                <tr>
                                    <td><a href="events.html">29 Park</a></td>
                                    <td style="text-align: center;">02/20/2017</td>
                                    <td style="text-align: center;">Not Active</td>
                                    <td style="text-align: center;">136</td>
                                    <td style="text-align:center;"><a href="gallery2.html" class="btns btns1">View Gallery</a></td>
                                </tr>
                                <tr>
                                    <td><a href="events.html">Southern Fire (July)</a></td>
                                    <td style="text-align: center;">06/30/2017</td>
                                    <td style="text-align: center;">Not Active</td>
                                    <td style="text-align: center;">720</td>
                                    <td style="text-align:center;" ><a href="gallery2.html" class="btns btns1">View Gallery</a></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>

                    </div>
                </div>
                <hr />
                <div class="row">
                    <div class="col-lg-12">
                        <br />
                        <h3 class="uni-header"><span>Notification panel</span></h3>
                        <div class="list-group">
                            <a href="#" class="list-group-item">
                                <span class="badge badge-success">just now</span>
                                New Event Added
                            </a>
                            <a href="#" class="list-group-item">
                                <span class="badge badge-success">4 minutes ago</span>
                                Photos updated on Wedding Mass
                            </a>
                            <a href="#" class="list-group-item">
                                <span class="badge badge-success">23 minutes ago</span>
                                Order 392 shipped
                            </a>
                            <a href="#" class="list-group-item">
                                <span class="badge badge-success">46 minutes ago</span>
                                Invoice 653 has been paid
                            </a>
                            <a href="#" class="list-group-item">
                                <span class="badge badge-success">1 hour ago</span>
                                A new user has been added
                            </a>
                            <a href="#" class="list-group-item">
                                <span class="badge badge-success">2 hours ago</span>
                                Completed task: "pick up dry cleaning"
                            </a>
                            <a href="#" class="list-group-item">
                                <span class="badge badge-success">yesterday</span>
                                Saved the world
                            </a>
                            <a href="#" class="list-group-item">
                                <span class="badge badge-success">two days ago</span>
                                Completed task: "fix error on sales page"
                            </a>
                        </div>
                    </div>
                </div>

                <!-- newwst upload -->
                <section class="new upload">
                    <div class="row" style="padding:0px;">
                        <div class="col-md-12">
                            <h3 class="uni-header"><span>Newest Uploads</span></h3>
                            <div class="row clearfix">
                                <div class="col-lg-3" class="box_padding_img">
                                    <a href="gallery2.html"><img src="<c:url value="/resources/images/img_01.jpg"/>" class="img-responsive event-img" style="height: 160px;"></a>
                                    <p style="font-size: 20px;margin-top: 10px;">4 days ago</p>
                                </div>
                                <div class="col-lg-3" class="box_padding_img">
                                    <a href="gallery2.html"><img src="<c:url value="/resources/images/img_02.jpg"/>" class="img-responsive event-img" style="height: 160px;"></a>
                                    <p style="font-size: 20px;margin-top: 10px;">12 hours ago</p>
                                </div>
                                <div class="col-lg-3" class="box_padding_img">
                                    <a href="gallery2.html"><img src="<c:url value="/resources/images/img_03.jpg"/>" class="img-responsive event-img" style="height: 160px;"></a>
                                    <p style="font-size: 20px;margin-top: 10px;">1 days ago</p>
                                </div>
                                <div class="col-lg-3" class="box_padding_img">
                                    <a href="gallery2.html"><img src="<c:url value="/resources/images/img_04.jpg"/>" class="img-responsive event-img" style="height: 160px;"></a>
                                    <p style="font-size: 20px;margin-top: 10px;">7 days ago</p>
                                </div>
                            </div>
                        </div>
                    </div>

                </section>
                <!-- newwst upload -->

                <!-- /.row -->

            </div>
            <!-- /.container-fluid -->

        </div>
        <script type="text/javascript">
            var label = document.querySelector(".label");
            var c = document.getElementById("c");
            var ctx = c.getContext("2d");
            var cw = c.width = 700;
            var ch = c.height = 350;
            var cx = cw / 2,
                cy = ch / 2;
            var rad = Math.PI / 180;
            var frames = 0;

            ctx.lineWidth = 1;
            ctx.strokeStyle = "#999";
            ctx.fillStyle = "#ccc";
            ctx.font = "14px monospace";

            var grd = ctx.createLinearGradient(0, 0, 0, cy);
            grd.addColorStop(0, "hsla(204,90%,60%,1)");
            grd.addColorStop(1, "hsla(204,90%,60%,0)");

            var oData = {
                "JAN": 10,
                "FEB": 32,
                "MAR": 29,
                "APR": 30.0,
                "MAY": 22.3,
                "JUN": 23,
                "JUL": 15.7,
                "AUG": 19.0,
                "SEP": 17.0,
                "OCT": 14.0,
                "NOV": 12.0,
                "DEC": 9.0,
            };

            var valuesRy = [];
            var propsRy = [];
            for (var prop in oData) {

                valuesRy.push(oData[prop]);
                propsRy.push(prop);
            }


            var vData = 4;
            var hData = valuesRy.length;
            var offset = 50.5; //offset chart axis
            var chartHeight = ch - 2 * offset;
            var chartWidth = cw - 2 * offset;
            var t = 1 / 7; // curvature : 0 = no curvature
            var speed = 2; // for the animation

            var A = {
                x: offset,
                y: offset
            }
            var B = {
                x: offset,
                y: offset + chartHeight
            }
            var C = {
                x: offset + chartWidth,
                y: offset + chartHeight
            }

            /*
             A  ^
             |  |
             + 25
             |
             |
             |
             + 25
             |__|_________________________________  C
             B
             */

            // CHART AXIS -------------------------
            ctx.beginPath();
            ctx.moveTo(A.x, A.y);
            ctx.lineTo(B.x, B.y);
            ctx.lineTo(C.x, C.y);
            ctx.stroke();

            // vertical ( A - B )
            var aStep = (chartHeight - 50) / (vData);

            var Max = Math.ceil(arrayMax(valuesRy) / 10) * 10;
            var Min = Math.floor(arrayMin(valuesRy) / 10) * 10;
            var aStepValue = (Max - Min) / (vData);
            console.log("aStepValue: " + aStepValue); //8 units
            var verticalUnit = aStep / aStepValue;

            var a = [];
            ctx.textAlign = "right";
            ctx.textBaseline = "middle";
            for (var i = 0; i <= vData; i++) {

                if (i == 0) {
                    a[i] = {
                        x: A.x,
                        y: A.y + 25,
                        val: Max
                    }
                } else {
                    a[i] = {}
                    a[i].x = a[i - 1].x;
                    a[i].y = a[i - 1].y + aStep;
                    a[i].val = a[i - 1].val - aStepValue;
                }
                drawCoords(a[i], 3, 0);
            }

            //horizontal ( B - C )
            var b = [];
            ctx.textAlign = "center";
            ctx.textBaseline = "hanging";
            var bStep = chartWidth / (hData + 1);

            for (var i = 0; i < hData; i++) {
                if (i == 0) {
                    b[i] = {
                        x: B.x + bStep,
                        y: B.y,
                        val: propsRy[0]
                    };
                } else {
                    b[i] = {}
                    b[i].x = b[i - 1].x + bStep;
                    b[i].y = b[i - 1].y;
                    b[i].val = propsRy[i]
                }
                drawCoords(b[i], 0, 3)
            }

            function drawCoords(o, offX, offY) {
                ctx.beginPath();
                ctx.moveTo(o.x - offX, o.y - offY);
                ctx.lineTo(o.x + offX, o.y + offY);
                ctx.stroke();

                ctx.fillText(o.val, o.x - 2 * offX, o.y + 2 * offY);
            }
            //----------------------------------------------------------

            // DATA
            var oDots = [];
            var oFlat = [];
            var i = 0;

            for (var prop in oData) {
                oDots[i] = {}
                oFlat[i] = {}

                oDots[i].x = b[i].x;
                oFlat[i].x = b[i].x;

                oDots[i].y = b[i].y - oData[prop] * verticalUnit - 25;
                oFlat[i].y = b[i].y - 25;

                oDots[i].val = oData[b[i].val];

                i++
            }



            ///// Animation Chart ///////////////////////////
            //var speed = 3;
            function animateChart() {
                requestId = window.requestAnimationFrame(animateChart);
                frames += speed; //console.log(frames)
                ctx.clearRect(60, 0, cw, ch - 60);

                for (var i = 0; i < oFlat.length; i++) {
                    if (oFlat[i].y > oDots[i].y) {
                        oFlat[i].y -= speed;
                    }
                }
                drawCurve(oFlat);
                for (var i = 0; i < oFlat.length; i++) {
                    ctx.fillText(oDots[i].val, oFlat[i].x, oFlat[i].y - 25);
                    ctx.beginPath();
                    ctx.arc(oFlat[i].x, oFlat[i].y, 3, 0, 2 * Math.PI);
                    ctx.fill();
                }

                if (frames >= Max * verticalUnit) {
                    window.cancelAnimationFrame(requestId);

                }
            }
            requestId = window.requestAnimationFrame(animateChart);

            /////// EVENTS //////////////////////
            c.addEventListener("mousemove", function(e) {
                label.innerHTML = "";
                label.style.display = "none";
                this.style.cursor = "default";

                var m = oMousePos(this, e);
                for (var i = 0; i < oDots.length; i++) {

                    output(m, i);
                }

            }, false);

            function output(m, i) {
                ctx.beginPath();
                ctx.arc(oDots[i].x, oDots[i].y, 20, 0, 2 * Math.PI);
                if (ctx.isPointInPath(m.x, m.y)) {
                    //console.log(i);
                    label.style.display = "block";
                    label.style.top = (m.y + 10) + "px";
                    label.style.left = (m.x + 10) + "px";
                    label.innerHTML = "<strong>" + propsRy[i] + "</strong>: " + valuesRy[i] + "%";
                    c.style.cursor = "pointer";
                }
            }

            // CURVATURE
            function controlPoints(p) {
                // given the points array p calculate the control points
                var pc = [];
                for (var i = 1; i < p.length - 1; i++) {
                    var dx = p[i - 1].x - p[i + 1].x; // difference x
                    var dy = p[i - 1].y - p[i + 1].y; // difference y
                    // the first control point
                    var x1 = p[i].x - dx * t;
                    var y1 = p[i].y - dy * t;
                    var o1 = {
                        x: x1,
                        y: y1
                    };

                    // the second control point
                    var x2 = p[i].x + dx * t;
                    var y2 = p[i].y + dy * t;
                    var o2 = {
                        x: x2,
                        y: y2
                    };

                    // building the control points array
                    pc[i] = [];
                    pc[i].push(o1);
                    pc[i].push(o2);
                }
                return pc;
            }

            function drawCurve(p) {

                var pc = controlPoints(p); // the control points array

                ctx.beginPath();
                //ctx.moveTo(p[0].x, B.y- 25);
                ctx.lineTo(p[0].x, p[0].y);
                // the first & the last curve are quadratic Bezier
                // because I'm using push(), pc[i][1] comes before pc[i][0]
                ctx.quadraticCurveTo(pc[1][1].x, pc[1][1].y, p[1].x, p[1].y);

                if (p.length > 2) {
                    // central curves are cubic Bezier
                    for (var i = 1; i < p.length - 2; i++) {
                        ctx.bezierCurveTo(pc[i][0].x, pc[i][0].y, pc[i + 1][1].x, pc[i + 1][1].y, p[i + 1].x, p[i + 1].y);
                    }
                    // the first & the last curve are quadratic Bezier
                    var n = p.length - 1;
                    ctx.quadraticCurveTo(pc[n - 1][0].x, pc[n - 1][0].y, p[n].x, p[n].y);
                }

                //ctx.lineTo(p[p.length-1].x, B.y- 25);
                ctx.stroke();
                ctx.save();
                ctx.fillStyle = grd;
                ctx.fill();
                ctx.restore();
            }

            function arrayMax(array) {
                return Math.max.apply(Math, array);
            };

            function arrayMin(array) {
                return Math.min.apply(Math, array);
            };

            function oMousePos(canvas, evt) {
                var ClientRect = canvas.getBoundingClientRect();
                return { //objeto
                    x: Math.round(evt.clientX - ClientRect.left),
                    y: Math.round(evt.clientY - ClientRect.top)
                }
            }
        </script>
    </jsp:body>
</t:genericpage>