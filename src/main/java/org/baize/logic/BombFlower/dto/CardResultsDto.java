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
public class CardResultsDto implements IProtostuff {
    List<CardResultDto> cardResultDtos;

    public CardResultsDto() {
    }

    public CardResultsDto(List<CardResultDto> cardResultDtos) {
        this.cardResultDtos = cardResultDtos;
    }

    public List<CardResultDto> getCardResultDtos() {
        return cardResultDtos;
    }

    public void setCardResultDtos(List<CardResultDto> cardResultDtos) {
        this.cardResultDtos = cardResultDtos;
    }
}
