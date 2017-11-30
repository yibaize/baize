package org.baize.utils.assemblybean.service;

import org.apache.commons.lang3.StringUtils;
import org.baize.utils.assemblybean.annon.DataTable;
import org.baize.utils.assemblybean.annon.ExcelInversion;
import org.baize.utils.assemblybean.annon.Protocol;
import org.baize.utils.assemblybean.annon.Protostuff;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * 作者： 白泽
 * 时间： 2017/10/31.
 * 描述：
 */
public class SelectAnnotationClass {
    public enum AnnonEnum{
        DataTable,
        Protocol,
        Protostuff,
        Excel
    }
    /**
     * 获取拥有Service注解的所有类
     * @param path 包路径
     * @return
     */
    public static void getIocClazz(String path){
        List<Class> clazzs = GetFileAllInit.getClasssFromPackage(path);
        Set<CodeModel> dataTableSet = new HashSet<>();
        Set<CodeModel> protocolSet = new HashSet<>();
        Set<CodeModel> protostuffSet = new HashSet<>();
        if (clazzs.size()<=0)
            return;
        for (Class c:clazzs) {
            Annotation proco = c.getAnnotation(Protocol.class);
            if(proco instanceof Protocol){
                Protocol proco1 = (Protocol) proco;
                protocolSet.add(new CodeModel(proco1.id(),c));
            }
            Annotation prostuff = c.getAnnotation(Protostuff.class);
            if(prostuff instanceof Protostuff){
                //Protostuff protostuff = (Protostuff)prostuff;
                protostuffSet.add(new CodeModel("",c));
            }
            Annotation table = c.getAnnotation(DataTable.class);
            if(table instanceof DataTable){
                //DataTable dataTable1 = (DataTable)table;
                dataTableSet.add(new CodeModel("",c));
            }
            Annotation excel = c.getAnnotation(ExcelInversion.class);
            if(excel instanceof ExcelInversion){
                org.baize.utils.excel.ExcelInversion.err.add(new CodeModel("",c));
            }
        }
        reflectBean(dataTableSet, AnnonEnum.DataTable);
        reflectBean(protocolSet, AnnonEnum.Protocol);
        reflectBean(protostuffSet, AnnonEnum.Protostuff);
        ProtocolRecive.assembly();
        org.baize.utils.excel.ExcelInversion.reflectBean();
    }

    /**
     * 反射对象
     * @param beans
     */
    private static void reflectBean(Set<CodeModel> beans,AnnonEnum ae){
        if (beans.size()<=0) return;
        Iterator<CodeModel> iterator = beans.iterator();
        while (iterator.hasNext()){
            Object o = null;
            try {
                CodeModel model = iterator.next();
                if(ae == AnnonEnum.Protocol)
                    ProtocolRecive.protocol(model);
                else {
                    o = model.getClazz().newInstance();
                    List<String> bean = reflectField(model.getId(), o,ae);
                    AssemblyBean.assemblyBean(o.getClass().getSimpleName(), bean, ae);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取对象属性名并生成协议类（java、C#）
     */
    private static List<String> reflectField(String id,Object o,AnnonEnum ae) {
        Field[] fields = o.getClass().getDeclaredFields();
        List<String> sb = new ArrayList<>();
        if (fields.length<=0)
            return null;
        String beanName = o.getClass().getName();
        String impl = "";
        String clazz = "";
        switch (ae){
            case Protocol:
                clazz = "public class "+o.getClass().getSimpleName()+" : IOperateCommand {\n";
                break;
            case DataTable:
                sb.add("namespace "+StringUtils.substringBeforeLast(beanName,".")+"{\n");
                clazz = "\tpublic class "+o.getClass().getSimpleName()+" : DataTableMessage {\n";
                break;
            case Protostuff:
                clazz = "public class "+o.getClass().getSimpleName()+" : IProtostuff {\n";
        }
        sb.add(clazz);
        if(ae == AnnonEnum.DataTable) {
            sb.add("\t\tpublic int id(){\n\t\t\treturn Id;\n\t\t}\n");
            sb.add("\t\tpublic static "+o.getClass().getSimpleName()+" get(int id){\n" +
            "\t\t\treturn StaticConfigMessage.Instance.get<"+o.getClass().getSimpleName()+">(typeof("+o.getClass().getSimpleName()+"),id);\n\t\t}\n");
        }
        for (int i = 0;i< fields.length;i++){
            Type isType = fields[i].getGenericType();
            String fieldName = fields[i].getName();
            //类型
            String typestr = "";
            //泛型类型
            if(isType instanceof ParameterizedType){
                typestr = CheckType.getGather(fields[i],beanName,fieldName);
                //数组
            }else if(fields[i].getType().isArray()){
                String arrFieldType = fields[i].getGenericType().getTypeName();
                Class<?> arr = fields[i].getType();
                arrFieldType = StringUtils.substringBeforeLast(arrFieldType,"[");
                typestr = CheckType.getArrType(arrFieldType,beanName,fieldName)+"[]";
            }
            else {
                Type type = fields[i].getGenericType();
                typestr = CheckType.getType(type,beanName,fieldName);
            }
            //属性名
            String field = fields[i].getName();
            field = StringUtils.capitalize(field);
            String s = "";
            if(ae == AnnonEnum.DataTable)
                s = "\t\tpublic readonly "+typestr+" "+field+";"+"\n";
            else
                s = "\tpublic "+typestr+" "+field+"{get;set;}"+"\n";
            sb.add(s);
        }
        if(ae == AnnonEnum.DataTable){
            sb.add("\t\tpublic void AfterInit(){}\n");
            sb.add("\t}\n");
            sb.add("}");
        }else
            sb.add("}");
        return sb;
    }
}
