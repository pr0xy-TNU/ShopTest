var app = angular.module("myApp", [])
    .controller("firstCtrl", function ($scope, $http) {

        $scope.userName = "Some name";
        $scope.userArray = [
            {name: 'Jani', country: 'Norway'},
            {name: 'Carl', country: 'Sweden'},
            {name: 'Margareth', country: 'England'},
            {name: 'Hege', country: 'Norway'},
            {name: 'Joe', country: 'Denmark'},
            {name: 'Gustav', country: 'Sweden'},
            {name: 'Birgit', country: 'Denmark'},
            {name: 'Mary', country: 'England'},
            {name: 'Kai', country: 'Norway'}
        ];

        $scope.addToUserList = function () {
            if ($scope.userName) {
                $scope.userArray.push($scope.userName);
                $scope.userName = ""
            } else {
                console.log("Nothink to add...");
            }
        }

        $scope.removeUserFromList = function (item) {
            var index = $scope.userArray.indexOf(item);
            $scope.userArray.splice(index, 1);
        }

    });

app.controller("httpCustomerController", function ($scope, $http) {
    //var stringUrl = "https://www.w3schools.com/angular/customers.php";
    //findAll
    var javaLocalUrl = "http://localhost:8080/hello/rest/emps";
    //findById
    var javaLocalShop = 'http://localhost:8080/shops/';
    $scope.findShopById = function (id) {
        if (id != null) {
            var totalQuery = javaLocalShop + id;
            console.info(totalQuery);
            if (totalQuery != null) {
                $http
                    .get(totalQuery)
                    .then(function (response) {
                        console.log("success!")
                        $scope.responseStatus = response.status;
                        $scope.responseData = response.data;
                        $scope.customerList = response.data;
                    }, function (reason) {
                        console.log("error!");
                    })

            }
        }


    }

})