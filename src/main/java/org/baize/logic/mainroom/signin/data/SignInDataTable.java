package org.baize.logic.mainroom.signin.data;

import org.baize.utils.assemblybean.annon.DataTable;
import org.baize.utils.excel.DataTableMessage;
import org.baize.utils.excel.StaticConfigMessage;

/**
 * 作者： 白泽
 * 时间： 2017/11/9.
 * 描述：
 */
@DataTable
public class SignInDataTable implements DataTableMessage {
    private final int id;
    private final int weekDay;
    private final int draw;
    private final String name;
    public SignInDataTable() {
        this.id = 0;
        this.weekDay = 0;
        this.draw = 0;
        name = "";
    }
    public static SignInDataTable get(int id){
        return StaticConfigMessage.getInstance().get(SignInDataTable.class,id);
    }

    public int getId() {
        return id;
    }

    public int getWeekDay() {
        return weekDay;
    }

    public int getDraw() {
        return draw;
    }

    public String getName() {
        return name;
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public void AfterInit() {

    }
}
