package org.baize.worktask;

import org.baize.utils.SpringUtils;
import org.baize.worktask.impl.*;
import org.springframework.stereotype.Service;

/**
 * 作者： 白泽
 * 时间： 2017/11/11.
 * 描述：
 */
@Service
public class TaskPoolFactary {
    public static TaskPoolFactary getInstance(){
        return SpringUtils.getBean(TaskPoolFactary.class);
    }
    public DailyTaskPoolManager getTimerExecutor(){return DailyTaskPoolManager.getInstance();}
    public WorkTaskPoolManager getWorkerExecutor(){return WorkTaskPoolManager.getInstance();}
    public SecondTaskPoolManager getSecondExecutor(){return SecondTaskPoolManager.getInstance();}
    public MinutesTaskPoolManager getMinutesExecutor(){return MinutesTaskPoolManager.getInstance();}
    public DailyTaskPoolManager getDailyExecutor(){return DailyTaskPoolManager.getInstance();}
    public PersistTaskPoolManager getPersistExecutor(){return PersistTaskPoolManager.getInstance();}
}
