package org.baize.server.message;

import io.netty.channel.Channel;
import org.apache.commons.lang3.StringUtils;
import org.baize.server.manager.Request;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 作者： 白泽
 * 时间： 2017/11/3.
 * 描述：
 */
@Component
public class TcpHandler {
    public static void messageRecieve(Channel cxt, Request request){
        int id = Integer.parseInt(request.getData()[0]);

      //  MessageAb msg = recieve.recieve(id,request.getData());
//        if(msg == null)
//            System.out.println("协议数据接收错误");
//        msg.setCtx(cxt);
//        WorkTaskPoolManager.getInstance().submit(msg);
    }

    public static void main(String[] args) {
        String[] s = StringUtils.split("");
        System.out.println(Arrays.toString(s));
    }
}
