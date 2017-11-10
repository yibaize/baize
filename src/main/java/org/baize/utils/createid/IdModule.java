package org.baize.utils.createid;

/**
 * 作者： 白泽
 * 时间： 2017/11/9.
 * 描述：
 */
public class IdModule {
    private int id;
    private long timer;

    public IdModule() {
    }

    public IdModule(int id, int timer) {
        this.id = id;
        this.timer = timer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimer() {
        return timer;
    }

    public void setTimer(long timer) {
        this.timer = timer;
    }

    @Override
    public String toString() {
        return "IdModule{" +
                "id=" + id +
                ", timer=" + timer +
                '}';
    }
}
