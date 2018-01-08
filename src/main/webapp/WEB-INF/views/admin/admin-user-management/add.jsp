<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<t:genericpage>

    <jsp:body>
        <!-- dropzone css -->
        <link rel="stylesheet" type="text/css" href="https://rawgit.com/enyo/dropzone/master/dist/min/dropzone.min.css">
        <!-- /#page-wrapper -->
        <div id="page-wrapper" style="min-height: 563px !important;">
            <div class="container">
                <h3 class="uni-header"><span>Personal Details</span></h3>
                <div class="col-md-12">
                    <div class="row">
                        <div class="btn-container-top">
                            <button class="btn btn-action-top"  onclick="submitAdminUserData()">Save</button>
                            <button class="btn btn-action-top">Save&nbsp;&&nbsp;Close</button>
                            <button class="btn btn-action-top">Save&nbsp;&&nbsp;New</button>
                            <button class="btn btn-action-top">Cancel</button>
                        </div>
                        <div class="form-group">
                            <label>Full Name</label>
                            <input id="fullName" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Phone Number</label>
                            <input id="phoneNumber" class="form-control" type="Number">
                        </div>
                        <div class="form-group">
                            <label>User Name</label>
                            <input id="userName" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <input id="email" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <input id="password" class="form-control" type="password" placeholder="******">
                        </div>
                        <div class="form-group">
                            <label>Confirm Password</label>
                            <input id="confirmPassword" class="form-control" type="password" placeholder="******">
                        </div>
                        <div class="imageupload panel panel-default">
                            <div class="panel-heading clearfix">
                                <h4 class="panel-title pull-left">Profile Image</h4>
                                <div class="btn-group pull-right">
                                </div>
                            </div>
                            <div class="file-tab panel-body">
                                <form action="/upload-target" class="dropzone"></form>
                            </div>
                        </div>
                        <!-- <div class="form-group">
                        <label>Require Password reset</label>
                        <div class="btn-group choose-btn">
                            <button type="button" class="active btn btn-default" id="regi1">Yes</button>
                            <button type="button" class="btn btn-default" id="regi3">No</button>
                        </div>
                        </div> -->

                    </div>

                </div>
            </div>





        </div>

        <%--Developer Hidden Field--%>
        <%--<input type="hidden" id="venueLogoToken" value="" />
        <input type="hidden" id="venueBgImgTokens" value="" />--%>


        <!-- jQuery -->
        <script src="<s:url value="/resources/js/jquery.js"/>"></script>


        <!-- Bootstrap Core JavaScript -->
        <script src="<s:url value="/resources/js/bootstrap.min.js"/>"></script>
        <script src="<s:url value="/resources/js/jscolor.js"/>"></script>

        <!-- select2 js -->
        <script src="<s:url value="/resources/js/select2.js"/>"></script>
        <script type="text/javascript" src="http://cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.js"></script>
        <link rel="stylesheet" type="text/css" href="http://cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.css" />

        <!-- image uploader script -->
        <script src="<s:url value="/resources/js/bootstrap-imageupload.js"/>"></script>
        <!-- image uploader script -->
        <!-- dropzone -->
        <script src="https://rawgit.com/enyo/dropzone/master/dist/min/dropzone.min.js"></script>
        <!-- dropzone -->

        <script src="<s:url value="/resources/developer/js/admin-user/common.js"/>"></script>
        <script src="<s:url value="/resources/developer/js/admin-user/create.js"/>"></script>








        <!-- Morris Charts JavaScript -->
        <!--     <script src="js/plugins/morris/raphael.min.js"></script>
        <script src="js/plugins/morris/morris.min.js"></script>
        <script src="js/plugins/morris/morris-data.js"></script> -->





    </jsp:body>
</t:genericpage>