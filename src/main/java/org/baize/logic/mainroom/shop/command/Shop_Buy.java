package org.baize.logic.mainroom.shop.command;

import org.baize.error.Error;
import org.baize.logic.mainroom.shop.data.ShopDataTable;
import org.baize.server.message.CommandAb;
import org.baize.utils.assemblybean.annon.Protocol;

/**
 * 作者： 白泽
 * 时间： 2017/11/9.
 * 描述：
 */
@Protocol(id = "10")
public class Shop_Buy extends CommandAb {
    /**商品id*/
    private final int goodsId;
    /**数量*/
    private final int count;

    public Shop_Buy(int goodsId, int count) {
        this.goodsId = goodsId;
        this.count = count;
    }

    @Override
    public void execute() {
        ShopDataTable dataTable = ShopDataTable.get(goodsId);
        if(dataTable == null)
            new Error(this.getClass(),this.getCtx()).debug(1);
    }
}
