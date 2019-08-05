package by.training.task07_synchrWrite;

import java.io.IOException;

/**main class.*/
public final class Main {
    /***/
    private Main() { }
    /**Main meth.
     * @param args -progr args.*/
    public static void main(final String[ ] args) {
        Resource s = null;
        try {
            s = new Resource("src/main/java/by/training/task07_synchrWrite"
                    + "/data/output.txt");
            SyncThread t1 = new SyncThread("First", s);
            SyncThread t2 = new SyncThread("Second", s);
            t1.start();
            t2.start();
            t1.join();
            t2.join();
        } catch (IOException e) {
            System.err.print("ошибка файла: " + e);
        } catch (InterruptedException e) {
            System.err.print("ошибка потока: " + e);
        } finally {
            s.close();
        }
    }
}
