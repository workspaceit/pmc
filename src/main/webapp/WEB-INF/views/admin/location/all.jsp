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
                <h1>Photographer List </h1>
                <hr>
                <div class="table-responsive">
                    <table class="table table-bordered table-hover table-responsive cstm-admin-table">
                        <thead>
                        <tr>
                            <th class="cstm-table-header">
                                Logo
                            </th>

                            <th class="cstm-table-header">
                                Event location
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
                                Action
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <d:forEach var="location" items="${locations}" >


                        <tr>
                            <td class="img-clm text-center">
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
                            </td>
                            <td class="des-clm">
                                <p class="text-left">${location.name}</p>
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
                            <td class="action-clm text-center">
                                <a href="<c:url value="/admin/location/update/${location.id}" />" class="btn btn-success"><i class="fa fa-pencil"></i></a>
                                <a href="#" class="btn btn-danger"><i class="fa fa-trash"></i></a>
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