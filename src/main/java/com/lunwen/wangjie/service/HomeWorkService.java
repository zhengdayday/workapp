package com.lunwen.wangjie.service;

import com.lunwen.wangjie.model.HomeWork;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 教师管理课程作业service
 * Created with IDEA
 * author: wangjie
 */
public interface HomeWorkService {

    /**
     * 获得所有work
     * @param lno 课程号
     * @return list home work
     */
    public List<HomeWork> getAllHomeWork(Long lno);

    /**
     * 新建作业
     * @param homeWork homework
     * @return boolean
     */
    public boolean saveHomeWork(HomeWork homeWork);

    /**
     * del hoemwork
     * @param homeWork work
     * @return boolean
     */
    public boolean delHomeWork(HomeWork homeWork);

    /**
     * 查找作业
     * @param wno 作业号
     * @return HomeWork
     */
    public HomeWork getHomeWork(Long wno);
}
