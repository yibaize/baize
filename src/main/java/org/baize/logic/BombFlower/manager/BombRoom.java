package org.baize.logic.BombFlower.manager;

import org.baize.EnumType.ScenesType;
import org.baize.logic.BombFlower.dto.CardResultDto;
import org.baize.logic.BombFlower.dto.CardResultsDto;
import org.baize.logic.card.data.Card;
import org.baize.logic.card.data.PersistCard;
import org.baize.logic.room.impl.CardRoomImpl;
import org.baize.worktask.ISecondTimer;
import org.springframework.beans.BeanUtils;

import java.util.*;

/**
 * 作者： 白泽
 * 时间： 2017/11/13.
 * 描述：
 */
public class BombRoom extends CardRoomImpl implements ISecondTimer{
    public BombRoom() {
        super(ScenesType.Flower.id());
    }

    @Override
    public void perflop() {
        List<PersistCard> list = shuffle();
        Set<Card> cards = new HashSet<>(5);
        for (int i = 0;i<5;i++){
            Card card = new Card();
            card.setId(i+1);
            int[] id = new int[3];
            int[] type = new int[3];
            int[] num = new int[3];
            List<PersistCard> cardlist = list.subList(i*3,i*3+3);
            for(int j = 0;j<3;j++) {
                id[j] = cardlist.get(j).getId();
                type[j] = cardlist.get(j).getCardType();
                num[j] = cardlist.get(j).getCardNum();
            }
            card.setCardId(id);
            card.setCardNum(num);
            card.setCardType(type);
            cards.add(card);
        }
        setCardSet(cards);
        CompareCardSize.result(getCardSet());
    }
    private int timer = 0;
    @Override
    public void executor() {
        bottomNotify();
        if(timer == 2){
            start(1);//开局
            perflop();
        }
        if(timer == 15){
            //发送本剧开牌结果
            CardResultsDto dtos = new CardResultsDto();
            List<CardResultDto> list = new ArrayList<>(5);
            Iterator<Card> iterator = getCardSet().iterator();
            while (iterator.hasNext()){
                CardResultDto dto = new CardResultDto();
                BeanUtils.copyProperties(iterator.next(),dto);
                list.add(dto);
            }
            dtos.setCardResultDtos(list);
            notifyAllx((short)106,dtos);
        }
        if(timer == 17){
            //结算
            settleAccounts();
        }
        if(timer == 20){
            start(2);//结束
            timer = 0;
        }
        timer++;
    }
}
