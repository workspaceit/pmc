<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:genericpage>
    <jsp:body>
        <div id="page-wrapper">
            <div class="container">
                <h1>Photographer List</h1>
                <%--<hr>--%>
                <div class="cs_action_btn">
                    <input type="hidden" id="type" value="photographer">
                    <a href="<c:url value="/admin/photographer/add"/>" class="ac_btn new"><i class="fa fa-plus"></i>NEW</a>
                    <button id="edit-selected-btn" disabled="disabled" class="ac_btn"><i class="fa fa-pencil"></i>EDIT</button>
                    <button id="activate-selected-btn" class="ac_btn"><i class="fa fa-check"></i>ENABLE</button>
                    <button id="deactivate-selected-btn" class="ac_btn"><i class="fa fa-check"></i>DISABLE</button>
                    <button id="delete-selected-btn" class="ac_btn"><i class="fa fa-trash"></i>DELETE</button>
                </div>
                <div class="table-responsive dtble">
                    <table id="photographer-datatable" class="table table-bordered table-hover table-responsive cstm-admin-table">
                        <thead>
                        <tr>
                            <th class="cstm-table-header">
                                <input type="checkbox" id="select-all-checkbox">
                            </th>
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
                            <th class="cstm-table-header">
                                Enabled
                            </th>
                            <th class="cstm-table-header">
                                Action
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <d:forEach var="photographer" items="${photographers}" >
                        <tr>
                            <td class="des-clm">
                                <input type="checkbox" class="select-checkbox" value="${photographer.id}">
                            </td>
                            <td class="img-clm text-center">
                                <a href="<c:url value="/admin/photographer/details/${photographer.id}"/>">
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
                                </a>
                            </td>
                            <td class="date-clm">
                                <span class="cstm-date-txt"><a href="<c:url value="/admin/photographer/details/${photographer.id}"/>">${photographer.userName}</a></span>
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
                                <fmt:formatDate pattern = "yyyy-MM-dd hh:mm a" value="${photographer.createdAt}"></fmt:formatDate>
                            </td>
                            <td class="date-clm">
                                <c:if test = "${photographer.active}">
                                    <input type="checkbox" class="activate-checkbox" checked value="${photographer.id}">
                                </c:if>
                                <c:if test = "${!photographer.active}">
                                    <input type="checkbox" class="activate-checkbox" value="${photographer.id}">
                                </c:if>
                            </td>
                            <td width="15%" class="action-clm text-center">
                                <a href="<c:url value="/admin/photographer/details/${photographer.id}" />"
                                   data-toggle="tooltip" title="Details"class="btn btn-info"><i class="fa fa-user"
                                                                                                aria-hidden="true"></i>
                                </a>
                                <a href="<c:url value="/admin/photographer/update/${photographer.id}" />"
                                   data-toggle="tooltip" title="Edit" class="btn btn-success"><i class="fa fa-pencil"></i>
                                </a>
                                <a href="javascript:void(0)" onclick="deleteEntity(${photographer.id},false)"
                                   data-toggle="tooltip" title="Delete" class="btn btn-danger"><i  class="fa fa-trash"></i>
                                </a>
                            </td>
                        </tr>
                        </d:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <%@include file="../others/delete-modal.jsp"%>
        <script>
            $(document).ready(function() {
                $('#photographer-datatable').DataTable({
                    "columnDefs": [
                        {
                            "targets": [0, 1, 7, 8],
                            "orderable": false,
                        },
                        {
                            "targets": [6],
                            "type": "date",
                            "render": function (d) {
                                return moment(d).format('YYYY-MM-DD hh:mm a');
                            }
                        }
                    ],
                    "order": [[6, 'desc']]
                });
            });
        </script>
        <script src="<s:url value="/resources/developer/js/helper/list.helper.common.js"/>"></script>
        
    </jsp:body>

</t:genericpage>