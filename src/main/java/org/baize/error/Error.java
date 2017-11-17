package org.baize.error;

import io.netty.channel.Channel;
import org.apache.log4j.Logger;
import org.baize.server.manager.Response;
import org.baize.utils.ProtostuffUtils;

/**
 * 作者： 白泽
 * 时间： 2017/11/8.
 * 描述：
 */
public class Error {
    private Channel ctx;
    public Error(Channel ctx) {
        this.ctx = ctx;
    }
    public void err(int id){
        this.response(id);
        throw new RuntimeException();
    }
    private String getMsg(int id){
        AppErrorDataTable code = AppErrorDataTable.get(id);
        return code.getValue();
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
