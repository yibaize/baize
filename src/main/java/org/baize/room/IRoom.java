package org.baize.room;
import org.baize.server.manager.Response;
import org.baize.utils.ProtostuffUtils;
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
    default void intoRoom(RoomPlayer player){
        if(!roomPlayer().contains(player)){
            roomPlayer().add(player);

            byte[] buf = ProtostuffUtils.serializer(playerOnline());
            Response response = new Response((short) 101,buf);

            for (RoomPlayer r:roomPlayer()){
                r.getSession().write(response);
            }
        }
    }

    /**
     * 离开房间
     * @param player
     */
    void leaveRoom(RoomPlayer player);
    Set<RoomPlayer> roomPlayer();
    default int playerOnline(){
        if(roomPlayer() != null)
            return roomPlayer().size();
        return 0;
    }

    /**
     * 比牌
     */
    void compare();
}
