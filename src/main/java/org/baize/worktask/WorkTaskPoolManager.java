package org.baize.worktask;

import org.baize.server.message.IMessage;
import org.baize.server.message.MessageAb;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 作者： 白泽
 * 时间： 2017/11/3.
 * 描述：
 */
public class WorkTaskPoolManager {
    //消息队列
    private final ConcurrentLinkedQueue<IMessage> taskQueue;
    private AtomicBoolean taskCompleted;
    private final LockUtils lock;
    private static WorkTaskPoolManager instance;
    public static WorkTaskPoolManager getInstance(){
        if(instance == null)
            instance = new WorkTaskPoolManager();
        return instance;
    }
//    /**定时器*/
//    public static ScheduledExecutorService timerTask;
    /**单线程线程池*/
    private final ExecutorService threadTask;
    private WorkTaskPoolManager() {
        this.threadTask = Executors.newSingleThreadExecutor();
        taskQueue = new ConcurrentLinkedQueue<>();
        taskCompleted = new AtomicBoolean(false);
        lock = LockUtils.getInstance();
    }

    public AtomicBoolean getTaskCompleted() {
        return taskCompleted;
    }

    public LockUtils getLock() {
        return lock;
    }
    public void submit(IMessage ab){
        taskQueue.offer(ab);
        boolean submit = false;
        lock.getLock().writeLock().lock();
        try {
            if(taskCompleted.get());{
                taskCompleted.compareAndSet(true,false);
                submit = true;
            }
        }finally {
            lock.getLock().writeLock().unlock();
        }
        //提交线程
        if(submit)
            threadTask.execute(taskRun);
    }
    private Runnable taskRun = new Runnable() {
        @Override
        public void run() {
            while (true) {
                lock.getLock().writeLock().lock();
                IMessage msg = null;
                OK:
                {
                    try {
                        msg = WorkTaskPoolManager.getInstance().taskQueue.poll();
                        if (msg != null)
                            break OK;
                        taskCompleted.compareAndSet(false, true);
                    } finally {
                        lock.getLock().writeLock().unlock();
                    }
                    return;//防止线程挂起阻塞
                }
                msg.run();
            }
        }
    };
}
