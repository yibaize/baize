package org.baize.logic.card.data;

/**
 * 作者： 白泽
 * 时间： 2017/11/6.
 * 描述：牌堆
 */
public class Card implements Comparable<Card>{
    private int id;//牌堆
    private int result;//输赢
    private int type;//散、对、顺子...
    private int[] cardType;//梅花、方块、红桃、黑桃
    private int[] cardId;//1-52
    private int[] cardNum;//1-13

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int[] getCardId() {
        return cardId;
    }

    public int[] getCardType() {
        return cardType;
    }

    public void setCardType(int[] cardType) {
        this.cardType = cardType;
    }

    public void setCardId(int[] cardId) {
        this.cardId = cardId;
    }

    public int[] getCardNum() {
        return cardNum;
    }

    public void setCardNum(int[] cardNum) {
        this.cardNum = cardNum;
    }

    @Override
    public int compareTo(Card o) {
        return id - o.getId();
    }
}
