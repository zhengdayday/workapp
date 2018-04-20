package com.lunwen.ztt.service;

import com.lunwen.ztt.model.Lesson;
import com.lunwen.ztt.view.LessonView;

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
     * @return list
     */
    public List<Lesson> getAllLesson();

    /**
     * 保存课程
     * @param lesson 课程
     * @return boolean
     */
    public boolean saveLesson(Lesson lesson);

    /**
     * 删除课程
     * @param lesson 课程
     * @return boolean
     */
    public boolean deleteLesson(Lesson lesson);

    /**
     * 查找教师的所有课程
     * @param tno 教师工号
     * @return list
     */
    public List<LessonView> getTeacherLesson(String tno);

}
