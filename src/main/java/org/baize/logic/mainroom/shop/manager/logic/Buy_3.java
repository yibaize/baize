package org.baize.logic.mainroom.shop.manager.logic;

import org.baize.dao.model.CorePlayer;
import org.baize.dao.model.Weath;
import org.baize.logic.mainroom.shop.data.ShopDataTable;

/**
 * 作者： 白泽
 * 时间： 2017/11/9.
 * 描述：vip
 */
public class Buy_3 extends ShopImpAdapter {
    @Override
    public void buy(CorePlayer corePlayer, ShopDataTable dataTable, int count) {
        Weath weath = corePlayer.entity().getWeath();
        weath.update();
    }
}
