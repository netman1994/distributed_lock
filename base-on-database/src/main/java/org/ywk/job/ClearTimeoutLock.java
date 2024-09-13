package org.ywk.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.ywk.mapper.DistributedLockMapper;

import javax.annotation.Resource;

@Slf4j
@Component
public class ClearTimeoutLock {


    @Resource
    private DistributedLockMapper lockMapper;

    @Scheduled(cron = "*/10 * * * * *")
    public void clearTimeoutLock() {


        log.info("execute....");


        lockMapper.clearTimoutLock("test",10);

    }

}
