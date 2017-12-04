package org.baize.error;

/**
 * 作者： 白泽
 * 时间： 2017/12/1.
 * 描述：
 */
public class LogAppError extends IError {
    public LogAppError(int errorCode) {
        super(errorCode);
    }
    public LogAppError(String msg) {
        super(msg);
        throw this;
    }
}
