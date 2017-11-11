package org.baize.worktask.impl;

import org.baize.utils.SpringUtils;

import java.util.concurrent.TimeUnit;

/**
 * 作者： 白泽
 * 时间： 2017/11/11.
 * 描述：
 */
public final class DailyTaskPoolManager extends TimerTaskPoolManager {
    public static DailyTaskPoolManager getInstance(){
        return SpringUtils.getBean(DailyTaskPoolManager.class);
    }
    private DailyTaskPoolManager(){
        super(new Delay(1,1,TimeUnit.DAYS));
    }
}
