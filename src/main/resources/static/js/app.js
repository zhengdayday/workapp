var zttApp = angular.module('zttApp', ["ui.router","restangular","ngCookies", "textAngular"]);

zttApp.controller('zttAppController', function ($scope) {
   $scope.isLogin = false;
   $scope.isTeacher = false;
   $scope.loginName = "";
   //localStorage.removeItem("token");
   //localStorage.removeItem("name");
   if (localStorage.hasOwnProperty("token")) {
       $scope.isLogin = true;
       $scope.loginName = localStorage.getItem("name");
       if(localStorage.getItem("level") == 1) {
           $scope.isTeacher = true;
       }
   }
});

zttApp.config(function($stateProvider, $urlRouterProvider, $locationProvider) {
    //开始路由
    $urlRouterProvider.otherwise('/home');
    //去除#！url
   // $locationProvider.hashPrefix('');
    //$locationProvider.html5Mode(true);
    $stateProvider

        .state('home', {
            url: '/home',
            templateUrl: '../partials/main.html' ,
            controller: function ($scope) {
                $scope.isLogin = false;
                $scope.loginName = "";
                if (localStorage.hasOwnProperty("token")) {
                    $scope.isLogin = true;
                    $scope.loginName = localStorage.getItem("name");
                }
            }
        })
        .state('home.login',{
            url:'/login',
            templateUrl:'../partials/login.html',
            controller: function ($cookieStore,$rootScope,$scope,$state,Restangular) {
                $scope.user = null;
                $scope.logSucc = false;
                $("#login").removeAttr("ui-sref");
                $scope.login = function () {
                    Restangular.one("users/login").customPOST($scope.user).then(function (value) {
                        if(value.token != null && value.token.trim() != "") {
                            localStorage.setItem("token", value.token);
                            localStorage.setItem("name", value.name);
                            localStorage.setItem("level", value.level);
                            localStorage.setItem("number", value.number);
                            $scope.logSuc = false;
                            $scope.isLogin = true;
                            if(localStorage.getItem("level") === 1) {
                                $scope.isTeacher = true;
                            }
                            $scope.loginName = value.name;
                            location.reload();
                        } else {
                            $scope.logSucc = true;
                        }
                    },function (err) {
                        console.warn(err);
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
                        if ($scope.regSucc == false) {
                            //注册成功跳转到登录页面
                            $state.go('home.login');
                        }
                    }, function (err) {
                        console.warn(err);
                    });
                };
            }
        })
        .state('userInfo', {
            url: '/userInfo',
            templateUrl: '../partials/userInfo.html',
            controller: function ($cookieStore, $scope,$state,Restangular) {
                Restangular.one("users/allUser").get().then(function (response) {
                    // 取得所有的用户
                    $scope.data = response.userlist;

                    // 每页多少条数据
                    $scope.pageSize = 5;
                    // 分页数
                    $scope.pages = Math.ceil($scope.data.length / $scope.pageSize);
                    $scope.newPages = $scope.pages > 5 ? 5 : $scope.pages;
                    $scope.pageList = [];
                    $scope.selPage = 1;
                    //设置表格数据源(分页)
                    $scope.setData = function () {
                        $scope.items = $scope.data.slice(($scope.pageSize * ($scope.selPage - 1)), ($scope.selPage * $scope.pageSize));//通过当前页数筛选出表格当前显示数据
                    }
                    $scope.items = $scope.data.slice(0, $scope.pageSize);
                    //分页要repeat的数组
                    for (var i = 0; i < $scope.newPages; i++) {
                        $scope.pageList.push(i + 1);
                    }
                    //打印当前选中页索引
                    $scope.selectPage = function (page) {
                    //不能小于1大于最大
                        if (page < 1 || page > $scope.pages) return;
                    //最多显示分页数5
                        if (page > 2) {
                    //因为只显示5个页数，大于2页开始分页转换
                            var newpageList = [];
                            for (var i = (page - 3) ; i < ((page + 2) > $scope.pages ? $scope.pages : (page + 2)) ; i++) {
                                newpageList.push(i + 1);
                            }
                            $scope.pageList = newpageList;
                        }
                        $scope.selPage = page;
                        $scope.setData();
                        $scope.isActivePage(page);
                        console.log("选择的页：" + page);
                    };
                    //设置当前选中页样式
                        $scope.isActivePage = function (page) {
                        return $scope.selPage == page;
                    };
                    //上一页
                    $scope.Previous = function () {
                        $scope.selectPage($scope.selPage - 1);
                    }
                    //下一页
                    $scope.Next = function () {
                        $scope.selectPage($scope.selPage + 1);
                    };
                });
            }
        })
        .state('userInfo.info1',{
            url: '/info1',
            templateUrl: '../partials/info1.html',
            controller: function ($cookieStore, $scope,$state,Restangular) {

            }
        })
        .state('userInfo.info2',{
            url: '/info2',
            templateUrl: '../partials/info2.html',
            controller: function ($cookieStore, $scope,$state,Restangular) {

            }
        })
        .state('lessonInfo', {
            url: '/lessonInfo',
            templateUrl: '../partials/lessonInfo.html',
            controller: function ($cookieStore, $scope,$state,Restangular) {
                $scope.getAllLesson = function () {
                    Restangular.one("lesson/teacherLesson").get({tno: localStorage.getItem("number")}).then(function (response) {
                        // 取得所有的课程
                        $scope.data = response.tnoLesson;
                        /*
                        $scope.data = [
                            {lessonName: "dayday", teacherName: "wj"}
                        ] mock数据
                        */
                        // 每页多少条数据
                        $scope.pageSize = 5;
                        // 分页数
                        $scope.pages = Math.ceil($scope.data.length / $scope.pageSize);
                        $scope.newPages = $scope.pages > 5 ? 5 : $scope.pages;
                        $scope.pageList = [];
                        $scope.selPage = 1;
                        //设置表格数据源(分页)
                        $scope.setData = function () {
                            $scope.items = $scope.data.slice(($scope.pageSize * ($scope.selPage - 1)), ($scope.selPage * $scope.pageSize));//通过当前页数筛选出表格当前显示数据
                        }
                        $scope.items = $scope.data.slice(0, $scope.pageSize);
                        //分页要repeat的数组
                        for (var i = 0; i < $scope.newPages; i++) {
                            $scope.pageList.push(i + 1);
                        }
                        //打印当前选中页索引
                        $scope.selectPage = function (page) {
                            //不能小于1大于最大
                            if (page < 1 || page > $scope.pages) return;
                            //最多显示分页数5
                            if (page > 2) {
                                //因为只显示5个页数，大于2页开始分页转换
                                var newpageList = [];
                                for (var i = (page - 3) ; i < ((page + 2) > $scope.pages ? $scope.pages : (page + 2)) ; i++) {
                                    newpageList.push(i + 1);
                                }
                                $scope.pageList = newpageList;
                            }
                            $scope.selPage = page;
                            $scope.setData();
                            $scope.isActivePage(page);
                            console.log("选择的页：" + page);
                        };
                        //设置当前选中页样式
                        $scope.isActivePage = function (page) {
                            return $scope.selPage == page;
                        };
                        //上一页
                        $scope.Previous = function () {
                            $scope.selectPage($scope.selPage - 1);
                        }
                        //下一页
                        $scope.Next = function () {
                            $scope.selectPage($scope.selPage + 1);
                        };
                    });
                }
                $scope.getAllLesson();
                $scope.lesson = {};
                $scope.saveLesson = function () {
                    $scope.lesson.tno = localStorage.getItem("number");
                    Restangular.one("lesson/save").customPOST($scope.lesson).then(function (value) {
                        if(value == true) {
                            swal("保存课程成功");
                        } else {
                            swal("保存课程失败已经重名");
                        }
                        $scope.lesson = {};
                        $scope.getAllLesson();
                    }, function (err) {
                        swal("保存课程失败");
                        console.warn(err);
                    });
                }

            }
        })
        .state('teacherwork',{
            url:'/teacherwork?lno&lname',
            templateUrl: '../partials/teacher-work.html',
            controller: function ($cookieStore, $scope,$state,Restangular,$stateParams) {
                $scope.lname = $stateParams.lname;
                $scope.lno = $stateParams.lno;
                $scope.getAllWork = function () {
                    Restangular.one("homework/getAllWork").get({tno: localStorage.getItem("number")}).then(function (response) {
                        // 取得所有的作业
                        $scope.data = response.workList;
                        /*
                        $scope.data = [
                            {lessonName: "dayday", teacherName: "wj"}
                        ] mock数据
                        */
                        // 每页多少条数据
                        $scope.pageSize = 5;
                        // 分页数
                        $scope.pages = Math.ceil($scope.data.length / $scope.pageSize);
                        $scope.newPages = $scope.pages > 5 ? 5 : $scope.pages;
                        $scope.pageList = [];
                        $scope.selPage = 1;
                        //设置表格数据源(分页)
                        $scope.setData = function () {
                            $scope.items = $scope.data.slice(($scope.pageSize * ($scope.selPage - 1)), ($scope.selPage * $scope.pageSize));//通过当前页数筛选出表格当前显示数据
                        }
                        $scope.items = $scope.data.slice(0, $scope.pageSize);
                        //分页要repeat的数组
                        for (var i = 0; i < $scope.newPages; i++) {
                            $scope.pageList.push(i + 1);
                        }
                        //打印当前选中页索引
                        $scope.selectPage = function (page) {
                            //不能小于1大于最大
                            if (page < 1 || page > $scope.pages) return;
                            //最多显示分页数5
                            if (page > 2) {
                                //因为只显示5个页数，大于2页开始分页转换
                                var newpageList = [];
                                for (var i = (page - 3) ; i < ((page + 2) > $scope.pages ? $scope.pages : (page + 2)) ; i++) {
                                    newpageList.push(i + 1);
                                }
                                $scope.pageList = newpageList;
                            }
                            $scope.selPage = page;
                            $scope.setData();
                            $scope.isActivePage(page);
                            console.log("选择的页：" + page);
                        };
                        //设置当前选中页样式
                        $scope.isActivePage = function (page) {
                            return $scope.selPage == page;
                        };
                        //上一页
                        $scope.Previous = function () {
                            $scope.selectPage($scope.selPage - 1);
                        }
                        //下一页
                        $scope.Next = function () {
                            $scope.selectPage($scope.selPage + 1);
                        };
                    });
                }
                $scope.getAllWork();
                $scope.work = {};
                $scope.saveWork = function () {
                    $scope.work.tno = localStorage.getItem("number");
                    $scope.work.lno = $scope.lno;
                    Restangular.one("homework/save").customPOST($scope.work).then(function (value) {
                        if(value == true) {
                            swal("保存课程成功");
                        } else {
                            swal("保存课程失败已经重名");
                        }
                        $scope.work = {};
                        $scope.getAllWork();
                    }, function (err) {
                        swal("保存课程失败");
                        console.warn(err);
                    });
                }

            }
        })
        .state('work',{
            url:'/work?wno',
            templateUrl: '../partials/work.html',
            controller: function ($cookieStore, $scope,$state,Restangular,$stateParams) {
                $scope.wno = $stateParams.wno;
                $scope.workInfo=[];
                $scope.workRead=[];
                $scope.workNotRead=[];
                $scope.getWorkInfo = function () {
                    Restangular.one("homework/getWorkInfo").get({wno:$scope.wno}).then(function (response) {
                        // 取得作业信息
                        $scope.workInfo = response;
                        console.log($scope.workInfo);
                    });
                    Restangular.one("studentWork/getWork").get({wno:$scope.wno}).then(function (response) {
                        // 取得作业信息
                        $scope.workRead = response.studentWorkRead;
                        $scope.workRead = [
                            {workName:"sdfsdf",workDesc:"zzzz",studentName:"zddd",gradle:"youxiuyouxiu 100!"}
                        ]
                        $scope.workNotRead = response.studentWorkNotRead;
                        $scope.workNotRead = [
                            {workName:"sdfsdf",workDesc:"zzzz",studentName:"zddd"}
                        ]
                        $scope.readpageSize = 5;
                        // 分页数
                        $scope.readpages = Math.ceil($scope.workRead.length / $scope.readpageSize);
                        $scope.readnewPages = $scope.readpages > 5 ? 5 : $scope.readpages;
                        $scope.readpageList = [];
                        $scope.readselPage = 1;
                        //设置表格数据源(分页)
                        $scope.readsetData = function () {
                            $scope.readitems = $scope.workRead.slice(($scope.readpageSize * ($scope.readselPage - 1)), ($scope.readselPage * $scope.readpageSize));//通过当前页数筛选出表格当前显示数据
                        }
                        $scope.readitems = $scope.workRead.slice(0, $scope.readpageSize);
                        //分页要repeat的数组
                        for (var i = 0; i < $scope.readnewPages; i++) {
                            $scope.readpageList.push(i + 1);
                        }
                        //打印当前选中页索引
                        $scope.readselectPage = function (page) {
                            //不能小于1大于最大
                            if (page < 1 || page > $scope.readpages) return;
                            //最多显示分页数5
                            if (page > 2) {
                                //因为只显示5个页数，大于2页开始分页转换
                                var newpageList = [];
                                for (var i = (page - 3) ; i < ((page + 2) > $scope.readpages ? $scope.readpages : (page + 2)) ; i++) {
                                    newpageList.push(i + 1);
                                }
                                $scope.readpageList = newpageList;
                            }
                            $scope.readselPage = page;
                            $scope.readsetData();
                            $scope.readisActivePage(page);
                            console.log("选择的页：" + page);
                        };
                        //设置当前选中页样式
                        $scope.readisActivePage = function (page) {
                            return $scope.readselPage == page;
                        };
                        //上一页
                        $scope.readPrevious = function () {
                            $scope.readselectPage($scope.readselPage - 1);
                        }
                        //下一页
                        $scope.readNext = function () {
                            $scope.readselectPage($scope.readselPage + 1);
                        };



                        $scope.readNotpageSize = 5;
                        // 分页数
                        $scope.readNotpages = Math.ceil($scope.workNotRead.length / $scope.readNotpageSize);
                        $scope.readNotnewPages = $scope.readNotpages > 5 ? 5 : $scope.readNotpages;
                        $scope.readNotpageList = [];
                        $scope.readNotselPage = 1;
                        //设置表格数据源(分页)
                        $scope.readNotsetData = function () {
                            $scope.readNotitems = $scope.workNotRead.slice(($scope.readNotpageSize * ($scope.readNotselPage - 1)), ($scope.readNotselPage * $scope.readNotpageSize));//通过当前页数筛选出表格当前显示数据
                        }
                        $scope.readNotitems = $scope.workNotRead.slice(0, $scope.readNotpageSize);
                        //分页要repeat的数组
                        for (var i = 0; i < $scope.readNotnewPages; i++) {
                            $scope.readNotpageList.push(i + 1);
                        }
                        //打印当前选中页索引
                        $scope.readNotselectPage = function (page) {
                            //不能小于1大于最大
                            if (page < 1 || page > $scope.readNotpages) return;
                            //最多显示分页数5
                            if (page > 2) {
                                //因为只显示5个页数，大于2页开始分页转换
                                var newpageList = [];
                                for (var i = (page - 3) ; i < ((page + 2) > $scope.readNotpages ? $scope.readNotpages : (page + 2)) ; i++) {
                                    newpageList.push(i + 1);
                                }
                                $scope.readNotpageList = newpageList;
                            }
                            $scope.readNotselPage = page;
                            $scope.readNotsetData();
                            $scope.readNotisActivePage(page);
                            console.log("选择的页：" + page);
                        };
                        //设置当前选中页样式
                        $scope.readNotisActivePage = function (page) {
                            return $scope.readNotselPage == page;
                        };
                        //上一页
                        $scope.readNotPrevious = function () {
                            $scope.readNotselectPage($scope.readNotselPage - 1);
                        }
                        //下一页
                        $scope.readNotNext = function () {
                            $scope.readNotselectPage($scope.readNotselPage + 1);
                        };
                    });
                }
                $scope.getWorkInfo();
                //$scope.workInfo = {};
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

zttApp.filter("parseHTML", function ($sce) {
    return function (text) {
        return $sce.trustAsHtml(text);
    }
})