
var myApp = angular.module('carsClub');

myApp.factory('findCarsFactory', ['$route', '$rootScope', '$http', 'appSettings', findCarsFactory]);

function findCarsFactory($route, $rootScope, $http, appSettings) {
    var service = {};
    service.findCars = findCars;

    return service;

    function findCars(searchCriteria) {
        var url = appSettings.serviceUrlBase + 'info/getCars?lon=-88.990174&lat=40.460362';
        return $http({
            method: 'GET',
            url: url,
            data: searchCriteria
        })
        return $http.get(Url);
    }
}