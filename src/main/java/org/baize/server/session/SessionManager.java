package org.baize.server.session;

import org.baize.server.manager.Response;
import org.baize.server.message.IProtostuff;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 作者： 白泽
 * 时间： 2017/12/1.
 * 描述：
 */
public class SessionManager {
    private static final ConcurrentHashMap<Integer, ISession> onlineSessions = new ConcurrentHashMap<>();
    public static boolean putSession(Integer playerId,ISession session){
        boolean success = false;
        if(!onlineSessions.containsKey(playerId))
            success = onlineSessions.putIfAbsent(playerId,session) == null ? true : false;
        return success;
    }
    public static ISession removeSession(int playerId){
        return onlineSessions.remove(playerId);
    }
    /**
     * 发送消息[protoBuf协议]
     * @param <T>
     * @param playerId
     * @param message
     */
    public static <T extends IProtostuff> void sendMessage(long playerId, short module, short cmd, T message){
        ISession session = onlineSessions.get(playerId);
        if (session != null && session.isConnected()) {
            Response response = new Response();
            session.write(response);
        }
    }
    /**
     * 是否在线
     * @param playerId
     * @return
     */
    public static boolean isOnlinePlayer(long playerId){
        return onlineSessions.containsKey(playerId);
    }

    /**
     * 获取所有在线玩家
     * @return
     */
    public static Set<Integer> getOnlinePlayers() {
        return Collections.unmodifiableSet(onlineSessions.keySet());
    }
}
