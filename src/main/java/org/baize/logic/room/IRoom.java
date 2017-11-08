package org.baize.logic.room;

import org.baize.dao.model.CorePlayer;
import org.baize.server.manager.Response;

/**
 * 作者： 白泽
 * 时间： 2017/11/8.
 * 描述：
 */
public interface IRoom {
    int getId();
    long currentTime();
    long endTime();
    long jackpot();
    /**
     * 离开房间
     * @param corePlayer
     * @return
     */
    boolean into(CorePlayer corePlayer);
    /**
     * 进入房间
     * @return
     */
    boolean leave(CorePlayer corePlayer);
    /**
     *  结算
     */
    void settleAccounts();
    void clear();
    void notifyAllx(Response response);
    void notifyAllx(CorePlayer corePlayer,Response response);
}
