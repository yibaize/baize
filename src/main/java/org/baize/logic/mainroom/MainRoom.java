package org.baize.logic.mainroom;

import org.baize.EnumType.ScenesType;
import org.baize.room.RoomAbstract;
import org.springframework.stereotype.Service;

/**
 * 作者： 白泽
 * 时间： 2017/12/8.
 * 描述：
 */
@Service
public class MainRoom extends RoomAbstract {
    public MainRoom() {
        super(ScenesType.Mian.id());
    }

    @Override
    public void compare() {

    }
}
