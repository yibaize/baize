package org.baize.logic.mainroom.shop.manager.logic;

import org.baize.dao.model.CorePlayer;
import org.baize.dao.model.Weath;
import org.baize.error.Error;
import org.baize.logic.mainroom.shop.data.ExChangeDataTable;
import org.baize.logic.mainroom.shop.data.ShopDataTable;

/**
 * 作者： 白泽
 * 时间： 2017/11/9.
 * 描述：diamond - gold
 */
public class Buy_2 extends ShopImpAdapter {
    @Override
    public void buy(CorePlayer corePlayer, ShopDataTable dataTable, int count) {
        ExChangeDataTable exChangeDataTable = ExChangeDataTable.get(2);
        if(exChangeDataTable == null)
            new Error(this.getClass(),corePlayer.getCtx()).debug(1);
        Weath weath = corePlayer.entity().getWeath();
        if(weath.getDiamond()<count)//钻石不足
            new Error(this.getClass(),corePlayer.getCtx()).debug(1);
        weath.decreaseDiamond(count);
        weath.increaseGold(count*exChangeDataTable.getGold());
        weath.update();
    }
}
