package org.baize.logic;

import io.netty.channel.Channel;
import org.baize.server.message.MessageAb;

/**
 * 作者： 白泽
 * 时间： 2017/11/3.
 * 描述：
 */
public class Test extends MessageAb {
    private final String l;
    private final int b;
    public Test(String l, int b) {
        this.l = l;
        this.b = b;
    }

    public void execute() {

    }
}
