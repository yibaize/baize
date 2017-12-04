package org.baize.logic.changeroom;

import org.baize.logic.BombFlower.manager.BombRoom;
import org.baize.logic.mainroom.friends.Dto.OtherInfoDto;
import org.baize.logic.mainroom.rank.manaer.RankManager;
import org.baize.room.RoomAbstract;
import org.baize.room.RoomFactory;
import org.baize.room.RoomPlayer;
import org.baize.server.manager.Response;
import org.baize.server.message.IProtostuff;
import org.baize.server.message.OperateCommandAbstract;
import org.baize.utils.DateUtils;
import org.baize.utils.ProtostuffUtils;
import org.baize.utils.assemblybean.annon.Protocol;

import java.util.Set;

/**
 * 作者： 白泽
 * 时间： 2017/11/17.
 * 描述：
 */
@Protocol(id = "13")
public class Change_Room extends OperateCommandAbstract {
    private final int id;

    public Change_Room(int id) {
        this.id = id;
    }

    @Override
    public IProtostuff execute() {
        RoomAbstract room = RoomFactory.getInstance().getBean(id);
        RoomPlayer player = new RoomPlayer(this.player().entity());
        player.setRoom(room);
        this.roomPlayer = player;
        room.intoRoom(player);
        ChangerRoomDto dto = new ChangerRoomDto();
        OtherInfoDto other = RankManager.getInstance().assembly(((BombRoom)room).getBanker().entity());
        dto.setBanker(other);
        dto.setOnline(room.playerOnline());
        dto.setBattle(room.getGamblingParty().isStartBattle());
        int time = (int)((room.getGamblingParty().getEndTime() - DateUtils.currentTime())/1000);
        dto.setTimer(time);
        return dto;
    }

    @Override
    public void broadcast() {
        RoomAbstract room = RoomFactory.getInstance().getBean(id);
        Set<RoomPlayer> players = room.roomPlayer();
        Response response = new Response();
        response.setId((short) 1);
        byte[] buf = ProtostuffUtils.serializer(room.playerOnline());
        response.setData(buf);
        for (RoomPlayer r:players){
            //r.getCtx().writeAndFlush(response);
        }
    }
}
