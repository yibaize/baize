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
    public static boolean putByCtx(Channel ctx,CorePlayer corePlayer){
        if(!ctxPlayer.containsKey(ctx)){
            ctxPlayer.put(ctx,corePlayer);
            return true;
        }
        return false;
    }
    public static CorePlayer getByCtx(Channel ctx){
        if(ctxPlayer.containsKey(ctx)){
            return ctxPlayer.get(ctx);
        }
        return null;
    }
    public static boolean putById(int id,CorePlayer corePlayer){
        if(!idPlayer.containsKey(id)){
            idPlayer.put(id,corePlayer);
            return true;
        }
        return false;
    }
    public static CorePlayer getById(int id){
        if(idPlayer.containsKey(id)){
            return idPlayer.get(id);
        }
        return null;
    }
}
