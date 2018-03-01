package com.lunwen.ztt.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class zttController {
    @RequestMapping(value = "/hello")
    public String ztt() {
        return "ztt";
    }

}
