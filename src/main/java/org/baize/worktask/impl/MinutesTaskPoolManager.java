package org.baize.worktask.impl;

import org.baize.utils.SpringUtils;
import org.baize.worktask.IMinutesTimer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 作者： 白泽
 * 时间： 2017/11/11.
 * 描述：
 */
@Service
public final class MinutesTaskPoolManager extends TimerTaskPoolManager {
    @Autowired(required = false)
    private Set<IMinutesTimer> set;
    public static MinutesTaskPoolManager getInstance(){
        return SpringUtils.getBean(MinutesTaskPoolManager.class);
    }
    private MinutesTaskPoolManager(){
        super(new Delay(1,1,TimeUnit.MINUTES));
    }
    @PostConstruct
    private void init(){
        submit(() -> {
            for (IMinutesTimer timer:set){
                timer.executor();
            }
        });
    }
}
