package by.training.task05_threadToDisable;

import by.training.task03_threadProperty.Priorthread;

/**main class.*/
public final class Runner {
    /**Def constr.*/
    private Runner() { }
    /**main method.
     * @param args -program args.*/
    public static void main(final String[] args) {
        System.out.println("Main thread start.");
        Priorthread min = new Priorthread("Min");
        Priorthread max = new Priorthread("Max");
        Priorthread norm = new Priorthread("Norm");
        min.setPriority(Thread.MIN_PRIORITY);
        max.setPriority(Thread.MAX_PRIORITY);
        norm.setPriority(Thread.NORM_PRIORITY);
        min.start();
        max.start();
        norm.start();
        try {
            min.join();
            max.join();
            //norm.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main thread stop.");
    }
}
