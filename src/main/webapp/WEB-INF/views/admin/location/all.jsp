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
                <h1>Location List </h1>
                <%--<hr>--%>
                <div class="cs_action_btn">
                    <input type="hidden" id="type" value="location">
                    <a href="<c:url value="/admin/location/add"/>" class="ac_btn new"><i class="fa fa-plus"></i>NEW</a>
                    <button id="edit-selected-btn" disabled="disabled" class="ac_btn"><i class="fa fa-pencil"></i>EDIT</button>
                    <button id="activate-selected-btn" disabled="disabled" class="ac_btn"><i class="fa fa-check"></i>ENABLE</button>
                    <button id="deactivate-selected-btn" disabled="disabled"  class="ac_btn"><i class="fa fa-check"></i>DISABLE</button>
                    <button id="delete-selected-btn" disabled="disabled" class="ac_btn"><i class="fa fa-trash"></i>DELETE</button>
                </div>
                <div class="table-responsive dtble">
                    <table id="location-datatable" class="table table-bordered table-hover table-responsive cstm-admin-table">
                        <thead>
                        <tr>
                            <th class="cstm-table-header">
                                <input type="checkbox" id="select-all-checkbox">
                            </th>
                            <th class="cstm-table-header">
                                Logo
                            </th>
                            <th class="cstm-table-header">
                                Location Name
                            </th>
                            <th class="cstm-table-header">
                                Address
                            </th>
                            <th class="cstm-table-header">
                                Phone
                            </th>
                            <th class="cstm-table-header">
                                State
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
                        <d:forEach var="location" items="${locations}" >
                            <tr>
                                <td class="des-clm" style="text-align: center;">
                                    <input type="checkbox" class="select-checkbox" value="${location.id}">
                                </td>

                                <td class="img-clm text-center">
                                    <a onclick="window.open('${frontEndAppBaseUrl}/user-panel/slideshow?locId=${location.id}','_blank')"
                                        style="cursor: pointer;">
                                        <c:set value="" var="imgSrc" />
                                        <c:choose>
                                            <c:when test="${location.locationLogo==null || location.locationLogo.trim().equals('')}">
                                                <c:set value="/resources/images/default_profile_pic.png" var="imgSrc" />
                                            </c:when>
                                            <c:otherwise>
                                                <c:set value="/common/${location.locationLogo}" var="imgSrc" />
                                            </c:otherwise>
                                        </c:choose>
                                        <img onerror="this.src='<c:url value="/resources/images/default_alternate.png" />'" src="<c:url value="${imgSrc}" /> " class="img-circle" width="70">
                                    </a>
                                </td>
                                <td class="des-clm">
                                    <p id="title-${location.id}" class="text-left">${location.name}</p>
                                </td>
                                <td class="date-clm">
                                    <span class="cstm-date-txt">${location.address}</span>
                                </td>
                                <td class="date-clm">
                                    <span class="cstm-date-txt">${location.phone}</span>
                                </td>
                                <td class="date-clm">
                                    <span class="cstm-date-txt">${location.state.name}</span>
                                </td>
                                <td class="date-clm">
                                        <fmt:formatDate pattern = "yyyy-MM-dd hh:mm a" value="${location.createdAt}"></fmt:formatDate>
                                </td>
                                <td class="date-clm">
                                    <c:if test = "${location.active}">
                                        <input type="checkbox" class="activate-checkbox" checked value="${location.id}">
                                    </c:if>
                                    <c:if test = "${!location.active}">
                                        <input type="checkbox" class="activate-checkbox" value="${location.id}">
                                    </c:if>
                                </td>
                                <td class="action-clm text-center">
                                    <a href="<c:url value="/admin/location/update/${location.id}" />" class="btn btn-success"
                                       data-toggle="tooltip" title="Edit">
                                        <i class="fa fa-pencil"></i>
                                    </a>
                                    <a href="javascript:void(0)" disabled="disabled" data-toggle="tooltip" title="Delete"
                                       onclick="deleteEntity(${location.id},false)" class="btn btn-danger">
                                        <i class="fa fa-trash"></i>
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
                $('#location-datatable').DataTable({
                    "columnDefs": [
                        {
                            "targets": [0, 1, 7, 8],
                            "orderable": false
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