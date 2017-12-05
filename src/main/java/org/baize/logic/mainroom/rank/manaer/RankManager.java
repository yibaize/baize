package org.baize.logic.mainroom.rank.manaer;

import org.baize.dao.manager.PersistPlayerMapper;
import org.baize.dao.model.PlayerEntity;
import org.baize.dao.sqlmapper.PlayerMapper;
import org.baize.logic.mainroom.friends.Dto.OtherInfoDto;
import org.baize.logic.mainroom.rank.dto.RankDto;
import org.baize.server.session.SessionManager;
import org.baize.utils.SpringUtils;
import org.baize.worktask.IDailyTimer;
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
public class RankManager implements IDailyTimer{
    public static RankManager getInstance(){
        return SpringUtils.getBean(RankManager.class);
    }
    private List<OtherInfoDto> ranks = new ArrayList<>(20);
    private synchronized void shor(){
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

    public synchronized List<OtherInfoDto> getRanks() {
        if(ranks == null)
            ranks = new ArrayList<>();
        return ranks;
    }
    public OtherInfoDto assembly(PlayerEntity entity){
        OtherInfoDto dto = new OtherInfoDto();
        dto.setId(entity.getId());
        dto.setAccount(entity.playerInfo().getAccount());
        dto.setName(entity.playerInfo().getName());
        dto.setLoginType(entity.playerInfo().getLoginType());
        dto.setPhon(entity.playerInfo().getPhon());
        dto.setRank(entity.playerInfo().getRank());

        dto.setGold(entity.weath().getGold());
        return dto;
    }
    private void notifyAllx(){
        RankDto rankDto = new RankDto(ranks);
        SessionManager.notifyAllx((short)107,rankDto);
    }
    @Override
    public void executor() {
        shor();
        notifyAllx();
    }
}
