package org.baize.dao.model;

import io.netty.channel.Channel;

import java.util.*;
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
    private static ConcurrentMap<String,CorePlayer> accountPlayer = new ConcurrentHashMap<>();

    private static ConcurrentMap<Integer,CorePlayer> offLinePlayer = new ConcurrentHashMap<>();
    private static ConcurrentMap<String,CorePlayer> offLineAccountPlayer = new ConcurrentHashMap<>();
    public static void putPlayer(CorePlayer corePlayer){
        idPlayer.put(corePlayer.getId(),corePlayer);
        ctxPlayer.put(corePlayer.getCtx(),corePlayer);
        accountPlayer.put(corePlayer.entity().playerInfo().getAccount(),corePlayer);
    }
    public static CorePlayer getByCtx(Channel ctx){
        if(ctxPlayer.containsKey(ctx)){
            return ctxPlayer.get(ctx);
        }
        return null;
    }
    public static CorePlayer getById(int id){
        if(idPlayer.containsKey(id)){
            return idPlayer.get(id);
        }
        return null;
    }
    public static CorePlayer getByAccount(String account){
        if(accountPlayer.containsKey(account)){
            return accountPlayer.get(account);
        }
        return null;
    }
    public static void removePlayer(CorePlayer corePlayer){
        if(ctxPlayer.containsKey(corePlayer.getCtx()))
            ctxPlayer.remove(corePlayer.getCtx());
        if(idPlayer.containsKey(corePlayer.getId()))
            idPlayer.remove(corePlayer.getId());
        if(accountPlayer.containsKey(corePlayer.entity().playerInfo().getAccount()))
            accountPlayer.remove(corePlayer.entity().playerInfo().getAccount());
    }

    public static CorePlayer getOffLinePlayer(int id) {
        if(offLinePlayer.containsKey(id))
            return offLinePlayer.get(id);
        return null;
    }
    public static CorePlayer getOffLineAccountPlayer(String account) {
        if(offLineAccountPlayer.containsKey(account))
            return offLineAccountPlayer.get(account);
        return null;
    }
    public static void putOffLinePlayer(CorePlayer corePlayer) {
        if(!offLinePlayer.containsKey(corePlayer.getId())){
            offLinePlayer.put(corePlayer.getId(),corePlayer);
        }
        if(!offLineAccountPlayer.containsKey(corePlayer.entity().playerInfo().getAccount())){
            offLineAccountPlayer.put(corePlayer.entity().playerInfo().getAccount(),corePlayer);
        }
    }
    public static void removeOffLinePlayer(CorePlayer corePlayer) {
        if(!offLinePlayer.containsKey(corePlayer.getId())){
            offLinePlayer.remove(corePlayer.getId());
        }
        if(!offLineAccountPlayer.containsValue(corePlayer.entity().playerInfo().getAccount())){
            offLineAccountPlayer.remove(corePlayer.entity().playerInfo().getAccount());
        }
    }
    public static List<CorePlayer> ctxPlayer(){
            return (List<CorePlayer>) ctxPlayer.values();
    }
    public static List<CorePlayer> offLinePlayer(){
        return (List<CorePlayer>) offLinePlayer.values();
    }
    public static Set<CorePlayer> playerByNotSelf(CorePlayer corePlayer,Set<CorePlayer> corePlayers){
        Set<CorePlayer> players = new HashSet<>(corePlayers.size()-1);
        Iterator<CorePlayer> iterator = corePlayers.iterator();
        while (iterator.hasNext()){
            CorePlayer player = iterator.next();
            if(player.getId() != corePlayer.getId())
                players.add(player);
        }
        return players;
    }
}
