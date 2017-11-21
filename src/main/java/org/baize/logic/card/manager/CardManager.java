package org.baize.logic.card.manager;

import org.baize.logic.card.data.Card;
import org.baize.logic.card.data.PersistCard;
import org.baize.utils.excel.StaticConfigMessage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public Card initCard(List<PersistCard> list, int i){
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
        return card;
    }
}
