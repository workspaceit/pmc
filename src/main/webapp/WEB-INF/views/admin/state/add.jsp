<div class="modal-dialog" role="document">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" >Add State</h4>
        </div>
        <div class="modal-body">
            <div class="btn-container-top">
                <button id="stateModalSaveBtn" class="btn btn-action-top" >Save</button>
            </div>
            <div id="stateFormBody">
                <div class="form-group">
                    <label>State Name</label>
                    <input id="stateName" name="name" type="text" class="form-control" placeholder="State Name">
                </div>
                <div class="form-group">
                    <label>State Code</label>
                    <input id="code" type="text" placeholder="State Code" class="form-control">
                </div>
            </div>
        </div>
    </div>
</div>

<script src="<s:url value="/resources/developer/js/state/create.js"/>"></script>
