package org.baize.logic.BombFlower.manager;

import org.apache.commons.collections4.set.ListOrderedSet;
import org.baize.EnumType.ScenesType;
import org.baize.arithmetic.BomFlower;
import org.baize.room.BottomPosition;
import org.baize.room.GamblingParty;
import org.baize.room.RoomAbstract;
import org.baize.room.RoomPlayer;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 作者： 白泽
 * 时间： 2017/11/13.
 * 描述：
 */
@Service
public class BombRoom extends RoomAbstract{
    private RoomPlayer banker;
    private LinkedList<RoomPlayer> bankerList;
    public BombRoom() {
        super(ScenesType.Bomb.id());
        bankerList = new LinkedList<>();
        GamblingParty party = new GamblingParty(5,3);
        BottomPosition position = new BottomPosition(4);
        party.setBottomPosition(position);
        party.setRoom(this);
        setGamblingParty(party);

    }
    public void bankerUp(RoomPlayer player){
        if(bankerList.contains(player))
            return;
        bankerList.offer(player);
        up();
    }
    private void up(){
        if(banker != null) return;
        if(bankerList.size() <=0 ) return;
        banker = bankerList.poll();
    }
    public void downBanker(RoomPlayer player){
        if(!banker.equals(player)) return;
        banker = null;
        up();
    }

    public RoomPlayer getBanker() {
        return banker;
    }

    public LinkedList<RoomPlayer> getBankerList() {
        return bankerList;
    }

    @Override
    public void compare() {
        BomFlower.handler(getGamblingParty().holdCard());
    }
}
