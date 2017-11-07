package org.baize.worktask;

import org.baize.server.message.MessageAb;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 作者： 白泽
 * 时间： 2017/11/7.
 * 描述：
 */
public class MessageQueueUtils {
    //单线程
    private final WorkTaskPoolManager taskPool;
    //消息队列
    private final ConcurrentLinkedQueue<MessageAb> blockingQueue;
    private AtomicBoolean taskCompleted;
    private final LockUtils lock;
    private static MessageQueueUtils instance;
    private MessageQueueUtils(){
        blockingQueue = new ConcurrentLinkedQueue<>();
        taskCompleted = new AtomicBoolean(false);
        lock = LockUtils.getInstance();
        taskPool = WorkTaskPoolManager.getInstance();
    }
    public static MessageQueueUtils getInstance(){
        if(instance == null)
            instance = new MessageQueueUtils();
        return instance;
    }

    public WorkTaskPoolManager getTaskPool() {
        return taskPool;
    }

    public ConcurrentLinkedQueue<MessageAb> getBlockingQueue() {
        return blockingQueue;
    }

    public AtomicBoolean getTaskCompleted() {
        return taskCompleted;
    }
    public LockUtils getLock() {
        return lock;
    }
}
