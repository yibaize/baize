package org.baize.logic.mainroom.friends.command;

import org.baize.logic.mainroom.friends.module.Friends;
import org.baize.server.message.IProtostuff;
import org.baize.server.message.OperateCommandAbstract;
import org.baize.utils.assemblybean.annon.Protocol;

/**
 * 作者： 白泽
 * 时间： 2017/11/9.
 * 描述：
 */
@Protocol(id = "4")
public class Friend_Remove extends OperateCommandAbstract {
    private final int id;

    public Friend_Remove(int id) {
        this.id = id;
    }

    @Override
    public IProtostuff execute() {
//        Friends friends = player().entity().friends();
//        friends.remove(id);
        return null;
    }
}
