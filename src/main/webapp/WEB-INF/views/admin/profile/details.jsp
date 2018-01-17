<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:genericpage>
    <jsp:body>
        <div id="page-wrapper" style="min-height: 563px !important;">
            <div class="container">
                <h3 class="uni-header"><span>Personal Details
                <a href="<c:url value="/admin/user/profile/edit" />" class="pull-right">
                  <button type="button" class="close" style="font-size: 42px">
                  <i class="fa fa-pencil-square" aria-hidden="true" style="font-size: 40px;color: #2d2356;"></i>
                </button>
                </a>
                </h3>
                <div class="col-md-12">
                    <div class="row clearfix">
                        <div class="btn-container-top">
                            <button class="btn btn-action-top">Save</button>
                            <button class="btn btn-action-top">Save&nbsp;&&nbsp;Close</button>
                            <button class="btn btn-action-top">Save&nbsp;&&nbsp;New</button>
                            <button class="btn btn-action-top">Cancel</button>
                        </div>
                        <div class="col-md-5">
                            <c:set value="" var="imgSrc" />
                            <c:choose>
                                <c:when test="${user.image==null || user.image.trim().equals('')}">
                                    <c:set value="/resources/images/default_profile_pic.png" var="imgSrc" />
                                </c:when>
                                <c:otherwise>
                                    <c:set value="/common/${user.image}" var="imgSrc" />
                                </c:otherwise>
                            </c:choose>
                            <img onerror="this.src='<c:url value="/resources/images/default_alternate.png" />'" src="<c:url value="${imgSrc}" /> " class="img-responsive">

                        </div>
                        <div class="col-md-7">
                            <label class="label-pd">Full Name</label>
                            <h3 class="margin-pd-01">${user.name}</h3>

                            <label class="label-pd">Phone Number</label>
                            <h3 class="margin-pd-01">${user.phoneNumber}</h3>

                            <label class="label-pd">User Name</label>
                            <h3 class="margin-pd-01">${user.userName}</h3>

                            <label class="label-pd">Email</label>
                            <h3 class="margin-pd-01">${user.email}</h3>
                        </div>



                    </div>

                </div>
            </div>





        </div>
    </jsp:body>

</t:genericpage>