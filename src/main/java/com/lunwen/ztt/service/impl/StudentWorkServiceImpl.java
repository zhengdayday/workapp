package com.lunwen.ztt.service.impl;

import com.lunwen.ztt.dao.HomeWorkDao;
import com.lunwen.ztt.dao.LessonDao;
import com.lunwen.ztt.dao.StudentWorkDao;
import com.lunwen.ztt.dao.UserDao;
import com.lunwen.ztt.model.HomeWork;
import com.lunwen.ztt.model.Lesson;
import com.lunwen.ztt.model.StudentWork;
import com.lunwen.ztt.model.User;
import com.lunwen.ztt.service.StudentWorkService;
import com.lunwen.ztt.view.ReadProblem;
import com.lunwen.ztt.view.WorkView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author: wangjie
 * Date: 18-4-19
 * Time: 下午7:51
 */
@Service
public class StudentWorkServiceImpl implements StudentWorkService {


    @Autowired
    private StudentWorkDao studentWorkDao;

    @Autowired
    private HomeWorkDao homeWorkDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private LessonDao lessonDao;

    @Override
    @Transactional
    public Map<String, Object> getListStudentWork(Long wno) {
        List<StudentWork> studentWorks = studentWorkDao.findAllByWno(wno);
        Map<String, Object> map = new HashMap<>();
        List<WorkView> listRead = new ArrayList<>();
        List<WorkView> listNotRead = new ArrayList<>();
        for (StudentWork studentWork : studentWorks) {
            // 已经修改了
            if(studentWork.getGrade() != null) {
                HomeWork homeWork = homeWorkDao.findHomeWorkByWno(studentWork.getWno());
                Lesson lesson = lessonDao.findLessonByLno(homeWork.getLno());
                User user = userDao.findUserByNumber(studentWork.getSno());
                WorkView view = new WorkView(homeWork.getWorkName(), homeWork.getDesc(), user.getName(), studentWork.getGrade(), lesson.getLessonName(), studentWork.getSno(), studentWork.getWno());
                listRead.add(view);
            } else {
                HomeWork homeWork = homeWorkDao.findHomeWorkByWno(studentWork.getWno());
                User user = userDao.findUserByNumber(studentWork.getSno());
                Lesson lesson = lessonDao.findLessonByLno(homeWork.getLno());
                WorkView view = new WorkView(homeWork.getWorkName(), homeWork.getDesc(), user.getName(), lesson.getLessonName(), studentWork.getSno(), studentWork.getWno());
                listNotRead.add(view);
            }
        }
        map.put("studentRead", listRead);
        map.put("studentNotRead", listNotRead);
        return map;
    }

    @Override
    public boolean saveWork(StudentWork studentWork) {
        return false;
    }

    @Override
    public boolean deleteWork(StudentWork studentWork) {
        return false;
    }

    @Override
    @Transactional
    public boolean saveAnswer(StudentWork studentWork) {
        StudentWork sw = studentWorkDao.findStudentWorkBySnoAndWno(studentWork.getSno(), studentWork.getWno());
        if (sw.getWork_status() == 1) return false;
        sw.setAnswer(studentWork.getAnswer());
        sw.setWork_status(1);
        studentWorkDao.save(sw);
        return true;
    }

    @Override
    @Transactional
    public boolean saveGrade(StudentWork studentWork) {
        StudentWork sw = studentWorkDao.findStudentWorkBySnoAndWno(studentWork.getSno(), studentWork.getWno());
        if (sw.getGrade() != null) return false;
        sw.setGrade(studentWork.getGrade());
        sw.setWorkRead(1);
        studentWorkDao.save(sw);
        return true;
    }

    @Override
    @Transactional
    public ReadProblem getProblem(StudentWork studentWork) {
        StudentWork sw = studentWorkDao.findStudentWorkBySnoAndWno(studentWork.getSno(), studentWork.getWno());
        HomeWork homeWork = homeWorkDao.findHomeWorkByWno(sw.getWno());
        User user = userDao.findUserByNumber(sw.getSno());
        ReadProblem readProblem = new ReadProblem(homeWork.getWorkName(), homeWork.getWorkInfo(), homeWork.getDesc(), sw.getAnswer(), user.getName());
        return readProblem;
    }

}
