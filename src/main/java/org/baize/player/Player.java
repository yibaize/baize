package org.baize.player;

import org.baize.dao.model.PlayerEntity;

/**
 * 作者： 白泽
 * 时间： 2017/11/27.
 * 描述：
 */
public abstract class Player implements IPlayer {
    private PlayerEntity entity;

    public Player(PlayerEntity entity) {
        this.entity = entity;
    }

    @Override
    public int id() {
        return entity.playerInfo().getId();
    }
    @Override
    public String name() {
        return entity.playerInfo().getName();
    }

    @Override
    public String account() {
        return entity.playerInfo().getAccount();
    }

    @Override
    public int loginType() {
        return entity.playerInfo().getLoginType();
    }

    @Override
    public PlayerEntity entity() {
        return entity;
    }
}
