package com.crystal.learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>CustomController必须在FirstApplication主程序同级目录或子级目录下，才能受Spring内容管理</p>
 * @author Crystal
 * @since 1.0.0
 */
@RestController
public class CustomController {

    // 从Spring的IOC容器中查找Date对象，注入到这里
    @Autowired
    private Date date;

    @GetMapping("/index")
    public String run() {
        return "My Controller have run";
    }

    @GetMapping("/date")
    public String getDate() {
        return date.toString();
    }
}
