package org.baize.utils.excel;

/**
 * 作者： 白泽
 * 时间： 2017/11/7.
 * 描述：
 */
public class CheckType {
    public static Object getTypr(Class<?> clazz,String value){
        Object type = null;
        if(clazz == int.class)
            type = Integer.valueOf(value);
        else if(clazz == float.class)
            type = Float.valueOf(value);
        else if(clazz == double.class)
            type = Double.valueOf(value);
        else if(clazz == long.class)
            type = Long.valueOf(value);
        else if(clazz == boolean.class)
            type = Boolean.valueOf(value);
        else if(clazz == byte.class)
            type = Byte.valueOf(value);
        else if(clazz == short.class)
            type = Short.valueOf(value);
        else if(clazz == String.class)
            type = value;
        return type;
    }
}
