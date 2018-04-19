package com.lunwen.ztt.service.impl;

import com.lunwen.ztt.dao.LessonDao;
import com.lunwen.ztt.model.Lesson;
import com.lunwen.ztt.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IDEA
 * author: daydayofzheng
 * Date: 18-4-19
 * Time: 下午7:25
 */

@Service
public class LessonServiceImpl implements LessonService {

    /** 课程数据访问对象 */
    @Autowired
    private LessonDao lessonDao;

    @Override
    @Transactional
    public List<Lesson> getAllLesson() {
        return lessonDao.findAll();
    }

    @Override
    @Transactional
    public boolean saveLesson(String lessonName, String tno) {
        Lesson findLesson = lessonDao.findLessonByLessonNameAndTno(lessonName, tno);
        if(findLesson != null) {
            Lesson lesson = new Lesson();
            lesson.setLessonName(lessonName);
            lesson.setTno(tno);
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
}
