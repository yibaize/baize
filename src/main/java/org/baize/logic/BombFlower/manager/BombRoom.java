package org.baize.logic.BombFlower.manager;

import org.baize.EnumType.ScenesType;
import org.baize.logic.room.RoomAbstract;
import org.baize.logic.room.RoomBottomImpl;
import org.springframework.stereotype.Service;
/**
 * 作者： 白泽
 * 时间： 2017/11/13.
 * 描述：
 */
@Service
public class BombRoom extends RoomAbstract{
    public BombRoom() {
        super(ScenesType.Bomb.id(),new BombPlay(),new RoomBottomImpl());
    }

    @Override
    public void battling() {
        setRoomBattleState(true);
        getPlay().perflop(5);
        notifyAllx((short)106,getPlay().end());
    }
}
