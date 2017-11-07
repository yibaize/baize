package org.baize.utils.assemblybean.test;

import org.baize.utils.assemblybean.annon.Protostuff;

import java.util.List;
import java.util.Set;

/**
 * 作者： 白泽
 * 时间： 2017/11/2.
 * 描述：
 */
@Protostuff
public class A {
    private List<Integer> a;
    private Set<B> b;
    private int[] ai;

    public int[] getAi() {
        return ai;
    }

    public void setAi(int[] ai) {
        this.ai = ai;
    }

    public List<Integer> getA() {
        return a;
    }

    public void setA(List<Integer> a) {
        this.a = a;
    }

    public Set<B> getB() {
        return b;
    }

    public void setB(Set<B> b) {
        this.b = b;
    }
}
