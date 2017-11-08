package org.baize.utils.assemblybean.config;


import org.baize.utils.assemblybean.service.SelectAnnotationClass;

/**
 * 作者： 白泽
 * 时间： 2017/11/2.
 * 描述：代码生成入口
 */
public class CodeGenerator {
    public static void main(String[] args) {
        SelectAnnotationClass.getIocClazz("org.baize");
    }
}
