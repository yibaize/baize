package org.baize.logic.card.manager;

import org.baize.logic.card.data.Card;
import org.baize.logic.card.data.PersistCard;
import org.baize.utils.excel.StaticConfigMessage;
import java.io.Serializable;
import java.util.*;

/**
 * 作者： 白泽
 * 时间： 2017/11/13.
 * 描述：
 */
public class CardManager {
    private static List<PersistCard> cardPool;
    private static CardManager _instance;
    private CardManager(){
        Map<Serializable,Object> map = StaticConfigMessage.getInstance().getMap(PersistCard.class);
        cardPool = new ArrayList<>(map.size());
        for (Map.Entry<Serializable,Object> e:map.entrySet()){
            cardPool.add((PersistCard) e.getValue());
        }
    }
    public static CardManager getInstance(){
        if(_instance == null)
            _instance = new CardManager();
        return _instance;
    }
    public List<PersistCard> getCardPool() {
        return cardPool;
    }
    public Card initCard(List<PersistCard> list, int i,int holdCount){
        Card card = new Card();
        card.setPosition(i+1);
        int[] types = new int[holdCount];
        int[] cardId = new int[holdCount];
        int[] ids = new int[holdCount];
        List<PersistCard> cardlist = list.subList(i*3,i*3+3);
        for(int j = 0;j<holdCount;j++) {
            PersistCard c = cardlist.get(j);
            types[j] = c.getCardType();
            cardId[j] = c.getCardNum();
            ids[j] = c.getId();
        }
        card.setPersistCards(cardlist);
        card.setCardIds(cardId);
        card.setCardTypes(types);
        card.setId(ids);
        return card;
    }
    private List<PersistCard> shuffle(){
        List<PersistCard> cards = cardPool;
        Collections.shuffle(cards);
        return cards;
    }
    /**
     * 发牌
     */
    public Set<Card> perflop(int count,int holdCount){
        List<PersistCard> list = shuffle();
        Set<Card> cards = new HashSet<>(count);
        for (int i = 0;i<count;i++){
            cards.add(CardManager.getInstance().initCard(list,i,holdCount));
        }
        return cards;
    }
}
