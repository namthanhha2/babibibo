<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@page import="java.util.Locale"%>
<%@page import="java.util.ResourceBundle"%>

<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="s" uri="/struts-tags"%>

<s:i18n name="com/havm/babibibo/config/Language">
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
    <html>
        <head>
            <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
            <meta name="description" content="" />
            <title>Vui vẻ tí thôi</title>
            <link href="share/images/favicon.png" type="image/x-icon" rel="shortcut icon" />
            <link href="share/css/main.css" type="text/css" rel="stylesheet"/>
            <link href="share/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" rel="stylesheet"/>
            <script src="share/js/angular.min.js" type="text/javascript"></script>
            <script src="share/js/connector.js" type="text/javascript"></script>
            <script src="share/js/app.js" type="text/javascript"></script>
        </head>
        <body>
            <div id="token">
                <s:token />
            </div>
            <div id="vt-loading-background"><div id="vt-loading-icon"></div></div>
            <div id="bodyPart" style="height: 90%">
                <tiles:insertAttribute name="body"/>  
            </div>
        </body>
    </html>
</s:i18n>