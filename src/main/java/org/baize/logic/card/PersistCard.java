package org.baize.logic.card;

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
    private final int[] both;
    private final int[] bomb;

    public PersistCard() {
        this.id = 0;
        this.cardNum = 0;
        this.cardType = 0;
        this.name = "";
        this.both = null;
        this.bomb = null;
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

    public int[] getBoth() {
        return both;
    }

    public int[] getBomb() {
        return bomb;
    }
    private int[] both4Init(String value){
        int[] arr = new int[3];
        return arr;
    }
    private int[] bomb4Init(String value){
        int[] arr = new int[3];
        return arr;
    }
    @Override
    public int id() {
        return id;
    }

    @Override
    public void AfterInit() {

    }
}
