package org.baize.logic.mainroom.shop.manager.logic;

import org.baize.dao.model.Weath;
import org.baize.error.AppErrorCode;
import org.baize.error.GenaryAppError;
import org.baize.logic.mainroom.shop.data.ShopDataTable;
import org.baize.room.RoomPlayer;
import org.springframework.stereotype.Service;

/**
 * 作者： 白泽
 * 时间： 2017/11/10.
 * 描述：Tree
 */
@Service
public class Buy_3 extends ShopImpAdapter{
    @Override
    public void buy(RoomPlayer roomPlayer, ShopDataTable dataTable, int count) {
        Weath weath = roomPlayer.entity().weath();
        if(weath.isHasTree())
            new GenaryAppError(AppErrorCode.TREE_NOT);
        weath.setHasTree(true);
        weath.update();
    }
}
