<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <title>PictureMeClubbing</title>
    </head>
    <body>
        <h1>Picture from ${eventImage.event.name}</h1>
        <h2>${eventImage.event.location.name}</h2>
        <img src="http://163.53.151.3:8080/img/images/20007070721006.jpg" alt="">
    </body>
</html>
