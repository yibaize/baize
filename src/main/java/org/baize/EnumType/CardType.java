package org.baize.EnumType;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * 作者： 白泽
 * 时间： 2017/11/13.
 * 描述：
 */
public enum  CardType {
    Default(0),
    /**散*/
    Scattered(1),
    /**对子*/
    Both(2),
    /**顺子2*/
    Obey(3),
    /**同花*/
    SumeType(4),
    /**同花顺*/
    ObyeAndSume(5),
    /**炸*/
    Bomb(6),
    /**三条A*/
    AAA(7);
    private int id;
    private CardType(int id) {
        this.id = id;
    }
    public int id(){
        return id;
    }
    private static final Map<Integer,CardType> MAP = new HashMap<>();
    public static CardType getCardType(int id){
        return MAP.getOrDefault(id,Default);
    }
    static {
        for (CardType t:CardType.values()){
            MAP.put(t.id,t);
        }
    }
}
