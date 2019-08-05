package by.training.task01_threadHelloWorld;

import static java.lang.Thread.currentThread;

/**Main class.*/
public final class Main {
    /**Default constructor.*/
    private Main() { }
    /**Main method.
     * @param args - args of progr.*/
    public static void main(final String[] args) {
        HelloWorld thread = new HelloWorld();
        System.out.println(currentThread());
        System.out.println("State= " + thread.getState());
        thread.start();
        //run нельзя
        System.out.println("State= " + thread.getState());
    }
}
