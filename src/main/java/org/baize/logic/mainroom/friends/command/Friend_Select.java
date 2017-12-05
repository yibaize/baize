package org.baize.logic.mainroom.friends.command;

import org.baize.dao.manager.PersistPlayerMapper;
import org.baize.dao.model.PlayerEntity;
import org.baize.dao.sqlmapper.PlayerMapper;
import org.baize.logic.mainroom.friends.Dto.OtherInfoDto;
import org.baize.logic.mainroom.friends.Dto.OtherInfosDto;
import org.baize.logic.mainroom.friends.module.Friends;
import org.baize.logic.mainroom.rank.manaer.RankManager;
import org.baize.server.message.IProtostuff;
import org.baize.server.message.OperateCommandAbstract;
import org.baize.utils.SpringUtils;
import org.baize.utils.assemblybean.annon.Protocol;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： 白泽
 * 时间： 2017/11/9.
 * 描述：
 */
@Protocol(id = "7")
public class Friend_Select extends OperateCommandAbstract {
    @Override
    public IProtostuff execute() {
//        Friends friends = player().entity().friends();
//        List<OtherInfoDto> friendList = new ArrayList<>();
//        List<OtherInfoDto> applyList = new ArrayList<>();
//        for (int id:friends.friendIds()){
//            friendList.add(RankManager.getInstance().assembly(find(id)));
//        }
//        for (int id : friends.apply()){
//            applyList.add(RankManager.getInstance().assembly(find(id)));
//        }
//        return (new OtherInfosDto(friendList,applyList));
        return null;
    }
    private PlayerEntity find(int id){
        PlayerMapper mapper = SpringUtils.getBean(PlayerMapper.class);
        PlayerEntity entity = null;
//        CorePlayer friend = PersistPlayer.getById(id);
//        if(friend != null){
//            entity = friend.entity();
//        }else {
//            PersistPlayerMapper playerMapper = mapper.selectOneForId(id);
//            if(playerMapper != null);
//                entity = playerMapper.playerEntity();
//        }
        return entity;
    }

}
