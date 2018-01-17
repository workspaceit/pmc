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
        var startDate = $('#startDate').val();
        var startTime = $('#startTime').val();
        var endDate = $('#endDate').val();
        var endTime = $('#endTime').val();
        var venueId = $('#venue-select2').val();
        var photographerIds = $('#photographer-select2').val();
        var advertiserIds = $('#advertiser-select2').val();
        var watermarkIds = $('#watermark-select2').val();
        var data = {
            'eventName': eventName,
            'startDate': startDate,
            'startTime': startTime,
            'endDate' : endDate,
            'endTime' : endTime,
            'venueId': venueId,
            'photographerIds': photographerIds,
            'advertiserIds': advertiserIds,
            'watermarkIds': watermarkIds,
            'imageToken': pictureToken
        };

        $.ajax({
            url: BASEURL+'api/event/create',
            data:data,
            type: 'POST',
            statusCode: {
                401: function (response) {
                    console.log(response);
                },
                422: function (response) {

                    BindErrorsWithHtml("errorObj_",response.responseJSON);
                    console.log(response);
                    btnAction
                }
            },success: function(data){
                UnBindErrors("errorObj_");
                photographerAfterSaveActionCreate(btnAction);
            }
        });
        console.log(data)
    });

    $("#event-form").submit(function (e) {
        var url = "path/to/your/script.php";
        $.ajax({
            type: "POST",
            url: url,
            data: $("#event-form").serialize(),
            success: function (data) {
                alert(data);
            }
        });
        e.preventDefault();
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
    $("#venue-select2").select2({
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
    $('input[name="startdate"]').daterangepicker({
            singleDatePicker: true,
            showDropdowns: true
        },
        function (start, end, label) {
            var years = moment().diff(start, 'years');
            alert("You are " + years + " years old.");
        });
    $('input[name="enddate"]').daterangepicker({
            singleDatePicker: true,
            showDropdowns: true
        },
        function (start, end, label) {
            var years = moment().diff(start, 'years');
            alert("You are " + years + " years old.");
        });
    $('#start-time').timepicker();
    $(".choose-btn > .btn").click(function () {
        $(".choose-btn > .btn").removeClass("active");
        $(this).addClass("active");
    });
});