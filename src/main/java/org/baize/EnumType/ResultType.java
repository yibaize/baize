package org.baize.EnumType;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者： 白泽
 * 时间： 2017/11/20.
 * 描述：
 */
public enum ResultType {
    Default(0),
    Lose(1),
    Win(2),
    Draw(3);
    private int id;
    private ResultType(int id) {
        this.id = id;
    }
    public int id(){
        return id;
    }
    private static final Map<Integer,ResultType> MAP = new HashMap<>();
    public static ResultType getLoginType(int id){
        return MAP.getOrDefault(id,Default);
    }
    static {
        for (ResultType t:ResultType.values()){
            MAP.put(t.id,t);
        }
    }
}
