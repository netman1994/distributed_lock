package org.ywk.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ywk.config.RedisLock;

import javax.annotation.Resource;

@RestController
public class RedisController {


    @Resource
    private RedisTemplate redisTemplate;


    @GetMapping("/lock")
    public String lock() {

        RedisLock redisLock = new RedisLock(redisTemplate, "test", 10);

        boolean lock = redisLock.getLock();

        return lock ? "success" : "fail";


    }


    /**
     * 无法释放别的线程的锁
     * @return
     */
    @Deprecated
    @GetMapping("unlock")
    public String unlock() {

        RedisLock redisLock = new RedisLock(redisTemplate, "test", 10);

        return redisLock.unLock() ? "success" : "fail";


    }

}
