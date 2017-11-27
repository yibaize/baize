package org.baize.logic.changeroom;

import org.baize.server.message.IProtostuff;
import org.baize.server.message.OperateCommandAbstract;
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
//        IRoom room = RoomFactory.getInstance().getBean(id);
//        this.player().setRoom(room);
//        if(room == null)
//            new Error(this.getCtx()).err(AppErrorCode.DATA_ERR);
//        int timer = (int) (room.endTime() - room.currentTime())/1000;
//        int online = room.getSet().size();
//        CorePlayer banker = room.banker();
//        OtherInfoDto bankerInfo = RankManager.getInstance().assembly(banker.entity());
//        ChangerRoomDto dto = new ChangerRoomDto();
//        dto.setTimer(timer);
//        dto.setOnline(online);
//        dto.setBanker(bankerInfo);
//        this.responce(dto);
//        room.into(this.player());
        return null;
    }
}
