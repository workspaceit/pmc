<%@ page import="com.workspaceit.pmc.entity.Location" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:genericpage>
    <jsp:body>
        <div id="page-wrapper">
            <div class="container">
                <h1>Event List </h1>
                <hr>
                <div class="table-responsive">
                    <table class="table table-bordered table-hover table-responsive cstm-admin-table">
                        <thead>
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
                            Starts At
                        </th>
                        <th class="cstm-table-header">
                            Action
                        </th>
                        </tr>
                        </thead>
                        <tbody>
                        <d:forEach var="event" items="${events}" >
                            <td class="des-clm">
                                <p class="text-left">${event.name}</p>
                            </td>
                            <td class="date-clm">
                                <span class="cstm-date-txt">${event.startsAt}</span>
                            </td>
                            <td class="date-clm">
                                <span class="cstm-date-txt">${event.endsAt}</span>
                            </td>
                            <td class="action-clm text-center">
                                <a href="<c:url value="/admin/event/update/${event.id}" />" class="btn btn-success"><i class="fa fa-pencil"></i></a>
                                <!--<a href="#" class="btn btn-danger"><i class="fa fa-trash"></i></a>-->
                            </td>
                            </tr>
                        </d:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </jsp:body>

</t:genericpage>