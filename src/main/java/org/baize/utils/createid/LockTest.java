package org.baize.utils.createid;

/**
 * 作者： 白泽
 * 时间： 2017/12/4.
 * 描述：死锁掩饰程序
 */
public class LockTest implements Runnable{
    private boolean flag;
    private static final Object o1 = new Object();
    private static final Object o2 = new Object();
    public LockTest(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        if(flag){
            synchronized (o1){
                System.out.println("if lock o1........");
                synchronized (o2){
                    System.out.println("if lock o2........");
                }
            }
        }else {
            synchronized (o2){
                System.out.println("if lock o2........");
                synchronized (o1){
                    System.out.println("if lock o1........");
                }
            }
        }
    }

    public static void main(String[] args) {
        LockTest l1 = new LockTest(true);
        LockTest l2 = new LockTest(false);
        Thread t1 = new Thread(l1);
        Thread t2 = new Thread(l2);
        t1.start();
        t2.start();
    }
}
