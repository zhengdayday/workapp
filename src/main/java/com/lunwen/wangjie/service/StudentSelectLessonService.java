package com.lunwen.wangjie.service;

import com.lunwen.wangjie.model.Lesson;
import com.lunwen.wangjie.model.StudentSelectLesson;
import com.lunwen.wangjie.view.StudentLesson;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author: wangjie
 */
public interface StudentSelectLessonService {

    /**
     * 获得学生所有在选课情况
     * @param sno sno
     * @return list
     */
    public List<StudentLesson> getAllSsl(String sno);

    /**
     * 保存课程
     * @param studentSelectLesson ssl
     * @return boolean
     */
    public boolean saveSsl(StudentSelectLesson studentSelectLesson);


    public Map<String, Object> getStudentWorks(StudentSelectLesson ssl);
}
