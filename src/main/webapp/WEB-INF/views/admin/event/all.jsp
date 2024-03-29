<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<t:genericpage>
    <jsp:body>
        <div id="page-wrapper">
            <div class="container">
                <h1>Event List </h1>
                <%--<hr>--%>
                <div class="cs_action_btn">
                    <input type="hidden" id="type" value="event">
                    <a href="<c:url value="/admin/event/add"/>" class="ac_btn new"><i class="fa fa-plus"></i>NEW</a>
                    <button id="edit-selected-btn" disabled="disabled" class="ac_btn"><i class="fa fa-pencil"></i>EDIT</button>
                    <button id="activate-selected-btn" disabled="disabled" class="ac_btn"><i class="fa fa-check"></i>ENABLE</button>
                    <button id="deactivate-selected-btn" disabled="disabled" class="ac_btn"><i class="fa fa-check"></i>DISABLE</button>
                    <button id="delete-selected-btn" disabled="disabled" class="ac_btn"><i class="fa fa-trash"></i>DELETE</button>
                </div>
                <div class="table-responsive dtble">
                    <table id="event-datatable" class=" table table-bordered table-hover table-responsive cstm-admin-table">
                        <thead>
                            <tr>
                                <th class="cstm-table-header" style="text-align: center;">
                                    <input type="checkbox" id="select-all-checkbox">
                                </th>
                                <th class="cstm-table-header">
                                    Event Name
                                </th>
                                <th class="cstm-table-header">
                                    Starts At
                                </th>
                                <th class="cstm-table-header">
                                    Ends At
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
                        <c:forEach var="event" items="${events}" >
                            <tr>
                                <td class="des-clm" style="text-align: center;">
                                    <input type="checkbox" class="select-checkbox" value="${event.id}">
                                </td>
                                <td class="des-clm">
                                    <p class="text-left" id="title-${event.id}">${event.name}</p>
                                </td>
                                <td class="date-clm">
                                    <span class="cstm-date-txt">
                                        <fmt:formatDate value="${event.startsAt}" pattern="yyyy-MM-DD hh:mm a"/>
                                    </span>
                                </td>
                                <td class="date-clm">
                                    <fmt:formatDate value="${event.endsAt}" pattern="yyyy-MM-DD hh:mm a"/>
                                </td>
                                <td class="date-clm">
                                    <fmt:formatDate value="${event.createdAt}" pattern="yyyy-MM-dd hh:mm a"/>
                                </td>
                                <td class="date-clm">
                                    <c:if test = "${event.active}">
                                        <input type="checkbox" class="activate-checkbox" checked value="${event.id}">
                                    </c:if>
                                    <c:if test = "${!event.active}">
                                        <input type="checkbox" class="activate-checkbox" value="${event.id}">
                                    </c:if>
                                </td>
                                <td class="action-clm text-center">
                                    <a href="<c:url value="/admin/event/${event.id}/gallery" />" class="btn btn-info"
                                       data-toggle="tooltip" title="Event Photos">
                                        <i class="fa fa-image"></i>
                                    </a>
                                    <a href="<c:url value="/admin/event/update/${event.id}" />" class="btn btn-success"
                                       data-toggle="tooltip" title="Edit">
                                        <i class="fa fa-pencil"></i>
                                    </a>
                                    <a href="javascript:void(0)" disabled="disabled" onclick="deleteEntity(${event.id},false)"
                                       data-toggle="tooltip" title="Delete" class="btn btn-danger">
                                        <i class="fa fa-trash"></i>
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
                $('#event-datatable').DataTable({
                    "columnDefs": [
                        {
                            "targets": [0,5,6],
                            "orderable": false
                        },
                        {
                            "targets": [4],
                            "type": "date",
                            "render": function (d) {
                                return moment(d).format('YYYY-MM-DD hh:mm a');
                            }
                        }
                    ],
                    "order": [[4, 'desc']]
                });
            });
        </script>
        <script src="<s:url value="/resources/developer/js/helper/list.helper.common.js"/>"></script>
    </jsp:body>
</t:genericpage>