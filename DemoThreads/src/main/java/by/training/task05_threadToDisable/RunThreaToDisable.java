package by.training.task05_threadToDisable;

/**Main class.*/
public final class RunThreaToDisable {
    private RunThreaToDisable() { }
    /**main meth.
     * @param args -progr args.*/
    public static void main(final String[] args) {
        System.out.println("Main thread start");
        ThreadToDisable maTh = new ThreadToDisable();
        Thread maT = new Thread(maTh, "name of ThreadToDisable");
        maT.start();

        try {
            Thread.sleep(1100);
            maTh.disable();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main thread end");
    }
}
