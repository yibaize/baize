package org.baize.logic.mainroom.shop.manager.logic;

import org.baize.dao.model.CorePlayer;
import org.baize.dao.model.Weath;
import org.baize.error.Error;
import org.baize.logic.mainroom.shop.data.ShopDataTable;
import org.springframework.stereotype.Service;

/**
 * 作者： 白泽
 * 时间： 2017/11/10.
 * 描述：Tree
 */
@Service
public class Buy_3 extends ShopImpAdapter{
    @Override
    public void buy(CorePlayer corePlayer, ShopDataTable dataTable, int count) {
        Weath weath = corePlayer.entity().getWeath();
        if(weath.isHasTree())
            new Error(this.getClass(),corePlayer.getCtx()).debug(1);
        weath.setHasTree(true);
        weath.update();
    }
}