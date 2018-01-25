<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:genericpage>
    <jsp:body>
        <div id="page-wrapper">
            <div class="container">
                <h1>Watermark List </h1>
                <hr>
                <div class="table-responsive">
                    <table id="watermark-datatable" class="table table-bordered table-hover table-responsive cstm-admin-table">
                        <thead>
                        <tr>
                            <th class="cstm-table-header">
                                Title
                            </th>
                            <th class="cstm-table-header">
                                Type
                            </th>

                            <th class="cstm-table-header">
                                Logo Name
                            </th>
                            <th class="cstm-table-header">
                                Size
                            </th>
                            <th class="cstm-table-header">
                                Fade
                            </th>
                            <th class="cstm-table-header">
                                Watermark Text
                            </th>
                            <th class="cstm-table-header">
                                Font Id
                            </th>
                            <th class="cstm-table-header">
                                Placement
                            </th>
                            <th class="cstm-table-header">
                                color
                            </th>
                            <th class="cstm-table-header">
                                Action
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <d:forEach var="wm" items="${watermarkList}" >
                        <tr>
                            <td class="des-clm">
                                <p class="text-left">${wm.name}</p>
                            </td>
                            <td class="des-clm">
                                <p class="text-left">${wm.type}</p>
                            </td>
                            <td class="date-clm">
                                <span class="cstm-date-txt">${wm.logoName}</span>
                            </td>
                            <td class="date-clm">
                                <span class="cstm-date-txt">${wm.size}</span>
                            </td>

                            <td class="date-clm">
                                <span class="cstm-date-txt">${wm.fade}</span>
                            </td>
                            <td class="date-clm">
                                <span class="cstm-date-txt">${wm.watermarkText}</span>
                            </td>
                            <td class="date-clm">
                                <span class="cstm-date-txt">${wm.font}</span>
                            </td>
                            <td class="date-clm">
                                <span class="cstm-date-txt">${wm.placement}</span>
                            </td>

                            <td class="date-clm">
                                <span class="cstm-date-txt">${wm.color}</span>
                            </td>
                            <td class="action-clm text-center">
                                <a href="<c:url value="/admin/watermark/edit/${wm.id}" />" class="btn btn-success"><i class="fa fa-pencil"></i></a>
                                <a href="#" class="btn btn-danger"><i class="fa fa-trash"></i></a>
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
                $('#watermark-datatable').DataTable();
            });
        </script>
    </jsp:body>
</t:genericpage>