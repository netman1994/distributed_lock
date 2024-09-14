package org.ywk.controller;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping
public class TestController {


    @Resource
    private Environment environment;

    @GetMapping("/getPro")
    public String getPro(@RequestParam(value = "key",defaultValue = "user.dir") String key) {

        return environment.getProperty(key,"not found");

    }

}
