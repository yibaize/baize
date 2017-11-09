package org.baize.logic.mainroom.rank.manaer;

import org.apache.commons.lang3.ArrayUtils;
import org.baize.dao.manager.PersistPlayerMapper;
import org.baize.dao.model.PlayerEntity;
import org.baize.dao.model.PlayerInfo;
import org.baize.dao.sqlmapper.PlayerMapper;
import org.baize.logic.mainroom.friends.Dto.OtherInfoDto;
import org.baize.utils.SpringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 作者： 白泽
 * 时间： 2017/11/9.
 * 描述：
 */
@Service
public class RankManager {
    public static RankManager getInstance(){
        return SpringUtils.getBean(RankManager.class);
    }
    private List<OtherInfoDto> ranks = new ArrayList<>(20);
    public synchronized void shor(){
        PlayerMapper mapper = SpringUtils.getBean(PlayerMapper.class);
        List<PersistPlayerMapper> allPlayer = mapper.selectAll();
        List<PlayerEntity> entities = new ArrayList<>();
        for (PersistPlayerMapper playerMapper:allPlayer){
            entities.add(playerMapper.playerEntity());
        }
        Collections.sort(entities);
        int count = entities.size() > 20 ? 20 : entities.size();
        for (int i = 0;i<count;i++){
            ranks.add(assembly(entities.get(i)));
        }
    }

    public List<OtherInfoDto> getRanks() {
        return ranks;
    }
    public OtherInfoDto assembly(PlayerEntity entity){
        OtherInfoDto dto = new OtherInfoDto();
        dto.setId(entity.getId());
        dto.setAccount(entity.getPlayerInfo().getAccount());
        dto.setName(entity.getPlayerInfo().getName());
        dto.setLoginType(entity.getPlayerInfo().getLoginType());
        dto.setPhon(entity.getPlayerInfo().getPhon());
        dto.setRank(entity.getPlayerInfo().getRank());

        dto.setVip(entity.getWeath().getVip());
        dto.setGold(entity.getWeath().getGold());
        dto.setVip(entity.getWeath().getVip());
        return dto;
    }
}
