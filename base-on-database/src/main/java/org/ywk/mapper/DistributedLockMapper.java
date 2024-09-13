package org.ywk.mapper;

import org.apache.ibatis.annotations.Param;
import org.ywk.entity.DistributedLock;

public interface DistributedLockMapper {


    int insert(DistributedLock distributedLock);

    int deleteByPrimaryKey(@Param("lockName") String lockName, @Param("lockOwner") String lockOwner);


    int clearTimoutLock(@Param("lockName")String lockName,@Param("intervalValue") int intervalValue);

}
