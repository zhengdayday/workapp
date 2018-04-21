package com.lunwen.ztt.service;

import com.lunwen.ztt.model.StudentWork;

import java.util.List;
import java.util.Map;

/**
 * 学生作业情况service
 * Created with IDEA
 * author: wangjie
 * Date: 18-4-19
 * Time: 下午7:37
 */
public interface StudentWorkService {

    /** 查找作业
     * @param wno 作业号
     * @return all Work
     */
    public Map<String, Object> getListStudentWork(Long wno);

    /**
     * save work
     * @param studentWork work
     * @return boolean
     */
    public boolean saveWork(StudentWork studentWork);

    /**
     * del work
     * @param studentWork work
     * @return boolean
     */
    public boolean deleteWork(StudentWork studentWork);

}