package org.baize.utils.excel;

/**
 * 作者： 白泽
 * 时间： 2017/11/7.
 * 描述：
 */
public class CheckType {
    public static Object getTypr(Class<?> clazz,String value){
        Object type = null;
        if(type == int.class)
            type = Integer.valueOf(value);
        else if(type == float.class)
            type = Float.valueOf(value);
        else if(type == double.class)
            type = Double.valueOf(value);
        else if(type == long.class)
            type = Long.valueOf(value);
        else if(type == boolean.class)
            type = Boolean.valueOf(value);
        else if(type == byte.class)
            type = Byte.valueOf(value);
        else if(type == short.class)
            type = Short.valueOf(value);
        else if(type == String.class)
            type = value;
        return type;
    }
}
