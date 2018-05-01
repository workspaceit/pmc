<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:genericpage>
    <jsp:body>
        <div id="page-wrapper">
            <div class="container">
                <h1>Venue List </h1>
                <%--<hr>--%>
                <div class="cs_action_btn">
                    <input type="hidden" id="type" value="venue">
                    <a href="<c:url value="/admin/venue/add"/>" class="ac_btn new"><i class="fa fa-plus"></i>NEW</a>
                    <button id="edit-selected-btn" disabled="disabled" class="ac_btn"><i class="fa fa-pencil"></i>EDIT</button>
                    <button id="activate-selected-btn" class="ac_btn"><i class="fa fa-check"></i>ENABLE</button>
                    <button id="deactivate-selected-btn" class="ac_btn"><i class="fa fa-check"></i>DISABLE</button>
                    <button id="delete-selected-btn" class="ac_btn"><i class="fa fa-trash"></i>DELETE</button>
                </div>
                <div class="table-responsive dtble">
                    <table id="venue-datatable" class="table table-bordered table-hover table-responsive cstm-admin-table">
                        <thead>
                        <tr>
                            <th class="cstm-table-header">
                                <input type="checkbox" id="select-all-checkbox">
                            </th>
                            <th class="cstm-table-header">
                                Venue
                            </th>
                            <th class="cstm-table-header">
                                Location
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
                        <d:forEach var="venue" items="${venues}" >
                            <td class="des-clm" style="text-align: center;">
                                <input type="checkbox" class="select-checkbox" value="${venue.id}">
                            </td>
                            <td class="des-clm">
                                <p id="title-${venue.id}" class="text-left">${venue.name}</p>
                            </td>
                            <td class="date-clm">
                                <span class="cstm-date-txt">${venue.location.name}</span>
                            </td>
                            <td class="date-clm">
                                <c:if test = "${venue.active}">
                                    <input type="checkbox" class="activate-checkbox" checked value="${venue.id}">
                                </c:if>
                                <c:if test = "${!venue.active}">
                                    <input type="checkbox" class="activate-checkbox" value="${venue.id}">
                                </c:if>
                            </td>
                            <td class="action-clm text-center">
                                <a href="<c:url value="/admin/venue/update/${venue.id}" />" class="btn btn-success"><i class="fa fa-pencil"></i></a>
                                <a href="javascript:void(0)" onclick="deleteEntity(${venue.id},false)" class="btn btn-danger"><i class="fa fa-trash"></i></a>
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
                $('#venue-datatable').DataTable({
                    "columnDefs": [{
                        "targets": [0, 3, 4],
                        "orderable": false,
                    }],
                    "order": [[1, 'asc']]
                });
            });
        </script>
        <script src="<s:url value="/resources/developer/js/helper/list.helper.common.js"/>"></script>
    </jsp:body>

</t:genericpage>