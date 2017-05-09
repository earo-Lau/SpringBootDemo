/**
 * Created by lauearo on 08/05/2017.
 */
var actionApp=angular.module('actionApp', ['ngRoute', 'ngResource']);

actionApp
    .config(function ($routeProvider) {
        $routeProvider.when('/oper', {
            templateUrl: '/webapp/views/view1.html',
            controller: 'View1Controller'
        }).when('/directive', {
            templateUrl: '/webapp/views/view2.html',
            controller: 'View2Controller'
        }).otherwise(
            {redirectTo: '/'}
        );
    });