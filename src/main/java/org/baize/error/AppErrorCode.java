package org.baize.error;

import org.baize.utils.assemblybean.annon.ExcelInversion;
import org.baize.utils.assemblybean.annon.ExcelValue;
import org.baize.utils.excel.DataTableMessage;
import org.baize.utils.excel.StaticConfigMessage;

/**
 * 作者： 白泽
 * 时间： 2017/11/8.
 * 描述：
 */
@ExcelInversion
public final class AppErrorCode{
    @ExcelValue(value = "数据异常")
    public final static int DATA_ERR = 404;
    @ExcelValue(value = "数据异常")
    public final static int NOT_ACCOUNT = 1;
}
