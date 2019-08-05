package by.training.task07_synchrWrite;

/**Runner class.*/
public class SyncThread extends Thread {
    /**Resource.*/
    private Resource rs;
    /**Constr.
     * @param name  -th name.
     * @param newRs  -resource.*/
    public SyncThread(final String name, final Resource newRs) {
        super(name);
        this.rs = newRs;
    }
    /**Run meth.*/
    public void run() {
        for (int i = 0; i < 5; i++) {
            rs.writing(getName(), i);
        }
    }
}
