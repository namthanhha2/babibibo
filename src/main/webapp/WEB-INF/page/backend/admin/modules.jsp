<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<script type="text/javascript">
    console.log("here");
    var moduleApp = angular.module("moduleApp",['ngMaterial', 'ngMessages']);
    moduleApp.controller('moduleCtrl', function ($scope,$http) {
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
    });
    console.log("end init");
    angular.bootstrap(document.getElementById("moduleId"), ['moduleApp']);

</script>
<div id="moduleId" ng-app="moduleApp" ng-controller="moduleCtrl">
    <md-toolbar class = "md-flex">
        <div class = "md-toolbar-tools">
            <h2 class = "md-flex">Quản lý chức năng</h2>
        </div>
    </md-toolbar>

    <md-content flex layout="column" layout-wrap>
        <form name="objectSearchForm">
            <sx:Input id="objectName" name="objectName" key="Tên chức năng" model="objectSearchForm.objectName"/>
            <sx:Input id="objectCode" name="objectCode" key="Mã chức năng" model="objectSearchForm.objectCode" />
            <md-datepicker 
                ng-model = "myDate" 
                md-placeholder = "Enter date"></md-datepicker>
        </form>
    </md-content>
</div>
