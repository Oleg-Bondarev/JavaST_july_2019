package by.training.task08_synchStringBuffer;

import java.util.concurrent.TimeUnit;

/**Main class.*/
public final class SynchBuffer {
    /**Constr.*/
    private SynchBuffer() { }
    /**Number to output.*/
    private static int counter = 0;
    /**StrBuffer.*/
    private static StringBuffer s = new StringBuffer();
   // static StringBuilder strb = new StringBuilder();

    /**main meth.
     * @param args -progr args.
     * @exception InterruptedException -thread sleep exception*/
    public static void main(final String[] args) {
        new Thread() {
            public void run() {
                synchronized (s) {
                    while (SynchBuffer.counter++ < 3) {
                        s.append("A");
                        System.out.print("> " + counter + " ");
                        System.out.println(s);
                        try {
                            TimeUnit.MILLISECONDS.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (SynchBuffer.counter++ < 6) {
            System.out.print("< " + counter + " ");
            s.append("B");
            System.out.println(s);
        }
    }
}
