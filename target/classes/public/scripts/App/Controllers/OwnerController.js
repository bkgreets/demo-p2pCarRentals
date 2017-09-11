var myApp = angular.module('carsClub');

myApp.controller('ownerController', ['$scope', function ownerController($scope) {
    $scope.activeTab = 'owner';
}]);

//function ownerController($scope)
//{
//    $scope.activeTab = 'owner';
//}