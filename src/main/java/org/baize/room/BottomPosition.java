package org.baize.room;
import org.baize.dao.model.Weath;
import org.baize.worktask.LockUtils;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 作者： 白泽
 * 时间： 2017/11/27.
 * 描述：
 */
public class BottomPosition {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Map<Integer,Set<RoomPlayer>> playerMap;
    private Map<Integer,Long> bottomPosition;
    private long allMoney;
    public BottomPosition(int positionCount) {
        allMoney = 0;
        playerMap = new ConcurrentHashMap<>(positionCount);
        bottomPosition = new ConcurrentHashMap<>(positionCount);
    }

    public void bottom(RoomPlayer player, int count, int position){
        lock.writeLock().lock();
        try {
            allMoney += count;
            if(bottomPosition.containsKey(position)){
                long money = bottomPosition.get(position);
                count += money;
            }
            bottomPosition.put(position,(long)count);
            if(playerMap.containsKey(position)){
                Set<RoomPlayer> set = playerMap.get(position);
                if(set.contains(player))
                    return;
                else
                    set.add(player);
            }else {
                Set<RoomPlayer> set = new HashSet<>();
                set.add(player);
            }
            Weath weath = player.entity().weath();
            weath.decreaseGold(count);
            weath.update();
        }finally {
            lock.writeLock().unlock();
        }
    }
    public long getAllMoney(){return allMoney;}
    public long getPositionMoney(int position){
        return bottomPosition.getOrDefault(position,0L);
    }
    public Set<RoomPlayer> getPositionPlayers(int postion){
        return playerMap.getOrDefault(postion,null);
    }
    public void settleAccounts(int position,int multiple){
        Set<RoomPlayer> players = playerMap.getOrDefault(position,null);
        if(players == null) return;
        for (RoomPlayer p:players){
            p.settleAccounts(position,multiple);
        }
    }
    public void leave(RoomPlayer roomPlayer){
        for (Map.Entry<Integer,Set<RoomPlayer>> e:playerMap.entrySet()){
            for (RoomPlayer r:e.getValue()){
                if(roomPlayer.id() == r.id())
                    e.getValue().remove(roomPlayer);
            }
        }
        roomPlayer.clearBottom();
    }
}
