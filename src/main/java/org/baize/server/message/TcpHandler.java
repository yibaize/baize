package org.baize.server.message;
import io.netty.channel.Channel;
import org.baize.dao.model.PersistPlayer;
import org.baize.error.GenaryAppError;
import org.baize.error.LogAppError;
import org.baize.server.manager.Request;
import org.baize.server.manager.Response;
import org.baize.server.session.ISession;
import org.baize.utils.LoggerUtils;
import org.baize.utils.ProtostuffUtils;
import org.springframework.stereotype.Component;

/**
 * 作者： 白泽
 * 时间： 2017/11/3.
 * 描述：
 */
@Component
public class TcpHandler {
    public static void messageRecieve(Channel cxt, Request request){
        try {
            int id = request.getId();
            OperateCommandAbstract msg = OperateCommandRecive.getInstance().recieve(id,request.getData());
            if(msg == null){
                throw new LogAppError("数据接收错误");
            }
            msg.setCtx(cxt);
            msg.setCmdId((short) id);
            if(PersistPlayer.getByCtx(cxt) != null)
                msg.setCorePlayer(PersistPlayer.getByCtx(cxt));
            //用户量少不用业务线程
            msg.run();
            //WorkTaskPoolManager.getInstance().submit(msg);
        }catch (GenaryAppError e){
            //下发客户端
            Response response = new Response();
            response.setId((short) 404);
            byte[] buf = ProtostuffUtils.serializer(e.getErrorCode());
            cxt.writeAndFlush(response);
            LoggerUtils.getLogicLog().debug("数据接收错误",e);
        }catch (LogAppError e){
            //添加日志
            LoggerUtils.getLogicLog().debug(e.getMessage(),e);
        }
    }
    public static void messageRecieve(ISession session,Request request){

    }
}
