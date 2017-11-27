package org.baize.player;

import org.baize.dao.model.PlayerEntity;

/**
 * 作者： 白泽
 * 时间： 2017/11/27.
 * 描述：
 */
public interface IPlayer {
    int id();
    String name();
    String account();
    int loginType();
    PlayerEntity entity();
}
