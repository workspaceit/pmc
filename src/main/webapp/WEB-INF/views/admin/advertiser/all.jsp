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
                <hr>
                <div class="table-responsive">
                    <table id="advertiser-datatable" class="table table-bordered table-hover table-responsive cstm-admin-table">
                        <thead>
                        <tr>
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
                                Action
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <d:forEach var="advertiser" items="${advertisers}" >


                            <tr>
                                <td class="img-clm text-center">
                                    <p class="text-left">${advertiser.name}</p>
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
                                <td class="action-clm text-center">
                                    <a href="<c:url value="/admin/advertiser/update/${advertiser.id}" />" class="btn btn-success"><i class="fa fa-pencil"></i></a>
                                    <a href="<c:url value="/admin/advertiser/checkout/${advertiser.id}" />" class="btn btn-danger"><i class="fa fa fa-shopping-cart"></i></a>
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
                $('#advertiser-datatable').DataTable();
            });
        </script>

    </jsp:body>

</t:genericpage>