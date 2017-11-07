package org.baize.utils.assemblybean.test;

import org.baize.server.message.MessageAb;
import org.baize.utils.assemblybean.annon.Protocol;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/11/4******
 * 描述：
 */
@Protocol(id = "1")
public class E extends MessageAb {
    private final boolean b;

    public E(boolean b) {
        this.b = b;
    }

    public boolean isB() {
        return b;
    }

    @Override
    public String toString() {
        return "E{" +
                "b=" + b +
                '}';
    }

    @Override
    public void execute() {

    }
}
