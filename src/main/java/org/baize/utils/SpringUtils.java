package org.baize.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 作者：泡泡大湿
 * 时间： 2017/8/22.
 * 描述：
 */
public class SpringUtils {
    public static ApplicationContext ctx = null;

    static {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    public static <T> T getBean(final Class<T> clazz) {
        System.out.println(clazz);
        try {
            T obj = ctx.getBean(clazz);
            return obj;
        } catch (Exception e) {
            throw new RuntimeException("找不到Action_" + clazz.getName() + "这个脚本类");
        }
    }

    public static <T> T getBean(final String beanName, Class<T> t) {
        try {
            T obj = (T) ctx.getBean(beanName);
            return obj;
        } catch (Exception e) {
            throw new RuntimeException("找不到Action_" + beanName + "这个脚本类");
        }
    }
}
