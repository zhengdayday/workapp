package com.lunwen.ztt.controller;

import com.lunwen.ztt.Utils.JwtToken;
import com.lunwen.ztt.model.User;
import com.lunwen.ztt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 通过名字获取User
     * @param userName
     * @return User
     */
    @RequestMapping(value = "/byname",method = RequestMethod.GET)
    public User getUser(String userName) {
        return userService.getUserByName(userName);
    }

    /**
     * 注册
     * @param user
     * @return boolean
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public boolean saveUser(@RequestBody User user){
        if(userService.saveUser(user)) {
            return false;
        } else {
            //邮箱已经被使用
            return true;
        }
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Map<String, String> login(@RequestBody User user) {
        Map<String, String> map = new HashMap<>();
        String token = null;
        if(userService.login(user)) {
            try {
                token = JwtToken.createToken();
                map.put("token", token);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            map.put("false", "false");
        }
        return map;
    }
}
