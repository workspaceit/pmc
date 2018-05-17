<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mi
  Date: 5/16/18
  Time: 2:51 PM
  To change this template use File | Settings | File Templates.
--%>

<c:set value="" var="radioSelected" />
<c:if test="${param.selectedStatic}" >
    <c:set value="checked" var="radioSelected" />
</c:if>

<div class="section-resource">
    <input id="staticSelectorUpdate_${param.id}"
           type="radio" class="static-selector" style="display: none;"
           onclick="staticSelected(this)" ${radioSelected}
           data-sec-res-id="${param.id}" />
    <img onerror="this.src='/resources/images/default_alternate.png'" src="<s:url value="/common/${param.fileName}"/>" class="img-thumbnail" width="150">
    <input id="imgUrlUpdated_${param.id}"
           class="form-control sec-res-url"
           type="text"
           value="${param.url}"
           data-sec-res-id="${param.id}"
           placeholder="Advertisement URL">
    <br>
    <%--ID_KEY._GALLERY_TOP_BANNER is global vaiable update.js --%>
    <c:if test="${param.isDeletable==null || param.isDeletable==false}" >
        <a href="javascript:void(0)" onclick="addIdToRemove(this,'${param.key}',${param.id})" >Delete</a>

    </c:if>
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