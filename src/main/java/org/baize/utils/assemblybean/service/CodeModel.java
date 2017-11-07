package org.baize.utils.assemblybean.service;

/**
 * 作者： 白泽
 * 时间： 2017/11/2.
 * 描述：
 */
public class CodeModel {
    private String id;
    private Class<?> clazz;

    public CodeModel() {
    }

    public CodeModel(String id, Class<?> clazz) {

        this.id = id;
        this.clazz = clazz;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }
}
