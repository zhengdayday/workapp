package com.lunwen.ztt.service;


import com.lunwen.ztt.model.User;

public interface UserService {

    /**
     * get user
     * @param userName name
     * @return
     */

    public User getUserByName(String userName);

    /**
     * save user
     * @param user uer
     * @return boolean
     */
    public boolean saveUser(User user);

    /**
     * login
     * @param user user
     * @return user
     */
    public User login(User user);
}

