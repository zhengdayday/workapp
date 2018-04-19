package com.lunwen.ztt.service;

import com.lunwen.ztt.model.Lesson;

import java.util.List;

/**
 *  教师管理课程service
 * Created with IDEA
 * author: daydayofzheng
 * Date: 18-4-19
 * Time: 下午7:22
 */
public interface LessonService {

    /**
     * 获得所有的课程
     * @return
     */
    public List<Lesson> getAllLesson();

    /**
     * 保存课程
     * @param lessonName 课程名
     * @param tno 老师工号
     * @return
     */
    public boolean saveLesson(String lessonName, String tno);

    /**
     * 删除课程
     * @param lesson
     * @return
     */
    public boolean deleteLesson(Lesson lesson);


}
