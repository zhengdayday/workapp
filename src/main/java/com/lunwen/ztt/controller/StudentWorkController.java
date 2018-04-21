package com.lunwen.ztt.controller;

import com.lunwen.ztt.service.StudentWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IDEA
 * author: daydayofzheng
 * Date: 18-4-21
 * Time: 下午12:36
 */
@RestController
@RequestMapping(value = "/studentWork")
public class StudentWorkController {

    /** 学生作业数据访问对象 */
    @Autowired
    private StudentWorkService studentWorkService;

    @RequestMapping(value = "/getWork", method = RequestMethod.GET)
    public Map<String, Object> getWork(Long wno) {
        return studentWorkService.getListStudentWork(wno);
    }
}
