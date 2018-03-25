<%-- 
    Document   : home
    Created on : Sep 13, 2017, 11:40:20 PM
    Author     : Vu Manh Ha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div ng-app="loginApp" ng-controller="loginCtrl" style="margin-left: auto;margin-top: 50px" class="box">
    <div style="text-align: center;margin-top: 20px;margin-bottom: 20px">
        <label style="font-size: 15px;font-weight: bold; color:#004b96">{{title}}</label>
    </div>
    <label>Tài khoản</label>
    <div><input type="text" ng-model="userName"/></div>
    <label style="display:{{passwordStyle}}">Mật khẩu</label>
    <div style="display:{{passwordStyle}}"><input type="password" ng-model="password"/></div>
    <label style="display:{{forgotStyle}}" id="emailLabel">Email</label>
    <div style="display:{{forgotStyle}}"><input type="email" ng-model="email"/></div>
    <div class="errorMessage">{{errorMessage}}</div>
    <div style="text-align: center;"> 
        <button ng-click="onLogin()" class="default">{{submitLabel}}</button>
        <button ng-click="onForgot()" class="negative">{{forgotLabel}}</button>
    </div>
</div>

<script type="text/javascript">
    document.getElementById("bodyPart").style.backgroundColor = "rgb(64,175,218)";
    var loginApp = angular.module('loginApp',['ngMaterial']);
    loginApp.controller('loginCtrl', function ($scope, $http) {
        $scope.title = "ĐĂNG NHẬP";
        $scope.submitLabel = "Đăng nhập";
        $scope.forgotLabel = "Quên mật khẩu";
        $scope.forgotStyle = "none";
        $scope.errorMessage = "";
        $scope.onLogin = function () {
            var data = {
                userName: $scope.userName,
                password: $scope.password,
                email: $scope.email
            };
            $http({
                method: 'POST',
                url: "loginAction!login.do",
                params: data
            }).then(
                    function successCallback(response) {
                        // this callback will be called asynchronously
                        // when the response is available                       
                        console.log(response);
                        if(response.data.resultCode == "2"){
                            $scope.errorMessage = "Mật khẩu hoặc password không chính xác";
                        } else {
                            window.location = "homeAction.do";
                        }
                    },
                    function errorCallback(response) {
                            $scope.errorMessage = "Có lỗi xảy ra";
                    }
                );

//            $http.post("loginAction!login.do", data).then(
//                    function (response) {
//                        alert(response.data);
//                    }
//            );
        };
        $scope.onForgot = function () {
            if ($scope.forgotLabel === "Quay lại") {
                $scope.title = "ĐĂNG NHẬP";
                $scope.submitLabel = "Đăng nhập";
                $scope.forgotLabel = "Quên mật khẩu";
                $scope.forgotStyle = "none";
                $scope.passwordStyle = "";
            } else {
                $scope.title = "QUÊN MẬT KHẨU";
                $scope.submitLabel = "Gửi";
                $scope.forgotLabel = "Quay lại";
                $scope.forgotStyle = "";
                $scope.passwordStyle = "none";
            }
        };
    });
</script>