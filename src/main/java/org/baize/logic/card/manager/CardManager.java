package org.baize.logic.card.manager;

import org.apache.poi.ss.formula.functions.T;
import org.baize.logic.card.data.PersistCard;
import org.baize.utils.SpringUtils;
import org.baize.utils.excel.StaticConfigMessage;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 作者： 白泽
 * 时间： 2017/11/13.
 * 描述：
 */
@Service
public class CardManager {
    private static List<PersistCard> cardPool;
    private CardManager(){
        Map<Serializable,Object> map = StaticConfigMessage.getInstance().getMap(PersistCard.class);
        for (Map.Entry<Serializable,Object> e:map.entrySet()){
            cardPool.add((PersistCard) e.getValue());
        }
    }
    public static CardManager getInstance(){
        return SpringUtils.getBean(CardManager.class);
    }
    public List<PersistCard> getCardPool() {
        return cardPool;
    }
}
