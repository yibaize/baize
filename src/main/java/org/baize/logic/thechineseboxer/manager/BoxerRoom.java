package org.baize.logic.thechineseboxer.manager;

import org.baize.EnumType.ScenesType;
import org.baize.logic.card.data.Card;
import org.baize.logic.card.data.PersistCard;
import org.baize.logic.card.manager.ICompareCardSize;
import org.baize.logic.room.impl.CardRoomImpl;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 作者： 白泽
 * 时间： 2017/11/20.
 * 描述：龙虎斗
 */
@Service
public class BoxerRoom extends CardRoomImpl{
    public BoxerRoom() {
        super(ScenesType.BoxerRoom.id());
    }

    @Override
    public void perflop() {
        List<PersistCard> list = shuffle();
        Set<Card> cards = new HashSet<>(2);
        for (int i = 0;i<2;i++){
            cards.add(initCard(list,i));
        }
        setCardSet(cards);
        ICompareCardSize iCompareCardSize = new BoxerCompareCardSizeImpl();
        iCompareCardSize.result(getCardSet());
        Set<Card> set = getCardSet();
    }

}
