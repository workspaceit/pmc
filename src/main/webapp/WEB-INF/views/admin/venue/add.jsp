<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<t:genericpage>
  <jsp:body>
    <div id="page-wrapper">

      <div class="container">
        <h3 class="uni-header"><span>Add New Venue</span></h3>

        <div class="col-md-12">
          <div class="row">
            <div class="btn-container-top">
              <button class="btn btn-action-top" onclick="submitData('save')">Save</button>
              <button class="btn btn-action-top" onclick="submitData('saveClose')">Save&nbsp;&&nbsp;Close</button>
              <button class="btn btn-action-top" onclick="submitData('saveNew')">Save&nbsp;&&nbsp;New</button>
              <button class="btn btn-action-top" onclick="cancel()">Cancel</button>
            </div>
            <div class="form-group col-md-6" style="padding:0px;">
              <div class="panel panel-default">
                <div class="panel-body">

                  <div class="">
                    <div class="form-group">
                      <label>Venue name</label>
                      <input class="form-control" id="name">
                    </div>

                    <div class="form-group">
                      <label>
                        <a href="#" style="color:#333;font-size: 14px;" data-toggle="modal" data-target="#add-new-city">Location</a>
                      </label>
                      <select class="js-example-placeholder-multiple js-states form-control" id="location">
                        <c:forEach var="item" items="${locations}">
                          <option value="${item.id}">${item.name}</option>
                        </c:forEach>
                      </select>
                    </div>
                  </div>
                </div>
              </div>
            </div>

          </div>
        </div>
      </div>


      <footer style="padding: 25px;">
        <div class="container">
          <p style="color: #ffffff;font-size: 1em;" >
            © Picture Me Clubbing Developed by Workspace InfoTech Limited.All Right Reserved
          </p>
        </div>
      </footer>





      <!-- modal -->

      <div class="modal fade" id="add-new-city" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
              <h4 class="modal-title" id="myModalLabel">Add your City</h4>
            </div>
            <div class="modal-body">
              <div class="form-group">
                <label>City Name</label>
                <input class="form-control">
              </div>

            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-default btn-sm-new" data-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary btn-sm-new">Add City</button>
            </div>
          </div>
        </div>
      </div>



      <div class="modal fade" id="add-new-venue" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
              <h4 class="modal-title" id="myModalLabel">Add Your Venue</h4>
            </div>
            <div class="modal-body">
              <div class="form-group">
                <label>Venue Name</label>
                <input class="form-control">
              </div>

            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-default btn-sm-new" data-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary btn-sm-new">Add Venue</button>
            </div>
          </div>
        </div>
      </div>

      <div class="modal fade" id="add-new-state" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
              <h4 class="modal-title" id="myModalLabel">Add Your State</h4>
            </div>
            <div class="modal-body">
              <div class="form-group">
                <label>State Name</label>
                <input class="form-control">
              </div>

            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-default btn-sm-new" data-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary btn-sm-new">Add State</button>
            </div>
          </div>
        </div>
      </div>


      <!-- modal -->

      <script src="<s:url value="/resources/developer/js/venue/add.js"/>"></script>
    </div>
  </jsp:body>
</t:genericpage>
