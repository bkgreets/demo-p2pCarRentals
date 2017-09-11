var myApp = angular.module('carsClub');

myApp.controller('loginController', ['$scope', 'loginFactory', 'facebookFactory', loginController]);

function loginController($scope, loginFactory, facebookFactory) {
    $scope.facebookurl = facebookFactory.getFacebookUrl();
    //$(".btn-fb").attr('href', facebookurl);


    $scope.login = function()
    {
        loginFactory
            .verifyUser($scope.login)
            .then(function successCallback(response) {
            // this callback will be called asynchronously
                // when the response is available

                alert('login details are ' + $scope.login.id + ' & password is ' + $scope.login.password)
        }, function errorCallback(response) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
            alert('login details are ' + $scope.login.id + ' & password is ' + $scope.login.password)
        });
        
    };

}