package org.ywk.controller;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class RedissonController {

    @Resource
    private RedissonClient redisson;



    @GetMapping("/lock")
    public String lock() {


        RLock rLock = redisson.getLock("test");

        if (rLock.tryLock()) {
            log.info("{} 获得了锁",Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(10);
            }catch (Exception e) {

            }finally {
                rLock.unlock();
                log.info("{} 释放了锁",Thread.currentThread().getName());
                return "success";
            }
        }else {
            return "fail";
        }
    }


}
