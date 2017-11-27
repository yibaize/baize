package org.baize.logic.mainroom.friends.command;

import org.baize.dao.manager.PersistPlayerMapper;
import org.baize.dao.model.CorePlayer;
import org.baize.dao.model.PersistPlayer;
import org.baize.dao.sqlmapper.PlayerMapper;
import org.baize.error.AppErrorCode;
import org.baize.error.Error;
import org.baize.logic.mainroom.friends.module.Friends;
import org.baize.server.message.IProtostuff;
import org.baize.server.message.OperateCommandAbstract;
import org.baize.utils.SpringUtils;
import org.baize.utils.assemblybean.annon.Protocol;

/**
 * 作者： 白泽
 * 时间： 2017/11/9.
 * 描述：
 */
@Protocol(id = "3")
public class Friend_Add extends OperateCommandAbstract {
    private final int id;
    public Friend_Add(int id) {
        this.id = id;
    }

    @Override
    public IProtostuff execute() {
        CorePlayer corePlayer = PersistPlayer.getById(id);
        Friends friends1 = null;
        if(corePlayer != null){
            friends1 = corePlayer.entity().friends();
        } else {
            PlayerMapper mapper = SpringUtils.getBean(PlayerMapper.class);
            PersistPlayerMapper playerMapper = mapper.selectOneForId(id);
            friends1 = (Friends) playerMapper.persist(friends1);
        }
        if (friends1 == null)
            new Error(this.getCtx()).err(AppErrorCode.DATA_ERR);
        friends1.apply(this.player().getId());
        friends1.update();
        return null;
    }
}
