
var myApp = angular.module('carsClub');

myApp.factory('facebookFactory', ['$route', '$rootScope', '$http',
  function ($route, $rootScope, $http) {
      var service = {};
      service.getFacebookUrl = getFacebookUrl;

      return service;

      function getFacebookUrl() {
          var appUrl = encodeURI('http://localhost:2496/#/FacebookLoggedIn');
          var facebookAppId = '396002473943468';//bram,in19 Id
          var facebookurl = 'https://www.facebook.com/dialog/oauth?client_id=' + facebookAppId + '&redirect_uri=' + appUrl + '&state=6511a76a9958d68f55648fe0fd7dfcb0&scope=email%2Cuser_birthday%2C+user_education_history%2C+user_location%2C+user_work_history';

          return facebookurl;
      }
  }]);