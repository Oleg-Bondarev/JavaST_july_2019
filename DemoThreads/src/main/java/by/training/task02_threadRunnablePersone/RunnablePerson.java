package by.training.task02_threadRunnablePersone;

/**Main class.*/
public class RunnablePerson extends Person implements Runnable {
    /**
     * Constr.
     * @param newName - name.
     */
    public RunnablePerson(final String newName) {
        super(newName);
    }
    /**Run method.*/
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(getName() + ": hello!!!");
        }
    }
    /**Main method.
     * @param args - program args.*/
    public static void main(final String[] args) {
        RunnablePerson p1 = new RunnablePerson("Alice");
        Thread th1 = new Thread(p1);
        RunnablePerson p2 = new RunnablePerson("Bob");
        Thread th2 = new Thread(p2);

        th1.start();
        try {
            th1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        th2.start();
        System.out.println("Stop main");
    }
}
