var zttApp = angular.module('zttApp', ["ui.router","restangular"]);

zttApp.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/home');

    $stateProvider

    // HOME STATES AND NESTED VIEWS ========================================
        .state('home', {
            url: '/home',
            templateUrl: '../partials/main.html'
        })
        .state('home.login',{
            url:'/login',
            templateUrl:'../partials/login.html',
            controller: function ($scope,Restangular) {
                $scope.userName = "zdd";
                //基础路由
                var user = Restangular.one("users","byname");
                user.get({userName:$scope.userName}).then(function (resp) {
                    $scope.users = resp;
                });
            }
        })
        // nested list with just some random string data
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
        // ABOUT PAGE AND MULTIPLE NAMED VIEWS =================================
        .state('about', {
            url: '/about',
            views: {

                // the main template will be placed here (relatively named)
                '': { templateUrl: '../partials/about.html' },

                // the child views will be defined here (absolutely named)
                'columnOne@about': { template: 'Look I am a column!' },

                // for column two, we'll define a separate controller
                'columnTwo@about': {
                    templateUrl: '../partials/table-data.html',
                    controller: 'scotchController'
                }
            }

        });

});
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