package by.training.task03_threadProperty;

/**Main class.*/
public final class PriorityRunner {
    /**Defaul constr.*/
    private PriorityRunner() { }
    /**main method.
     * @param args -program args.*/
    public static void main(final String[] args) {
        Priorthread min = new Priorthread("Min");
        Priorthread max = new Priorthread("Max");
        Priorthread norm = new Priorthread("Norm");
        min.setPriority(Thread.MIN_PRIORITY);
        max.setPriority(Thread.MAX_PRIORITY);
        norm.setPriority(Thread.NORM_PRIORITY);
        min.start();
        max.start();
        norm.start();
    }
}
