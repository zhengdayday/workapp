package com.lunwen.ztt.service.impl;

import com.lunwen.ztt.dao.StudentWorkDao;
import com.lunwen.ztt.model.StudentWork;
import com.lunwen.ztt.service.StudentWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IDEA
 * author: wangjie
 * Date: 18-4-19
 * Time: 下午7:51
 */
@Service
public class StudentWorkServiceImpl implements StudentWorkService {


    @Autowired
    private StudentWorkDao studentWorkDao;

    @Override
    public List<StudentWork> getAllWork() {
        return studentWorkDao.findAll();
    }

    @Override
    public boolean saveWork(StudentWork studentWork) {
        return false;
    }

    @Override
    public boolean deleteWork(StudentWork studentWork) {
        return false;
    }
}
