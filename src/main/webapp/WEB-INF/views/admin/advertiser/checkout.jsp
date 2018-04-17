

<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<t:genericpage>
    <jsp:attribute name="developerScript">
            <script src="<s:url value="/resources/js/numeral.min.js"/>"></script>
            <script src="<s:url value="/resources/developer/js/checkout/common.js"/>"></script>
            <script src="<s:url value="/resources/developer/js/checkout/create.js"/>"></script>
    </jsp:attribute>
    <jsp:body>

        <%@include file="/WEB-INF/views/admin/advertiser/checkout-body.jsp" %>

    </jsp:body>

</t:genericpage>