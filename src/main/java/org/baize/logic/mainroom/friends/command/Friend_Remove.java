package org.baize.logic.mainroom.friends.command;

import org.baize.logic.mainroom.friends.module.Friends;
import org.baize.server.message.CommandAb;
import org.baize.utils.assemblybean.annon.Protocol;

/**
 * 作者： 白泽
 * 时间： 2017/11/9.
 * 描述：
 */
@Protocol(id = "4")
public class Friend_Remove extends CommandAb {
    private final int id;

    public Friend_Remove(int id) {
        this.id = id;
    }

    @Override
    public void execute() {
        Friends friends = player().entity().getFriends();
        friends.remove(id);
    }
}
