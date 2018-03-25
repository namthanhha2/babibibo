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
            <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/angular_material/1.1.0/angular-material.min.css">
            <link href="share/css/main.css" type="text/css" rel="stylesheet"/>
            <!--script src="share/js/angular.min.js" type="text/javascript"></script-->
            <script src="share/js/connector.js" type="text/javascript"></script>
            <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular.min.js"></script>
            <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-animate.min.js"></script>
            <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-aria.min.js"></script>
            <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-messages.min.js"></script>

            <!-- Angular Material Library -->
            <script src="https://ajax.googleapis.com/ajax/libs/angular_material/1.1.0/angular-material.min.js"></script>

            <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/angular_material/1.1.0/angular-material.min.css">
            <script src="share/js/app.js" type="text/javascript"></script>
        </head>

        <body>
            <div id="token">
                <s:token />
            </div>
            <div id="vt-loading-background"><div id="vt-loading-icon"></div></div>
            <div id="headerPart">
                <tiles:insertAttribute name="header"/>  
            </div>
            <div id="bodyPart">
                <tiles:insertAttribute name="body"/>  
            </div>
            <div id="footerPart">
                <tiles:insertAttribute name="footer"/> 
            </div>
            <script type="text/javascript">
                redrawFrame = function () {
                    var header = document.getElementById("headerPart");
                    var body = document.getElementById("bodyPart");
                    var footer = document.getElementById("footerPart");
                    var screenHeight = window.innerHeight;
                    var bodyHeight = screenHeight - header.clientHeight - footer.clientHeight - 20;
                    body.style.height = bodyHeight + "px";
                    //console.log("redrr");
                }
                document.body.onresize = redrawFrame;
                redrawFrame();
            </script>
        </body>
    </html>
</s:i18n>