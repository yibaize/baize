package org.baize.room;

import org.baize.player.Player;
import org.baize.server.manager.Response;
import org.baize.utils.ProtostuffUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * 作者： 白泽
 * 时间： 2017/11/27.
 * 描述：
 */
public abstract class RoomAbstract implements IRoom{
    private int roomId;
    private Set<RoomPlayer> roomPlayer;
    /**牌局*/
    private GamblingParty gamblingParty;

    public RoomAbstract(int roomId) {
        this.roomId = roomId;
        roomPlayer = new HashSet<>();
    }

    public int getRoomId() {
        return roomId;
    }

    @Override
    public Set<RoomPlayer> roomPlayer() {
        if(roomPlayer == null)
            roomPlayer = new HashSet<>();
        return roomPlayer;
    }

    public Set<RoomPlayer> getRoomPlayer() {
        return roomPlayer;
    }

    public void setRoomPlayer(Set<RoomPlayer> roomPlayer) {
        this.roomPlayer = roomPlayer;
    }

    public GamblingParty getGamblingParty() {
        return gamblingParty;
    }

    public void setGamblingParty(GamblingParty gamblingParty) {
        this.gamblingParty = gamblingParty;
    }

    @Override
    public void leaveRoom(RoomPlayer player) {
        if(roomPlayer().contains(player)){
            roomPlayer().remove(player);
        }
        if(gamblingParty != null)
            gamblingParty.getBottomPosition().leave(player);

        byte[] buf = ProtostuffUtils.serializer(playerOnline());
        Response response = new Response((short) 102,buf);
        for (RoomPlayer r:roomPlayer){
            r.getSession().write(response);
        }
    }
}
