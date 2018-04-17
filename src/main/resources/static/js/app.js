var zttApp = angular.module('zttApp', ["ui.router","restangular","ngCookies"]);

zttApp.config(function($stateProvider, $urlRouterProvider, $locationProvider) {
    //开始路由
    $urlRouterProvider.otherwise('/home');
    //去除#！url
   // $locationProvider.hashPrefix('');
    //$locationProvider.html5Mode(true);
    $stateProvider

        .state('home', {
            url: '/home',
            templateUrl: '../partials/main.html'

        })
        .state('home.login',{
            url:'/login',
            templateUrl:'../partials/login.html',
            controller: function ($cookieStore,$rootScope,$scope,$state,Restangular) {
                $scope.user = null;
                $scope.logSucc = false;
                $scope.login = function () {
                    Restangular.one("users/login").customPOST($scope.user).then(function (value) {
                        console.log(value);
                        if(value != null && value != "") {
                            //登录成功跳转页面
                            localStorage.setItem("token", value);
                            $state.go('about');
                            $scope.logSucc = true;
                        } else {
                            $scope.logSucc = false;
                        }
                    },function (err) {
                        console.log(err);
                    })
                };
            }
        })
        .state('home.register', {
            url: '/register',
            templateUrl: '../partials/register.html',
            controller:function ($cookieStore,$scope,$state,Restangular) {
                $scope.user = null;
                $scope.regSucc = false;
                $scope.register = function () {
                    Restangular.one("users/save").customPOST($scope.user).then(function (value) {
                        $scope.regSucc = value;
                        if($scope.regSucc == false) {
                            //注册成功跳转到登录页面
                            $state.go('home.login');
                        }
                    },function (err) {
                        console.log(err);
                    });
                };
            }
        })
        .state('about', {
            url: '/about',
            views: {
                '': { templateUrl: '../partials/about.html' },
                'columnOne@about': {
                    template: 'Look I am a column!',
                    controller : function ($state) {
                        if(!localStorage.hasOwnProperty('name')) {
                            $state.go('home.login');
                        }
                    }
                },
                'columnTwo@about': {
                    templateUrl: '../partials/table-data.html',
                    controller : function ($state) {
                        if(!localStorage.hasOwnProperty('name')) {
                            $state.go('home.login');
                        }
                    }
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