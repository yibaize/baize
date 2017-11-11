package org.baize.worktask.impl;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 作者： 白泽
 * 时间： 2017/11/11.
 * 描述：
 */
public class Delay {
    private long start;
    private long delay;
    private TimeUnit timeUnit;

    public Delay(long start, long delay, TimeUnit timeUnit) {
        this.start = start;
        this.delay = delay;
        this.timeUnit = timeUnit;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }
    protected static ScheduledExecutorService timerTask = Executors.newScheduledThreadPool(1);
    public static void main(String[] args) {
        timerTask.scheduleAtFixedRate(() -> {
            System.out.println("我是1111");
        },1,1,TimeUnit.SECONDS);
        timerTask.scheduleAtFixedRate(() -> {
            System.err.println("我是333");
        },1,2,TimeUnit.SECONDS);
        timerTask.scheduleAtFixedRate(() -> {
            System.out.println("我是222");
        },1,1,TimeUnit.SECONDS);
    }
}
