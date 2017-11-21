package org.baize.logic.BombFlower.manager;

import org.baize.dao.model.CorePlayer;
import org.baize.dao.model.PlayerEntity;
import org.baize.logic.BombFlower.dto.CardResultDto;
import org.baize.logic.BombFlower.dto.CardResultsDto;
import org.baize.logic.card.data.Card;
import org.baize.logic.card.manager.ICompareCardSize;
import org.baize.logic.login.manager.LoginManager;
import org.baize.logic.room.PlayAbstract;
import org.baize.utils.SpringUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 作者： 白泽
 * 时间： 2017/11/21.
 * 描述：
 */
public class BombPlay extends PlayAbstract {
    private CorePlayer banker;
    private ConcurrentLinkedQueue<CorePlayer> bankerLineUp;

    @Override
    public CardResultsDto end() {
        ICompareCardSize iCompareCardSize = new BombCompareCardSize();
        iCompareCardSize.result(getCardSet());
        CardResultsDto dtos = new CardResultsDto();
        List<CardResultDto> list = new ArrayList<>(5);
        Iterator<Card> iterator = getCardSet().iterator();
        while (iterator.hasNext()){
            CardResultDto dto = new CardResultDto();
            BeanUtils.copyProperties(iterator.next(),dto);
            list.add(dto);
        }
        dtos.setCardResultDtos(list);
        return dtos;
    }
    /**
     * 上庄申请
     * @param corePlayer
     * @return
     */
    public boolean bankerLineUp(CorePlayer corePlayer){
        if(bankerLineUp == null)
            bankerLineUp = new ConcurrentLinkedQueue<>();
        if(bankerLineUp.contains(corePlayer)){
            bankerLineUp.offer(corePlayer);
            bankerUp();
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
    private boolean bankerUp(){
        synchronized (banker) {
            if (checkBanker() && bankerLineUp != null || bankerLineUp.size() > 0) {
                banker = bankerLineUp.poll();
                return true;
            }
            return false;
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
