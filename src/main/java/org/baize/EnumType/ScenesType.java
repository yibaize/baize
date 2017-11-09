package org.baize.EnumType;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者： 白泽
 * 时间： 2017/11/8.
 * 描述：
 */
public enum ScenesType {
    Login(0),
    Mian(1),
    Flower(2);
    private int id;
    private ScenesType(int id) {
        this.id = id;
    }
    public int id(){
        return id;
    }
    private static final Map<Integer,ScenesType> MAP = new HashMap<>();
    public static ScenesType getLoginType(int id){
        return MAP.getOrDefault(id,Login);
    }
    static {
        for (ScenesType t:ScenesType.values()){
            MAP.put(t.id,t);
        }
    }
}
