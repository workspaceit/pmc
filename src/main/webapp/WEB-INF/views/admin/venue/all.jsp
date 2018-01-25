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
                <hr>
                <div class="table-responsive">
                    <table id="venue-datatable" class="table table-bordered table-hover table-responsive cstm-admin-table">
                        <thead>
                        <th class="cstm-table-header">
                            Venue
                        </th>
                            <th class="cstm-table-header">
                                Location
                            </th>
                            <th class="cstm-table-header">
                                Action
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <d:forEach var="venue" items="${venues}" >

                            <td class="des-clm">
                                <p class="text-left">${venue.name}</p>
                            </td>
                            <td class="date-clm">
                                <span class="cstm-date-txt">${venue.location.name}</span>
                            </td>
                            <td class="action-clm text-center">
                                <a href="<c:url value="/admin/venue/update/${venue.id}" />" class="btn btn-success"><i class="fa fa-pencil"></i></a>
                                <!--<a href="#" class="btn btn-danger"><i class="fa fa-trash"></i></a>-->
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
                $('#venue-datatable').DataTable();
            });
        </script>
    </jsp:body>

</t:genericpage>