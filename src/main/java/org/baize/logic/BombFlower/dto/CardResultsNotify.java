package org.baize.logic.BombFlower.dto;

import org.baize.server.message.IProtostuff;
import org.baize.utils.assemblybean.annon.Protostuff;

import java.util.List;

/**
 * 作者： 白泽
 * 时间： 2017/11/13.
 * 描述：
 */
@Protostuff
public class CardResultsNotify implements IProtostuff {
    List<CardResultNotify> cardResultDtos;

    public CardResultsNotify() {
    }

    public CardResultsNotify(List<CardResultNotify> cardResultDtos) {
        this.cardResultDtos = cardResultDtos;
    }

    public List<CardResultNotify> getCardResultDtos() {
        return cardResultDtos;
    }

    public void setCardResultDtos(List<CardResultNotify> cardResultDtos) {
        this.cardResultDtos = cardResultDtos;
    }
}
