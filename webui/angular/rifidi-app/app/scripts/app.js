'use strict';

/**
 * @ngdoc overview
 * @name rifidiApp
 * @description
 * # rifidiApp
 *
 * Main module of the application.
 */
angular
  .module('rifidiApp', [
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'angularTreeview',
    'ui.bootstrap',
    'mgo-angular-wizard',
    'directive.contextMenu'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl'
      })
      .when('/sensorWizard/:restProtocol/:ipAddress/:restPort', {
        templateUrl: 'views/sensorWizard.html',
        controller: 'SensorWizardCtrl'
      })
      .when('/serverWizard', {
        templateUrl: 'views/serverWizard.html',
        controller: 'ServerWizardCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
