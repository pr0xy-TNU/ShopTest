app.controller("httpCustomerController", function ($scope, $http) {
    //var stringUrl = "https://www.w3schools.com/angular/customers.php";
    var javaLocalUrl = "http://localhost:8080/hello/rest/emp/dummy.json";

    $http
        .get(javaLocalUrl)
        .then(function (response) {
            $scope.responseStatus = response.status;
            $scope.responseData = response.data;
            $scope.customerList = response.data;
        })
})