/**
 * Created by mi_rafi on 1/3/18.
 */
var $imageupload = $('.imageupload');
$imageupload.imageupload({
    maxWidth: 500,
    maxHeight: 500,
    maxFileSizeKb: 3048
});

$(document).ready(function()
{
    $("#e9").select2( {maximumSelectionSize: 3});
    $("#e1").select2();
    $("#e2").select2();
    $("#e-placement").select2();
    $("#e-size").select2();
});


// $(function() {
//     $('input[name="startdate"]').daterangepicker({
//             singleDatePicker: true,
//             showDropdowns: true
//         },
//
//         function(start, end, label) {
//             var years = moment().diff(start, 'years');
//             alert("You are " + years + " years old.");
//         });
//     $('input[name="enddate"]').daterangepicker({
//             singleDatePicker: true,
//             showDropdowns: true
//         },
//
//         function(start, end, label) {
//             var years = moment().diff(start, 'years');
//             alert("You are " + years + " years old.");
//         });
//
//     $('input[name="starttime"]').timepicker({
//         timeFormat: 'hh:mm tt'
//     });
// });
//
//
// $(".choose-btn > .btn").click(function(){
//     $(".choose-btn > .btn").removeClass("active");
//     $(this).addClass("active");
// });
//
//
// var label = document.querySelector(".label");
// var c = document.getElementById("c");
// var ctx = c.getContext("2d");
// var cw = c.width = 700;
// var ch = c.height = 350;
// var cx = cw / 2,
//     cy = ch / 2;
// var rad = Math.PI / 180;
// var frames = 0;
//
// ctx.lineWidth = 1;
// ctx.strokeStyle = "#999";
// ctx.fillStyle = "#ccc";
// ctx.font = "14px monospace";
//
// var grd = ctx.createLinearGradient(0, 0, 0, cy);
// grd.addColorStop(0, "hsla(167,72%,60%,1)");
// grd.addColorStop(1, "hsla(167,72%,60%,0)");
//
// var oData = {
//     "2008": 10,
//     "2009": 39.9,
//     "2010": 17,
//     "2011": 30.0,
//     "2012": 5.3,
//     "2013": 38.4,
//     "2014": 15.7,
//     "2015": 9.0
// };
//
// var valuesRy = [];
// var propsRy = [];
// for (var prop in oData) {
//
//     valuesRy.push(oData[prop]);
//     propsRy.push(prop);
// }
//
//
// var vData = 4;
// var hData = valuesRy.length;
// var offset = 50.5; //offset chart axis
// var chartHeight = ch - 2 * offset;
// var chartWidth = cw - 2 * offset;
// var t = 1 / 7; // curvature : 0 = no curvature
// var speed = 2; // for the animation
//
// var A = {
//     x: offset,
//     y: offset
// }
// var B = {
//     x: offset,
//     y: offset + chartHeight
// }
// var C = {
//     x: offset + chartWidth,
//     y: offset + chartHeight
// }
//
// /*
//  A  ^
//  |  |
//  + 25
//  |
//  |
//  |
//  + 25
//  |__|_________________________________  C
//  B
//  */
//
// // CHART AXIS -------------------------
// ctx.beginPath();
// ctx.moveTo(A.x, A.y);
// ctx.lineTo(B.x, B.y);
// ctx.lineTo(C.x, C.y);
// ctx.stroke();
//
// // vertical ( A - B )
// var aStep = (chartHeight - 50) / (vData);
//
// var Max = Math.ceil(arrayMax(valuesRy) / 10) * 10;
// var Min = Math.floor(arrayMin(valuesRy) / 10) * 10;
// var aStepValue = (Max - Min) / (vData);
// console.log("aStepValue: " + aStepValue); //8 units
// var verticalUnit = aStep / aStepValue;
//
// var a = [];
// ctx.textAlign = "right";
// ctx.textBaseline = "middle";
// for (var i = 0; i <= vData; i++) {
//
//     if (i == 0) {
//         a[i] = {
//             x: A.x,
//             y: A.y + 25,
//             val: Max
//         }
//     } else {
//         a[i] = {}
//         a[i].x = a[i - 1].x;
//         a[i].y = a[i - 1].y + aStep;
//         a[i].val = a[i - 1].val - aStepValue;
//     }
//     drawCoords(a[i], 3, 0);
// }
//
// //horizontal ( B - C )
// var b = [];
// ctx.textAlign = "center";
// ctx.textBaseline = "hanging";
// var bStep = chartWidth / (hData + 1);
//
// for (var i = 0; i < hData; i++) {
//     if (i == 0) {
//         b[i] = {
//             x: B.x + bStep,
//             y: B.y,
//             val: propsRy[0]
//         };
//     } else {
//         b[i] = {}
//         b[i].x = b[i - 1].x + bStep;
//         b[i].y = b[i - 1].y;
//         b[i].val = propsRy[i]
//     }
//     drawCoords(b[i], 0, 3)
// }
//
// function drawCoords(o, offX, offY) {
//     ctx.beginPath();
//     ctx.moveTo(o.x - offX, o.y - offY);
//     ctx.lineTo(o.x + offX, o.y + offY);
//     ctx.stroke();
//
//     ctx.fillText(o.val, o.x - 2 * offX, o.y + 2 * offY);
// }
// //----------------------------------------------------------
//
// // DATA
// var oDots = [];
// var oFlat = [];
// var i = 0;
//
// for (var prop in oData) {
//     oDots[i] = {}
//     oFlat[i] = {}
//
//     oDots[i].x = b[i].x;
//     oFlat[i].x = b[i].x;
//
//     oDots[i].y = b[i].y - oData[prop] * verticalUnit - 25;
//     oFlat[i].y = b[i].y - 25;
//
//     oDots[i].val = oData[b[i].val];
//
//     i++
// }
//
//
//
// ///// Animation Chart ///////////////////////////
// //var speed = 3;
// function animateChart() {
//     requestId = window.requestAnimationFrame(animateChart);
//     frames += speed; //console.log(frames)
//     ctx.clearRect(60, 0, cw, ch - 60);
//
//     for (var i = 0; i < oFlat.length; i++) {
//         if (oFlat[i].y > oDots[i].y) {
//             oFlat[i].y -= speed;
//         }
//     }
//     drawCurve(oFlat);
//     for (var i = 0; i < oFlat.length; i++) {
//         ctx.fillText(oDots[i].val, oFlat[i].x, oFlat[i].y - 25);
//         ctx.beginPath();
//         ctx.arc(oFlat[i].x, oFlat[i].y, 3, 0, 2 * Math.PI);
//         ctx.fill();
//     }
//
//     if (frames >= Max * verticalUnit) {
//         window.cancelAnimationFrame(requestId);
//
//     }
// }
// requestId = window.requestAnimationFrame(animateChart);
//
// /////// EVENTS //////////////////////
// c.addEventListener("mousemove", function(e) {
//     label.innerHTML = "";
//     label.style.display = "none";
//     this.style.cursor = "default";
//
//     var m = oMousePos(this, e);
//     for (var i = 0; i < oDots.length; i++) {
//
//         output(m, i);
//     }
//
// }, false);
//
// function output(m, i) {
//     ctx.beginPath();
//     ctx.arc(oDots[i].x, oDots[i].y, 20, 0, 2 * Math.PI);
//     if (ctx.isPointInPath(m.x, m.y)) {
//         //console.log(i);
//         label.style.display = "block";
//         label.style.top = (m.y + 10) + "px";
//         label.style.left = (m.x + 10) + "px";
//         label.innerHTML = "<strong>" + propsRy[i] + "</strong>: " + valuesRy[i] + "%";
//         c.style.cursor = "pointer";
//     }
// }
//
// // CURVATURE
// function controlPoints(p) {
//     // given the points array p calculate the control points
//     var pc = [];
//     for (var i = 1; i < p.length - 1; i++) {
//         var dx = p[i - 1].x - p[i + 1].x; // difference x
//         var dy = p[i - 1].y - p[i + 1].y; // difference y
//         // the first control point
//         var x1 = p[i].x - dx * t;
//         var y1 = p[i].y - dy * t;
//         var o1 = {
//             x: x1,
//             y: y1
//         };
//
//         // the second control point
//         var x2 = p[i].x + dx * t;
//         var y2 = p[i].y + dy * t;
//         var o2 = {
//             x: x2,
//             y: y2
//         };
//
//         // building the control points array
//         pc[i] = [];
//         pc[i].push(o1);
//         pc[i].push(o2);
//     }
//     return pc;
// }
//
// function drawCurve(p) {
//
//     var pc = controlPoints(p); // the control points array
//
//     ctx.beginPath();
//     //ctx.moveTo(p[0].x, B.y- 25);
//     ctx.lineTo(p[0].x, p[0].y);
//     // the first & the last curve are quadratic Bezier
//     // because I'm using push(), pc[i][1] comes before pc[i][0]
//     ctx.quadraticCurveTo(pc[1][1].x, pc[1][1].y, p[1].x, p[1].y);
//
//     if (p.length > 2) {
//         // central curves are cubic Bezier
//         for (var i = 1; i < p.length - 2; i++) {
//             ctx.bezierCurveTo(pc[i][0].x, pc[i][0].y, pc[i + 1][1].x, pc[i + 1][1].y, p[i + 1].x, p[i + 1].y);
//         }
//         // the first & the last curve are quadratic Bezier
//         var n = p.length - 1;
//         ctx.quadraticCurveTo(pc[n - 1][0].x, pc[n - 1][0].y, p[n].x, p[n].y);
//     }
//
//     //ctx.lineTo(p[p.length-1].x, B.y- 25);
//     ctx.stroke();
//     ctx.save();
//     ctx.fillStyle = grd;
//     ctx.fill();
//     ctx.restore();
// }
//
// function arrayMax(array) {
//     return Math.max.apply(Math, array);
// };
//
// function arrayMin(array) {
//     return Math.min.apply(Math, array);
// };
//
// function oMousePos(canvas, evt) {
//     var ClientRect = canvas.getBoundingClientRect();
//     return { //objeto
//         x: Math.round(evt.clientX - ClientRect.left),
//         y: Math.round(evt.clientY - ClientRect.top)
//     }
// }




