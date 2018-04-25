package com.lunwen.wangjie.service;


import com.lunwen.wangjie.model.User;

import java.util.List;

/**
 * 用户service
 */

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
     * save teacher
     * @param user uer
     * @return boolean
     */
    public boolean saveTeacher(User user);

    /**
     * login
     * @param user user
     * @return user
     */
    public User login(User user);


    /**
     * get all user
     * @return list user
     */
    public List<User> getAllUser();
}
