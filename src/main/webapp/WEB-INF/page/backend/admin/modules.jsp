<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<script type="text/javascript">
    var moduleApp = angular.module("moduleApp", ['ngMaterial', 'ngMessages', 'ui.grid', 'ui.grid.pagination']);
    moduleApp.controller('moduleCtrl', function ($scope, $http) {
        $scope.searchForm = {};
        $scope.replaceForm = function(form,formName){
            var newForm = {};
            for(x in form){
                var newAttr = formName+"."+x;
                newForm[newAttr] = form[x];
            }
            console.log(newForm);
            return newForm;
        };
        
        $scope.onSearch = function () {
            var newobj = $scope.replaceForm($scope.searchForm,"searchForm");
           
            $http({
                method: 'POST',
                url: 'modulesAction!onSearch.do',
                headers: {'Content-Type': 'application/json'},
                data:newobj
            }).then(
                    function successCallback(response) {
                        $scope.myData = response.data.lstItems;
                    },
                    function errorCallback(response) {
                        return false;
                    }
            );
        };
        $scope.filterOptions = {
            filterText: "",
            useExternalFilter: true
        };
        $scope.pagingOptions = {
            pageSizes: [10, 20],
            currentPage: 1
        };
        $scope.gridOptions = {
            data: 'myData',
            enablePaging: true,
            showFooter: true,
            pagingOptions: $scope.pagingOptions,
            filterOptions: $scope.filterOptions,
            useExternalPagination: true, // custom      
            columnDefs: [
                {name: 'name'},
                {name: 'link'},
                {name: 'imgPath'},
                {name: 'delete', cellTemplate: '<div style="text-align:center"><img src="share/images/roles.png" width="16px" onclick="deleteX();"/></div>'}
            ],
            onRegisterApi: function (gridApi) {
                $scope.gridApi = gridApi;
                //Added for custom paging      
                gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
                    //paginationOptions.pageNumber = newPage;
                    //paginationOptions.pageSize = pageSize;
                    alert("hêr");
                });
            }

        };
        //$scope.myData = this.myData;
        if (!$scope.$$phase) {
            $scope.$apply();
        }
        ;
        $scope.deleteX = function () {
            alert("Dete");
        };
    });
    console.log("end init");
    angular.bootstrap(document.getElementById("moduleId"), ['moduleApp']);
    deleteX = function () {
        alert("Dete");
    }

</script>
<style>
    .grid {
        height: 250px;
    }
</style>
<div id="moduleId" ng-app="moduleApp" ng-controller="moduleCtrl as $ctrl">
    <md-toolbar class = "md-flex">
        <div class = "md-toolbar-tools">
            <h3 class = "md-flex">Quản lý chức năng</h3>
        </div>
    </md-toolbar>

    <md-content flex layout="column" layout-wrap>
        <form>
            <div layout="row">
                <div>
                    <sx:Input id="name" name="name" key="Tên chức năng" model="searchForm.name"/>
                </div>
            </div>
            <md-button class="md-primary md-raised" ng-click="onSearch()">Tìm kiếm</md-button>
        </form>
    </md-content>
    <div id="grid1" ui-grid="gridOptions" ui-grid-pagination class="grid"></div>
</div>
