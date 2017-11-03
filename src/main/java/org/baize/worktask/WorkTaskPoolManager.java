package org.baize.worktask;

import org.baize.server.message.IMessage;
import org.baize.server.message.MessageAb;

import java.util.concurrent.*;

/**
 * 作者： 白泽
 * 时间： 2017/11/3.
 * 描述：
 */
public class WorkTaskPoolManager {
    private static WorkTaskPoolManager instance;
    public static WorkTaskPoolManager getInstance(){
        if(instance == null)
            instance = new WorkTaskPoolManager();
        return instance;
    }
    private ConcurrentLinkedQueue<IMessage> taskQueue = new ConcurrentLinkedQueue();
//    /**定时器*/
//    public static ScheduledExecutorService timerTask;
    /**单线程线程池*/
    private final ExecutorService threadTask;
    private WorkTaskPoolManager() {
        this.threadTask = Executors.newSingleThreadExecutor();
    }
    public void submint(IMessage msg){
        taskQueue.offer(msg);
        run();
    }
    private void run(){
        if(taskQueue.size()<=0) return;
        IMessage i = taskQueue.poll();
        threadTask.execute(i);
    }
}
