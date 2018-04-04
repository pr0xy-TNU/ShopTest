angular.module("myApp", [])
    .controller("shopCtrl", function ($scope, $http) {
        $scope.shopId = 34;
        if ($scope) {
            var javaLocalShop = "http://localhost:8080/shops/";
            $http
                .get(javaLocalShop + $scope.shopId)
                .then(function (response) {
                    console.log("Was founded!");
                    $scope.shopEntity = response.data;
                    $scope.responseData = response.data;
                    $scope.responseStatus = response.status;
                }, function (reason) {
                    console.log("Was not found!");
                })
        }


    })