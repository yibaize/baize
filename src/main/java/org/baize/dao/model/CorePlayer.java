package org.baize.dao.model;

import io.netty.channel.Channel;
import org.baize.logic.room.IRoom;

/**
 * 作者： 白泽
 * 时间： 2017/11/6.
 * 描述：
 */
public class CorePlayer {
    private int id;
    private Channel ctx;
    private int scenesId;
    private PlayerEntity entity;
    private IRoom room;
    public void respones(Object o){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Channel getCtx() {
        return ctx;
    }

    public void setCtx(Channel ctx) {
        this.ctx = ctx;
    }

    public IRoom getRoom() {
        return room;
    }

    public void setRoom(IRoom room) {
        this.room = room;
    }

    public int getScenesId() {
        return scenesId;
    }

    public void setScenesId(int scenesId) {
        this.scenesId = scenesId;
    }

    public PlayerEntity getEntity() {
        return entity;
    }
    public PlayerEntity entity() {
        return entity;
    }
    public void setEntity(PlayerEntity entity) {
        this.entity = entity;
    }
}
