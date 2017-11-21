package org.baize.worktask.impl;

import org.baize.utils.SpringUtils;
import org.baize.worktask.ISecondTimer;
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
public final class SecondTaskPoolManager extends TimerTaskPoolManager {
    @Autowired
    private Set<ISecondTimer> secondTimer;
    public static SecondTaskPoolManager getInstance(){
        return SpringUtils.getBean(SecondTaskPoolManager.class);
    }
    protected SecondTaskPoolManager(){
        super(new Delay(1,1,TimeUnit.SECONDS));

    }
    @PostConstruct
    private void init(){
        submit(() -> {
            for (ISecondTimer timer:secondTimer){
                timer.executor();
            }
        });
    }
}
