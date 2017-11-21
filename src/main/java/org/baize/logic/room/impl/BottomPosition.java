package org.baize.logic.room.impl;

import org.baize.dao.model.CorePlayer;
import org.baize.dao.model.Weath;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 作者： 白泽
 * 时间： 2017/11/21.
 * 描述：
 */
public class BottomPosition {
    /**牌堆位置*/
    private int position;
    private ConcurrentMap<CorePlayer,Integer> map;
    private AtomicInteger allNum;

    public BottomPosition() {
    }

    public BottomPosition(int position, ConcurrentMap<CorePlayer, Integer> map, AtomicInteger allNum) {
        this.position = position;
        this.map = map;
        this.allNum = allNum;
    }

    public int allNum(){
        return allNum.get();
    }
    public void addAllNum(int num){
        allNum.getAndSet(num);
    }
    public void leave(CorePlayer corePlayer){
        if(map.containsKey(corePlayer))
            map.remove(corePlayer);
    }
    public void bottom(int num, CorePlayer corePlayer) {
        addAllNum(num);
        synchronized (map) {
            if (map.containsKey(corePlayer)) {
                int money = map.get(corePlayer);
                num += money;
            }
            map.put(corePlayer, num);
            Weath weath = corePlayer.entity().weath();
            weath.decreaseDiamond(num);
            //减少金币
            weath.update();
        }
    }

    public int bottomAllNumber() {
        return allNum.get();
    }

    public int playerBottomNumber(CorePlayer player) {
        if(map.containsKey(player))
            return map.get(player);
        return 0;
    }

    public void clearBottom() {
        map.clear();
        allNum.set(0);
        position = 0;
    }
    public void removeLeavePlayer(CorePlayer corePlayer){
        if(map.containsKey(corePlayer))
            map.remove(corePlayer);
    }
    public void settleAccounts(int multiple){
        for (Map.Entry<CorePlayer,Integer> e:map.entrySet()){
            Weath w = e.getKey().entity().weath();
            int count = e.getValue() * multiple;
            w.increaseGold(count);
            w.update();
        }
    }
}
