package org.baize.worktask.impl;

import org.baize.utils.SpringUtils;
import org.baize.worktask.IDailyTimer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 作者： 白泽
 * 时间： 2017/11/11.
 * 描述：
 */
@Service
public final class DailyTaskPoolManager extends TimerTaskPoolManager {
    @Autowired
    private Set<IDailyTimer> set;
    public static DailyTaskPoolManager getInstance(){
        return SpringUtils.getBean(DailyTaskPoolManager.class);
    }
    private DailyTaskPoolManager(){
        super(new Delay(0,1,TimeUnit.DAYS));
        submit(() -> {
            for (IDailyTimer dailyTimer:set){
                dailyTimer.executor();
            }
        });
    }
}
