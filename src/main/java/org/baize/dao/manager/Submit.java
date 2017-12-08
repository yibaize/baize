package org.baize.dao.manager;

import org.baize.dao.model.Persist;
import org.baize.utils.SpringUtils;
import org.baize.worktask.ISecondTimer;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 作者： 白泽
 * 时间： 2017/11/11.
 * 描述：
 */
@Service
public final class Submit implements ISecondTimer{
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Map<Class<? extends Persist>,Map<Integer,Persist>> taskQueue = new HashMap<>();
    private final AtomicInteger submitCount = new AtomicInteger(0);
    public static Submit getInstance(){
        return SpringUtils.getBean(Submit.class);
    }
    public void put(Class<? extends Persist> clazz,Persist persist){
        lock.writeLock().lock();
        submitCount.incrementAndGet();
        Map<Integer,Persist> con;
        try {
            if(!taskQueue.containsKey(clazz)) {
                con = new ConcurrentHashMap<>(1);
                con.put(persist.getId(),persist);
                taskQueue.put(clazz,con);
            }else {
                con = taskQueue.get(clazz);
                if(!con.containsKey(persist.getId())){
                    con.put(persist.getId(),persist);
                }
            }
        }finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void executor() {
        if(taskQueue.size() <=0 )return;
        if(submitCount.get() <= 0) return;
        lock.writeLock().lock();
        try {
            System.err.println("会儿答复");
            List<Map<Integer,Persist>> queue = new ArrayList<>(taskQueue.values());
            for (int i = 0;i<queue.size();i++){
                for (Map.Entry<Integer,Persist> e:queue.get(i).entrySet()){
                    e.getValue().submit();
                }
            }
            submitCount.set(0);
        }finally {
            lock.writeLock().unlock();
        }
    }
}
