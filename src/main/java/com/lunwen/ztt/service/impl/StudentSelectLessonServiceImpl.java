package com.lunwen.ztt.service.impl;

import com.lunwen.ztt.dao.*;
import com.lunwen.ztt.model.*;
import com.lunwen.ztt.service.StudentSelectLessonService;
import com.lunwen.ztt.view.StudentLesson;
import com.lunwen.ztt.view.StudentWorkView;
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
 */

@Service
public class StudentSelectLessonServiceImpl implements StudentSelectLessonService{


    @Autowired
    private StudentSelectLessonDao studentSelectLessonDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private LessonDao lessonDao;

    @Autowired
    private HomeWorkDao homeWorkDao;

    @Autowired
    private StudentWorkDao studentWorkDao;

    @Override
    @Transactional
    public List<StudentLesson> getAllSsl(String sno) {
        // 通过学号获取所有学生选了的课程
        List<StudentSelectLesson> list = studentSelectLessonDao.findAllBySno(sno);
        List<StudentLesson> viewList = new ArrayList<>();
        for (StudentSelectLesson studentSelectLesson : list) {
            // 通过课程号获取课程信息
            Lesson lesson = lessonDao.findLessonByLno(studentSelectLesson.getLno());
            // 通过教师号获取教师信息
            User user = userDao.findUserByNumber(lesson.getTno());
            StudentLesson studentLesson = new StudentLesson(user.getName(), lesson.getLessonName(), lesson.getDesc(), lesson.getLno());
            viewList.add(studentLesson);
        }
        return viewList;
    }

    @Override
    @Transactional
    public boolean saveSsl(StudentSelectLesson studentSelectLesson) {
        // 判断是否已经选修
        StudentSelectLesson findssl = studentSelectLessonDao.findStudentSelectLessonBySnoAndAndLno(studentSelectLesson.getSno(), studentSelectLesson.getLno());
        if (findssl == null) {
            // 需要关联课程的作业
            studentSelectLessonDao.save(studentSelectLesson);
            // 找出该课程在所有作业列表
            List<HomeWork> list = homeWorkDao.findAllByLno(studentSelectLesson.getLno());
            if(list == null) return true;
            for (HomeWork homeWork : list) {

                StudentWork sw = studentWorkDao.findStudentWorkBySnoAndWno(studentSelectLesson.getSno(), homeWork.getWno());
                if(sw != null) {
                    continue;
                }
                StudentWork studentWork = new StudentWork(studentSelectLesson.getSno(), homeWork.getWno(), 0, 0);
                studentWorkDao.save(studentWork);
            }
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Map<String, Object> getStudentWorks(StudentSelectLesson ssl) {
        List<StudentWork> sworkList = studentWorkDao.findAllBySno(ssl.getSno());
        if(sworkList == null) return null;
        List<StudentWorkView> viewListok = new ArrayList<>();
        List<StudentWorkView> viewListno = new ArrayList<>();
        Lesson lesson = lessonDao.findLessonByLno(ssl.getLno());
        if(lesson == null) return null;
        for(StudentWork studentWork : sworkList) {
            HomeWork homeWork = homeWorkDao.findHomeWorkByWno(studentWork.getWno());
            //如果不存在课
            if(homeWork.getLno() != ssl.getLno()) {
                continue;
            }
            if(studentWork.getGrade() != null) {
                StudentWorkView studentWorkView = new StudentWorkView(homeWork.getWorkName(), homeWork.getDesc(), "已做", studentWork.getGrade(), lesson.getLessonName(), homeWork.getWno());
                viewListok.add(studentWorkView);
            } else {
                if(studentWork.getWork_status() == 0) {
                    StudentWorkView studentWorkView = new StudentWorkView(homeWork.getWorkName(), homeWork.getDesc(), "未做", lesson.getLessonName(), homeWork.getWno());
                    viewListno.add(studentWorkView);
                } else {
                    StudentWorkView studentWorkView = new StudentWorkView(homeWork.getWorkName(), homeWork.getDesc(), "已做", lesson.getLessonName(), homeWork.getWno());
                    viewListno.add(studentWorkView);
                }
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("ok", viewListok);
        map.put("no", viewListno);
        return map;
    }


}
