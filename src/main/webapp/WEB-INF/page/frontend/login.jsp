<%-- 
    Document   : home
    Created on : Sep 13, 2017, 11:40:20 PM
    Author     : Vu Manh Ha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div ng-app="" ng-controller="loginCtrl" style="margin-left: auto;margin-top: 50px" class="box">
    <div style="text-align: center;margin-top: 20px;">
    <label style="font-size: 14px;font-weight: bold; color:#112233">ĐĂNG NHẬP</label>
    </div>
    <br>
    <label>Tài khoản</label>
    <div><input type="text" ng-model="userName"/></div>
    <label>Mật khẩu</label>
    <div><input type="password" ng-model="password"/></div>
    <label>Email</label>
    <div><input type="email" ng-model="email"/></div>
    <button id="" class="default">Đăng nhập</button>
    <button id="" class="negative">Quên mật khẩu</button>
</div>

<script type="text/javascript">
    document.getElementById("bodyPart").style.backgroundColor = "rgb(64,175,218)";
</script>