package org.baize.logic.mainroom.info;

import org.baize.dao.model.PlayerInfo;
import org.baize.server.message.IProtostuff;
import org.baize.server.message.OperateCommandAbstract;
import org.baize.utils.assemblybean.annon.Protocol;

/**
 * 作者： 白泽
 * 时间： 2017/11/9.
 * 描述：
 */
@Protocol(id = "2")
public class Info_Reset extends OperateCommandAbstract {
    private final String name;
    private final String password;
    private final String describe;
    private final int head;
    private final String phon;

    public Info_Reset(String name, String password, String describe, int head, String phon) {
        this.name = name;
        this.password = password;
        this.describe = describe;
        this.head = head;
        this.phon = phon;
    }

    @Override
    public IProtostuff execute() {
        PlayerInfo info = roomPlayer().entity().playerInfo();
        info.setName(name);
        info.setPassword(password);
        info.setHead(head);
        info.setDiscibe(describe);
        info.setPhon(phon);
        info.update();
        return null;
    }
}
