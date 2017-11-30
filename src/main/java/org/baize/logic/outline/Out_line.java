package org.baize.logic.outline;

import org.baize.room.RoomFactory;
import org.baize.server.message.IProtostuff;
import org.baize.server.message.OperateCommandAbstract;
import org.baize.utils.assemblybean.annon.Protocol;

/**
 * 作者： 白泽
 * 时间： 2017/11/17.
 * 描述：
 */
@Protocol(id = "14")
public class Out_line extends OperateCommandAbstract {
    @Override
    public IProtostuff execute() {
        //RoomFactory.getInstance().notifyOfferLine(player());
        return null;
    }
}
