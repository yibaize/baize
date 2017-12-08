package org.baize.logic.outline;

import org.baize.server.manager.Response;
import org.baize.server.message.CommandCode;
import org.baize.server.message.IProtostuff;
import org.baize.server.message.OperateCommandAbstract;
import org.baize.server.session.SessionManager;
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
        if(roomPlayer() != null) {
            SessionManager.removeSession(roomPlayer().id());
            Response response = new Response(getCmdId(), null);
            getSession().write(response);
            if(roomPlayer().getRoom() != null) {
                roomPlayer().getRoom().leaveRoom(roomPlayer());
            }
        }
        getSession().close();
        return null;
    }
}
