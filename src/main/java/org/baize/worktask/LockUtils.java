package org.baize.worktask;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 作者： 白泽
 * 时间： 2017/11/7.
 * 描述：
 */
public class LockUtils {
    private static ReentrantReadWriteLock lock;
    private static LockUtils instance;
    public static LockUtils getInstance(){
        if(instance == null) {
            instance = new LockUtils();
            lock = new ReentrantReadWriteLock();
        }
        return instance;
    }

    public ReentrantReadWriteLock getLock() {
        return lock;
    }
}
