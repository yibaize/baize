package org.baize.logic.room.impl;

import org.baize.EnumType.ScenesType;
import org.baize.dao.model.CorePlayer;
import org.baize.logic.room.IRoom;
import org.baize.server.message.IProtostuff;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
    public boolean into(CorePlayer corePlayer) {
        playerSet.add(corePlayer);
        return true;
    }

    @Override
    public boolean leave(CorePlayer corePlayer) {
        if(playerSet.contains(corePlayer)) {
            playerSet.remove(corePlayer);
            notifyAllx((short) 10,corePlayer,null);
            return true;
        }
        return false;
    }
    @Override
    public Set<CorePlayer> getSet() {
        return playerSet;
    }
}
