package org.baize.dao.model;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.baize.dao.sqlmapper.PlayerMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者： 白泽
 * 时间： 2017/11/6.
 * 描述：
 */
public abstract class Persist {
    private transient int id;
    private PlayerMapper mapper;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void update(){
        //提交消息队列，线程调用submit方法
    }
    private void submit(){
        //mapper = springutils.getbean(playermapper.class)
        String str = JSON.toJSONString(this);
        if(this == null || !(this instanceof Persist) || StringUtils.isEmpty(str))
            return;
        String sql = "'"+str+"'";
        Map<String,String> map = new HashMap<>();
        map.put("k",this.getClass().getSimpleName());
        map.put("v",sql);
        map.put("id",id+"");
        mapper.updateField(map);
    }
}
