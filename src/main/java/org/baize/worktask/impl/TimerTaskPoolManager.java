package org.baize.worktask.impl;

import org.baize.server.message.ICommand;
import org.baize.utils.LoggerUtils;
import org.baize.utils.SpringUtils;
import org.baize.worktask.TaskPoolManagerAbstract;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 作者： 白泽
 * 时间： 2017/11/11.
 * 描述：
 */

public abstract class TimerTaskPoolManager{
    public static TimerTaskPoolManager getInstance(){
        return SpringUtils.getBean(TimerTaskPoolManager.class);
    }
    private Delay delay;
//    /**定时器*/
    protected static ScheduledExecutorService timerTask;
    /**单线程线程池*/
    protected TimerTaskPoolManager(Delay delay) {
        this.delay = delay;
        if(timerTask != null) return;
        this.timerTask = Executors.newScheduledThreadPool(1);
        LoggerUtils.getLogicLog().error("----------------------定时任务线程启动成功----------------------");
    }

    public void submit(Runnable runnable){
        timerTask.scheduleAtFixedRate(runnable,delay.getStart(),delay.getDelay(),delay.getTimeUnit());
    }
}
