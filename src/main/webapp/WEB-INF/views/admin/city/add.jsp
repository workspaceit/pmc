<div class="modal-dialog" role="document">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" >Add City</h4>
        </div>
        <div class="modal-body">
            <div class="btn-container-top">
                <button id="cityModalSaveBtn" class="btn btn-action-top" >Save</button>
            </div>
            <div id="cityFormBody">
                <div class="form-group">
                    <label>City Name</label>
                    <input id="cityName" type="text" class="form-control" placeholder="City Name">
                </div>
                <div class="form-group">
                    <label>State</label>
                    <select id="cityStateId" class="form-control">
                        <option value="" >Select State</option>
                        <c:forEach var="state" items="${states}">z
                            <option value="${state.id}" >${state.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<s:url value="/resources/developer/js/city/create.js"/>"></script>