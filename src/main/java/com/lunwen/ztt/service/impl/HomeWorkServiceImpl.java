package com.lunwen.ztt.service.impl;

import com.lunwen.ztt.dao.HomeWorkDao;
import com.lunwen.ztt.model.HomeWork;
import com.lunwen.ztt.service.HomeWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IDEA
 * author: daydayofzheng
 * Date: 18-4-19
 * Time: 下午8:06
 */

@Service
public class HomeWorkServiceImpl implements HomeWorkService{

    @Autowired
    private HomeWorkDao homeWorkDao;

    @Override
    public List<HomeWork> getAllHomeWork() {
        return homeWorkDao.findAll();
    }

    @Override
    @Transactional
    public boolean saveHomeWork(HomeWork homeWork) {
        homeWorkDao.save(homeWork);
        return true;
    }

    @Override
    @Transactional
    public boolean delHomeWork(HomeWork homeWork) {
        homeWorkDao.delete(homeWork);
        return true;
    }
}
