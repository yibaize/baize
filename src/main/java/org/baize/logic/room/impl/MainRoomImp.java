package org.baize.logic.room.impl;

import org.baize.EnumType.ScenesType;
import org.baize.dao.model.CorePlayer;
import org.baize.logic.room.IRoom;
import org.baize.logic.room.RoomBottomDto;
import org.baize.server.message.IProtostuff;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 作者： 白泽
 * 时间： 2017/11/8.
 * 描述：
 */
@Service
public class MainRoomImp implements IRoom {
    private int id = ScenesType.Mian.id();
    private Set<CorePlayer> playerSet;

    public MainRoomImp() {
        this.id = ScenesType.Mian.id();
        if(this.playerSet == null)
            this.playerSet = new HashSet<>();
    }
    @Override
    public int getId() {
        return id;
    }

    @Override
    public CorePlayer banker() {
        return null;
    }

    @Override
    public List<CorePlayer> bankerUpList() {
        return null;
    }

    @Override
    public RoomBottomDto roomBottom() {
        return null;
    }

    @Override
    public boolean into(CorePlayer corePlayer) {
        playerSet.add(corePlayer);
        notifyAllx((short)102,corePlayer,null);
        return true;
    }

    @Override
    public boolean leave(CorePlayer corePlayer) {
        if(playerSet.contains(corePlayer)) {
            playerSet.remove(corePlayer);
            notifyAllx((short) 101,corePlayer,null);
            return true;
        }
        return false;
    }
    @Override
    public Set<CorePlayer> getSet() {
        return playerSet;
    }

    @Override
    public void settleAccounts() {

    }

    @Override
    public void bottom(int position, int num, CorePlayer corePlayer) {
    }

}
