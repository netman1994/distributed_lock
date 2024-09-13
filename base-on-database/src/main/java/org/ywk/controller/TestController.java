package org.ywk.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ywk.entity.DistributedLock;
import org.ywk.mapper.DistributedLockMapper;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    private DistributedLockMapper distributedLockMapper;

    @PostMapping("insert")
    public String insert() {

        DistributedLock lock = new DistributedLock();

        lock.setLockName("test");
        lock.setLockOwner(Thread.currentThread().getName());

        int i = distributedLockMapper.insert(lock);

        return 1 == i ? "success" : "fail";


    }


}
