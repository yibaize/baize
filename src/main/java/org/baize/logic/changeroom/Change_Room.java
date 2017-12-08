package org.baize.logic.changeroom;

import org.baize.EnumType.ScenesType;
import org.baize.logic.BombFlower.manager.BombRoom;
import org.baize.logic.mainroom.friends.Dto.OtherInfoDto;
import org.baize.room.RoomAbstract;
import org.baize.room.RoomFactory;
import org.baize.server.message.IProtostuff;
import org.baize.server.message.OperateCommandAbstract;
import org.baize.utils.DateUtils;
import org.baize.utils.assemblybean.annon.Protocol;

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
        roomPlayer().getRoom().leaveRoom(roomPlayer());
        roomPlayer().setRoom(room);
        room.intoRoom(roomPlayer());
        ChangerRoomDto dto = new ChangerRoomDto();
        //TODO 不一定有庄家，不一定是BombRoom场景
        OtherInfoDto other = null;

        if(id == ScenesType.Mian.id())
            other = null;
        else if(id == ScenesType.Bomb.id())
            other = ((BombRoom)room).getBanker().playerInfo();

        dto.setRoomId(id);
        dto.setBanker(other);
        dto.setOnline(room.playerOnline());
        dto.setBattle(room.getGamblingParty().isStartBattle());
        int time = (int)((room.getGamblingParty().getEndTime() - DateUtils.currentTime())/1000);
        dto.setTimer(time);
        return dto;
    }
}
