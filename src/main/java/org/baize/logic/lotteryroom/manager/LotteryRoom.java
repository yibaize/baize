package org.baize.logic.lotteryroom.manager;

import org.baize.EnumType.ScenesType;
import org.baize.dao.model.CorePlayer;
import org.baize.logic.BombFlower.manager.BombCompareCardSize;
import org.baize.logic.BombFlower.manager.CompareCardType;
import org.baize.logic.card.data.Card;
import org.baize.logic.card.data.PersistCard;
import org.baize.logic.card.manager.ICompareCardSize;
import org.baize.logic.room.RoomAbstract;
import org.baize.logic.room.impl.CardRoomImpl;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * 作者： 白泽
 * 时间： 2017/11/20.
 * 描述：彩票
 */
@Service
public class LotteryRoom extends RoomAbstract{
    public LotteryRoom(int id) {
        super(ScenesType.LotteryRoom.id(),new LotteryPlay());
    }

    public boolean leave(CorePlayer corePlayer) {
//        if(getSet().contains(corePlayer)){
//            getSet().remove(corePlayer);
//                return true;
//        }
        return false;
    }

    @Override
    public void battling() {
//        List<PersistCard> list = shuffle();
//        Set<Card> cards = new HashSet<>(1);
//        for (int i = 0;i<1;i++){
//            cards.add(initCard(list,i));
//        }
//        setCardSet(cards);
//        for (Card card:getCardSet()){
//            CompareCardType.setType(card);
//        }
    }
}
