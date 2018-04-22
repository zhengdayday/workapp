package com.lunwen.ztt.controller;

import com.lunwen.ztt.model.StudentWork;
import com.lunwen.ztt.service.StudentWorkService;
import com.lunwen.ztt.view.ReadProblem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IDEA
 * author: wangjie
 */
@RestController
@RequestMapping(value = "/studentWork")
public class StudentWorkController {

    /** 学生作业数据访问对象 */
    @Autowired
    private StudentWorkService studentWorkService;

    /**
     * get work
     * @param wno work no
     * @return map
     */
    @RequestMapping(value = "/getWork", method = RequestMethod.GET)
    public Map<String, Object> getWork(Long wno) {
        return studentWorkService.getListStudentWork(wno);
    }


    /**
     * save anser
     * @param studentWork sw
     * @return boolean
     */
    @RequestMapping(value = "/saveAnswer", method = RequestMethod.POST)
    public boolean saveAnswer(@RequestBody StudentWork studentWork) {
        return studentWorkService.saveAnswer(studentWork);
    }

    /**
     * save grade
     * @param studentWork sw
     * @return boolean
     */
    @RequestMapping(value = "/saveGrade", method = RequestMethod.POST)
    public boolean saveGrade(@RequestBody StudentWork studentWork) {
        return studentWorkService.saveGrade(studentWork);
    }

    /**
     * get problem
     * @param sno sno
     * @param wno wno
     * @return rp
     */
    @RequestMapping(value = "/problem" , method = RequestMethod.GET)
    public ReadProblem getProblem(String sno, Long wno) {
        StudentWork studentWork  = new StudentWork();
        studentWork.setSno(sno);
        studentWork.setWno(wno);
        return studentWorkService.getProblem(studentWork);
    }
}
