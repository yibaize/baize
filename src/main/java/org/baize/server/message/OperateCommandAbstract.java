package org.baize.server.message;

import io.netty.channel.Channel;
import org.baize.dao.model.CorePlayer;
import org.baize.room.RoomPlayer;
import org.baize.server.manager.Response;
import org.baize.utils.ProtostuffUtils;

/**
 * 作者： 白泽
 * 时间： 2017/11/3.
 * 描述：
 */
public abstract class OperateCommandAbstract implements IOperateCommand {
    private short cmdId;
    private Channel ctx;
    private CorePlayer corePlayer;
    private boolean hasSend = false;
    protected RoomPlayer roomPlayer;
    public OperateCommandAbstract() {
    }

    public OperateCommandAbstract(short cmdId, Channel ctx, CorePlayer corePlayer) {
        this.cmdId = cmdId;
        this.ctx = ctx;
        this.corePlayer = corePlayer;
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

    public Channel getCtx() {
        return ctx;
    }
    public void setCtx(Channel ctx) {
        this.ctx = ctx;
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
        ctx.writeAndFlush(response);
        broadcast();
    }
}
