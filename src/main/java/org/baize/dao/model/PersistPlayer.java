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
    public static void putByCtx(Channel ctx,CorePlayer corePlayer){
        ctxPlayer.put(ctx,corePlayer);
    }
    public static CorePlayer getByCtx(Channel ctx){
        if(ctxPlayer.containsKey(ctx)){
            return ctxPlayer.get(ctx);
        }
        return null;
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
}
