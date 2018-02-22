<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.workspaceit.pmc.constant.watermark.Size" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<t:genericpage>
    <jsp:body>
        <div id="page-wrapper">
            <div class="container">
                <h1>Watermark List </h1>
                <%--<hr>--%>
                <div class="cs_action_btn">
                    <input type="hidden" id="type" value="watermark">
                    <a href="<c:url value="/admin/watermark/add"/>" class="ac_btn new"><i class="fa fa-plus"></i>NEW</a>
                    <button id="edit-selected-btn" disabled="disabled" class="ac_btn"><i class="fa fa-pencil"></i>EDIT</button>
                    <button id="activate-selected-btn" class="ac_btn"><i class="fa fa-check"></i>ENABLE</button>
                    <button id="deactivate-selected-btn" class="ac_btn"><i class="fa fa-check"></i>DISABLE</button>
                    <button id="delete-selected-btn" class="ac_btn"><i class="fa fa-trash"></i>DELETE</button>
                </div>
                <div class="table-responsive dtble">
                    <table id="watermark-datatable" class="table table-bordered table-hover table-responsive cstm-admin-table">
                        <thead>
                        <tr>
                            <th class="cstm-table-header">
                                <input type="checkbox" id="select-all-checkbox">
                            </th>
                            <th class="cstm-table-header">
                                Date
                            </th>
                            <th class="cstm-table-header">
                                Title
                            </th>
                            <th class="cstm-table-header">
                                Type
                            </th>
                            <th class="cstm-table-header">
                                Preview
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
                        <d:forEach var="wm" items="${watermarkList}" >
                        <tr>
                            <td class="des-clm">
                                <input type="checkbox" class="select-checkbox" value="${wm.id}">
                            </td>
                            <td class="des-clm">
                                <p class="text-left"><fmt:formatDate pattern='yyyy-MM-dd hh:mm a' value="${wm.createdAt}"/></p>
                            </td>
                            <td class="des-clm">
                                <p id="title-${wm.id}" class="text-left">${wm.name}</p>
                            </td>
                            <td class="des-clm">
                                <p class="text-left">${wm.type}</p>
                            </td>
                            <td class="date-clm">
                                <img src="<c:url value="/img/watermarked-preview/${wm.id}/${Size.thumb.name()}" />"
                                     alt="No preview found">
                            </td>
                            <td class="date-clm">
                                <c:if test = "${wm.active}">
                                    <input type="checkbox" class="activate-checkbox" checked value="${wm.id}">
                                </c:if>
                                <c:if test = "${!wm.active}">
                                    <input type="checkbox" class="activate-checkbox" value="${wm.id}">
                                </c:if>
                            </td>
                            <td class="action-clm text-center">
                                <a href="<c:url value="/admin/watermark/edit/${wm.id}" />" class="btn btn-success"><i class="fa fa-pencil"></i></a>
                                <a href="javascript:void(0)" onclick="deleteEntity(${wm.id},false)" class="btn btn-danger"><i class="fa fa-trash"></i></a>
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
                $('#watermark-datatable').DataTable({
                    "columnDefs": [{
                        "targets": [0, 4, 5, 6],
                        "orderable": false,
                    }],
                    "order": [[2, 'asc']]
                });
            });
        </script>
        <script src="<s:url value="/resources/developer/js/helper/list.helper.common.js"/>"></script>
    </jsp:body>
</t:genericpage>