package by.training.task04_threadManagement;

/**Main class.*/
public final class RunnerJoin {
    static {
        System.out.println("Main thread start.");
    }
    /**Def constr.*/
    private RunnerJoin() { }
    /**Main meth.
     * @param args -program args.*/
    public static void main(final String[] args) {
        JoinThread th1 = new JoinThread("First");
        JoinThread th2 = new JoinThread("Second");
        th1.start();
        th2.start();
        try {
            th1.join(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}
