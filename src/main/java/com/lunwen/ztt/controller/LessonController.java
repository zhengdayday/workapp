package com.lunwen.ztt.controller;

import com.lunwen.ztt.dao.LessonDao;
import com.lunwen.ztt.model.Lesson;
import com.lunwen.ztt.service.LessonService;
import com.lunwen.ztt.view.LessonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author: wangjie
 * Date: 18-4-20
 * Time: 上午9:31
 */

@RestController
@RequestMapping(value = "/lesson")
public class LessonController {


    @Autowired
    private LessonService lessonService;


    /**
     * 获得所有课程
     * @return map
     */
    @RequestMapping(value = "/allLesson", method = RequestMethod.GET)
    public Map<String, Object> getAllLesson() {
        List<LessonView> list =  lessonService.getAllLesson();
        Map<String, Object> map = new HashMap<>();
        map.put("lessonList", list);
        return map;
    }

    /**
     *
     * @param tno tno
     * @return map
     */
    @RequestMapping(value = "/teacherLesson", method = RequestMethod.GET)
    public Map<String, Object> getTeacherLesson(String tno) {
        List<LessonView> list = lessonService.getTeacherLesson(tno);
        Map<String, Object> map = new HashMap<>();
        map.put("tnoLesson", list);
        return map;
    }

    /**
     *
     * @param lesson 需要保存的课程
     * @return boolean
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public boolean saveLesson(@RequestBody Lesson lesson) {
      return   lessonService.saveLesson(lesson);
    }
}
