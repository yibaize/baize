package org.baize.utils.excel;

import org.baize.utils.assemblybean.annon.DataTable;

/**
 * 作者： 白泽
 * 时间： 2017/11/11.
 * 描述：
 */
@DataTable
public class ExcelErrorCodeModule implements DataTableMessage{
    private final String id;
    private final String name;
    private final String value;

    public ExcelErrorCodeModule(String id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int id() {
        return Integer.parseInt(id);
    }

    @Override
    public void AfterInit() {

    }
}
