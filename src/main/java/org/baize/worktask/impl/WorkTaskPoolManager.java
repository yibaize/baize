package org.baize.worktask.impl;

import org.baize.server.message.ICommand;
import org.baize.utils.LoggerUtils;
import org.baize.utils.SpringUtils;
import org.baize.worktask.TaskPoolManagerAbstract;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 作者： 白泽
 * 时间： 2017/11/3.
 * 描述：
 */
//@Service
public final class WorkTaskPoolManager extends TaskPoolManagerAbstract {
    //消息队列
    private final ConcurrentLinkedQueue<ICommand> taskQueue;
    public static WorkTaskPoolManager getInstance(){
        return SpringUtils.getBean(WorkTaskPoolManager.class);
    }
    /**单线程线程池*/
    private WorkTaskPoolManager() {
        super();
        taskQueue = new ConcurrentLinkedQueue<>();
        LoggerUtils.getLogicLog().error("----------------------业务线程启动成功----------------------");
    }

    @Override
    public ConcurrentLinkedQueue getQueue() {
        return taskQueue;
    }

}
