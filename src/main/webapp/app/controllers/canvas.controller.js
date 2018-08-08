(function () {
  'use strict';

  angular
    .module('app')
    .controller('CanvasController', CanvasController);

  CanvasController.$inject = ['$rootScope', '$scope', '$cookies', '$routeParams', '$uibModal', '$location', 'UserService', 'ErrorController'];

  function CanvasController($rootScope, $scope, $cookies, $routeParams, $uibModal, $location, UserService, ErrorController) {

    var ctrl = this;

    var canvas = new fabric.Canvas('c');

    canvas.on('mouse:down', function(options){
      console.log('mouse down:', options.e.clientX, options.e.clientY);
    });
    canvas.on('mouse:up', function(options){
      console.log('mouse up:', options.e.clientX, options.e.clientY);
    });
//    canvas.on('mouse:move', function(options){
//      console.log('mouse move:', options.e.clientX, options.e.clientY);
//    });
    
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