<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<t:genericpage>
  <jsp:body>
    <div id="page-wrapper">

      <div class="container">
        <h3 class="uni-header"><span>Advertising Pricing</span></h3>
        <div class="col-md-12" id="notification"></div>
        <div class="col-md-12 well">
          <c:forEach var="price" items="${pricesList}" >
          <div class="form-group">
            <label>${price.description}</label>
            <input type="hidden" name="price_id[]" value="${price.id}" class="form-control" />
            <input type="text" name="price[]" value="${price.price}" class="form-control" />

          </div>
          </c:forEach>
          <button class="btn btn-primary" onclick="submitData()">Save</button>
        </div>
      </div>
      <!-- /#page-wrapper -->
      <script src="<s:url value="/resources/developer/js/advertisementPrices/update.js"/>"></script>

    </div>
  </jsp:body>
</t:genericpage>