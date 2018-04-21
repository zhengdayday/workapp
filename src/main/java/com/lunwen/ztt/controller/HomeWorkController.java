package com.lunwen.ztt.controller;

import com.lunwen.ztt.model.HomeWork;
import com.lunwen.ztt.service.HomeWorkService;
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
 * Date: 18-4-20
 * Time: 下午5:09
 */

@RestController
@RequestMapping(value = "/homework")
public class HomeWorkController {

    @Autowired
    private HomeWorkService homeWorkService;

    /**
     * 得到教师在所有work
     * @param tno 工号
     * @return map
     */
    @RequestMapping(value = "/getAllWork", method = RequestMethod.GET)
    public Map<String, Object> getAllWork(String tno) {
        List<HomeWork> list = homeWorkService.getAllHomeWork(tno);
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
