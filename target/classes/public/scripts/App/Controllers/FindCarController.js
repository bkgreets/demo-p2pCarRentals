var myApp = angular.module('carsClub');

myApp.controller('findCarController', ['$scope', 'findCarsFactory', findCarController]);

function findCarController($scope, findCarsFactory) {
    $scope.latitude = 40.460362;
    $scope.longitude = 88.990174;
    var searchDetails = { lon: $scope.longitude, lat: $scope.latitude };
    findCarsFactory.findCars()
    .then(function successCallback(response) {
        // this callback will be called asynchronously
        // when the response is available

        $scope.searchedCars = response.data;
        _.each($scope.searchedCars, function (searchedCar) {
            getAddressByCoords(searchedCar.locationInfo.coordinates[0], searchedCar.locationInfo.coordinates[1], function (i, a) {
                updateAddressOfCarByCoords(i,a, searchedCar);
                });
        })

    }, function errorCallback(response) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
        $scope.searchedCars = [];
        $scope.errors = " some thing got errord : " + response;
    });
}

function updateAddressOfCarByCoords(i, a, searchedCar) {
    searchedCar.fullCarAddress = i[0].formatted_address;
            
}