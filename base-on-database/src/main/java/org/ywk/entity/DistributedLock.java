package org.ywk.entity;


public class DistributedLock {

    private String lockName;

    private String lockOwner;

    private Long lockTime;


    public String getLockName() {
        return lockName;
    }

    public void setLockName(String lockName) {
        this.lockName = lockName;
    }

    public String getLockOwner() {
        return lockOwner;
    }

    public void setLockOwner(String lockOwner) {
        this.lockOwner = lockOwner;
    }

    public Long getLockTime() {
        return lockTime;
    }

    public void setLockTime(Long lockTime) {
        this.lockTime = lockTime;
    }

    @Override
    public String toString() {
        return "DistributedLock{" +
                "lockName='" + lockName + '\'' +
                ", lockOwner='" + lockOwner + '\'' +
                ", lockTime=" + lockTime +
                '}';
    }
}
