package org.baize.EnumType;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者： 白泽
 * 时间： 2017/11/8.
 * 描述：
 */
public enum LoginType {
    Default(0),
    Account(1),
    Phon(2),
    QQ(3),
    WX(4);
    private int id;
    private LoginType(int id) {
        this.id = id;
    }
    public int id(){
        return id;
    }
    private static final Map<Integer,LoginType> MAP = new HashMap<>();
    public static LoginType getLoginType(int id){
        return MAP.getOrDefault(id,Default);
    }
    static {
        for (LoginType t:LoginType.values()){
            MAP.put(t.id,t);
        }
    }
}
