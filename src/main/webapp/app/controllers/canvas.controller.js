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
    
    // https://stackoverflow.com/questions/31207624/fabricjs-draw-line-but
    
    ctrl.$onInit = function() {
//      var rect = new fabric.Rect({
//        left: 100,
//        top: 100,
//        fill: 'red',
//        width: 20,
//        height: 20
//      });
//      canvas.add(rect);

      var path = new fabric.Path('M 50 50 L 150 50 L 150 260 L 70 250',
        {
          stroke: 'blue',
          strokeWidth: 3,
          fill: 'transparent'
        }
      );
      canvas.add(path);
      
      var polyline = new fabric.Polyline([
        { x: 40, y: 40 },
        { x: 150, y: 40 },
        { x: 150, y: 150 },
        { x: 50, y: 150 },
        { x: 40, y: 50 }
      ], {
      fill: 'transparent',
      borderScaleFactor: 1,
      stroke: 'red',
      strokeWidth: 3,
      left: 220,
      top: 150
      });
      canvas.add(polyline);
    };
    
  }

})();