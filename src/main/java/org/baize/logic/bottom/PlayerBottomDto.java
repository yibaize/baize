package org.baize.logic.bottom;

import org.baize.server.message.IProtostuff;
import org.baize.utils.assemblybean.annon.Protostuff;

/**
 * 作者： 白泽
 * 时间： 2017/11/17.
 * 描述：
 */
@Protostuff
public class PlayerBottomDto implements IProtostuff {
    private int id;
    private int count;

    public PlayerBottomDto() {
    }

    public PlayerBottomDto(int id, int count) {
        this.id = id;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
