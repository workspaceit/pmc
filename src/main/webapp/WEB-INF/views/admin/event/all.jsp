<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:genericpage>
    <jsp:body>
        <div id="page-wrapper">
            <div class="container">
                <a class="btn btn-success" href="<c:url value="/admin/event/add"/>">Add New Event</a>
                <h1>Event List </h1>
                <hr>
                <div class="table-responsive">
                    <table id="event-datatable" class="table table-bordered table-hover table-responsive cstm-admin-table">
                        <thead>
                            <tr>
                                <th class="cstm-table-header">
                                    Event
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
                                    Action
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="event" items="${events}" >
                            <tr>
                                <td class="des-clm">
                                    <p class="text-left">${event.name}</p>
                                </td>
                                <td class="des-clm">
                                    <p class="text-left">${event.venue.name}</p>
                                </td>
                                <td class="date-clm">
                                    <span class="cstm-date-txt">${event.startsAt}</span>
                                </td>
                                <td class="date-clm">
                                    <span class="cstm-date-txt">${event.endsAt}</span>
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
                $('#event-datatable').DataTable();
            });
        </script>
    </jsp:body>
</t:genericpage>