package org.baize.logic;

/**
 * 作者： 白泽
 * 时间： 2017/11/8.
 * 描述：
 */
public interface IFactory<T> {
    <T> T getBean(Class<T> clazz);
}
