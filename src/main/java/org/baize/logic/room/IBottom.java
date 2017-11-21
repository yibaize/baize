package org.baize.logic.room;

import org.baize.dao.model.CorePlayer;
import org.baize.logic.ILogic;
import org.baize.logic.room.impl.BottomPosition;
import org.baize.server.message.IProtostuff;

import java.util.Map;
import java.util.Set;

/**
 * 作者： 白泽
 * 时间： 2017/11/21.
 * 描述：玩法接口
 */
public interface IBottom extends ILogic{
    /**
     * 下注
     * @param num 本次下注金额
     * @param corePlayer 下注玩家
     */
    void bottom(int position,int num,CorePlayer corePlayer);

    /**
     * 本牌堆所有金额
     * @return
     */
    int bottomAllNumber(int position);

    /**
     * 玩家在牌堆下注的金额
     * @param player
     * @return
     */
    int playerBottomNumber(int position,CorePlayer player);

    /**
     * 清空本局下注记录
     */
    void clearBottom();

    /**
     * 有玩家离开时也要清除下注记录
     * @param corePlayer
     */
    void leave(CorePlayer corePlayer);
    IProtostuff bottomInfo();
    void players(Set<CorePlayer> players);
    Map<Integer,BottomPosition> bottomMap();
}
