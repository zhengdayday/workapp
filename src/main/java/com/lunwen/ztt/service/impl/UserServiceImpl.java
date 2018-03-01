package com.lunwen.ztt.service.impl;

import com.lunwen.ztt.dao.UserDao;
import com.lunwen.ztt.model.User;
import com.lunwen.ztt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    /**
     *
     * @param userName
     * @return
     */

    @Override
    public User getUserByName(String userName) {
        return userDao.findUser(userName);
    }
}
