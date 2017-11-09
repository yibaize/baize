package org.baize.logic.room;

import org.baize.dao.model.CorePlayer;
import org.baize.logic.ILogic;
import org.baize.server.manager.Response;
import org.baize.server.message.IProtostuff;
import org.baize.utils.DateUtils;
import org.baize.utils.ProtostuffUtils;

import java.util.Iterator;
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
    };
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
    default void notifyAllx(short id,IProtostuff pro){
        if(getSet() != null) {
            Iterator<CorePlayer> iterator = getSet().iterator();
            Response response = new Response();
            response.setId(id);
            byte[] buf = ProtostuffUtils.serializer(pro);
            response.setData(buf);
            while (iterator.hasNext()) {
                //发送消息
                iterator.next().respones(pro);
            }
        }
    };
    /**
     * 通知处自己之外的玩家
     * @param corePlayer
     * @param
     */
    default void notifyAllx(short id,CorePlayer corePlayer,IProtostuff pro){
        if(getSet() != null) {
            Iterator<CorePlayer> iterator = getSet().iterator();
            Response response = new Response();
            response.setId(id);
            byte[] buf = ProtostuffUtils.serializer(pro);
            response.setData(buf);
            while (iterator.hasNext()) {
                if (iterator.next().equals(corePlayer)) continue;
                //发送消息
                iterator.next().respones(pro);
            }
        }
    };
    Set<CorePlayer> getSet();
}
