package org.baize.logic.changeroom;

import org.baize.dao.model.CorePlayer;
import org.baize.dao.model.PlayerEntity;
import org.baize.error.AppErrorCode;
import org.baize.error.Error;
import org.baize.logic.mainroom.friends.Dto.OtherInfoDto;
import org.baize.logic.mainroom.rank.manaer.RankManager;
import org.baize.logic.room.IRoom;
import org.baize.logic.room.RoomFactory;
import org.baize.server.message.CommandAb;
import org.baize.utils.assemblybean.annon.Protocol;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： 白泽
 * 时间： 2017/11/17.
 * 描述：
 */
@Protocol(id = "13")
public class Change_Room extends CommandAb{
    private final int id;

    public Change_Room(int id) {
        this.id = id;
    }

    @Override
    public void execute() {
        IRoom room = RoomFactory.getInstance().getBean(id);
        this.player().setRoom(room);
        if(room == null)
            new Error(this.getCtx()).err(AppErrorCode.DATA_ERR);
        int timer = (int) (room.endTime() - room.currentTime())/1000;
        int online = room.getSet().size();
        CorePlayer banker = room.banker();
        List<CorePlayer> corePlayers = room.bankerUpList();
        List<OtherInfoDto> bankerList = new ArrayList<>(corePlayers.size());
        OtherInfoDto bankerInfo = RankManager.getInstance().assembly(banker.entity());
        for (CorePlayer corePlayer:corePlayers){
            bankerList.add(RankManager.getInstance().assembly(corePlayer.entity()));
        }
        ChangerRoomDto dto = new ChangerRoomDto();
        dto.setTimer(timer);
        dto.setOnline(online);
        dto.setBanker(bankerInfo);
        dto.setBankerList(bankerList);
        this.responce(dto);
        room.into(this.player());
    }
}
