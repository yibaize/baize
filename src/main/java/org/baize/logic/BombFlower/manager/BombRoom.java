package org.baize.logic.BombFlower.manager;

import org.baize.EnumType.ScenesType;
import org.baize.logic.room.RoomAbstract;
import org.springframework.stereotype.Service;
/**
 * 作者： 白泽
 * 时间： 2017/11/13.
 * 描述：
 */
@Service
public class BombRoom extends RoomAbstract{
    public BombRoom() {
        super(ScenesType.Flower.id(),new BombPlay());
    }

    @Override
    public void battling() {
        setRoomBattleState(false);
        getPlay().perflop(5);
    }
}
