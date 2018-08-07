(function () {
  'use strict';

  angular
    .module('app')
    .controller('CanvasController', CanvasController);

  CanvasController.$inject = ['$rootScope', '$scope', '$cookies', '$routeParams', '$uibModal', '$location', 'UserService', 'ErrorController'];

  function CanvasController($rootScope, $scope, $cookies, $routeParams, $uibModal, $location, UserService, ErrorController) {

    var ctrl = this;

    var canvas = new fabric.Canvas('c');

    ctrl.$onInit = function() {
      var rect = new fabric.Rect({
        left: 100,
        top: 100,
        fill: 'red',
        width: 20,
        height: 20
      });
      canvas.add(rect);
    };
    
  }

})();