var rangeSlider = function(){
    var slider = $('.range-slider'),
        range = $('.range-slider__range'),
        value = $('.range-slider__value');

    slider.each(function(){

        value.each(function(){
            var value = $(this).prev().attr('value');
            $(this).html(value);
        });

        range.on('input', function(){
            $(this).next(value).html(this.value);
        });
    });
};

rangeSlider();




$(document).ready( function() {
    $(document).on('change', '.btn-file :file', function() {
        var input = $(this),
            label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
        input.trigger('fileselect', [label]);
    });

    $('.btn-file :file').on('fileselect', function(event, label) {

        var input = $(this).parents('.input-group').find(':text'),
            log = label;

        if( input.length ) {
            input.val(log);
        } else {
            if( log ) alert(log);
        }

    });
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#img-upload').attr('src', e.target.result);
            }

            reader.readAsDataURL(input.files[0]);
        }
    }

    $("#imgInp").change(function(){
        readURL(this);
    });
});



$(document).ready(function(){
    //Drop Zone Binding
    configwatermarkLogoDropZone();

    //Injecting necessary hidden elements
    // to store uploaded file token
    injectHiddenTokenFields();
});

function notifyUpdateStatus(){
    $("#successMsg").html("Successfully updated").fadeIn(500).delay( 1000 ).fadeIn(500,function(){
        location.reload();
    });

}
function injectHiddenTokenFields(){
    var watermarkLogoTokenElement = $("<input>", {type:"hidden",id: "watermarkLogoToken", "value": ""});
    $("body").append(watermarkLogoTokenElement);

}
function getwatermarkLogoToken(){
    var token=0;
    try{
        token = parseInt($("#watermarkLogoToken").val());
        if(isNaN(token)){
            token = 0;
        }
    }catch(ex) {
        console.log(ex);
        token = 0;
    }
    return token;
}
function storewatermarkLogoToken(token){
    $("#watermarkLogoToken").val(token);
}
function removewatermarkLogoToken(token){
    var watermarkLogoToken = getwatermarkLogoToken();
    if(watermarkLogoToken==token){
        $("#watermarkLogoToken").val("");
    }

}

function configwatermarkLogoDropZone(){
    var watermarkLogoImgDropZone = new Dropzone("div#watermarkLogoImg",
        {
            url: BASEURL+"file/upload/photographer-profile-image",
            method:"post",
            paramName:"profileImg",
            maxFilesize: 1,
            maxFiles:1,
            addRemoveLinks: true,
            previewTemplate:$("#dropZonePreview").html(),
            init:function(){

                this.on("maxfilesexceeded", function(file) {
                    this.removeAllFiles();
                    this.addFile(file);
                });
                this.on("addedfile", function(file) {
                    file._removeLink.addEventListener("click", function() {
                        console.log(file);
                        removeImageByToken(file.token,function (data) {
                            removewatermarkLogoToken(data.token);
                        });
                        profilePictureToken = 0;
                        var _ref;
                        watermarkLogoImgDropZone.removeFile(file);
                    });
                });

            },
            error:function(file,response){
                var msg = (typeof response == "object")?((response.length>0)?response[0].msg:""):response;
                $("#profileImg").find(".dz-error-message span").html(msg);
            },
            success:function(file,response){

                file.token = response.token;
                storewatermarkLogoToken(response.token);
                console.log(file);
            }
        }
    );
}