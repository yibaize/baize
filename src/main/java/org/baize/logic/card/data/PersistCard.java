package org.baize.logic.card.data;

import org.baize.utils.excel.DataTableMessage;

/**
 * 作者： 白泽
 * 时间： 2017/11/6.
 * 描述：
 */
public class PersistCard implements DataTableMessage{
    private final int id;
    private final int cardNum;
    private final int cardType;
    private final String name;
    public PersistCard() {
        this.id = 0;
        this.cardNum = 0;
        this.cardType = 0;
        this.name = "";
    }

    public int getId() {
        return id;
    }

    public int getCardNum() {
        return cardNum;
    }

    public int getCardType() {
        return cardType;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "PersistCard{" +
                "id=" + id +
                ", cardNum=" + cardNum +
                ", cardType=" + cardType +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public void AfterInit() {

    }
}
