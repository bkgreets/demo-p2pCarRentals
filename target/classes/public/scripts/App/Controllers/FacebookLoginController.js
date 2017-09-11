var myApp = angular.module('carsClub');

myApp.controller('facebookLoginController', ['$scope', facebookLoginController]);

function facebookLoginController($scope, $route) {
    $scope.params = $route.params;
}