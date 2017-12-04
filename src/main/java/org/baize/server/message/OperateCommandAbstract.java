package org.baize.server.message;

import io.netty.channel.Channel;
import org.baize.dao.model.CorePlayer;
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
    private CorePlayer corePlayer;
    protected RoomPlayer roomPlayer;
    private ISession session;
    public OperateCommandAbstract() {
    }

    public OperateCommandAbstract(short cmdId, CorePlayer corePlayer) {
        this.cmdId = cmdId;
        this.corePlayer = corePlayer;
    }


    public ISession getSession() {
        return session;
    }

    public void setSession(ISession session) {
        this.session = session;
    }

    public CorePlayer getCorePlayer() {
        return corePlayer;
    }

    public void setCorePlayer(CorePlayer corePlayer) {
        this.corePlayer = corePlayer;
    }

    public short getCmdId() {
        return cmdId;
    }

    public void setCmdId(short cmdId) {
        this.cmdId = cmdId;
    }

    public CorePlayer player(){
        return this.corePlayer;
    }

    @Override
    public void run() {
        IProtostuff pro = execute();
        byte[] buf = null;
        if(pro != null)
            buf = ProtostuffUtils.serializer(pro);
        Response response = new Response();
        response.setId(cmdId);
        response.setData(buf);
        session.write(response);
        broadcast();
    }
}
