$(document).ready(function () {
    var pictureToken = 0;
    var eventImgDropzone = new Dropzone("div#eventImg", {
            url: BASEURL + "file/upload/event-image",
            method: "post",
            paramName: "profileImg",
            maxFilesize: 1,
            maxFiles: 1,
            addRemoveLinks: true,
            previewTemplate: $("#dropZonePreview").html(),
            init: function () {
                this.on("maxfilesexceeded", function (file) {
                    this.removeAllFiles();
                    this.addFile(file);
                });
                this.on("addedfile", function (file) {
                    file._removeLink.addEventListener("click", function () {
                        console.log(file);
                        removeFileByToken(file.token);
                        pictureToken = 0;
                        var _ref;
                        profileImgDropzone.removeFile(file);
                    });
                });

            },
            error: function (file, response) {
                var msg = (typeof response == "object") ? ((response.length > 0) ? response[0].msg : "") : response;
                $("#eventImg").find(".dz-error-message span").html(msg);
            },
            success: function (file, response) {
                file.token = response.token;
                pictureToken = response.token;
                console.log(file);
            }
        }
    );

    $('#save-watermark-btn').click(function () {
        console.log("submitting . . .");
        var eventName = $('#eventName').val();
        var startDateOnly = $('#startDate').val();
        var startTime = $('#startTime').val();
        var endDateOnly = $('#endDate').val();
        var endTime = $('#endTime').val();
        var isPrivate = $("input[name='private']:checked").val();
        var venueId = $('#venueId').val();
        var photographerIds = $('#photographer-select2').val();
        var advertiserIds = $('#advertiser-select2').val();
        var watermarkIds = $('#watermark-select2').val();


        var startDate = moment(startDateOnly + " " + startTime, "MM/DD/YYYY HH:mm").format('YYYY-MM-DD HH:mm:ss');
        var endDate = moment(endDateOnly + " " + endTime, "MM/DD/YYYY HH:mm").format('YYYY-MM-DD HH:mm:ss');


        var data = {
            'eventName': eventName,
            'startDate': startDate,
            'endDate' : endDate,
            'venueId': venueId,
            'photographerIds': photographerIds,
            'advertiserIds': advertiserIds,
            'watermarkIds': watermarkIds,
            'imageToken': pictureToken,
            'isPrivate': isPrivate
        };

        $.ajax({
            url: BASEURL+'api/event/create',
            data: data,
            type: 'POST',
            traditional: true,
            statusCode: {
                401: function (response) {
                    console.log(response);
                },
                422: function (response) {

                    BindErrorsWithHtml("errorObj_",response.responseJSON);
                    console.log(response);
                    // btnAction
                }
            },success: function(data){
                UnBindErrors("errorObj_");
                photographerAfterSaveActionCreate(btnAction);
            }
        });

    });

    $("#watermark-select2").select2({
        placeholder: 'Select Watermark(s)',
        multiple: true,
//                    minimumInputLength: 1,
        width: 'resolve',
        hideSelectionFromResult: true,
        ajax: {
            url: BASEURL + 'api/watermark/auto-suggest/',
            type: "GET",
            quietMillis: 100,
            data: function (query) {
                var term = "";
                if (query.term != undefined) {
                    term = query.term;
                }
                return {
                    searchTerm: term
                };
            },
            processResults: function (data, params) {
                return {
                    results: $.map(data, function (item) {
                        return {
                            'text': item.name,
                            'id': item.id
                        }
                    })
                };
            }
        }
    });
    $("#venueId").select2({
        placeholder: 'Select a Venue',
//                    minimumInputLength: 1,
        width: 'resolve',
        ajax: {
            url: BASEURL + 'api/venue/auto-suggest/',
            type: "GET",
            quietMillis: 100,
            data: function (query) {
                var term = "";
                if (query.term != undefined) {
                    term = query.term;
                }
                return {
                    searchTerm: term
                };
            },
            processResults: function (data, params) {
                return {
                    results: $.map(data, function (item) {
                        return {
                            'text': item.name,
                            'id': item.id
                        }
                    })
                };
            }
        }
    });
    $("#photographer-select2").select2({
        placeholder: 'Select Photographer(s)',
        multiple: true,
//                    minimumInputLength: 1,
        width: 'resolve',
        ajax: {
            url: BASEURL + 'api/photographer/auto-suggest/',
            type: "GET",
            quietMillis: 100,
            data: function (query) {
                var term = "";
                if (query.term != undefined) {
                    term = query.term;
                }
                return {
                    searchTerm: term
                };
            },
            processResults: function (data, params) {
                return {
                    results: $.map(data, function (item) {
                        return {
                            'text': item.fullName,
                            'id': item.id
                        }
                    })
                };
            }
        }
    });
    $("#advertiser-select2").select2({
        placeholder: 'Select Advertiser(s)',
        multiple: true,
//                    minimumInputLength: 1,
        width: 'resolve',
        ajax: {
            url: BASEURL + 'api/pmc-advsr/auto-suggest/',
            type: "GET",
            quietMillis: 100,
            data: function (query) {
                var term = "";
                if (query.term != undefined) {
                    term = query.term;
                }
                return {
                    searchTerm: term
                };
            },
            processResults: function (data, params) {
                return {
                    results: $.map(data, function (item) {
                        return {
                            'text': item.name,
                            'id': item.id
                        }
                    })
                };
            }
        }
    });
    $('input[name="startDate"]').daterangepicker({
            singleDatePicker: true,
            showDropdowns: true
        },
        function (start, end, label) {
            var years = moment().diff(start, 'years');
            alert("You are " + years + " years old.");
        });
    $('input[name="endDate"]').daterangepicker({
            singleDatePicker: true,
            showDropdowns: true
        },
        function (start, end, label) {
            var years = moment().diff(start, 'years');
            alert("You are " + years + " years old.");
        });
    // $('#startTime').timepicker();
    $(".choose-btn > .btn").click(function () {
        $(".choose-btn > .btn").removeClass("active");
        $(this).addClass("active");
    });
});