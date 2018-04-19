package com.lunwen.ztt.service.impl;

import com.lunwen.ztt.dao.StudentSelectLessonDao;
import com.lunwen.ztt.model.StudentSelectLesson;
import com.lunwen.ztt.service.StudentSelectLessonService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with IDEA
 * author: daydayofzheng
 * Date: 18-4-19
 * Time: 下午8:48
 */
public class StudentSelectLessonServiceImpl implements StudentSelectLessonService{


    @Autowired
    private StudentSelectLessonDao studentSelectLessonDao;

    @Override
    public List<StudentSelectLesson> getAllSsl() {
        return studentSelectLessonDao.findAll();
    }
}
