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
                    <a href="<c:url value="/admin/event/add"/>" class="ac_btn new"><i class="fa fa-plus"></i>NEW</a>
                    <button class="ac_btn"><i class="fa fa-pencil"></i>EDIT</button>
                    <button class="ac_btn"><i class="fa fa-check"></i>ACTIVATE</button>
                    <button class="ac_btn"><i class="fa fa-trash"></i>DELETE</button>
                </div>
                <div class="dtble">
                    <table id="event-datatable" class=" table table-bordered table-hover table-responsive cstm-admin-table">
                        <thead>
                            <tr>
                                <th class="cstm-table-header">
                                    <input type="checkbox" id="select-all-checkbox">
                                </th>
                                <th class="cstm-table-header">
                                    Event Name
                                </th>
                                <th class="cstm-table-header">
                                    Venue
                                </th>
                                <th class="cstm-table-header">
                                    Starts At
                                </th>
                                <th class="cstm-table-header">
                                    Ends At
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
                                <td class="des-clm">
                                    <input type="checkbox" class="select-checkbox" value="${event.id}">
                                </td>
                                <td class="des-clm">
                                    <p class="text-left">${event.name}</p>
                                </td>
                                <td class="des-clm">
                                    <p class="text-left">${event.venue.name}</p>
                                </td>
                                <td class="date-clm">
                                    <span class="cstm-date-txt">
                                        <fmt:formatDate value="${event.startsAt}" pattern="yyyy-MM-DD hh:mm:ss a"/>
                                    </span>
                                </td>
                                <td class="date-clm">
                                    <fmt:formatDate value="${event.endsAt}" pattern="yyyy-MM-DD hh:mm:ss a"/>
                                </td>
                                <td class="date-clm">
                                    <input type="checkbox">
                                </td>
                                <td class="action-clm text-center">
                                    <a href="<c:url value="/admin/event/update/${event.id}" />" class="btn btn-success">
                                        <i class="fa fa-pencil"></i>
                                    </a>
                                    <!--<a href="#" class="btn btn-danger"><i class="fa fa-trash"></i></a>-->
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <script>
            $(document).ready(function() {
                $('#event-datatable').DataTable({
                    "columnDefs": [{
                        "targets": [0, 5, 6],
                        "orderable": false,
                    }],
                    "order": [[1, 'asc']]
                });

                $('#select-all-checkbox').click(function () {
                    var isSelected = $(this).prop('checked');
                    if(isSelected){
                        $('.select-checkbox').prop('checked', true);
                        console.log($('.select-checkbox:checked').map(function(){return $(this).val();}).get());
                    }
                    else {
                        $('.select-checkbox').prop('checked', false);
                    }
                });
                
                function enableOrDisableEditButton() {

                }
                
            });
        </script>
    </jsp:body>
</t:genericpage>