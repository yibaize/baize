package org.baize.logic.room;

import org.baize.dao.model.CorePlayer;
import org.baize.logic.card.data.Card;
import org.baize.logic.card.data.PersistCard;
import org.baize.logic.card.manager.CardManager;
import org.baize.server.message.IProtostuff;

import java.util.*;

/**
 * 作者： 白泽
 * 时间： 2017/11/21.
 * 描述：
 */
public abstract class PlayAbstract {
    private Set<Card> cardSet;
    /**
     * 洗牌
     */
    public List<PersistCard> shuffle(){
        List<PersistCard> cards = CardManager.getInstance().getCardPool();
        Collections.shuffle(cards);
        return cards;
    }
    /**
     * 发牌
     */
    public void perflop(int count){
        List<PersistCard> list = shuffle();
        Set<Card> cards = new HashSet<>(count);
        for (int i = 0;i<count;i++){
            cards.add(CardManager.getInstance().initCard(list,i));
        }
        cardSet = cards;
    }

    public Set<Card> getCardSet() {
        return cardSet;
    }

    /**
     * 结算
     */
    public abstract IProtostuff end();
    public abstract void roomPlayers(Set<CorePlayer> players);
}
