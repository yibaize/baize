package org.baize.worktask;

import org.baize.server.message.ICommand;
import org.baize.utils.SpringUtils;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 作者： 白泽
 * 时间： 2017/11/3.
 * 描述：
 */
@Service
public final class WorkTaskPoolManager {
    //消息队列
    private final ConcurrentLinkedQueue<ICommand> taskQueue;
    private AtomicBoolean taskCompleted;
    private final LockUtils lock;
    public static WorkTaskPoolManager getInstance(){
        return SpringUtils.getBean(WorkTaskPoolManager.class);
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
        System.err.println((System.currentTimeMillis()+":----------------------业务线程启动成功----------------------"));
    }

    public AtomicBoolean getTaskCompleted() {
        return taskCompleted;
    }

    public LockUtils getLock() {
        return lock;
    }
    public final void submit(ICommand ab){
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
                ICommand msg = null;
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
