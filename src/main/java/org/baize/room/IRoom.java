package org.baize.room;

import org.baize.dao.model.Player;

import java.util.Set;

/**
 * 作者： 白泽
 * 时间： 2017/11/27.
 * 描述：
 */
public interface IRoom {
    /**
     * 进入房间
     * @param player
     */
    default void intoRoom(Player player){
        if(!roomPlayer().contains(player)){
            roomPlayer().add(player);
        }
    }

    /**
     * 离开房间
     * @param player
     */
    default void laeveRoom(Player player){
        if(roomPlayer().contains(player)){
            roomPlayer().remove(player);
        }
    }
    Set<Player> roomPlayer();
    default int playerOnline(){
        if(roomPlayer() != null)
            return roomPlayer().size();
        return 0;
    }
}
