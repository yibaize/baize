package org.baize.worktask.impl;

import org.baize.dao.manager.Submit;
import org.baize.utils.SpringUtils;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 作者： 白泽
 * 时间： 2017/11/11.
 * 描述：
 */
@Service
public final class PersistTaskPoolManager extends SecondTaskPoolManager{
    private PersistTaskPoolManager() {
        super();
//        submit(() -> {
//            Submit.getInstance().update();
//        });
    }

    public static PersistTaskPoolManager getInstance(){
        return SpringUtils.getBean(PersistTaskPoolManager.class);
    }
}
