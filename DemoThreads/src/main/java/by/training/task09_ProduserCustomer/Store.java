package by.training.task09_ProduserCustomer;

/**store class.*/
class Store {
    /**counter of products.*/
    int counter = 0;
    /**max count of the product.*/
    final int N = 5;

    /**synchr for produser.
     * @return 1 if put was success, 0 -  fail.*/
    synchronized int put() {
        if (counter <= N) {
            counter++; // кладем товар
            System.out.println("склад имеет " + counter + " товар(ов)");
            return 1;
        }
        return 0;
    }
    /**synchr for customers.
     * @return 1 if get was success, 0 -  fail.*/
    synchronized int get() {
        if (counter>0) {
            counter--;
            System.out.println("склад имеет " + counter + " товар(ов)");
            return 1;
        }
        return 0;
    }
}
