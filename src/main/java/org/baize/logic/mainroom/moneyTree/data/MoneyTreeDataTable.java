package org.baize.logic.mainroom.moneyTree.data;

import org.baize.utils.assemblybean.annon.DataTable;
import org.baize.utils.excel.DataTableMessage;
import org.baize.utils.excel.StaticConfigMessage;

/**
 * 作者： 白泽
 * 时间： 2017/11/10.
 * 描述：
 */
@DataTable
public class MoneyTreeDataTable implements DataTableMessage {
    private final int id;
    private final int drawNum;
    private final int max;
    public MoneyTreeDataTable() {
        this.id = 0;
        this.drawNum = 0;
        this.max = 0;
    }
    public static MoneyTreeDataTable get(int id){
        return StaticConfigMessage.getInstance().get(MoneyTreeDataTable.class,id);
    }
    public int getId() {
        return id;
    }

    public int getDrawNum() {
        return drawNum;
    }

    public int getMax() {
        return max;
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public void AfterInit() {

    }
}
