package com.lunwen.ztt.service;


import com.lunwen.ztt.model.User;

public interface UserService {

    /**
     * get user
     * @param userName
     * @return
     */

    public User getUserByName(String userName);
}

