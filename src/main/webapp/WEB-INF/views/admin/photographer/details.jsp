<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:genericpage>
    <jsp:body>
        <div id="page-wrapper" style="min-height: 563px !important;">
            <div class="container">
                <h3 class="uni-header">
                    <span>Photographer Details
                        <a href="<c:url value="/admin/photographer/update/${photographer.id}" />" class="pull-right">
                          <button type="button" class="close" style="font-size: 42px">
                          <i class="fa fa-pencil-square" aria-hidden="true" style="font-size: 40px;color: #2d2356;"></i>
                        </button>
                        </a>
                    </span>
                </h3>
                <div class="col-md-12">
                    <div class="row clearfix">
                        <div class="col-md-5">
                            <c:set value="" var="imgSrc" />
                            <c:choose>
                                <c:when test="${photographer.profilePhoto==null || photographer.profilePhoto.trim().equals('')}">
                                    <c:set value="/resources/images/default_profile_pic.png" var="imgSrc" />
                                </c:when>
                                <c:otherwise>
                                    <c:set value="/photographer-profile-img/${photographer.profilePhoto}" var="imgSrc" />
                                </c:otherwise>
                            </c:choose>
                            <img onerror="this.src='<c:url value="/resources/images/default_alternate.png" />'"
                                 src="<c:url value="${imgSrc}" /> " class="img-responsive">
                        </div>
                        <div class="col-md-7">
                            <label class="label-pd">Full Name</label>
                            <h3 class="margin-pd-01">${photographer.fullName}</h3>

                            <label class="label-pd">Phone Number</label>
                            <h3 class="margin-pd-01">${photographer.phoneNumber}</h3>

                            <label class="label-pd">User Name</label>
                            <h3 class="margin-pd-01">${photographer.userName}</h3>

                            <label class="label-pd">Email</label>
                            <h3 class="margin-pd-01">${photographer.email}</h3>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:genericpage>