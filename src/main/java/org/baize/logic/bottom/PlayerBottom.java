package org.baize.logic.bottom;

import org.baize.room.RoomAbstract;
import org.baize.room.RoomPlayer;
import org.baize.server.message.IProtostuff;
import org.baize.server.message.OperateCommandAbstract;
import org.baize.utils.assemblybean.annon.Protocol;

/**
 * 作者： 白泽
 * 时间： 2017/11/17.
 * 描述：
 */
@Protocol(id = "12")
public class PlayerBottom extends OperateCommandAbstract {
    private final int id;
    private final int count;

    public PlayerBottom(int id, int count) {
        this.id = id;
        this.count = count;
    }

    @Override
    public IProtostuff execute() {
        RoomPlayer p = this.roomPlayer();
        p.bottom(id,count);
        PlayerBottomDto dto = new PlayerBottomDto(id,count);
        return dto;
    }
}
