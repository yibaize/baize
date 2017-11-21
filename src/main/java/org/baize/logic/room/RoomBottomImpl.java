package org.baize.logic.room;

import org.baize.dao.model.CorePlayer;
import org.baize.logic.room.impl.BottomPosition;
import org.baize.server.message.IProtostuff;
import org.baize.worktask.ISecondTimer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 作者： 白泽
 * 时间： 2017/11/6.
 * 描述：
 */
public class RoomBottomImpl implements IBottom,ISecondTimer{
    /**牌堆位置*/
    private ConcurrentMap<Integer,BottomPosition> map;
    private AtomicBoolean hasBtoom = new AtomicBoolean(false);
    private Set<CorePlayer> players;

    public void setPlayers(Set<CorePlayer> players) {
        this.players = players;
    }

    @Override
    public void leave(CorePlayer corePlayer){
        for (Map.Entry<Integer,BottomPosition> e:map.entrySet()){
            e.getValue().removeLeavePlayer(corePlayer);
        }
    }
    @Override
    public void bottom(int position,int num, CorePlayer corePlayer) {
        hasBtoom.compareAndSet(false,true);
        if(map.containsKey(position)){
            BottomPosition bottom = map.get(position);
            bottom.bottom(num,corePlayer);
        }else{
            ConcurrentMap<CorePlayer,Integer> bottomPosition = new ConcurrentHashMap<>();
            AtomicInteger atomicInteger = new AtomicInteger(0);
            BottomPosition bottom = new BottomPosition(position,bottomPosition,atomicInteger);
            bottom.bottom(num,corePlayer);
        }
    }

    @Override
    public int bottomAllNumber(int position) {
        if(map.containsKey(position))
            return map.get(position).allNum();
        return 0;
    }

    @Override
    public int playerBottomNumber(int position, CorePlayer player) {
        if(map.containsKey(position)){
            BottomPosition bottomPosition = map.get(position);
            return bottomPosition.playerBottomNumber(player);
        }
        return 0;
    }

    @Override
    public void clearBottom() {
        for (Map.Entry<Integer,BottomPosition> e:map.entrySet()){
            e.getValue().clearBottom();
        }
    }
    @Override
    public IProtostuff bottomInfo() {
        Map<Integer,Integer> money = new HashMap<>(4);
        for (Map.Entry<Integer,BottomPosition> e:map.entrySet()){
            money.put(e.getKey(),e.getValue().allNum());
        }
        RoomBottomDto dto = new RoomBottomDto(money);
        return dto;
    }

    @Override
    public void players(Set<CorePlayer> players) {
        this.players = players;
    }

    @Override
    public Map<Integer, BottomPosition> bottomMap() {
        return map;
    }

    @Override
    public void executor() {
        if(hasBtoom.get()){
            RoomBottomDto dto = (RoomBottomDto) bottomInfo();
            Iterator<CorePlayer> iterator = players.iterator();
            while (iterator.hasNext()){
                CorePlayer corePlayer = iterator.next();
                corePlayer.respones((short)104,dto);
            }
            hasBtoom.compareAndSet(true,false);
        }
    }
}
