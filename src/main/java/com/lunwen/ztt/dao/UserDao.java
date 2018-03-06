package com.lunwen.ztt.dao;

import com.lunwen.ztt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<User, Long>{

    @Query("from User u where u.name=:name")
    User findUser(@Param("name") String name);

    @Query("from User u where u.email=:email")
    User findUserByEmail(@Param("email") String email);
}
