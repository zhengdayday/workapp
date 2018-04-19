package com.lunwen.ztt.service;

import com.lunwen.ztt.model.StudentWork;

import java.util.List;

/**
 * 学生作业情况service
 * Created with IDEA
 * author: daydayofzheng
 * Date: 18-4-19
 * Time: 下午7:37
 */
public interface StudentWorkService {

    /**
     *
     * @return all Work
     */
    public List<StudentWork> getAllWork();

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
