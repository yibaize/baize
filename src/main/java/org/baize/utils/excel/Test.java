package org.baize.utils.excel;

import org.apache.log4j.Logger;
import org.baize.error.Error;
import org.baize.server.message.CommandAb;
import org.baize.utils.assemblybean.annon.ExcelInversion;
import org.baize.utils.assemblybean.annon.ExcelValue;

import java.lang.reflect.Field;

/**
 * 作者： 白泽
 * 时间： 2017/11/3.
 * 描述：
 */
@ExcelInversion
public class Test{
    @ExcelValue(value = "奥术大师多")
    public static final int ASD = 1;
    public static final int SAD = 2;
    @ExcelValue(value = "体育空压机")
    public static final int FGH = 3;
    @ExcelValue(value = "weygrewtfwef")
    public static final int FDG = 4;
    @ExcelValue(value = "一天里访问")
    public static final int TYU = 5;
}
