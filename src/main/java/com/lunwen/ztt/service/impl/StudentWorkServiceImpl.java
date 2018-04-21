package com.lunwen.ztt.service.impl;

import com.lunwen.ztt.dao.HomeWorkDao;
import com.lunwen.ztt.dao.StudentWorkDao;
import com.lunwen.ztt.dao.UserDao;
import com.lunwen.ztt.model.HomeWork;
import com.lunwen.ztt.model.StudentWork;
import com.lunwen.ztt.model.User;
import com.lunwen.ztt.service.StudentWorkService;
import com.lunwen.ztt.view.WorkView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private HomeWorkDao homeWorkDao;

    @Autowired
    private UserDao userDao;


    @Override
    public Map<String, Object> getListStudentWork(Long wno) {
        List<StudentWork> studentWorks = studentWorkDao.findAllByWno(wno);
        Map<String, Object> map = new HashMap<>();
        List<WorkView> listRead = new ArrayList<>();
        List<WorkView> listNotRead = new ArrayList<>();
        for (StudentWork studentWork : studentWorks) {
            // 已经修改了
            if(studentWork.getWorkRead() == 1) {
                HomeWork homeWork = homeWorkDao.findHomeWorkByWno(studentWork.getWno());
                User user = userDao.findUserByNumber(studentWork.getSno());
                WorkView view = new WorkView(homeWork.getWorkName(), homeWork.getDesc(), user.getName(), studentWork.getGrade());
                listRead.add(view);
            } else {
                HomeWork homeWork = homeWorkDao.findHomeWorkByWno(studentWork.getWno());
                User user = userDao.findUserByNumber(studentWork.getSno());
                WorkView view = new WorkView(homeWork.getWorkName(), homeWork.getDesc(), user.getName());
                listRead.add(view);
                listNotRead.add(view);
            }
        }
        map.put("studentRead", listRead);
        map.put("studentNotRead", listNotRead);
        return map;
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
