package org.baize.server.message;
import org.baize.error.GenaryAppError;
import org.baize.error.LogAppError;
import org.baize.room.RoomPlayer;
import org.baize.server.manager.Request;
import org.baize.server.manager.Response;
import org.baize.server.session.ISession;
import org.baize.server.session.SessionManager;
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
    public static void messageRecieve(ISession session,Request request){
        try {
            short id = request.getId();
            OperateCommandAbstract msg = OperateCommandRecive.getInstance().recieve(id,request.getData());
            if(msg == null)
                new LogAppError("数据接收错误");
            msg.setCmdId(id);
            msg.setSession(session);
            RoomPlayer roomPlayer = (RoomPlayer) session.getAttachment();
            if(roomPlayer != null)
                msg.roomPlayer(roomPlayer);
            msg.run();
        }catch (Exception e){
            //下发客户端
            if(e instanceof GenaryAppError) {
                GenaryAppError error = (GenaryAppError)e;
                Response response = new Response();
                response.setId((short) 404);
                byte[] buf = ProtostuffUtils.serializer(error.getErrorCode());
                response.setData(buf);
                session.write(response);
                LoggerUtils.getLogicLog().debug("数据接收错误", error);
            }else if(e instanceof LogAppError) {
                LoggerUtils.getLogicLog().error(e.getMessage(), e);
            }else {
                LoggerUtils.getLogicLog().error(e);
            }
        }
    }

    public static void main(String[] args) {
        GenaryAppError a = new GenaryAppError(1);
        System.out.println(a instanceof GenaryAppError);
    }
}
