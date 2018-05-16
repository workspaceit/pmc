<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mi
  Date: 5/16/18
  Time: 2:51 PM
  To change this template use File | Settings | File Templates.
--%>

<div>
    <input type="radio" class="static-selector" style="display: none;" onclick="staticSelected(this)" />
    <img onerror="this.src='/resources/images/default_alternate.png'" src="<s:url value="/common/${param.fileName}"/>" class="img-thumbnail" width="150">
    <input id="imgUrlUpdated_${param.id}"   onchange="addImageUrlForUpdate(${param.id})"
           class="form-control" type="text" value="${param.url}"
           placeholder="Advertisement URL">
    <br>
    <%--ID_KEY._GALLERY_TOP_BANNER is global vaiable update.js --%>
    <a href="javascript:void(0)" onclick="addIdToRemove(this,${param.key},${param.id})" >Delete</a>
</div>



<%--
<div class="preview-holder">
                        <img  onerror="this.src='/resources/images/default_alternate.png'" src="<s:url value="/common/${secResource.fileName}" />" class="img-thumbnail" width="150">
                        <input id="imgUrlUpdated_${secResource.id}"
                               class="form-control"
                               type="text"
                               value="${secResource.url}"
                               placeholder="Advertisement URL"
                               onchange="addImageUrlForUpdate(${secResource.id})">
                        <br>

<a href="javascript:void(0)" onclick="addIdToRemove(this,ID_KEY._GALLERY_BOTTOM_BANNER,${secResource.id})" >Delete</a>
</div>

--%>