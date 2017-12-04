package org.baize.dao.model;

import io.netty.channel.Channel;
import org.baize.server.GameServer;
import org.baize.server.message.IProtostuff;

/**
 * 作者： 白泽
 * 时间： 2017/11/6.
 * 描述：
 */
public class CorePlayer {
    private int id;
    private int scenesId;
    private PlayerEntity entity;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
