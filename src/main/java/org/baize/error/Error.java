package org.baize.error;

import com.sun.xml.internal.bind.v2.model.core.ID;
import io.netty.channel.Channel;
import org.apache.log4j.Logger;
import org.baize.server.manager.Response;
import org.baize.utils.ProtostuffUtils;
import org.baize.utils.excel.User;

import javax.management.RuntimeErrorException;
import java.lang.reflect.Field;

/**
 * 作者： 白泽
 * 时间： 2017/11/8.
 * 描述：
 */
public class Error {
    private static Logger logger;
    private Channel ctx;
    public Error(Class<?> clazz) {
        logger = Logger.getLogger(clazz);
    }
    public Error(Class<?> clazz,Channel ctx) {
        logger = Logger.getLogger(clazz);
        this.ctx = ctx;
    }
    public void debug(int id){
        this.response(id);
        throw new RuntimeException(getMsg(id));
    }
    public void debug(int id,Throwable t){
        this.response(id);
        throwException(id,t);
    }
    public void err(int id){
        this.response(id);
        logger.error(getMsg(id));
        throw new RuntimeException();
    }
    public void err(int id,Throwable t){
        this.response(id);
        logger.error(getMsg(id),t);
        throw new RuntimeException();
    }
    private String getMsg(int id){
        ErrorCode code = ErrorCode.get(id);
        return code.getMsg();
    }
    private void throwException(int id,Throwable t){
        throw new RuntimeException(getMsg(id),t);
    }
    private void response(int errId){
        Response response = new Response();
        response.setId((short) 404);
        byte[] buf = ProtostuffUtils.serializer(errId);
        this.ctx.write(response);
     }
}
