package org.baize.logic.room;

import org.baize.server.message.IProtostuff;
import org.baize.utils.assemblybean.annon.Protostuff;

import java.util.Map;

/**
 * 作者： 白泽
 * 时间： 2017/11/17.
 * 描述：
 */
@Protostuff
public class RoomBottomDto implements IProtostuff{
    private Map<Integer,Integer> position;

    public RoomBottomDto() {
    }

    public RoomBottomDto(Map<Integer, Integer> position) {
        this.position = position;
    }

    public Map<Integer, Integer> getPosition() {
        return position;
    }

    public void setPosition(Map<Integer, Integer> position) {
        this.position = position;
    }
}
