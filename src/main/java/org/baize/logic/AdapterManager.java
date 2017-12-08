package org.baize.logic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public abstract class AdapterManager<T extends ILogic> {
    private final Map<String,T> map = new HashMap<>();
    protected void init(){
        if(this.getSet() != null){
            Iterator arg1 = this.getSet().iterator();
            while (arg1.hasNext()){
                ILogic logic = (ILogic)arg1.next();
                String name = logic.getClass().getSimpleName();
                String type = name.substring(name.indexOf("_")+1);
                this.map.put(type, (T) logic);
            }
        }
    }
    public T getLogic(int type){
        return (T) this.map.get(String.valueOf(type));
    }
    public T getLogic(String type){
        return (T) this.map.get(type);
    }
    public abstract Set<T> getSet();
}