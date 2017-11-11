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
public class SecondTaskPoolManager extends TimerTaskPoolManager {
    public static SecondTaskPoolManager getInstance(){
        return SpringUtils.getBean(SecondTaskPoolManager.class);
    }
    protected SecondTaskPoolManager(){
        super(new Delay(1,1,TimeUnit.SECONDS));
    }
}
