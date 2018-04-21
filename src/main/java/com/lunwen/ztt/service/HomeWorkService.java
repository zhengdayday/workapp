package com.lunwen.ztt.service;

import com.lunwen.ztt.model.HomeWork;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 教师管理课程作业service
 * Created with IDEA
 * author: wangjie
 * Date: 18-4-19
 * Time: 下午8:03
 */
public interface HomeWorkService {

    /**
     * 获得所有work
     * @param tno 工号
     * @return list home work
     */
    public List<HomeWork> getAllHomeWork(String tno);

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