
<div class="modal-dialog" role="document">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" >Add Location</h4>
        </div>
        <div class="modal-body">
            <div class="btn-container-top">
                <button class="btn btn-action-top" onclick="submitLocationDataFromModal()">Save</button>
                <%--<button class="btn btn-action-top">Save&nbsp;&&nbsp;Close</button>
                <button class="btn btn-action-top">Save&nbsp;&&nbsp;New</button>
                <button class="btn btn-action-top">Cancel</button>--%>
            </div>
            <div class="form-group">
                <%@  include file="/WEB-INF/views/admin/location/pertial/form-body-for-modal.jsp" %>
            </div>
        </div>
    </div>
</div>
