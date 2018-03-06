package com.lunwen.ztt.service.impl;

import com.lunwen.ztt.dao.UserDao;
import com.lunwen.ztt.model.User;
import com.lunwen.ztt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByName(String userName) {
        return userDao.findUser(userName);
    }

    @Transactional
    @Override
    public boolean saveUser(User user) {
        User findUser = userDao.findUserByEmail(user.getEmail());
        if(findUser == null) {
            userDao.save(user);
            return true;
        } else {
            return false;
        }
    }
}
