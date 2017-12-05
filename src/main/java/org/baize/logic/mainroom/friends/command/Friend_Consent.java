package org.baize.logic.mainroom.friends.command;

import org.baize.dao.manager.PersistPlayerMapper;
import org.baize.dao.sqlmapper.PlayerMapper;
import org.baize.error.AppErrorCode;
import org.baize.error.GenaryAppError;
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
@Protocol(id = "5")
public class Friend_Consent extends OperateCommandAbstract {
    private final int id;

    public Friend_Consent(int id) {
        this.id = id;
    }

    @Override
    public IProtostuff execute() {
//        Friends friends = player().entity().friends();
//        friends.add(id);
//        CorePlayer corePlayer = PersistPlayer.getById(id);
//        Friends friends1 = null;
//        if(corePlayer != null){
//            friends1 = corePlayer.entity().friends();
//        }else {
//            PlayerMapper mapper = SpringUtils.getBean(PlayerMapper.class);
//            PersistPlayerMapper playerMapper = mapper.selectOneForId(id);
//            friends1 = (Friends) playerMapper.persist(friends1);
//        }
//        if(friends1 == null)
//            new GenaryAppError(AppErrorCode.DATA_ERR);
//        friends1.add(player().getId());
//        friends.update();
//        friends1.update();
        return null;
    }
}
