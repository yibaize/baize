package org.baize.logic.mainroom.shop.manager.logic;

import org.baize.dao.model.Weath;
import org.baize.error.AppErrorCode;
import org.baize.error.GenaryAppError;
import org.baize.logic.mainroom.shop.data.ExChangeDataTable;
import org.baize.logic.mainroom.shop.data.ShopDataTable;
import org.baize.room.RoomPlayer;
import org.springframework.stereotype.Service;

/**
 * 作者： 白泽
 * 时间： 2017/11/9.
 * 描述：rmb - gold
 */
@Service
public class Buy_1 extends ShopImpAdapter{
    @Override
    public void buy(RoomPlayer roomPlayer, ShopDataTable dataTable, int count) {
        ExChangeDataTable exChangeDataTable = ExChangeDataTable.get(1);
        if(exChangeDataTable == null)
            new GenaryAppError(AppErrorCode.DATA_ERR);
        Weath weath = roomPlayer.entity().weath();
        int changeCount = exChangeDataTable.getGold()*count;
        weath.increaseGold(changeCount);
        weath.update();
    }
}
