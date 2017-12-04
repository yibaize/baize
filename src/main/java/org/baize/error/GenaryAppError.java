package org.baize.error;

/**
 * 作者： 白泽
 * 时间： 2017/12/1.
 * 描述：
 */
public class GenaryAppError extends IError {
    public GenaryAppError(int errorCode) {
        super(errorCode);
        throw this;
    }
}
