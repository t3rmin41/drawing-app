(function () {
  'use strict';

  angular
    .module('app')
    .controller('CanvasController', CanvasController);

  CanvasController.$inject = ['$rootScope', '$scope', '$cookies', '$routeParams', '$uibModal', '$location', 'UserService', 'ErrorController'];

  function CanvasController($rootScope, $scope, $cookies, $routeParams, $uibModal, $location, UserService, ErrorController) {

    var ctrl = this;

  }

})();