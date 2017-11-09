package org.baize.server.message;
import io.netty.channel.Channel;
import org.baize.dao.model.CorePlayer;
import org.baize.dao.model.PersistPlayer;
import org.baize.server.manager.Request;
import org.springframework.stereotype.Component;
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
            System.out.println("协议数据接收错误");
        msg.setCtx(cxt);
        msg.setCmdId((short) id);
        if(PersistPlayer.getByCtx(cxt) != null)
            msg.setCorePlayer(PersistPlayer.getByCtx(cxt));
        //用户量少不用业务线程
        msg.execute();
       // WorkTaskPoolManager.getInstance().submit(msg);
    }
}
