package org.baize.logic.BombFlower.dto;

import org.baize.server.message.IProtostuff;
import org.baize.utils.assemblybean.annon.Protostuff;

/**
 * 作者： 白泽
 * 时间： 2017/11/13.
 * 描述：
 */
@Protostuff
public class OpeningDto implements IProtostuff{
    private int start;

    public OpeningDto() {
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public OpeningDto(int start) {
        this.start = start;
    }
}
