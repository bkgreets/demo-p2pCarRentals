var myApp = angular.module('carsClub');

myApp.controller('renterController', ['$scope', renterController]);

function renterController($scope) {
    $scope.activeTab = 'renter';
}