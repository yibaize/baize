package org.baize.utils.assemblybean.service;

import java.util.Set;

/**
 * 作者： 白泽
 * 时间： 2017/11/2.
 * 描述：
 */
public class GeneratorModel {
    private Set<CodeModel> dataTable;
    private Set<CodeModel> protocol;
    private Set<CodeModel> protostuff;

    public Set<CodeModel> getDataTable() {
        return dataTable;
    }

    public void setDataTable(Set<CodeModel> dataTable) {
        this.dataTable = dataTable;
    }

    public Set<CodeModel> getProtocol() {
        return protocol;
    }

    public void setProtocol(Set<CodeModel> protocol) {
        this.protocol = protocol;
    }

    public Set<CodeModel> getProtostuff() {
        return protostuff;
    }

    public void setProtostuff(Set<CodeModel> protostuff) {
        this.protostuff = protostuff;
    }
}
