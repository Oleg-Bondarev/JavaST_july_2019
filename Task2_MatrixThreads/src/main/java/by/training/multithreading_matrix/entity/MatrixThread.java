package by.training.multithreading_matrix.entity;

/**
 * Matrix thread class.
 * */
public class MatrixThread extends Thread {
    /**
     * Number that thread write in matrix diagonal position.
     * */
    private int numberForThread;
    /**
     * Constructor.
     * @param target -task.
     * @param number -number for thread work.
     * */
    public MatrixThread(final Runnable target, final int number) {
        super(target);
        this.numberForThread = number;
    }
    /**
     * Getter.
     * @return number for thread work.
     * */
    public int getNumberForThread() {
        return numberForThread;
    }
}
