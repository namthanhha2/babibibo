<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<script type="text/javascript">
    console.log("here");
    var moduleApp = angular.module("moduleApp", ['ngMaterial', 'ngMessages', 'ui.grid']);
    moduleApp.controller('moduleCtrl', function ($scope, $http) {
        console.log("start");
        $scope.myDate = new Date();
        $scope.minDate = new Date(
                $scope.myDate.getFullYear(),
                $scope.myDate.getMonth() - 2,
                $scope.myDate.getDate());

        $scope.maxDate = new Date(
                $scope.myDate.getFullYear(),
                $scope.myDate.getMonth() + 2,
                $scope.myDate.getDate()
                );

        $scope.onlyWeekendsPredicate = function (date) {
            var day = date.getDay();
            return day === 0 || day === 6;
        };
        console.log("end");

        this.myData = [
            {
                firstName: "Cox",
                lastName: "Carney",
                company: "Enormo",
                employed: true
            },
            {
                firstName: "Lorraine",
                lastName: "Wise",
                company: "Comveyer",
                employed: false
            },
            {
                firstName: "Nancy",
                lastName: "Waters",
                company: "Fuelton",
                employed: false
            }
        ];
    });
    console.log("end init");
    angular.bootstrap(document.getElementById("moduleId"), ['moduleApp']);

</script>
<div id="moduleId" ng-app="moduleApp" ng-controller="moduleCtrl as $ctrl">
    <md-toolbar class = "md-flex">
        <div class = "md-toolbar-tools">
            <h3 class = "md-flex">Quản lý chức năng</h3>
        </div>
    </md-toolbar>

    <md-content flex layout="column" layout-wrap>
        <form name="objectSearchForm">
            <div layout="row">
                <div flex="40">
                    <sx:Input id="objectName" name="objectName" key="Tên chức năng" model="objectSearchForm.objectName"/>
                </div>
                <div flex="40">
                    <sx:Input id="objectCode" name="objectCode" key="Mã chức năng" model="objectSearchForm.objectCode" />
                </div>
            </div>
            <md-button class="md-primary md-raised" ng-click="onSearch()">Tìm kiếm</md-button>
        </form>
    </md-content>
    <div id="grid1" ui-grid="{ data: $ctrl.myData }" class="grid"></div>

</div>
