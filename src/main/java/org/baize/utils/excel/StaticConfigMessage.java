package org.baize.utils.excel;

import org.baize.utils.SpringUtils;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.*;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/10/15******
 * 描述：
 */
@Service
public class StaticConfigMessage {
    private Map<Class<?>,Map<Serializable,Object>> allData = new HashMap<>();
    public static StaticConfigMessage getInstance(){
        return SpringUtils.getBean(StaticConfigMessage.class);
    }

    /**
     * 添加导表数据
     * @param clazz
     * @param data
     */
    public void put(Class<?> clazz,Map<Serializable,Object> data){
        allData.put(clazz,data);
    }

    /**
     *获取导表数据对应的类
     * @param clazz
     * @param id
     * @param <T>
     * @return
     */
    public <T> T get(Class<T> clazz, Serializable id){
        Map<Serializable,Object> map = allData.get(clazz);
        if(map == null)
            return null;
        return (T) map.get(id);
    }
    public Map<Serializable,Object> getMap(Class<?> clazz){
        if(!allData.containsKey(clazz))
            return new HashMap<>();
        return allData.get(clazz);
    }
}
