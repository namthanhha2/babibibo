<%-- 
    Document   : ButtonAdd
    Created on : Dec 7, 2011, 3:32:13 PM
    Author     : gpdn_huannn
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Add Button" pageEncoding="UTF-8"%>
<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="id"%>
<%@attribute name="onclick"%>
<%@attribute name="disable"%>
<%@attribute name="image"%>
<%@attribute name="key"%>
<%@attribute name="cssClass"%>
<%@attribute name="cssStyle"%>
<c:if test="${empty cssClass}">
    <c:set var="cssClass" value="btn-primary"/>
</c:if>
<c:if test="${not empty id}">
    <c:set var="id" value="id='${id}'"/>
</c:if>
<c:if test="${not empty cssStyle}">
    <c:set var="cssStyle" value="style=${cssStyle}"/>
</c:if>
<button onclick="${onclick}; return false;" ${id} class="btn ${cssClass}" ${cssStyle}>
    <c:if test="${not empty image}">
        <img src="${image}" height="16px" alt="${key}">
    </c:if>
    ${key}
</button>
