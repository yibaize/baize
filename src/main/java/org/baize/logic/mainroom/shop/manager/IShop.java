package org.baize.logic.mainroom.shop.manager;
import org.baize.dao.model.CorePlayer;
import org.baize.logic.ILogic;
import org.baize.logic.mainroom.shop.data.ShopDataTable;

/**
 * 作者： 白泽
 * 时间： 2017/11/9.
 * 描述：
 */
public interface IShop extends ILogic{
    void buy(CorePlayer corePlayer, ShopDataTable dataTable, int count);
}
