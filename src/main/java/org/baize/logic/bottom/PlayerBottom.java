package org.baize.logic.bottom;

import org.baize.server.message.CommandAb;
import org.baize.utils.assemblybean.annon.Protocol;

/**
 * 作者： 白泽
 * 时间： 2017/11/17.
 * 描述：
 */
@Protocol(id = "12")
public class PlayerBottom extends CommandAb {
    private final int id;
    private final int count;

    public PlayerBottom(int id, int count) {
        this.id = id;
        this.count = count;
    }

    @Override
    public void execute() {
        IRoom room = this.player().getRoom();
        room.bottom(id,count,this.player());
        PlayerBottomDto dto = new PlayerBottomDto(id,count);
        this.responce(dto);
    }
}
