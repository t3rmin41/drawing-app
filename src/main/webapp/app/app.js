(function () {
    'use strict';
    angular
      .module('app', ['ngRoute', 'ngCookies', 'ui.bootstrap'])
      .config([ '$routeProvider', function($routeProvider) {
        $routeProvider
        .when('/', {
            templateUrl: 'app/views/home.html',
            controller: 'HomeController'
        })
        .when('/login', {
            templateUrl: 'app/views/login.html',
            controller: 'LoginController'
        })
        .otherwise({
          redirectTo : '/login'
        });
      } ])
      .config([ '$httpProvider', function($httpProvider) {
        $httpProvider.interceptors.push('TokenAuthInterceptor');
      } ])
      .factory('TokenAuthInterceptor', function($cookies) {
          return {
            request : function(config) {
              var authToken = $cookies.get('token');
              if (authToken) {
                  config.headers['Authorization'] = authToken;
              }
              return config;
            }
          };
    })
})();