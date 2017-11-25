package org.baize.logic.BombFlower.manager;

import org.baize.EnumType.CardType;
import org.baize.arithmetic.BomFlower;
import org.baize.dao.model.CorePlayer;
import org.baize.logic.BombFlower.dto.CardResultDto;
import org.baize.logic.BombFlower.dto.CardResultsDto;
import org.baize.logic.card.data.Card;
import org.baize.logic.card.data.PersistCard;
import org.baize.logic.mainroom.friends.Dto.OtherInfoDto;
import org.baize.logic.mainroom.rank.dto.RankDto;
import org.baize.logic.mainroom.rank.manaer.RankManager;
import org.baize.logic.room.PlayAbstract;
import org.baize.server.GameServer;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 作者： 白泽
 * 时间： 2017/11/21.
 * 描述：
 */
public class BombPlay extends PlayAbstract {
    private CorePlayer banker;
    private ConcurrentLinkedQueue<CorePlayer> bankerLineUp;
    private Set<CorePlayer> playerSet;
    @Override
    public CardResultsDto end() {
        BomFlower.handler(getCardSet());
        CardResultsDto dtos = new CardResultsDto();
        List<CardResultDto> list = new ArrayList<>(5);
        Iterator<Card> iterator = getCardSet().iterator();
        while (iterator.hasNext()){
            Card card = iterator.next();
            CardResultDto dto = new CardResultDto();
            int[] ids = new int[3];
            List<PersistCard> p = card.getPersistCards();
            for (int i = 0;i<p.size();i++){
                ids[i] = p.get(i).getId();
            }
            dto.setId(card.getPosition());
            dto.setCardId(ids);
            dto.setType(card.getCardType().id());
            dto.setResult(card.getResult().id());
            list.add(dto);
        }
        dtos.setCardResultDtos(list);
        return dtos;
    }

    @Override
    public void roomPlayers(Set<CorePlayer> players) {
        this.playerSet = players;
    }

    /**
     * 上庄申请
     * @param corePlayer
     * @return
     */
    public boolean bankerLineUp(CorePlayer corePlayer){
        if(bankerLineUp == null)
            bankerLineUp = new ConcurrentLinkedQueue<>();
        if(!bankerLineUp.contains(corePlayer)){
            bankerLineUp.offer(corePlayer);
            return true;
        }
        return false;
    }
    private boolean checkBanker(){
        return banker == null;
    }
    /**
     * 需要同步
     * @return
     */
    private void bankerUp(){
        synchronized (banker) {
            if (checkBanker() && bankerLineUp != null || bankerLineUp.size() > 0) {
                banker = bankerLineUp.poll();
                RankDto dto = new RankDto();
                List<OtherInfoDto> other = new ArrayList<>(bankerLineUp.size());
                for (CorePlayer player:bankerLineUp){
                    OtherInfoDto otherInfoDto = RankManager.getInstance().assembly(player.entity());
                    other.add(otherInfoDto);
                }
                dto.setRank(other);
                GameServer.notifyAllx(playerSet,(short)109,dto);
            }
        }
    }
    public boolean bankerDown(CorePlayer corePlayer){
        if(corePlayer.equals(banker)) {
            banker = null;
            bankerUp();
            return true;
        }
        return false;
    }
}
