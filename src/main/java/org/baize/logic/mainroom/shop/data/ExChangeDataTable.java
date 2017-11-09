package org.baize.logic.mainroom.shop.data;

import org.baize.utils.excel.DataTableMessage;
import org.baize.utils.excel.StaticConfigMessage;

/**
 * 作者： 白泽
 * 时间： 2017/11/9.
 * 描述：
 */
//@DataTable不用生成客户端代码
public class ExChangeDataTable implements DataTableMessage {
    /**货币id*/
    private final int id;
    private final int gold;
    private final int diamond;
    public static ExChangeDataTable get(int id){
        return StaticConfigMessage.getInstance().get(ExChangeDataTable.class,id);
    }
    public ExChangeDataTable() {
        this.id = 0;
        this.gold = 0;
        this.diamond = 0;
    }

    public int getId() {
        return id;
    }

    public int getGold() {
        return gold;
    }

    public int getDiamond() {
        return diamond;
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public void AfterInit() {

    }
}
