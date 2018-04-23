package com.lunwen.wangjie.dao;

import com.lunwen.wangjie.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<User, Long>{

    /**
     * 通过名字 查找用户
     * @param name 姓名
     * @return User
     */
    @Query("from User u where u.name=:name")
    User findUser(@Param("name") String name);

    /**
     * 通过email查找用户
     * @param email
     * @return
     */
    @Query("from User u where u.email=:email")
    User findUserByEmail(@Param("email") String email);

    /**
     * 通过学号/工号查找用户
     * @param number number
     * @return User
     */
    @Query("from User u where u.number=:number")
    User findUserByNumber(@Param("number") String number);

    //User findUserByNumber(String numer);
}
