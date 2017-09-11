var myApp = angular.module('carsClub');

myApp.controller('headerController', ['$scope', 'loginFactory', headerController]);

function headerController($scope, loginFactory) {
    loginFactory
            .verifyUser($scope.login)
            .then(function successCallback(response) {
                // this callback will be called asynchronously
                // when the response is available

                alert('success login. Login details are ' + $scope.login.id + ' & password is ' + $scope.login.password)
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                alert('error logging in  ' + response)
            });
}