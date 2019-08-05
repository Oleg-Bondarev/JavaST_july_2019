package by.training.task06_commonResource;

/**Runner class.*/
public class CountThread implements Runnable {
    /**common res.*/
    CommonRes res;
    /**@param NewRes  -resource.*/
    CountThread(final CommonRes NewRes) {
        this.res = NewRes;
    }
    /**run method.*/
    @Override
    public void run() {
        synchronized (res) {
            res.x = 1;
            for (int i = 1; i < 5; i++) {
                System.out.printf("%s %d \n", Thread.currentThread().getName(), res.x);
                res.x++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
