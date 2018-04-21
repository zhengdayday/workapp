package com.lunwen.ztt.service.impl;

import com.lunwen.ztt.dao.HomeWorkDao;
import com.lunwen.ztt.dao.StudentSelectLessonDao;
import com.lunwen.ztt.dao.StudentWorkDao;
import com.lunwen.ztt.model.HomeWork;
import com.lunwen.ztt.model.StudentSelectLesson;
import com.lunwen.ztt.model.StudentWork;
import com.lunwen.ztt.service.HomeWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IDEA
 * author: wangjie
 * Date: 18-4-19
 * Time: 下午8:06
 */

@Service
public class HomeWorkServiceImpl implements HomeWorkService{

    @Autowired
    private HomeWorkDao homeWorkDao;

    @Autowired
    private StudentSelectLessonDao studentSelectLessonDao;

    @Autowired
    private StudentWorkDao studentWorkDao;

    @Override
    public List<HomeWork> getAllHomeWork(String tno) {
        return homeWorkDao.findAllByTno(tno);
    }

    @Override
    @Transactional
    public boolean saveHomeWork(HomeWork homeWork) {
        HomeWork findWork = homeWorkDao.findHomeWorkByTnoAndWorkName(homeWork.getTno(), homeWork.getWorkName());
        if(findWork == null) {
            homeWorkDao.save(homeWork);
            StudentSelectLesson studentSelectLesson = studentSelectLessonDao.findStudentSelectLessonByLno(homeWork.getLno());
            if(studentSelectLesson == null) {
                return true;
            }
            StudentWork studentWork = new StudentWork(studentSelectLesson.getSno(), studentSelectLesson.getLno(), 0, 0);
            studentWorkDao.save(studentWork);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean delHomeWork(HomeWork homeWork) {
        homeWorkDao.delete(homeWork);
        return true;
    }

    @Override
    public HomeWork getHomeWork(Long wno) {
        return homeWorkDao.findHomeWorkByWno(wno);
    }
}
