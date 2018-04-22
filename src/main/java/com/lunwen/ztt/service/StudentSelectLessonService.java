package com.lunwen.ztt.service;

import com.lunwen.ztt.model.Lesson;
import com.lunwen.ztt.model.StudentSelectLesson;
import com.lunwen.ztt.view.StudentLesson;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author: wangjie
 * Date: 18-4-19
 * Time: 下午8:47
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
