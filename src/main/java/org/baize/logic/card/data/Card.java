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
    private int position;
    private List<PersistCard> persistCards;
    private int[] cardIds;
    private int[] cardTypes;
    private int[] id;//牌对应excel的id
    private ResultType result;
    private CardType cardType;
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
