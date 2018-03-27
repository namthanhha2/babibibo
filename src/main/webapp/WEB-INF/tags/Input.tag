<%-- 
    Document   : ButtonAdd
    Created on : Dec 7, 2011, 3:32:13 PM
    Author     : gpdn_huannn
--%>
<%@tag description="input" pageEncoding="UTF-8"%>
<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="id"%>
<%@attribute name="key"%>
<%@attribute name="name"%>
<%@attribute name="model"%>
<md-input-container class = "md-block">
    <label>${key}</label>
    <input required id="${id}" name= "${name}" ng-model="${model}">
    <div ng-messages = "${model}.$error">
        <div ng-message = "required">This is required.</div>
    </div>
</md-input-container>
