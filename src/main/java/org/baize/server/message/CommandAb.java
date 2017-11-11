package org.baize.server.message;

import io.netty.channel.Channel;
import org.baize.dao.model.CorePlayer;
import org.baize.logic.room.IRoom;
import org.baize.server.manager.Response;
import org.baize.utils.ProtostuffUtils;

/**
 * 作者： 白泽
 * 时间： 2017/11/3.
 * 描述：
 */
public abstract class CommandAb implements ICommand {
    private short cmdId;
    private Channel ctx;
    private CorePlayer corePlayer;
    private boolean hasSend = false;
    public CommandAb() {
    }

    public CommandAb(short cmdId, Channel ctx, CorePlayer corePlayer) {
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
    public void run() {
        this.execute();
        this.sendSucceed();
    }
    protected void responce(IProtostuff pro){
        corePlayer.respones(pro);
        hasSend = true;
    }
    private void sendSucceed(){
        if(hasSend) return;
        corePlayer.respones(null);
        hasSend = false;
    }
}
