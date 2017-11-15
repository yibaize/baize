package org.baize.logic.BombFlower.dto;

import org.baize.server.message.IProtostuff;
import org.baize.utils.assemblybean.annon.Protostuff;

/**
 * 作者： 白泽
 * 时间： 2017/11/13.
 * 描述：
 */
@Protostuff
public class CardResultDto implements IProtostuff {
    private int id;//牌堆
    private boolean result;//输赢
    private int type;//散、对、顺子...
    private int[] cardId;//1-52

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
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

    public void setCardId(int[] cardId) {
        this.cardId = cardId;
    }
}
