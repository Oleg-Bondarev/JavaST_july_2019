package by.training.task06_commonResource;

/**Main class.*/
public final class ThreadsApp {
    private ThreadsApp() { }
    /**main meth.
     * @param args -progr args.*/
    public static void main(final String[] args) {
        CommonRes commRes = new CommonRes();

        for (int i = 1; i < 6; i++) {
            Thread t = new Thread(new CountThread(commRes));
            t.setName("Thread " + i);
            t.start();
        }
    }
}
