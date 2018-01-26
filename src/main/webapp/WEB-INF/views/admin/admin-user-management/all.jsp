<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:genericpage>
    <jsp:body>
        <div id="page-wrapper">
            <div class="container">
                <h1>Admin List </h1>
                <%--<hr>--%>
                <div class="cs_action_btn">
                    <input type="hidden" id="type" value="user">
                    <a href="<c:url value="/admin/user/add"/>" class="ac_btn new"><i class="fa fa-plus"></i>NEW</a>
                    <button id="edit-selected-btn" disabled="disabled" class="ac_btn"><i class="fa fa-pencil"></i>EDIT</button>
                    <button id="activate-selected-btn" class="ac_btn"><i class="fa fa-check"></i>ENABLE</button>
                    <button id="deactivate-selected-btn" class="ac_btn"><i class="fa fa-check"></i>DISABLE</button>
                    <button class="ac_btn"><i class="fa fa-trash"></i>DELETE</button>
                </div>
                <div class="table-responsive dtble">
                    <table id="admin-datatable" class="table table-bordered table-hover table-responsive cstm-admin-table">
                        <thead>
                        <tr>
                            <th class="cstm-table-header">
                                <input type="checkbox" id="select-all-checkbox">
                            </th>
                            <th class="cstm-table-header">
                                Image
                            </th>
                            <th class="cstm-table-header">Username</th>
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
                                Enabled
                            </th>
                            <th class="cstm-table-header">
                                Action
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <d:forEach var="admin" items="${admins}" >
                        <tr>
                            <td class="des-clm">
                                <input type="checkbox" class="select-checkbox" value="${admin.id}">
                            </td>
                            <td class="img-clm text-center">
                                <c:set value="" var="imgSrc" />
                                <c:choose>
                                    <c:when test="${admin.image==null || admin.image.trim().equals('')}">
                                        <c:set value="/resources/images/default_profile_pic.png" var="imgSrc" />
                                    </c:when>
                                    <c:otherwise>
                                        <c:set value="/common/${admin.image}" var="imgSrc" />
                                    </c:otherwise>
                                </c:choose>
                                <img onerror="this.src='<c:url value="/resources/images/default_alternate.png" />'" src="<c:url value="${imgSrc}"/>"class="img-circle" width="70">
                            </td>

                            <td class="date-clm">
                                <span class="cstm-date-txt">${admin.userName}</span>
                            </td>
                            <td class="date-clm">
                                <span class="cstm-date-txt">${admin.email}</span>
                            </td>
                            <td class="des-clm">
                                <p class="text-left" id="title-${admin.id}">${admin.name}</p>
                            </td>
                            <td class="date-clm">
                                <span class="cstm-date-txt">${admin.phoneNumber}</span>
                            </td>
                            <td class="date-clm">
                                <c:if test = "${admin.active}">
                                    <input type="checkbox" class="activate-checkbox" checked value="${admin.id}">
                                </c:if>
                                <c:if test = "${!admin.active}">
                                    <input type="checkbox" class="activate-checkbox" value="${admin.id}">
                                </c:if>
                            </td>
                            <td class="action-clm text-center">
                                <a href="<c:url value="/admin/user/update/${admin.id}" />" class="btn btn-success">
                                    <i class="fa fa-pencil"></i>
                                </a>
                                <a href="#" class="btn btn-danger"><i class="fa fa-trash"></i></a>
                            </td>
                        </tr>
                        </d:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <script>
            $(document).ready(function() {
                $('#admin-datatable').DataTable();
//                $('#admin-datatable').DataTable( {
//                    "processing": true,
//                    "columns": [
//                        { "data": "first_name" },
//                        { "data": "Username" },
//                        { "data": "position" },
//                        { "data": "office" },
//                        { "data": "start_date" },
//                        { "data": "salary" }
//                    ],
//                    "columnDefs": [ {
//                        "targets": [0,5],
//                        "orderable": false,
//                    } ],
//                    "serverSide": true,
//                    "ajax": {
//                        "url": BASEURL + 'admin/admin-datatable-data',
//                        "type":"POST",
////                        "traditional": true
//                    }
//                } );
            } );
        </script>
        <script src="<s:url value="/resources/developer/js/helper/list.helper.common.js"/>"></script>
    </jsp:body>
</t:genericpage>