package org.baize.logic.room;

import org.baize.dao.model.CorePlayer;
import org.baize.logic.ILogic;
import org.baize.server.manager.Response;
import org.baize.server.message.IProtostuff;
import org.baize.utils.DateUtils;
import org.baize.utils.ProtostuffUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 作者： 白泽
 * 时间： 2017/11/8.
 * 描述：
 */
public interface IRoom extends ILogic{
    int getId();
    default long currentTime(){
        return DateUtils.currentTime();
    }
    default long endTime() {
        return DateUtils.getFutureTimeMillis();
    }
    CorePlayer banker();
    List<CorePlayer> bankerUpList();
    RoomBottomDto roomBottom();
    /**
     *
     * 进入房间
     * @param corePlayer
     * @return
     */
    default boolean into(CorePlayer corePlayer){
        if(getSet().contains(corePlayer))
            return false;
        getSet().add(corePlayer);
        notifyAllx((short) 102,corePlayer,null);
        return true;
    }
    /**
     * 离开房间
     * @return
     */
    boolean leave(CorePlayer corePlayer);
    default void notifyAllx(short id,IProtostuff pro){
        if(getSet() != null) {
            Iterator<CorePlayer> iterator = getSet().iterator();
            while (iterator.hasNext()) {
                //发送消息
                iterator.next().respones(id,pro);
            }
        }
    }
    /**
     * 通知处自己之外的玩家
     * @param corePlayer
     * @param
     */
    default void notifyAllx(short id,CorePlayer corePlayer,IProtostuff pro){
        if(getSet() != null) {
            Iterator<CorePlayer> iterator = getSet().iterator();
            while (iterator.hasNext()) {
                if (iterator.next().equals(corePlayer)) continue;
                //发送消息
                iterator.next().respones(id,pro);
            }
        }
    }
    Set<CorePlayer> getSet();
    void settleAccounts();
    void bottom(int position,int num,CorePlayer corePlayer);
    /**
     * 发牌
     */
    default void perflop(){}
}
