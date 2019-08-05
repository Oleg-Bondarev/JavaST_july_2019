package by.training.task01_threadHelloWorld;

/**Hello world class.*/
public class HelloWorld extends Thread {
    /**run method.*/
    public void run() {
        System.out.println(currentThread() + " Hello world");
    }
}
