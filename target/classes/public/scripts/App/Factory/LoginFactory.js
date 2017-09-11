
var myApp = angular.module('carsClub');

myApp.factory('loginFactory', ['$route', '$rootScope', '$http',
  function ($route, $rootScope, $http) {
      var service = {};
      service.verifyUser = verifUser;

      return service;

      function verifUser(loginDetials) {
          var loginId = loginDetials.id;
          var password = loginDetials.password;

          var url = '/api/verifyUser/';
          return $http({
              method: 'POST',
              url: url,
              data: loginDetials
          })
          .then(function successCallback(response) {
              // this callback will be called asynchronously
              // when the response is available

              localStorage.loginDetails = loginDetails;
          }, function errorCallback(response) {
              // called asynchronously if an error occurs
              // or server returns response with an error status.
              localStorage.loginDetials = null;
          });
      }
  }]);