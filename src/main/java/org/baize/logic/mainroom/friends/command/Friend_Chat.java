package org.baize.logic.mainroom.friends.command;

import org.baize.server.message.IProtostuff;
import org.baize.server.message.OperateCommandAbstract;
import org.baize.utils.assemblybean.annon.Protocol;

/**
 * 作者： 白泽
 * 时间： 2017/11/9.
 * 描述：
 */
@Protocol(id = "6")
public class Friend_Chat extends OperateCommandAbstract {
    private final int id;
    private final String msg;

    public Friend_Chat(int id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    @Override
    public IProtostuff execute() {
        return null;
    }
}
