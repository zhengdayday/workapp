package com.lunwen.wangjie.service.impl;

import com.lunwen.wangjie.dao.UserDao;
import com.lunwen.wangjie.model.User;
import com.lunwen.wangjie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public User getUserByName(String userName) {
        return userDao.findUser(userName);
    }

    @Transactional
    @Override
    public boolean saveUser(User user) {
        User findUserByEmail = userDao.findUserByEmail(user.getEmail());
        User findUserByNumber = userDao.findUserByNumber(user.getNumber());
        if(findUserByEmail == null && findUserByNumber == null) {
            // 只能学生注册
            user.setLevel(0);
            userDao.save(user);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean saveTeacher(User user) {
        User findUserByEmail = userDao.findUserByEmail(user.getEmail());
        User findUserByNumber = userDao.findUserByNumber(user.getNumber());
        if(findUserByEmail == null && findUserByNumber == null) {
            // 只能教师注册
            user.setLevel(1);
            userDao.save(user);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public User login(User user) {
        User findUser = userDao.findUserByEmail(user.getEmail());
        if(findUser != null && findUser.getPassword().equals(user.getPassword())) {
            return findUser;
        } else {
            return null;
        }
    }

    @Override
    public List<User> getAllUser() {
        return userDao.findAll();
    }
}
