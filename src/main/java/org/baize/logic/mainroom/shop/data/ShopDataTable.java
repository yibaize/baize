package org.baize.logic.mainroom.shop.data;

import org.baize.utils.assemblybean.annon.DataTable;
import org.baize.utils.excel.DataTableMessage;
import org.baize.utils.excel.StaticConfigMessage;

/**
 * 作者： 白泽
 * 时间： 2017/11/9.
 * 描述：
 */
@DataTable
public class ShopDataTable implements DataTableMessage {
    private final int id;
    /**售价*/
    private final int sellingPrice;
    private final String name;
    private final int shopId;

    public ShopDataTable() {
        this.id = 0;
        this.sellingPrice = 0;
        this.name = "";
        this.shopId = 0;
    }
    public static ShopDataTable get(int id){
        return StaticConfigMessage.getInstance().get(ShopDataTable.class,id);
    }
    public int getId() {
        return id;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public String getName() {
        return name;
    }

    public int getShopId() {
        return shopId;
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public void AfterInit() {

    }
}
