package org.baize.logic.room;

import org.apache.log4j.Logger;
import org.baize.dao.model.CorePlayer;
import org.baize.dao.model.PersistPlayer;
import org.baize.logic.IFactory;
import org.baize.utils.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 作者： 白泽
 * 时间： 2017/11/8.
 * 描述：
 */
@Service
public class RoomFactory implements IFactory<RoomAbstract>{
    public static RoomFactory getInstance(){
        return SpringUtils.getBean(RoomFactory.class);
    }
    private Map<Integer,RoomAbstract> room = new HashMap<>(5);
    private static Logger logger = Logger.getLogger(RoomFactory.class);
    @Autowired
    private Set<RoomAbstract> set;
    @Override
    public <T> T getBean(Class<T> clazz) {
        if(room.containsKey(clazz))
            return (T) room.get(clazz);
        return null;
    }

    @Override
    public <T> T getBean(int id) {
        if(room.containsKey(id))
            return (T) room.get(id);
        return null;
    }

    @PostConstruct
    private void  init(){
        if(set != null){
            Iterator<RoomAbstract> iterator = set.iterator();
            while (iterator.hasNext()){
                RoomAbstract iRoom = iterator.next();
                room.put(iRoom.getRoomId(),iRoom);
            }
        }
        set.clear();
    }

    /**
     * 下线通知
     * @param corePlayer
     */
    public void notifyOfferLine(CorePlayer corePlayer){
        for (Map.Entry<Integer,RoomAbstract> e:room.entrySet()){
            e.getValue().leaveRoom(corePlayer);
            PersistPlayer.removePlayer(corePlayer);
            //添加下线列表
            PersistPlayer.putOffLinePlayer(corePlayer);
        }
    }
}
