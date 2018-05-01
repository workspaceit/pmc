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
                <h1>Photographer List</h1>
                <%--<hr>--%>
                <div class="cs_action_btn">
                    <input type="hidden" id="type" value="photographer">
                    <a href="<c:url value="/admin/photographer/add"/>" class="ac_btn new"><i class="fa fa-plus"></i>NEW</a>
                    <button id="edit-selected-btn" disabled="disabled" class="ac_btn"><i class="fa fa-pencil"></i>EDIT</button>
                    <button id="activate-selected-btn" disabled="disabled" class="ac_btn"><i class="fa fa-check"></i>ENABLE</button>
                    <button id="deactivate-selected-btn" disabled="disabled" class="ac_btn"><i class="fa fa-check"></i>DISABLE</button>
                    <button id="delete-selected-btn" disabled="disabled" class="ac_btn"><i class="fa fa-trash"></i>DELETE</button>
                </div>
                <div class="table-responsive dtble">
                    <table id="photographer-datatable" class="table table-bordered table-hover table-responsive cstm-admin-table">
                        <thead>
                        <tr>
                            <th class="cstm-table-header">
                                <input type="checkbox" id="select-all-checkbox">
                            </th>
                            <th class="cstm-table-header">
                                Image
                            </th>

                            <th class="cstm-table-header">
                                Username
                            </th>
                            <th class="cstm-table-header">
                                Email
                            </th>
                            <th class="cstm-table-header">
                                Full Name
                            </th>
                            <th class="cstm-table-header">
                                Phone
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
                        <d:forEach var="photographer" items="${photographers}" >
                        <tr>
                            <td class="des-clm" style="text-align: center;">
                                <input type="checkbox" class="select-checkbox" value="${photographer.id}">
                            </td>
                            <td class="img-clm text-center">
                                <c:if test = "${photographer.profilePhoto==null || photographer.profilePhoto.trim().equals('')}">
                                    <a class="p-thumbnail" href="#" data-photographer-id="${photographer.id}"
                                       data-toggle="modal" data-title="">
                                        <img class="img-circle" width="70" src="<s:url value="/resources/images/default_profile_pic.png" />" alt="No image">
                                    </a>
                                </c:if>
                                <c:if test = "${photographer.profilePhoto!=null && !photographer.profilePhoto.trim().equals('')}">
                                    <a class="p-thumbnail" href="#" data-photographer-id="${photographer.id}"
                                       data-toggle="modal" data-title="">
                                        <img class="img-circle" width="70" src="<s:url value="/photographer-profile-img/${photographer.profilePhoto}" />" alt="No image">
                                    </a>
                                </c:if>
                            </td>
                            <td class="date-clm">
                                <span class="cstm-date-txt"><a href="<c:url value="/admin/photographer/details/${photographer.id}"/>">${photographer.userName}</a></span>
                            </td>
                            <td class="date-clm">
                                <span class="cstm-date-txt">${photographer.email}</span>
                            </td>
                            <td class="des-clm">
                                <p id="title-${photographer.id}" class="text-left">${photographer.fullName}</p>
                            </td>
                            <td class="date-clm">
                                <span class="cstm-date-txt">${photographer.phoneNumber}</span>
                            </td>
                            <td class="date-clm">
                                <fmt:formatDate pattern = "yyyy-MM-dd hh:mm a" value="${photographer.createdAt}"></fmt:formatDate>
                            </td>
                            <td class="date-clm">
                                <c:if test = "${photographer.active}">
                                    <input type="checkbox" class="activate-checkbox" checked value="${photographer.id}">
                                </c:if>
                                <c:if test = "${!photographer.active}">
                                    <input type="checkbox" class="activate-checkbox" value="${photographer.id}">
                                </c:if>
                            </td>
                            <td width="15%" class="action-clm text-center">
                                <%--<a href="<c:url value="/admin/photographer/details/${photographer.id}" />"--%>
                                   <%--data-toggle="tooltip" title="Details"class="btn btn-info"><i class="fa fa-user"                                                     aria-hidden="true"></i>--%>
                                <%--</a>--%>
                                <a href="<c:url value="/admin/photographer/update/${photographer.id}" />"
                                   data-toggle="tooltip" title="Edit" class="btn btn-success"><i class="fa fa-pencil"></i>
                                </a>
                                <a href="javascript:void(0)" onclick="deleteEntity(${photographer.id},false)"
                                   data-toggle="tooltip" title="Delete" class="btn btn-danger"><i  class="fa fa-trash"></i>
                                </a>
                            </td>
                        </tr>
                        </d:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div class="modal fade" id="photographer-profile-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title" id="image-gallery-title"></h4>
                    </div>
                    <div class="modal-body clearfix">
                        <div class="container">
                            <h3 class="uni-header">
                                <span>Photographer Details
                                    <%--<a href="<c:url value="/admin/photographer/update/${photographer.id}" />" class="pull-right">--%>
                                      <%--<button type="button" class="close" style="font-size: 42px">--%>
                                          <%--<i class="fa fa-pencil-square" aria-hidden="true" style="font-size: 40px;color: #2d2356;"></i>--%>
                                      <%--</button>--%>
                                    <%--</a>--%>
                                </span>
                            </h3>
                            <div class="col-md-12">
                                <div class="row clearfix">
                                    <div class="col-md-5">
                                        <img id="p-profilephoto" onerror="this.src='<c:url value="/resources/images/default_alternate.png"/>'"
                                             src="" class="img-responsive">
                                    </div>
                                    <div class="col-md-7">
                                        <label class="label-pd">Full Name</label>
                                        <h4><strong id="p-fullname"></strong></h4>
                                        <label class="label-pd">Phone Number</label>
                                        <h4><strong id="p-phone"></strong></h4>
                                        <label class="label-pd">User Name</label>
                                        <h4><strong id="p-username"></strong></h4>
                                        <label class="label-pd">Email</label>
                                        <h4><strong id="p-email"></strong></h4>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="../others/delete-modal.jsp"%>
        <script>
            $(document).ready(function() {
                $('#photographer-datatable').DataTable({
                    "columnDefs": [
                        {
                            "targets": [0, 1, 7, 8],
                            "orderable": false,
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

                $('.p-thumbnail').click(function () {
                    var $popUp = $('#photographer-profile-modal');
                    var photographerId = $(this).data('photographer-id');
                    $.ajax({
                        url:BASEURL+'api/photographer/details/' + photographerId,
                        type:'GET',
                        dataType:'JSON',
                        success:function (photographer) {
                            var imagePath = BASEURL+ 'photographer-profile-img/' + photographer.profilePhoto;
                            $popUp.find('#p-profilephoto').attr('src', imagePath);
                            $popUp.find('#p-fullname').html(photographer.fullName);
                            $popUp.find('#p-phone').html(photographer.phoneNumber);
                            $popUp.find('#p-email').html(photographer.email);
                            $popUp.find('#p-username').html(photographer.userName);
                            $popUp.modal('show');
                        }
                    });
                });

            });
        </script>
        <script src="<s:url value="/resources/developer/js/helper/list.helper.common.js"/>"></script>
        
    </jsp:body>

</t:genericpage>