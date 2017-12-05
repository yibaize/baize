package org.baize.logic.mainroom.shop.manager.logic;

import org.baize.dao.model.Weath;
import org.baize.error.AppErrorCode;
import org.baize.error.GenaryAppError;
import org.baize.logic.mainroom.shop.data.ExChangeDataTable;
import org.baize.logic.mainroom.shop.data.ShopDataTable;
import org.baize.room.RoomPlayer;

/**
 * 作者： 白泽
 * 时间： 2017/11/9.
 * 描述：diamond - gold
 */
public class Buy_2 extends ShopImpAdapter {
    @Override
    public void buy(RoomPlayer roomPlayer, ShopDataTable dataTable, int count) {
        ExChangeDataTable exChangeDataTable = ExChangeDataTable.get(2);
        if(exChangeDataTable == null)
            new GenaryAppError(AppErrorCode.DATA_ERR);
        Weath weath = roomPlayer.entity().weath();
        if(weath.getDiamond()<count)//钻石不足
            new GenaryAppError(AppErrorCode.DIAMOND_ERR);
        weath.decreaseDiamond(count);
        weath.increaseGold(count*exChangeDataTable.getGold());
        weath.update();
    }
}
