package org.baize.logic.linestart;

import io.netty.channel.Channel;
import org.baize.server.manager.Response;
import org.baize.server.message.IProtostuff;
import org.baize.utils.ProtostuffUtils;

/**
 * 作者： 白泽
 * 时间： 2017/11/15.
 * 描述：断线重连
 */
public class LineStateManager {
    public static void offLine(Channel ctx){
//        CorePlayer corePlayer = PersistPlayer.getByCtx(ctx);
//        if(corePlayer != null){
//            PersistPlayer.putOffLinePlayer(corePlayer);
//            PersistPlayer.removePlayer(corePlayer);
//            notifyx(ctx,401,null);
//        }
    }
    private static void notifyx(Channel ctx, int id, IProtostuff pro){
        Response response = new Response();
        response.setId((short) id);
        byte[] buf = null;
        if(pro != null){
            buf = ProtostuffUtils.serializer(pro);
        }
        response.setData(buf);
        ctx.writeAndFlush(response);
        ctx.closeFuture();
    }
    public static void onLine(int id){
//        CorePlayer corePlayer = PersistPlayer.getOffLinePlayer(id);
//        if(corePlayer != null){
//            PersistPlayer.putPlayer(corePlayer);
//            PersistPlayer.removeOffLinePlayer(corePlayer);
//        }
    }
}
