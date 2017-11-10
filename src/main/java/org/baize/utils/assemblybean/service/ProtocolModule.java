package org.baize.utils.assemblybean.service;

import java.util.List;

/**
 * 作者： 白泽
 * 时间： 2017/11/3.
 * 描述：
 */
public class ProtocolModule implements Comparable<ProtocolModule>{
    private int clazzId;
    private String clazzName;
    private List<String> field;

    public ProtocolModule() {
    }

    public int getClazzId() {
        return clazzId;
    }

    public void setClazzId(int clazzId) {
        this.clazzId = clazzId;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public List<String> getField() {
        return field;
    }

    public void setField(List<String> field) {
        this.field = field;
    }

    @Override
    public int compareTo(ProtocolModule o) {
        return clazzId - o.getClazzId();
    }
}
