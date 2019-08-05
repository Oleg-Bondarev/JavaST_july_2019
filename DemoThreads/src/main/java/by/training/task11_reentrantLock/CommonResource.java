package by.training.task11_reentrantLock;

/**Common resource class.*/
public class CommonResource {
    /**Common res.*/
    private int x = 0;
    /**Setter.
     * @param newX -new x.*/
    public void setX(final int newX) {
        this.x = newX;
    }
    /**Getter.
     * @return x.*/
    public int getX() {
        return x;
    }
}
