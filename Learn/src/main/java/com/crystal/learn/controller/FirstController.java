package com.crystal.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName: FirstController
 * Description:
 * Datetime: 2025/5/14 21:29
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
@Controller
public class FirstController {

    /* 请求映射：返回逻辑视图名称 */
    @RequestMapping("/test")
    public String method() {
        return "FirstController";
    }
}
