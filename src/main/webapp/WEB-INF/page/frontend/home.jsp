<%-- 
    Document   : home
    Created on : Sep 13, 2017, 11:40:20 PM
    Author     : Vu Manh Ha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>

<div ng-app="desktopApp"  ng-cloak>
    <div ng-controller="desktopCtrl">
        <div id="function0">
            <div ng-repeat="item in lstFunctions">
                <div class="function {{item.style}}" id="{{item.functionId}}" ng-click="toFunction(item)">
                    <img src="{{item.imgPath}}"/>
                    <div>{{item.name}}</div>
                </div>
            </div>
        </div>
        <div class="taskbar">
            <div ng-repeat="item in lstRunningModules" class="runningModule" ng-click="selectModule(item.functionId)" title="{{item.name}}">

                <img src="{{item.imgPath}}"/>
            </div>
        </div>
    </div>
</div>
<div id="moduleDetail">
</div>
<script type="text/javascript">
    desktopApp = angular.module('desktopApp', ['ngMaterial', 'ngMessages']);
    //var desktopApp = angular.module("desktopApp", []);
    desktopApp.controller('desktopCtrl', function ($scope, $http) {
        $scope.lstFunctions = [
            {'functionId': 1, 'imgPath': 'share/images/users.png', 'name': 'Quản lý người dùng', 'style': 'crimson', 'link': 'modulesAction.do'},
            {'functionId': 2, 'imgPath': 'share/images/roles.png', 'name': 'Quản lý vai trò', 'style': 'dimgray', 'link': 'modulesAction.do'},
            {'functionId': 3, 'imgPath': 'share/images/function.png', 'name': 'Quản lý chức năng', 'style': 'violet', 'link': 'modulesAction.do'}
        ];

        $scope.lstRunningModules = [];
        $scope.lstRunningModules.push({
            functionId: 0,
            name: "Trang chủ",
            imgPath: "share/images/home.png",
            isActive: true

        });

        $scope.toFunction = function (item) {
            if (!$scope.selectModule(item.functionId)) {
                var data = {
                };
                $http({
                    method: 'POST',
                    url: item.link,
                    params: data
                }).then(
                        function successCallback(response) {
                            //console.log(response);
                            var newEl = document.createElement("div");
                            newEl.setAttribute("id", "function" + item.functionId);
                            newEl.innerHTML = response.data;
                            document.getElementById("moduleDetail").appendChild(newEl);
                            app.parseAndExecScript(response.data);
                            var task = {
                                functionId: item.functionId,
                                name: item.name,
                                imgPath: item.imgPath,
                                isActive: true
                            };
                            $scope.lstRunningModules.push(task);
                            return true;
                        },
                        function errorCallback(response) {
                            return false;
                        }
                );
            }
        };

        $scope.toHome = function () {

        };

        $scope.selectModule = function (functionId) {
            var bReturn = false;
            for (var i = 0; i < $scope.lstRunningModules.length; i++) {
                if ($scope.lstRunningModules[i].functionId === functionId) {
                    document.getElementById("function" + functionId).style.display = "";
                    bReturn = true;
                } else {
                    document.getElementById("function" + $scope.lstRunningModules[i].functionId).style.display = "none";
                }
            }
            return bReturn;
        };
    });

</script>