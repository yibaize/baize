package org.baize.room;
import org.baize.worktask.LockUtils;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
/**
 * 作者： 白泽
 * 时间： 2017/11/27.
 * 描述：
 */
public class BottomPosition {
    private Map<Integer,Set<RoomPlayer>> playerMap;
    private Map<Integer,Long> bottomPosition;
    private long allMoney;
    public BottomPosition(int positionCount) {
        allMoney = 0;
        playerMap = new ConcurrentHashMap<>(positionCount);
        bottomPosition = new ConcurrentHashMap<>(positionCount);
    }

    public void bottom(RoomPlayer player, int count, int position){
        LockUtils.getInstance().getLock().writeLock().lock();
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
            player.entity().weath().decreaseGold(count);
        }finally {
            LockUtils.getInstance().getLock().writeLock().unlock();
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
}
