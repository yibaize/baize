package org.baize.dao.model;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 作者： 白泽
 * 时间： 2017/11/6.
 * 描述：
 */
public class PersistPlayer {
    private static ConcurrentMap<Channel,CorePlayer> ctxPlayer = new ConcurrentHashMap<>();
    private static ConcurrentMap<Integer,CorePlayer> idPlayer = new ConcurrentHashMap<>();
    private static ConcurrentMap<Integer,CorePlayer> offLinePlayer = new ConcurrentHashMap<>();
    public static void putByCtx(Channel ctx,CorePlayer corePlayer){
        ctxPlayer.put(ctx,corePlayer);
    }
    public static CorePlayer getByCtx(Channel ctx){
        if(ctxPlayer.containsKey(ctx)){
            return ctxPlayer.get(ctx);
        }
        return null;
    }
    public static void removeByCtx(Channel ctx){
        if(ctxPlayer.containsKey(ctx)){
            ctxPlayer.remove(ctx);
        }
    }
    public static void putById(int id,CorePlayer corePlayer){
        idPlayer.put(id,corePlayer);
    }
    public static CorePlayer getById(int id){
        if(idPlayer.containsKey(id)){
            return idPlayer.get(id);
        }
        return null;
    }
    public static void removeById(int id){
        if(idPlayer.containsKey(id)){
            idPlayer.remove(id);
        }
    }
    public static CorePlayer getOffLinePlayer(int id) {
        if(offLinePlayer.containsKey(id))
            return offLinePlayer.get(id);
        return null;
    }

    public static void putOffLinePlayer(CorePlayer corePlayer) {
        if(!offLinePlayer.containsValue(corePlayer)){
            offLinePlayer.put(corePlayer.getId(),corePlayer);
        }
    }
    public static void removeOffLinePlayer(CorePlayer corePlayer) {
        if(!offLinePlayer.containsValue(corePlayer)){
            offLinePlayer.remove(corePlayer.getId());
        }
    }
}
