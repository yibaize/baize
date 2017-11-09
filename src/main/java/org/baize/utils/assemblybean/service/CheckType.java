package org.baize.utils.assemblybean.service;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 作者： 白泽
 * 时间： 2017/11/3.
 * 描述：
 */
public class CheckType {
    /**
     * 根据类型获取该生成的类型
     * @param t
     * @return
     */
    public static String getArrType(String t,String beanName,String fieldName){
        String type = "";
        switch (t){
            case "java.lang.String":
                type = "string";
                break;
            case "java.lang.Double":
                type = "double";
                break;
            case "java.lang.Boolean":
                type = "bool";
                break;
            case "java.lang.Short":
                type = "short";
                break;
            case "java.lang.Byte":
                type = "byte";
                break;
            case "java.lang.Float":
                type = "float";
                break;
            case "java.lang.Long":
                type = "long";
                break;
            case "java.lang.Char":
                type = "char";
                break;
            case "java.lang.Integer":
                type = "int";
                break;
            default:
                type = StringUtils.substringAfterLast(t,".");
        }
        if (StringUtils.isBlank(type)) {
            type = "Object";
            System.err.println("对象"+beanName+"的"+fieldName+"变量的类型属性"+t+"不正确：反射数据类型异常将自动转为string类型");
        }
        return type;
    }
    public static String getType(Type t,String beanName,String fieldName){
        String type = "";
        if(t == String.class)
            type = "string";
        else if(t == byte.class)
            type = "byte";
        else if(t == char.class)
            type = "char";
        else if(t == short.class)
            type = "string";
        else if(t == int.class)
            type = "int";
        else if(t == boolean.class)
            type = "bool";
        else if(t == float.class)
            type = "float";
        else if(t == double.class)
            type = "double";
        else if(t == long.class)
            type = "long";
        else
            type = t.getClass().getSimpleName();
        if (StringUtils.isBlank(type)) {
            type = "Object";
            System.err.println("对象"+beanName+"的"+fieldName+"变量的类型属性"+t+"不正确：反射数据类型异常将自动转为string类型");
        }
        return type;
    }
    public static String getType(Class<?> t,String beanName,String fieldName){
        String type = "";
        if(t == String.class)
            type = "string";
        else if(t == byte.class)
            type = "byte";
        else if(t == char.class)
            type = "char";
        else if(t == short.class)
            type = "string";
        else if(t == int.class)
            type = "int";
        else if(t == boolean.class)
            type = "bool";
        else if(t == float.class)
            type = "float";
        else if(t == double.class)
            type = "double";
        else if(t == long.class)
            type = "long";
        else
            type = t.getSimpleName();
        if (StringUtils.isBlank(type)) {
            type = "Object";
            System.err.println("对象"+beanName+"的"+fieldName+"变量的类型属性"+t+"不正确：反射数据类型异常将自动转为string类型");
        }
        return type;
    }
    public static String checkProtocolType(String str,String clazz){
        String type = "";
        switch (str){
            case "boolean":
                type = "Boolean.parseBoolean";
                break;
            case "bool":
                type = "Boolean.parseBoolean";
                break;
            case "int":
                type = "Integer.parseInt";
                break;
            case "short":
                type = "Short.parseShort";
                break;
            case "byte":
                type = "Byte.parseByte";
                break;
            case "long":
                type = "Long.parseLong";
                break;
            case "float":
                type = "Float.parseFloat";
                break;
            case "double":
                type = "Double.parseDouble";
                break;

            case "Bool":
                type = "Boolean.parseBoolean";
                break;
            case "Boolean":
                type = "Boolean.parseBoolean";
                break;
            case "Integer":
                type = "Integer.parseInt";
                break;
            case "Short":
                type = "Short.parseShort";
                break;
            case "Byte":
                type = "Byte.parseByte";
                break;
            case "Long":
                type = "Long.parseLong";
                break;
            case "Float":
                type = "Float.parseFloat";
                break;
            case "Double":
                type = "Double.parseDouble";
                break;

            default:
                type = "String";
                break;
        }
        if(StringUtils.isEmpty(type))
            System.err.println("协议类"+clazz+"的"+str+"类型异常错误");
        return type;
    }
    /**
     * 获取集合类型
     * @param
     * @return
     */
    public static String getGather(Field ft, String beanName, String fieldName){
        String type = "";
        switch (ft.getType().toString()){
            case "interface java.util.List":
                String ty = getGatherType(false,ft.getGenericType(),beanName,fieldName);
                type = "List<"+ty+">";
                break;
            case "interface java.util.Set":
                String ty1 = getGatherType(false,ft.getGenericType(),beanName,fieldName);
                type = "Set<"+ty1+">";
                break;
            case "interface java.util.Map":
                String ty2 = getGatherType(true,ft.getGenericType(),beanName,fieldName);
                type = "Dictionary<"+ty2+">";
                break;
        }
        if (StringUtils.isBlank(type))
            System.err.println("反射数据类型异常");
        return type;
    }


    private static String getGatherType(boolean isMap, Type t, String beanName, String fieldName){
        String type = "";
        ParameterizedType pt = (ParameterizedType) t;
        if(isMap){
            Class<?> genericClazz = (Class)pt.getActualTypeArguments()[0];
            Class<?> genericClazz1 = (Class)pt.getActualTypeArguments()[1];
            String k = CheckType.getType(genericClazz,beanName,fieldName);
            String v = CheckType.getType(genericClazz1,beanName,fieldName);
            type = k+","+v;
        }else {
            Class genericClazz = (Class)pt.getActualTypeArguments()[0];
            type = CheckType.getType(genericClazz,beanName,fieldName);
        }
        if (StringUtils.isBlank(type))
            System.err.println("集合类型错误异常");
        return type;
    }
}
