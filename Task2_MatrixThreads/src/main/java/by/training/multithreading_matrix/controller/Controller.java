package by.training.multithreading_matrix.controller;

import by.training.multithreading_matrix.entity.Matrix;
import by.training.multithreading_matrix.service.interfaces.FileService;
import by.training.multithreading_matrix.service.interfaces.MatrixService;
import by.training.multithreading_matrix.service.ServiceException;
import by.training.multithreading_matrix.service.ServiceFactory;
import by.training.multithreading_matrix.validator.MatrixValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**Initiates command execution.*/
public final class Controller {
    /**
     * Instance.
     * */
    private static final Controller INSTANCE = new Controller();
    /**
     * Logger.
     * */
    private static final Logger LOGGER = LogManager.getLogger(Controller.class);
    /**
     * Service factory.
     * */
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    /**
     * Msg. for exception.
     * */
    private static final String MSG = "Function arguments must be not null.";
    /**
     * Validator.
     * */
    private MatrixValidator validator = new MatrixValidator();

    /**Constructor.*/
    private Controller() { }
    /**Getter for instance.
     * @return instance.*/
    public static Controller getInstance() {
        return INSTANCE;
    }

    /**Init matrix by random elements.
     * @param matrix -matrix.
     * @param start -start of generation.
     * @param end -end of generation.
     * @return matrix with random elements.*/
    public Matrix generateMatrix(final Matrix matrix, final int start,
                                 final int end) {
        MatrixService matrixService = serviceFactory.getMatrixService();
        return matrixService.generateMatrix(matrix, start, end);
    }

    /**Read and create matrix from file.
     * @param path -path to file with matrix.
     * @return matrix.*/
    public Matrix ctreateMatrixFromFile(final String path) {
        FileService fileService = serviceFactory.getFileService();
        List<String> matrixInList;
        Matrix matrix;
        try {
            matrixInList = fileService.read(path);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Problems with file: " + e.getMessage());
            throw new RuntimeException(e);
        }
        MatrixService matrixService = serviceFactory.getMatrixService();
        try {
            matrix = matrixService.createMatrix(matrixInList);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Problems with file: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return matrix;
    }

    /**Multiplication.
     * @param matrixA      -first matrix.
     * @param matrixB      -second matrix.
     * @param countThreads -count threads to multiplication.
     * @return result matrix.*/
    public Matrix multiplicateMatrix(final Optional<Matrix> matrixA,
                                        final Optional<Matrix> matrixB,
                                        final int countThreads) {
        if (validator.isPresent(matrixA, matrixB)) {
            LOGGER.log(Level.WARN, MSG);
            throw new IllegalArgumentException(MSG);
        }
        int countRows = matrixA.get().getHorizontalSize();
        int countColumns = matrixB.get().getVerticalSize();
        Matrix matrixResult = new Matrix(countRows, countColumns);

        MatrixService matrixService = serviceFactory.getMatrixService();
        try {
            matrixResult = matrixService.multiplicateMatrix(matrixA.get(),
                    matrixB.get(), countThreads);
        } catch (ServiceException e) {
            LOGGER.log(Level.WARN, e.getMessage());
        }

        return matrixResult;
    }
    /**Multiplication in one thread.
     * @param matrixA      -first matrix.
     * @param matrixB      -second matrix.
     * @param countThreads -count threads to multiplication.
     * @return result matrix.*/
    public Matrix multiplicateMatrixSecondWay(final Optional<Matrix> matrixA,
                                     final Optional<Matrix> matrixB,
                                     final int countThreads) {
        if (validator.isPresent(matrixA, matrixB)) {
            LOGGER.log(Level.WARN, MSG);
            throw new IllegalArgumentException(MSG);
        }
        int countRows = matrixA.get().getHorizontalSize();
        int countColumns = matrixB.get().getVerticalSize();
        Matrix matrixResult = new Matrix(countRows, countColumns);

        MatrixService matrixService = serviceFactory.getMatrixService();
        try {
            matrixResult = matrixService
                    .multiplicationByMultiThreadSecondWay(matrixA.get(),
                                                matrixB.get(), countThreads);
        } catch (ServiceException e) {
            LOGGER.log(Level.WARN, e.getMessage());
        }

        return matrixResult;
    }
    /**
     * Threads write numbers on matrix diagonal.
     * @param matr -matrix for work.
     * @return new matrix.
     * */
    public Matrix workWithDiagonal(final Optional<Matrix> matr) {
        if (!matr.isPresent()) {
            LOGGER.log(Level.WARN, MSG);
            throw new IllegalArgumentException("Function arguments must be "
                    + "not null.");
        }
        Matrix matrix = matr.get();
        if (matrix.getHorizontalSize() != matrix.getVerticalSize()) {
            LOGGER.log(Level.WARN, "Incorrect input matrix. Count of rows"
                    + " must be equals count of columns.");
            throw new IllegalArgumentException("Incorrect input matrix. Count"
                    + " of rows must be equals count of columns.");
        }
        MatrixService matrixService = serviceFactory.getMatrixService();
        try {
            matrix = matrixService.fillZeroOnDiagonal(matrix);
        } catch (ServiceException e) {
            LOGGER.log(Level.WARN, e.getMessage());
        }
        matrix = matrixService.transformDiagonalByThreads(matrix);
        return matrix;
    }
}
