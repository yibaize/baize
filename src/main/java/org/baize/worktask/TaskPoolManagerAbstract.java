package org.baize.worktask;

import org.baize.server.message.IOperateCommand;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 作者： 白泽
 * 时间： 2017/11/11.
 * 描述：
 */
public abstract class TaskPoolManagerAbstract {
    private AtomicBoolean taskCompleted;
    private final LockUtils lock;
    private final ExecutorService threadTask;
    protected TaskPoolManagerAbstract() {
        this.threadTask = Executors.newSingleThreadExecutor();
        this.taskCompleted = new AtomicBoolean(false);
        this.lock = LockUtils.getInstance();
    }

    public void submit(Runnable msg){
        getQueue().offer(msg);
        lock.getLock().writeLock().lock();
        boolean submit = false;
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
            execute(taskRun);
    }
    private Runnable taskRun = new Runnable() {
        @Override
        public void run() {
            while (true) {
                lock.getLock().writeLock().lock();
                IOperateCommand msg = null;
                OK:
                {
                    try {
                        msg = (IOperateCommand) getQueue().poll();
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
    protected abstract ConcurrentLinkedQueue getQueue();
    private void execute(Runnable runnable){
        threadTask.execute(runnable);
    }
}
