var zttApp = angular.module('zttApp', ["ui.router","restangular"]);

zttApp.config(function($stateProvider, $urlRouterProvider) {
    //开始路由
    $urlRouterProvider.otherwise('/home');

    $stateProvider

        .state('home', {
            url: '/home',
            templateUrl: '../partials/main.html'
        })
        .state('home.login',{
            url:'/login',
            templateUrl:'../partials/login.html',
            controller: function ($scope,Restangular) {
                $scope.user = null;
                $scope.logSucc = false;
                $scope.login = function () {
                    Restangular.one("users/login").customPOST($scope.user).then(function (value) {
                        $scope.logSucc = value;
                    },function (err) {
                        console.warn(err);
                    })
                };
            }
        })
        .state('home.register', {
            url: '/register',
            templateUrl: '../partials/register.html',
            controller:function ($scope,Restangular) {
                $scope.user = null;
                $scope.regSucc = false;
                $scope.register = function () {
                    Restangular.one("users/save").customPOST($scope.user).then(function (value) {
                        $scope.regSucc = value;
                    },function (err) {
                        console.warn(err);
                    });
                };
            }
        })
        .state('about', {
            url: '/about',
            views: {
                '': { templateUrl: '../partials/about.html' },
                'columnOne@about': { template: 'Look I am a column!' },
                'columnTwo@about': {
                    templateUrl: '../partials/table-data.html',
                    controller: 'scotchController'
                }
            }

        });

});
//test table
zttApp.controller('scotchController', function($scope) {

    $scope.message = 'test';

    $scope.scotches = [
        {
            name: 'Macallan 12',
            price: 50
        },
        {
            name: 'Chivas Regal Royal Salute',
            price: 10000
        },
        {
            name: 'Glenfiddich 1937',
            price: 20000
        }
    ];

});