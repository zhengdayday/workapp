package com.lunwen.wangjie.controller;

import com.lunwen.wangjie.model.HomeWork;
import com.lunwen.wangjie.service.HomeWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author: wangjie
 */

@RestController
@RequestMapping(value = "/homework")
public class HomeWorkController {

    @Autowired
    private HomeWorkService homeWorkService;

    /**
     * 得到课程在所有work
     * @param lno 工号
     * @return map
     */
    @RequestMapping(value = "/getAllWork", method = RequestMethod.GET)
    public Map<String, Object> getAllWork(Long lno) {
        List<HomeWork> list = homeWorkService.getAllHomeWork(lno);
        Map<String, Object> map = new HashMap<>();
        map.put("workList", list);
        return map;
    }

    /**
     * 新建作业
     * @param homeWork 作业
     * @return boolean
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public boolean saveHomeWork(@RequestBody HomeWork homeWork) {
        return homeWorkService.saveHomeWork(homeWork);
    }

    /**
     * 通过作业号查找作业
     * @param wno 作业号
     * @return HomeWork
     */
    @RequestMapping(value = "/getWorkInfo", method = RequestMethod.GET)
    public HomeWork getHomeWorkInfo(Long wno) {
        return homeWorkService.getHomeWork(wno);
    }
}
