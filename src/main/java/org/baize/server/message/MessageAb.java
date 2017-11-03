package org.baize.server.message;

import io.netty.channel.Channel;

/**
 * 作者： 白泽
 * 时间： 2017/11/3.
 * 描述：
 */
public abstract class MessageAb implements IMessage {
    private int scenesId;
    private Channel ctx;
    public MessageAb(int scenesId, Channel ctx) {
        this.scenesId = scenesId;
        this.ctx = ctx;
    }

    public MessageAb() {
    }

    public int getScenesId() {
        return scenesId;
    }

    public void setScenesId(int scenesId) {
        this.scenesId = scenesId;
    }

    public Channel getCtx() {
        return ctx;
    }

    public void setCtx(Channel ctx) {
        this.ctx = ctx;
    }

    public void run() {
        this.execute();
    }
}
