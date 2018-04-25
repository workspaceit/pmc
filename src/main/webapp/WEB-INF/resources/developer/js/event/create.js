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

    function save(action){
        UnBindErrors("errorObj_");
        console.log("submitting . . .");
        var eventName = $('#eventName').val();
        var startDateOnly = $('#startDate').val();
        var startTime = $('#startTime').val();
        var endDateOnly = $('#endDate').val();
        var endTime = $('#endTime').val();
        var isPrivate = $("input[name='private']:checked").val();
        var locationId = $("#locationIds").val();
        var venueId = $('#venueId').val();
        var photographerIds = $('#photographer-select2').val();
        var advertiserIds = $('#advertiser-select2').val();
        var watermarkIds = $('#watermark-select2').val();
        var startDate = moment(startDateOnly + " " + startTime, "MM/DD/YYYY HH:mm").format('YYYY-MM-DD HH:mm:ss');
        var endDate = moment(endDateOnly + " " + endTime, "MM/DD/YYYY HH:mm").format('YYYY-MM-DD HH:mm:ss');
        var isAllAdvertiserSelected = $("#allAdvertiserSelection").is(":checked");

        var data = {
            'eventName': eventName,
            'startDate': startDate,
            'endDate' : endDate,
            'locationId':locationId,
            'venueId': venueId,
            'photographerIds': photographerIds,
            'advertiserIds': advertiserIds,
            'watermarkIds': watermarkIds,
            'imageToken': pictureToken,
            'isPrivate': isPrivate,
            'isAllAdvertiserSelected':isAllAdvertiserSelected
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
                if(action === "save") {
                    window.location = BASEURL + "admin/event/update/" + data.id;
                }
                else if(action === "save-close") {
                    window.location = BASEURL + "admin/event/all";
                }
                else if(action === "save-new"){
                    window.location = BASEURL + "admin/event/add";
                }
            }
        });
    }
    $('.remove-current-image').click(function () {
        $(this).parent().remove();
        pictureToken = -2;
    });
    $('#save-watermark-btn').click(function () {
        save('save');
    });
    $('#save-close-watermark-btn').click(function () {
        save('save-close');
    });
    $('#save-new-watermark-btn').click(function () {
        save('save-new');
    });

    function update(action){
        UnBindErrors("errorObj_");
        console.log("submitting . . .");
        var eventName = $('#eventName').val();
        var startDateOnly = $('#startDate').val();
        var startTime = $('#startTime').val();
        var endDateOnly = $('#endDate').val();
        var endTime = $('#endTime').val();
        var isPrivate = $("input[name='private']:checked").val();
        var locationId = $("#locationIds").val();
        var venueId = $('#venueId').val();
        var photographerIds = $('#photographer-select2').val();
        var advertiserIds = $('#advertiser-select2').val();
        var watermarkIds = $('#watermark-select2').val();

        var startDate = moment(startDateOnly + " " + startTime, "MM/DD/YYYY HH:mm").format('YYYY-MM-DD HH:mm:ss');
        var endDate = moment(endDateOnly + " " + endTime, "MM/DD/YYYY HH:mm").format('YYYY-MM-DD HH:mm:ss');
        var isAllAdvertiserSelected = $("#allAdvertiserSelection").is(":checked")

        var data = {
            'eventName': eventName,
            'startDate': startDate,
            'endDate' : endDate,
            'locationId': locationId,
            'venueId': venueId,
            'photographerIds': photographerIds,
            'advertiserIds': advertiserIds,
            'watermarkIds': watermarkIds,
            'imageToken': pictureToken == 0 ? -1 :pictureToken,
            'isPrivate': isPrivate,
            'isAllAdvertiserSelected':isAllAdvertiserSelected
        };
        var eventId = $('#eventId').val();
        $.ajax({
            url: BASEURL+'api/event/update/' + eventId,
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
                if(action === "save-close") {
                    window.location = BASEURL + "admin/event/all";
                }
                else if(action === "save-new"){
                    window.location = BASEURL + "admin/event/add";
                }
            }
        });
    }

    $('#update-watermark-btn').click(function () {
        update('save');
    });
    $('#update-close-watermark-btn').click(function () {
        update('save-close');
    });
    $('#update-new-watermark-btn').click(function () {
        update('save-new');
    });

    function select2FormatState (state) {
        // console.log(state);
        if (!state.id) {
            return state.text;
        }
        var baseUrl = "/common";
        var $state = $(
            '<span><label class="option_text">' + state.text + '</label><img src="' + baseUrl + '/' + state.logo + '"  class="option_img" />' + '</span>'
        );
        return $state;
    };

    $("#watermark-select2").select2({
        placeholder: 'Select Watermark(s)',
        templateResult: select2FormatState,
        // multiple: true,
        // minimumInputLength: 1,
        allowClear: true,
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
                            'id': item.id,
                            'logo': item.logoImage
                        }
                    })
                };
            }
        }
    }).on('change', function (e) {
        var waterMarkLogo = $(this).select2('data')[0].logo;
        $('#watermark-img-preview').attr('src', '/common/' + waterMarkLogo).show();
    });
    var locationSelect2 = $("#locationIds").select2({
        placeholder: 'Select a Location',
//                    minimumInputLength: 1,
        width: 'resolve',
        ajax: {
            url: BASEURL + 'api/location/auto-suggest/',
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
    var $venueSelect2 = $("#venue").select2({
        placeholder: 'Select a Location',
                        minimumInputLength: 1,
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
    var $photographerSelect2 = $("#photographer-select2").select2({
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
    var startDate = ( $('#startDate').val().trim()=="" )?moment().format('MM/D/YYYY'):$('#startDate').val();
    var endDate = ( $('#endDate').val().trim()=="" )?moment().format('MM/D/YYYY'):$('#endDate').val();
    $('input[name="startDate"]').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true,
        locale: {
            format: 'MM/DD/YYYY'
        },
        startDate: startDate
    });
    $('input[name="endDate"]').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true,
        locale: {
            format: 'MM/DD/YYYY'
        },
        startDate: endDate
    });


    /** Location modal location submit function call back */

    overrideSubmitLocation(
        function (response) {

            var newOption = new Option(response.name, response.id, false, true);
            locationSelect2.append(newOption).trigger('change');
        }
    );
    // $('#startTime').timepicker();





    $(".choose-btn > .btn").click(function () {
        $(".choose-btn > .btn").removeClass("active");
        $(this).addClass("active");
    });



    $('#btn-add-venue').click(function () {
        submitData();
    });

    function submitData(){
        var name = $('#name').val();
        var location = $('#location').val();
        var data = {
            name: name,
            location_id: location
        };
        console.log(data);
        $.ajax({
            url: BASEURL+"api/venue/create",
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
            success: function(venue) {
                $('#add-new-venue-modal').modal('hide');
                var data = {
                    id: venue.id,
                    text: venue.name
                };
                var newOption = new Option(data.text, data.id, false, true);
                $venueSelect2.append(newOption).trigger('change');
            }
        });
    }

    $('#btn-photographer-add').click(function () {
        submitPhotographerData('save');
    });

    Dropzone.autoDiscover = false;
    var profilePictureToken = 0;
    var banerImagesToken = [];

    var profileImgDropzone;

    $('#add-photographer-modal').on('show.bs.modal', function(){
        initializePhotographerForm();
        if(profileImgDropzone){
            profileImgDropzone.destroy();
        }
    });

    $('#add-new-venue-modal').on('show.bs.modal', function(){
        initializeVenueForm();
    });

    $('#add-photographer-modal').on('shown.bs.modal', function(){
        profileImgDropzone = new Dropzone("div#profileImg",
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
                            removeFileByToken(file.token);
                            profilePictureToken = 0;
                            var _ref;
                            profileImgDropzone.removeFile(file);
                        });
                    });

                },
                error:function(file,response){
                    var msg = (typeof response == "object")?((response.length>0)?response[0].msg:""):response;
                    $("#profileImg").find(".dz-error-message span").html(msg);
                },
                success:function(file,response){
                    file.token = response.token;
                    profilePictureToken = response.token;
                    console.log(file);
                }
            }
        );
    });

    function submitPhotographerData(btnAction){
        var fullName = $("#fullName").val();
        var phoneNumber = $("#phoneNumber").val();
        var email = $("#email").val();
        var userName = $("#userName").val();
        var password =  $("#password").val();
        var confirmPassword = 	$("#confirmPassword").val();
        var data = {
            "fullName":fullName,
            "phoneNumber":phoneNumber,
            "email":email,
            "userName":userName,
            "password":password,
            "confirmPassword":confirmPassword,
            "profilePictureToken":profilePictureToken
        };
        $.ajax({
            url: BASEURL+'api/photographer/create',
            data:data,
            type: 'POST',
            statusCode: {
                401: function (response) {
                    console.log(response);
                },
                422: function (response) {
                    BindErrorsWithHtml("errorObj_",response.responseJSON);
                    console.log(response);
                }
            },success: function(photographer){
                console.log(photographer.id + ": " + photographer.fullName);
                $('#add-photographer-modal').modal('hide');
                // var newOption = new Option(photographer.text, photographer.id, false, false);
                if ($("#photographer-select2").find("option[value=" + photographer.id + "]").length) {
                    console.log("if");
                    $("#photographer-select2").val(photographer.id).trigger("change");
                } else {
                    console.log("else");
                    // Create the DOM option that is pre-selected by default
                    var newState = new Option(photographer.fullName, photographer.id, true, true);
                    // Append it to the select
                    $("#photographer-select2").append(newState).trigger('change');
                }

            }
        });
        console.log(data)
    }

    function initializePhotographerForm(){
        $('#add-photographer-modal input').val("");
    }

    function initializeVenueForm(){
        $('#add-new-venue-modal #name').val("");
    }

});