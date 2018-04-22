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

                // 学生注册
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

                //教师注册
                $scope.teacher = null;
                $scope.regSucc1 = false;
                $scope.registerTeacher = function () {
                    Restangular.one("users/saveTeacher").customPOST($scope.teacher).then(function (value) {
                        $scope.regSucc1 = value;
                        if ($scope.regSucc1 == false) {
                            //注册成功跳转到登录页面
                            $state.go('home.login');
                        }
                    }, function (err) {
                        console.warn(err);
                    });
                };
            }
        })
        .state('doWork', {
            url: '/doWork?wno',
            templateUrl: '../partials/doWork.html',
            controller:function ($cookieStore,$scope,$state,Restangular, $stateParams) {
                $scope.wno = $stateParams.wno;
                $scope.doWorkInfo = {};
                $scope.doWork = function () {
                    Restangular.one("homework/getWorkInfo").get({wno:$scope.wno}).then(function (response) {
                        $scope.workinfo = response;
                    });
                }
                $scope.answerSave = function () {
                    $scope.doWorkInfo.wno = $scope.wno;
                    $scope.doWorkInfo.sno = localStorage.getItem("number");
                    Restangular.one("studentWork/saveAnswer").customPOST($scope.doWorkInfo).then(function (value) {
                        swal("完成作业");
                    });
                }
                $scope.doWork();
            }
        })
        .state('readWork', {
            url: '/readWork?wno&sno',
            templateUrl: '../partials/readWork.html',
            controller:function ($cookieStore,$scope,$state,Restangular, $stateParams) {
                $scope.wno = $stateParams.wno;
                $scope.sno = $stateParams.sno;
                $scope.readWorkInfo = {};
                $scope.readWork = function () {
                    Restangular.one("studentWork/problem").get({wno:$scope.wno, sno:$scope.sno}).then(function (response) {
                        $scope.student = response;
                    });
                }
                $scope.readAnswerSave = function () {
                    $scope.readWorkInfo.wno = $scope.wno;
                    $scope.readWorkInfo.sno = $scope.sno;
                    Restangular.one("studentWork/saveGrade").customPOST($scope.readWorkInfo).then(function (value) {
                        swal("批改成功");
                    });
                }
                $scope.readWork();
            }
        })
        .state('userInfo', {
            url: '/userInfo',
            templateUrl: '../partials/userInfo.html',
            controller: function ($cookieStore, $scope,$state,Restangular) {
               $scope.logout = function () {
                   localStorage.removeItem("token");
                   localStorage.removeItem("name");
                   localStorage.removeItem("level");
                   localStorage.removeItem("number");
                   $scope.isLogin = false;
                   $scope.isTeacher = false;
                   $scope.loginName = "";
                   swal("已经注销，跳转到登录页面");
                   $state.go('home.login');
                   location.reload();
               }
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
                localStorage.removeItem()
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

                    Restangular.one("lesson/allLesson").get().then(function (response) {
                        // 取得所有的课程
                        $scope.datas = response.lessonList;
                        /*
                        $scope.data = [
                            {lessonName: "dayday", teacherName: "wj"}
                        ] mock数据
                        */
                        // 每页多少条数据
                        $scope.pageSizes = 5;
                        // 分页数
                        $scope.pagess = Math.ceil($scope.datas.length / $scope.pageSizes);
                        $scope.newPagess = $scope.pagess > 5 ? 5 : $scope.pagess;
                        $scope.pageLists = [];
                        $scope.selPages = 1;
                        //设置表格数据源(分页)
                        $scope.setDatas = function () {
                            $scope.itemss = $scope.datas.slice(($scope.pageSizes * ($scope.selPages - 1)), ($scope.selPages * $scope.pageSizes));//通过当前页数筛选出表格当前显示数据
                        }
                        $scope.itemss = $scope.datas.slice(0, $scope.pageSizes);
                        //分页要repeat的数组
                        for (var i = 0; i < $scope.newPagess; i++) {
                            $scope.pageLists.push(i + 1);
                        }
                        //打印当前选中页索引
                        $scope.selectPages = function (page1) {
                            //不能小于1大于最大
                            if (page1 < 1 || page1 > $scope.pagess) return;
                            //最多显示分页数5
                            if (page1 > 2) {
                                //因为只显示5个页数，大于2页开始分页转换
                                var newpageList = [];
                                for (var i = (page1 - 3) ; i < ((page1+ 2) > $scope.pagess ? $scope.pagess : (page1 + 2)) ; i++) {
                                    newpageList.push(i + 1);
                                }
                                $scope.pageLists = newpageList;
                            }
                            $scope.selPages = page1;
                            $scope.setDatas();
                            $scope.isActivePages(page1);
                            console.log("选择的页：" + page1);
                        };
                        //设置当前选中页样式
                        $scope.isActivePages = function (page1) {
                            return $scope.selPages == page1;
                        };
                        //上一页
                        $scope.Previouss = function () {
                            $scope.selectPages($scope.selPages - 1);
                        }
                        //下一页
                        $scope.Nexts = function () {
                            $scope.selectPages($scope.selPages + 1);
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
                    Restangular.one("homework/getAllWork").get({lno: $scope.lno}).then(function (response) {
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
        .state('sw',{
            url:'/sw?lno',
            templateUrl: '../partials/student-work.html',
            controller: function ($cookieStore, $scope,$state,Restangular,$stateParams) {
                $scope.lno = $stateParams.lno;
                $scope.lessonInfo=[];
                $scope.lessonOk=[];
                $scope.lessonNo=[];
                $scope.lessonBody = {lno:$scope.lno,sno:localStorage.getItem("number")};
                $scope.getWorkOK = function () {
                    Restangular.one("lesson/getLessonBylno").get({lno:$scope.lno}).then(function (response) {
                        // 取得课程信息
                        $scope.lessonInfo = response;
                        console.log($scope.lessonInfo);
                    });
                    Restangular.one("ssl/getSsl").get({sno:localStorage.getItem("number"),lno:$scope.lno}).then(function (response) {
                        // 取得作业信息
                        $scope.okList = response.ok;
                        $scope.noList = response.no;

                        $scope.okListpageSize = 5;
                        // 分页数
                        $scope.okListpages = Math.ceil($scope.okList.length / $scope.okListpageSize);
                        $scope.okListnewPages = $scope.okListpages > 5 ? 5 : $scope.okListpages;
                        $scope.okListpageList = [];
                        $scope.okListselPage = 1;
                        //设置表格数据源(分页)
                        $scope.okListsetData = function () {
                            $scope.okListitems = $scope.okList.slice(($scope.okListpageSize * ($scope.okListselPage - 1)), ($scope.okListselPage * $scope.okListpageSize));//通过当前页数筛选出表格当前显示数据
                        }
                        $scope.okListitems = $scope.okList.slice(0, $scope.okListpageSize);
                        //分页要repeat的数组
                        for (var i = 0; i < $scope.okListnewPages; i++) {
                            $scope.okListpageList.push(i + 1);
                        }
                        //打印当前选中页索引
                        $scope.okListselectPage = function (page) {
                            //不能小于1大于最大
                            if (page < 1 || page > $scope.okListpages) return;
                            //最多显示分页数5
                            if (page > 2) {
                                //因为只显示5个页数，大于2页开始分页转换
                                var newpageList = [];
                                for (var i = (page - 3) ; i < ((page + 2) > $scope.okListpages ? $scope.okListpages : (page + 2)) ; i++) {
                                    newpageList.push(i + 1);
                                }
                                $scope.okListpageList = newpageList;
                            }
                            $scope.okListselPage = page;
                            $scope.okListsetData();
                            $scope.okListisActivePage(page);
                            console.log("选择的页：" + page);
                        };
                        //设置当前选中页样式
                        $scope.okListisActivePage = function (page) {
                            return $scope.okListselPage == page;
                        };
                        //上一页
                        $scope.okListPrevious = function () {
                            $scope.okListselectPage($scope.okListselPage - 1);
                        }
                        //下一页
                        $scope.okListNext = function () {
                            $scope.okListselectPage($scope.okListselPage + 1);
                        };



                        $scope.noListpageSize = 5;
                        // 分页数
                        $scope.noListpages = Math.ceil($scope.noList.length / $scope.noListpageSize);
                        $scope.noListnewPages = $scope.noListpages > 5 ? 5 : $scope.noListpages;
                        $scope.noListpageList = [];
                        $scope.noListselPage = 1;
                        //设置表格数据源(分页)
                        $scope.noListsetData = function () {
                            $scope.noListitems = $scope.noList.slice(($scope.noListpageSize * ($scope.noListselPage - 1)), ($scope.noListselPage * $scope.noListpageSize));//通过当前页数筛选出表格当前显示数据
                        }
                        $scope.noListitems = $scope.noList.slice(0, $scope.noListpageSize);
                        //分页要repeat的数组
                        for (var i = 0; i < $scope.noListnewPages; i++) {
                            $scope.noListpageList.push(i + 1);
                        }
                        //打印当前选中页索引
                        $scope.noListselectPage = function (page) {
                            //不能小于1大于最大
                            if (page < 1 || page > $scope.noListpages) return;
                            //最多显示分页数5
                            if (page > 2) {
                                //因为只显示5个页数，大于2页开始分页转换
                                var newpageList = [];
                                for (var i = (page - 3) ; i < ((page + 2) > $scope.noListpages ? $scope.noListpages : (page + 2)) ; i++) {
                                    newpageList.push(i + 1);
                                }
                                $scope.noListpageList = newpageList;
                            }
                            $scope.noListselPage = page;
                            $scope.noListsetData();
                            $scope.noListisActivePage(page);
                            console.log("选择的页：" + page);
                        };
                        //设置当前选中页样式
                        $scope.noListisActivePage = function (page) {
                            return $scope.noListselPage == page;
                        };
                        //上一页
                        $scope.noListPrevious = function () {
                            $scope.noListselectPage($scope.noListselPage - 1);
                        }
                        //下一页
                        $scope.noListNext = function () {
                            $scope.noListselectPage($scope.noListselPage + 1);
                        };
                    });
                }
                $scope.getWorkOK();
                //$scope.workInfo = {};
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
                        $scope.workRead = response.studentRead;
                        $scope.workNotRead = response.studentNotRead;

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
        .state('studentLesson',{
            url:'/studentLesson',
            templateUrl: '../partials/userLesson.html',
            controller: function ($cookieStore, $scope,$state,Restangular) {
                $scope.lessonInfo=[];
                $scope.lessonSave=[];
                $scope.lessons = function () {
                    Restangular.one("ssl/getAllSsl").get({sno: localStorage.getItem("number")}).then(function (response) {
                        Restangular.one("lesson/allLesson").get().then(function (response) {
                            // 取得所有课程的信息
                            $scope.lessonInfo = response.lessonList;
                            $scope.lessonInfopageSize = 5;
                            // 分页数
                            $scope.lessonInfopages = Math.ceil($scope.lessonInfo.length / $scope.lessonInfopageSize);
                            $scope.lessonInfonewPages = $scope.lessonInfopages > 5 ? 5 : $scope.lessonInfopages;
                            $scope.lessonInfopageList = [];
                            $scope.lessonInfoselPage = 1;
                            //设置表格数据源(分页)
                            $scope.lessonInfosetData = function () {
                                $scope.lessonInfoitems = $scope.lessonInfo.slice(($scope.lessonInfopageSize * ($scope.lessonInfoselPage - 1)), ($scope.lessonInfoselPage * $scope.lessonInfopageSize));//通过当前页数筛选出表格当前显示数据
                            }
                            $scope.lessonInfoitems = $scope.lessonInfo.slice(0, $scope.lessonInfopageSize);
                            //分页要repeat的数组
                            for (var i = 0; i < $scope.lessonInfonewPages; i++) {
                                $scope.lessonInfopageList.push(i + 1);
                            }
                            //打印当前选中页索引
                            $scope.lessonInfoselectPage = function (page) {
                                //不能小于1大于最大
                                if (page < 1 || page > $scope.lessonInfopages) return;
                                //最多显示分页数5
                                if (page > 2) {
                                    //因为只显示5个页数，大于2页开始分页转换
                                    var newpageList = [];
                                    for (var i = (page - 3) ; i < ((page + 2) > $scope.lessonInfopages ? $scope.lessonInfopages : (page + 2)) ; i++) {
                                        newpageList.push(i + 1);
                                    }
                                    $scope.lessonInfopageList = newpageList;
                                }
                                $scope.lessonInfoselPage = page;
                                $scope.lessonInfosetData();
                                $scope.lessonInfoisActivePage(page);
                                console.log("选择的页：" + page);
                            };
                            //设置当前选中页样式
                            $scope.lessonInfoisActivePage = function (page) {
                                return $scope.lessonInfoselPage == page;
                            };
                            //上一页
                            $scope.lessonInfoPrevious = function () {
                                $scope.lessonInfoselectPage($scope.lessonInfoselPage - 1);
                            }
                            //下一页
                            $scope.lessonInfoNext = function () {
                                $scope.lessonInfoselectPage($scope.lessonInfoselPage + 1);
                            };


                            console.log($scope.lessonInfo);
                        });
                        // 取得选生选修课程在信息
                        $scope.lessonSave = response.studentLesson;
                        $scope.lessonSavepageSize = 5;
                        // 分页数
                        $scope.lessonSavepages = Math.ceil($scope.lessonSave.length / $scope.lessonSavepageSize);
                        $scope.lessonSavenewPages = $scope.lessonSavepages > 5 ? 5 : $scope.lessonSavepages;
                        $scope.lessonSavepageList = [];
                        $scope.lessonSaveselPage = 1;
                        //设置表格数据源(分页)
                        $scope.lessonSavesetData = function () {
                            $scope.lessonSaveitems = $scope.lessonSave.slice(($scope.lessonSavepageSize * ($scope.lessonSaveselPage - 1)), ($scope.lessonSaveselPage * $scope.lessonSavepageSize));//通过当前页数筛选出表格当前显示数据
                        }
                        $scope.lessonSaveitems = $scope.lessonSave.slice(0, $scope.lessonSavepageSize);
                        //分页要repeat的数组
                        for (var i = 0; i < $scope.lessonSavenewPages; i++) {
                            $scope.lessonSavepageList.push(i + 1);
                        }
                        //打印当前选中页索引
                        $scope.lessonSaveselectPage = function (page) {
                            //不能小于1大于最大
                            if (page < 1 || page > $scope.lessonSavepages) return;
                            //最多显示分页数5
                            if (page > 2) {
                                //因为只显示5个页数，大于2页开始分页转换
                                var newpageList = [];
                                for (var i = (page - 3) ; i < ((page + 2) > $scope.lessonSavepages ? $scope.lessonSavepages : (page + 2)) ; i++) {
                                    newpageList.push(i + 1);
                                }
                                $scope.lessonSavepageList = newpageList;
                            }
                            $scope.lessonSaveselPage = page;
                            $scope.lessonSavesetData();
                            $scope.lessonSaveisActivePage(page);
                            console.log("选择的页：" + page);
                        };
                        //设置当前选中页样式
                        $scope.lessonSaveisActivePage = function (page) {
                            return $scope.lessonSaveselPage == page;
                        };
                        //上一页
                        $scope.lessonSavePrevious = function () {
                            $scope.lessonSaveselectPage($scope.lessonSaveselPage - 1);
                        }
                        //下一页
                        $scope.lessonSaveNext = function () {
                            $scope.lessonSaveselectPage($scope.lessonSaveselPage + 1);
                        };
                    });
                }
                $scope.lessons();
                $scope.studentSaveLesson =function (lno) {
                    $scope.ssl = {lno:lno,sno:localStorage.getItem("number")};
                    Restangular.one("ssl/saveSsl").customPOST($scope.ssl).then(function (value) {
                        if(value == true) {
                            swal("选课成功");
                            $scope.lessons();
                        } else {
                            swal("已经选过该课程");
                        }
                        $scope.lessons();
                    }, function (err) {
                        swal("选课失败");
                        console.warn(err);
                    });
                }
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