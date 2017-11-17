package org.baize.server.message;
import io.netty.channel.Channel;
import org.baize.dao.model.CorePlayer;
import org.baize.dao.model.PersistPlayer;
import org.baize.server.manager.Request;
import org.baize.server.manager.Response;
import org.baize.utils.LoggerUtils;
import org.baize.worktask.impl.WorkTaskPoolManager;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 作者： 白泽
 * 时间： 2017/11/3.
 * 描述：
 */
@Component
public class TcpHandler {
    public static void messageRecieve(Channel cxt, Request request){
        int id = Integer.parseInt(request.getData()[0]);
        CommandAb msg = CommandRecive.getInstance().recieve(id,request.getData());
        if(msg == null)
            LoggerUtils.getLogicLog().debug("数据接收错误");
        msg.setCtx(cxt);
        msg.setCmdId((short) id);
        if(PersistPlayer.getByCtx(cxt) != null)
            msg.setCorePlayer(PersistPlayer.getByCtx(cxt));
        //用户量少不用业务线程
        msg.run();
        //WorkTaskPoolManager.getInstance().submit(msg);
    }
}
