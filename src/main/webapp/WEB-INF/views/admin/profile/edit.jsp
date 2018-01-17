<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:genericpage>
    <jsp:body>
        <div id="page-wrapper" style="min-height: 563px !important;">
            <div class="container">
                <h3 class="uni-header"><span>Personal Details</span></h3>
                <div class="col-md-12">
                    <div class="row">
                        <div class="btn-container-top">
                            <button class="btn btn-action-top">Save</button>
                            <button class="btn btn-action-top">Save&nbsp;&&nbsp;Close</button>
                            <button class="btn btn-action-top">Save&nbsp;&&nbsp;New</button>
                            <button class="btn btn-action-top">Cancel</button>
                        </div>
                        <div class="form-group">
                            <label>Full Name</label>
                            <input  class="form-control" value="${user.name}">
                        </div>
                        <div class="form-group">
                            <label>Phone Number</label>
                            <input class="form-control" type="Number" value="${user.phoneNumber}" >
                        </div>
                        <div class="form-group">
                            <label>User Name</label>
                            <input disabled="disabled" class="form-control" value="${user.userName}" >
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <input disabled="disabled" class="form-control" value="${user.email}" >
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <input class="form-control" type="password" placeholder="******">
                        </div>
                        <div class="form-group">
                            <label>Confirm Password</label>
                            <input class="form-control" type="password" placeholder="******">
                        </div>
                        <div class="imageupload panel panel-default">
                            <div class="panel-heading clearfix">
                                <h4 class="panel-title pull-left">Profile Image</h4>
                                <div class="btn-group pull-right">
                                </div>
                            </div>
                            <div id="profileImage"  class="panel-body" >
                                <div class="dz-default dz-message">
                                    <span>Click here to upload</span>
                                    <p id="errorObj_"></p>
                                </div>
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
    </jsp:body>

</t:genericpage>