package org.baize.logic.mainroom.shop.manager;

import org.baize.logic.AdapterManager;
import org.baize.utils.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * 作者： 白泽
 * 时间： 2017/12/8.
 * 描述：
 */
@Service
public class ShopLogicManager extends AdapterManager<IShop>{
    public static ShopLogicManager getInstance(){
        return SpringUtils.getBean(ShopLogicManager.class);
    }
    @Autowired
    private Set<IShop> set;
    @Override
    public Set<IShop> getSet() {
        return set;
    }
    @PostConstruct
    @Override
    protected void init(){
        super.init();
    }
}
