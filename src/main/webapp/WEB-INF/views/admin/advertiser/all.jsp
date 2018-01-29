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
                <h1>Advertiser List</h1>
                <%--<hr>--%>
                <div class="cs_action_btn">
                    <input type="hidden" id="type" value="advertiser">
                    <a href="<c:url value="/admin/advertiser/add"/>" class="ac_btn new"><i class="fa fa-plus"></i>NEW</a>
                    <button id="edit-selected-btn" disabled="disabled" class="ac_btn"><i class="fa fa-pencil"></i>EDIT</button>
                    <button id="activate-selected-btn" class="ac_btn"><i class="fa fa-check"></i>ENABLE</button>
                    <button id="deactivate-selected-btn" class="ac_btn"><i class="fa fa-check"></i>DISABLE</button>
                    <button id="delete-selected-btn" class="ac_btn"><i class="fa fa-trash"></i>DELETE</button>
                </div>
                <div class="table-responsive dtble">
                    <table id="advertiser-datatable" class="table table-bordered table-hover table-responsive cstm-admin-table">
                        <thead>
                        <tr>
                            <th class="cstm-table-header">
                                <input type="checkbox" id="select-all-checkbox">
                            </th>
                            <th class="cstm-table-header">
                                Name
                            </th>
                            <th class="cstm-table-header">
                                Phone
                            </th>
                            <th class="cstm-table-header">
                                City
                            </th>
                            <th class="cstm-table-header">
                                State
                            </th>
                            <th class="cstm-table-header">
                                Zip
                            </th>

                            <th class="cstm-table-header">
                                Run time start
                            </th>
                            <th class="cstm-table-header">
                                Run time ends
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
                        <d:forEach var="advertiser" items="${advertisers}" >
                            <tr>
                                <td class="des-clm">
                                    <input type="checkbox" class="select-checkbox" value="${advertiser.id}">
                                </td>
                                <td class="img-clm text-center">
                                    <p id="title-${advertiser.id}" class="text-left">${advertiser.name}</p>
                                </td>
                                <td class="date-clm">
                                    <span class="cstm-date-txt">${advertiser.phone}</span>
                                </td>
                                <td class="des-clm">
                                    <p class="text-left">${advertiser.city.name}</p>
                                </td>
                                <td class="des-clm">
                                    <p class="text-left">${advertiser.state.name}</p>
                                </td>
                                <td class="date-clm">
                                    <span class="cstm-date-txt">${advertiser.zip}</span>
                                </td>
                                <td class="date-clm">

                                    <span class="cstm-date-txt"><fmt:formatDate pattern = "MM-dd-yyyy" value="${advertiser.runtimeStarts}"  ></fmt:formatDate></span>
                                </td>
                                <td class="date-clm">
                                    <span class="cstm-date-txt"><fmt:formatDate pattern = "MM-dd-yyyy" value="${advertiser.runtimeEnds}"  ></fmt:formatDate></span>
                                </td>
                                <td class="date-clm">
                                    <c:if test = "${advertiser.active}">
                                        <input type="checkbox" class="activate-checkbox" checked value="${advertiser.id}">
                                    </c:if>
                                    <c:if test = "${!advertiser.active}">
                                        <input type="checkbox" class="activate-checkbox" value="${advertiser.id}">
                                    </c:if>
                                </td>
                                <td class="action-clm text-center">
                                    <a href="<c:url value="/admin/advertiser/update/${advertiser.id}" />" class="btn btn-success"><i class="fa fa-pencil"></i></a>
                                    <a href="<c:url value="/admin/advertiser/checkout/${advertiser.id}" />" class="btn btn-info"><i class="fa fa fa-shopping-cart"></i></a>
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
                $('#advertiser-datatable').DataTable({
                    "columnDefs": [{
                        "targets": [0, 8, 9],
                        "orderable": false,
                    }],
                    "order": [[1, 'asc']]
                });
            });
        </script>
        <script src="<s:url value="/resources/developer/js/helper/list.helper.common.js"/>"></script>

    </jsp:body>

</t:genericpage>