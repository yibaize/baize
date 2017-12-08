package org.baize.logic.card.data;

import org.baize.EnumType.CardType;
import org.baize.EnumType.ResultType;

import java.util.Arrays;
import java.util.List;

/**
 * 作者： 白泽
 * 时间： 2017/11/25.
 * 描述：
 */
public class Card implements Comparable<Card>{
    /**牌堆位置*/
    private int position;
    /**静态数据*/
    private List<PersistCard> persistCards;
    /**牌面id ，1-13*/
    private int[] cardIds;
    /**牌面类型*/
    private int[] cardTypes;
    /**静态数据id*/
    private int[] id;//牌对应excel的id
    /**输，赢，和*/
    private ResultType result = ResultType.Default;
    /**炸，同花顺。。。。*/
    private CardType cardType = CardType.Default;
    public Card() {
    }

    public ResultType getResult() {
        return result;
    }

    public void setResult(ResultType result) {
        this.result = result;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public List<PersistCard> getPersistCards() {
        return persistCards;
    }

    public void setPersistCards(List<PersistCard> persistCards) {
        this.persistCards = persistCards;
    }

    public int[] getCardIds() {
        return cardIds;
    }

    public void setCardIds(int[] cardIds) {
        this.cardIds = cardIds;
    }

    public int[] getCardTypes() {
        return cardTypes;
    }

    public void setCardTypes(int[] cardTypes) {
        this.cardTypes = cardTypes;
    }

    /**
     * 在牌类型相同时要比较花色的时候使用
     * @param id
     * @return
     */
    public int getTypeById(int id){
        for (PersistCard p:persistCards){
            if(p.getCardNum() == id)
                return p.getCardType();
        }
        return -1;
    }

    public int[] getId() {
        return id;
    }

    public void setId(int[] id) {
        this.id = id;
    }

    @Override
    public int compareTo(Card o) {
        return position - o.getPosition();
    }

    @Override
    public String toString() {
        return "Card{" +
                "position=" + position +
                ", persistCards=" + persistCards +
                ", cardIds=" + Arrays.toString(cardIds) +
                ", cardTypes=" + Arrays.toString(cardTypes) +
                ", result=" + result +
                ", cardType=" + cardType +
                '}';
    }
}
