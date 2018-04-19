package com.lunwen.ztt.dao;

import com.lunwen.ztt.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created with IDEA
 * author: daydayofzheng
 * Date: 18-4-18
 * Time: 下午6:47
 */
public interface LessonDao extends JpaRepository<Lesson, Long>{

    /**
     * 通过课程名 查找lesson
     * @param lessonName 课程名
     * @param tno 教师工号
     * @return
     */
    @Query("from Lesson l where l.lessonName=:lessonName and l.tno:=tno")
    Lesson findLessonByLessonNameAndTno(String lessonName, String tno);
}
