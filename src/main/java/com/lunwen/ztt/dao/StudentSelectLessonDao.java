package com.lunwen.ztt.dao;

import com.lunwen.ztt.model.StudentSelectLesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IDEA
 * author: wangjie
 * Date: 18-4-18
 * Time: 下午6:49
 */
public interface StudentSelectLessonDao extends JpaRepository<StudentSelectLesson, Long> {

    /**
     * 获取学生的所有课程
     * @param sno sno
     * @return list
     */
   List<StudentSelectLesson> findAllBySno(String sno);


    /**
     * 判断学生是否应选课
     * @param sno sno
     * @param lno lno
     * @return boolean
     */
   StudentSelectLesson findStudentSelectLessonBySnoAndAndLno(String sno, Long lno);

    /**
     * 查找课程
     * @param lno lno
     * @return ssl
     */
   StudentSelectLesson findStudentSelectLessonByLno(Long lno);

}