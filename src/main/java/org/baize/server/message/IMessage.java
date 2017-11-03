package org.baize.server.message;

/**
 * 作者： 白泽
 * 时间： 2017/11/3.
 * 描述：
 */
public interface IMessage extends Runnable {
    void execute();
}
