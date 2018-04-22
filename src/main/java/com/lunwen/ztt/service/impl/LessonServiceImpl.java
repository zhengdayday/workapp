package com.lunwen.ztt.service.impl;

import com.lunwen.ztt.dao.LessonDao;
import com.lunwen.ztt.dao.UserDao;
import com.lunwen.ztt.model.Lesson;
import com.lunwen.ztt.model.User;
import com.lunwen.ztt.service.LessonService;
import com.lunwen.ztt.view.LessonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IDEA
 * author: wangjie
 */

@Service
public class LessonServiceImpl implements LessonService {

    /** 课程数据访问对象 */
    @Autowired
    private LessonDao lessonDao;

    /** 用户数据访问对象 */
    @Autowired
    private UserDao userDao;


    @Override
    @Transactional
    public List<LessonView> getAllLesson() {
        List<Lesson> list = lessonDao.findAll();
        List<LessonView> lessonViews = new ArrayList<>();
        for (Lesson lesson : list) {
            User user = userDao.findUserByNumber(lesson.getTno());
            LessonView lessonView = new LessonView(lesson.getLno(), lesson.getLessonName(), lesson.getDesc(), user.getName());
            lessonViews.add(lessonView);
        }
        return lessonViews;
    }

    @Override
    @Transactional
    public boolean saveLesson(Lesson lesson) {
        Lesson findLesson = lessonDao.findLessonByLessonNameAndTno(lesson.getLessonName(), lesson.getTno());
        if(findLesson == null) {
            lessonDao.save(lesson);
            return true;
        }
        return false;
    }


    @Override
    @Transactional
    public boolean deleteLesson(Lesson lesson) {
        lessonDao.delete(lesson);
        return true;
    }

    @Override
    @Transactional
    public List<LessonView> getTeacherLesson(String tno) {
        //查找教师的所有课程
        List<Lesson> list = lessonDao.findAllByTno(tno);
        List<LessonView> viewList = new ArrayList<>();
        User user = userDao.findUserByNumber(tno);
        for (Lesson lesson : list) {
            LessonView view = new LessonView(lesson.getLno(), lesson.getLessonName(), lesson.getDesc(), user.getName());
            viewList.add(view);
        }
        return viewList;
    }

    @Override
    @Transactional
    public Lesson getLessonByLno(Long lno) {
        return lessonDao.findLessonByLno(lno);
    }
}
