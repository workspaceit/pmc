<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<t:genericpage>
    <jsp:body>
        <div id="page-wrapper">
            <div class="container">
                <h1>Photographers for ${event.name}</h1>
                <%--<hr>--%>
                <div class="table-responsive dtble">
                    <table id="photographer-datatable" class="table table-bordered table-hover table-responsive cstm-admin-table">
                        <thead>
                        <tr>
                            <th class="cstm-table-header">
                                Image
                            </th>

                            <th class="cstm-table-header">
                                Username
                            </th>
                            <th class="cstm-table-header">
                                Email
                            </th>
                            <th class="cstm-table-header">
                                Full Name
                            </th>
                            <th class="cstm-table-header">
                                Phone
                            </th>
                            <th class="cstm-table-header">
                                Created At
                            </th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="photographer" items="${photographers}" >
                            <tr>
                                <td class="img-clm text-center">
                                    <c:set value="" var="imgSrc" />
                                    <c:choose>
                                        <c:when test="${photographer.profilePhoto==null || photographer.profilePhoto.trim().equals('')}">
                                            <c:set value="/resources/images/default_profile_pic.png" var="imgSrc" />
                                        </c:when>
                                        <c:otherwise>
                                            <c:set value="/photographer-profile-img/${photographer.profilePhoto}" var="imgSrc" />
                                        </c:otherwise>
                                    </c:choose>
                                    <img onerror="this.src='<c:url value="/resources/images/default_alternate.png" />'" src="<c:url value="${imgSrc}" /> " class="img-circle" width="70">
                                </td>
                                <td class="date-clm">
                                    <span class="cstm-date-txt">${photographer.userName}</span>
                                </td>
                                <td class="date-clm">
                                    <span class="cstm-date-txt">${photographer.email}</span>
                                </td>
                                <td class="des-clm">
                                    <p id="title-${photographer.id}" class="text-left">${photographer.fullName}</p>
                                </td>
                                <td class="date-clm">
                                    <span class="cstm-date-txt">${photographer.phoneNumber}</span>
                                </td>
                                <td class="date-clm">
                                    <span class="cstm-date-txt">
                                        <fmt:formatDate pattern = "yyyy-MM-dd hh:mm a" value="${photographer.createdAt}"></fmt:formatDate>
                                    </span>
                                </td>
                                <td>
                                    <a href="<c:url value="/admin/photographer/details/${photographer.id}"/>" class="btn btn-info">
                                        <i class="fa fa-user" aria-hidden="true"></i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <%@include file="../others/delete-modal.jsp"%>
        <script>
            $(document).ready(function() {
                $('#photographer-datatable').DataTable({
                    "columnDefs": [{
                        "targets": [0, 1, 7, 8],
                        "orderable": false,
                    }],
                    "order": [[6, 'desc']]
                });
            });
        </script>
        <script src="<s:url value="/resources/developer/js/helper/list.helper.common.js"/>"></script>
    </jsp:body>
</t:genericpage>