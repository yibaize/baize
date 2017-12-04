package org.baize.room;

import io.netty.channel.Channel;
import org.baize.dao.model.PlayerEntity;
import org.baize.logic.card.data.Card;
import org.baize.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 作者： 白泽
 * 时间： 2017/11/27.
 * 描述：
 */
public class RoomPlayer extends Player {
    private RoomAbstract room;
    /**玩家下注哪个位置，下注多少*/
    private Map<Integer,Integer> bottomPosition;

    public RoomPlayer(PlayerEntity entity) {
        super(entity);
        bottomPosition = new HashMap<>(4);
    }

    public void bottom(int position,int count){
        int moneyNumber = bottomPosition.getOrDefault(position,0);
        bottomPosition.put(position,moneyNumber + count);
        room.getGamblingParty().getBottomPosition().bottom(this,count,position);
    }

    /**
     *
     * @param multiple 倍数
     */
    public void settleAccounts(int multiple,int position){
        int money = bottomPosition.getOrDefault(position,0);
        if(money == 0) return;
        entity().weath().increaseGold(money * (multiple + 1));
        entity().weath().update();
    }

    public RoomAbstract getRoom() {
        return room;
    }

    public void setRoom(RoomAbstract room) {
        this.room = room;
    }
}
