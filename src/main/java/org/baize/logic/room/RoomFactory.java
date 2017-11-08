package org.baize.logic.room;

import org.apache.log4j.Logger;
import org.baize.logic.IFactory;

/**
 * 作者： 白泽
 * 时间： 2017/11/8.
 * 描述：
 */
public class RoomFactory implements IFactory<IRoom>{
    private static Logger logger = Logger.getLogger(RoomFactory.class);
    @Override
    public <T> T getBean(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            logger.warn("初始化Room异常",e);
            return null;
        }
    }
}
