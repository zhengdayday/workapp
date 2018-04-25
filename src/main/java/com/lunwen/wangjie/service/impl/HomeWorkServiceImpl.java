package com.lunwen.wangjie.service.impl;

import com.lunwen.wangjie.dao.HomeWorkDao;
import com.lunwen.wangjie.dao.StudentSelectLessonDao;
import com.lunwen.wangjie.dao.StudentWorkDao;
import com.lunwen.wangjie.model.HomeWork;
import com.lunwen.wangjie.model.StudentSelectLesson;
import com.lunwen.wangjie.model.StudentWork;
import com.lunwen.wangjie.service.HomeWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IDEA
 * author: wangjie
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
    public List<HomeWork> getAllHomeWork(Long lno) {
        return homeWorkDao.findAllByLno(lno);
    }

    @Override
    @Transactional
    public boolean saveHomeWork(HomeWork homeWork) {
        HomeWork findWork = homeWorkDao.findHomeWorkByTnoAndWorkName(homeWork.getTno(), homeWork.getWorkName());
        if(findWork == null) {
            HomeWork homeWork1 = homeWorkDao.save(homeWork);
            List<StudentSelectLesson> studentSelectLessonList = studentSelectLessonDao.findAllByLno(homeWork.getLno());
            for (StudentSelectLesson studentSelectLesson : studentSelectLessonList) {
                if(studentSelectLesson == null) {
                    continue;
                }
                StudentWork sw = studentWorkDao.findStudentWorkBySnoAndWno(studentSelectLesson.getSno(), homeWork.getWno());
                if (sw != null) {
                    continue;
                }
                StudentWork studentWork = new StudentWork(studentSelectLesson.getSno(), homeWork1.getWno(), 0, 0);
                studentWorkDao.save(studentWork);
            }
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
