package org.ywk.service;

import org.springframework.stereotype.Component;
import org.ywk.entity.DistributedLock;
import org.ywk.mapper.DistributedLockMapper;

import javax.annotation.Resource;

@Component
public class DistributedLockService {

    @Resource
    private DistributedLockMapper lockMapper;



    public boolean tryLock(String lockName) {

        DistributedLock lock = new DistributedLock();
        lock.setLockName(lockName);
        lock.setLockOwner(Thread.currentThread().getName());
        return 1 == lockMapper.insert(lock);


    }

    public boolean releaseLock(String lockName) {
        return 1 == lockMapper.deleteByPrimaryKey(lockName,Thread.currentThread().getName());
    }






}
