'use strict';

angular
  .module('finance-simplified', [
    'ngRoute',
    'main',
    'categories'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'app/views/main.html',
        controller: 'MainCtrl'
      })
      .when('/categories', {
        templateUrl: 'app/views/categories.html',
        controller: 'CategoriesCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
