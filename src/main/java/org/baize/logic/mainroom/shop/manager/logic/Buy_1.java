package org.baize.logic.mainroom.shop.manager.logic;

import org.baize.dao.model.CorePlayer;
import org.baize.dao.model.Weath;
import org.baize.error.Error;
import org.baize.logic.mainroom.shop.data.ExChangeDataTable;
import org.baize.logic.mainroom.shop.data.ShopDataTable;
import org.springframework.stereotype.Service;

/**
 * 作者： 白泽
 * 时间： 2017/11/9.
 * 描述：rmb - diamond
 */
@Service
public class Buy_1 extends ShopImpAdapter{
    @Override
    public void buy(CorePlayer corePlayer, ShopDataTable dataTable,int count) {
        ExChangeDataTable exChangeDataTable = ExChangeDataTable.get(1);
        if(exChangeDataTable == null)
            new Error(this.getClass(),corePlayer.getCtx()).debug(1);
        Weath weath = corePlayer.entity().getWeath();
        int changeCount = exChangeDataTable.getDiamond()*count;
        weath.increaseDiamond(changeCount);
        weath.update();
    }
}
