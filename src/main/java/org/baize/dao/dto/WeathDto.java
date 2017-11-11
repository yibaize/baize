package org.baize.dao.dto;

import org.baize.server.message.IProtostuff;
import org.baize.utils.assemblybean.annon.Protocol;
import org.baize.utils.assemblybean.annon.Protostuff;

/**
 * 作者： 白泽
 * 时间： 2017/11/11.
 * 描述：
 */
@Protostuff
public class WeathDto implements IProtostuff {
    /**金幣*/
    private long gold; // 金币
    /**鑽石*/
    private int diamond; // 钻石

    public WeathDto() {
    }

    public WeathDto(long gold, int diamond) {
        this.gold = gold;
        this.diamond = diamond;
    }

    public long getGold() {
        return gold;
    }

    public void setGold(long gold) {
        this.gold = gold;
    }

    public int getDiamond() {
        return diamond;
    }

    public void setDiamond(int diamond) {
        this.diamond = diamond;
    }
}
