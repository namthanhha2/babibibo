<%-- 
    Document   : home
    Created on : Sep 13, 2017, 11:40:20 PM
    Author     : Vu Manh Ha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div ng-app="desktopApp" ng-controller="desktopCtrl">
    <div ng-repeat="item in lstFunctions">
        <div class="function {{item.style}}" id="{{item.functionId}}" ng-click="toFunction(item.functionId)">
            <img src="{{item.imgPath}}"/>
            <div>{{item.name}}</div>
        </div>
    </div>
    <div id="moduleDetail">

    </div>
</div>

<script type="text/javascript">
    var desktopApp = angular.module("desktopApp", []);
    desktopApp.controller('desktopCtrl', function ($scope, $http) {
        $scope.lstFunctions = [{'functionId': 1, 'imgPath': '/share/images/1.png', 'name': 'havm', 'style': 'brown'},
            {'functionId': 1, 'imgPath': '/share/images/1.png', 'name': 'Quản lý người dùng', 'style': 'crimson'},
            {'functionId': 1, 'imgPath': '/share/images/1.png', 'name': 'Quản lý vai trò', 'style': 'dimgray'},
            {'functionId': 1, 'imgPath': '/share/images/1.png', 'name': 'Quản lý chức năng', 'style': 'violet'}
        ];

        $scope.toFunction = function (link) {
            var data = {
            };
            $http({
                method: 'POST',
                url: link,
                params: data
            }).then(
                    function successCallback(response) {

                    },
                    function errorCallback(response) {
                    }
            );
        }
    });

</script>