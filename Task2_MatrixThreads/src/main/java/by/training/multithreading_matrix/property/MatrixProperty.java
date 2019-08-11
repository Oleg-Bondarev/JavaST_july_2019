package by.training.multithreading_matrix.property;

/**
 * Property data for matrix task.
 * */
public final class MatrixProperty {
    /**
     * Constructor.
     * */
    private MatrixProperty() { }
    /**
     * Path to file. Matrix transformation task.
     * */
    public static final String PATH_TRANSFORMATION_MATRIX = "data\\input.txt";
    /**
     * Path to file. Numbers for threads, transformation task.
     * */
    public static final String PATH_TRANSFORMATION_NUMBERS =
                                                    "data\\thread_numbers.txt";
    /**
     * Min. matrix dimension.
     * */
    public static final int MINIMUM_DIMENSION = 8;
    /**
     * Max. matrix dimension.
     * */
    public static final int MAXIMUM_DIMENSION = 12;
    /**
     * Min. count of threads.
     * */
    public static final int MINIMUM_COUNT_OF_THREADS = 4;
    /**
     * Max. count of threads.
     * */
    public static final int MAXIMUM_COUNT_OF_THREADS = 6;
}
