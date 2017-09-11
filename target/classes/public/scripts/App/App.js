var myApp = angular.module("carsClub", ["ngRoute"]);

myApp.constant("appSettings", {
    "serviceUrlBase": "http://localhost:8090/"

});

myApp.config(function ($routeProvider) {

    $routeProvider.when("/", {
        templateUrl: "partials/Home.html",
        //controller: "LoginController",
        routeParameters: { isAuthReq: false }
    });

    $routeProvider.when("/FacebookLoggedIn", {
        templateUrl: "partials/FacebookLoggedIn.html",
        controller: "FacebookLoginController",
        routeParameters: { isAuthReq: false }
    });
    

    $routeProvider.when("/findcar", {
        templateUrl: "partials/FindCar.html",
        controller: "findCarController",
        routeParameters: { isAuthReq: false }
    });

    $routeProvider.when("/listcar", {
        templateUrl: "partials/ListCar.html",
        controller: "ListCarController",
        routeParameters: { isAuthReq: false }
    });

    $routeProvider.when("/owner", {
        templateUrl: "partials/Owner.html",
        controller: "ownerController",
        routeParameters: { isAuthReq: false}
    });

    $routeProvider.when("/renter", {
        templateUrl: "partials/Owner.html",
        controller: "renterController",
        routeParameters: { isAuthReq: false }
    });

    $routeProvider.when("/signup", {
        templateUrl: "partials/signup.html",
        controller: "signupController",
        routeParameters: { isAuthReq: false }
    });

    $routeProvider.when("/login", {
        templateUrl: "partials/login.html",
        controller: "loginController",
        routeParameters: { isAuthReq: false }
    })
})
 .run(function ($rootScope, $location) {
      $rootScope.$on("$routeChangeStart", function (event, next, current) {
          if ((localStorage.loginDetails == null || localStorage.loginDetails == undefined)
              && (next.$$route && next.$$route.routeParameters && next.$$route.routeParameters.isAuthReq)) {
              // no logged user, redirect to /login
              if (next.templateUrl === "partials/login.html") {
              } else {
                  $location.path("/login");
              }
          }
      });
 });



//$rootScope.$on('$routeChangeSuccess', function () {
//    batchLog($route.current ? $route.current.template : null);
//});