package org.baize.logic.outline;

import org.baize.logic.room.RoomFactory;
import org.baize.server.message.CommandAb;
import org.baize.utils.assemblybean.annon.Protocol;

/**
 * 作者： 白泽
 * 时间： 2017/11/17.
 * 描述：
 */
@Protocol(id = "14")
public class Out_line extends CommandAb{
    @Override
    public void execute() {
        RoomFactory.getInstance().notifyOfferLine(player());
    }
}
