package org.baize.server.message;

import org.baize.room.RoomPlayer;
import org.baize.server.manager.Response;
import org.baize.server.session.ISession;
import org.baize.utils.ProtostuffUtils;

/**
 * 作者： 白泽
 * 时间： 2017/11/3.
 * 描述：
 */
public abstract class OperateCommandAbstract implements IOperateCommand {
    private short cmdId;
    private RoomPlayer roomPlayer;
    private ISession session;
    public OperateCommandAbstract() {
    }
    public OperateCommandAbstract(short cmdId) {
        this.cmdId = cmdId;
    }
    public ISession getSession() {
        return session;
    }

    public void setSession(ISession session) {
        this.session = session;
    }

    public short getCmdId() {
        return cmdId;
    }

    public void setCmdId(short cmdId) {
        this.cmdId = cmdId;
    }
    protected RoomPlayer roomPlayer(){
        return roomPlayer;
    }
    protected void roomPlayer(RoomPlayer roomPlayer){
        this.roomPlayer = roomPlayer;
    }
    @Override
    public void run() {
        IProtostuff pro = execute();
        byte[] buf = null;
        if(pro != null)
            buf = ProtostuffUtils.serializer(pro);
        Response response = new Response(cmdId,buf);
        session.write(response);
        broadcast();
    }
}
