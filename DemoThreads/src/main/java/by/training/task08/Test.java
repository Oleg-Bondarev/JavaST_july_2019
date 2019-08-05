package by.training.task08;
//11 pt

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    public static void main(String[] args) {
        CommonRes commonRes = new CommonRes();
        ReentrantLock locker = new ReentrantLock();
        for (int i=1; i<6; i++) {
            Thread t = new Thread(new CountThread(commonRes, locker));
            t.setName("Thread " + i);
            t.start();
        }
    }
}

class CommonRes {
    int x = 0;
}

class CountThread implements Runnable {
    CommonRes res;
    ReentrantLock locker;

    CountThread(CommonRes res, ReentrantLock loc) {
        this.res = res;
        locker = loc;
    }
    @Override
    public void run() {
        locker.lock();
        try {
            res.x = 1;
            for (int i=1; i<5; i++) {
                System.out.printf("%s %d \n", Thread.currentThread().getName(), res.x);
                res.x++;
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }
}


