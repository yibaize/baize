package org.baize.worktask.impl;

import org.baize.utils.SpringUtils;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 作者： 白泽
 * 时间： 2017/11/11.
 * 描述：
 */
@Service
public final class MinutesTaskPoolManager extends TimerTaskPoolManager {
    public static MinutesTaskPoolManager getInstance(){
        return SpringUtils.getBean(MinutesTaskPoolManager.class);
    }
    private MinutesTaskPoolManager(){
        super(new Delay(1,1,TimeUnit.MINUTES));
    }
}
