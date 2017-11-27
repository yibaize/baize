package org.baize.room;

import org.baize.dao.model.Player;

import java.util.Set;

/**
 * 作者： 白泽
 * 时间： 2017/11/27.
 * 描述：
 */
public abstract class RoomAbstract implements IRoom{
    private Set<Player> roomPlayer;

    @Override
    public Set<Player> roomPlayer() {
        return roomPlayer;
    }
}
