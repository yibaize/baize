package org.baize.server.message;

import io.netty.channel.Channel;
import org.apache.commons.lang3.StringUtils;
import org.baize.logic.Test;
import org.baize.server.manager.Request;
import org.baize.worktask.WorkTaskPoolManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 作者： 白泽
 * 时间： 2017/11/3.
 * 描述：
 */
@Component
public class TcpHandler {
    @Autowired
    private MessageRecieve recieve;
    public void messageRecieve(Channel cxt, Request request){
        int id = Integer.parseInt(request.getData()[0]);
        MessageAb msg = recieve.recieve(id,request.getData());
        if(msg == null)
            System.out.println("协议数据接收错误");
        msg.setCtx(cxt);
        WorkTaskPoolManager.getInstance().submint(msg);
    }

    public static void main(String[] args) {
        String[] s = StringUtils.split("a,d,f,g,h,wer,ert");
        System.out.println(Arrays.toString(s));
    }
}
