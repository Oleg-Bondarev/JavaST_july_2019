package by.training.multithreading_matrix.controller;

import by.training.multithreading_matrix.entity.Matrix;
import by.training.multithreading_matrix.service.FileService;
import by.training.multithreading_matrix.service.MatrixService;
import by.training.multithreading_matrix.service.ServiceException;
import by.training.multithreading_matrix.service.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**Initiates command execution.*/
public class Controller {
    /**Instance.*/
    private static final Controller INSTANCE = new Controller();
    /**Logger*/
    static final Logger LOGGER = LogManager.getLogger(Controller.class);
    /**Service factory.*/
    ServiceFactory serviceFactory = ServiceFactory.getInstance();

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
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        MatrixService matrixService = serviceFactory.getMatrixService();
        return matrixService.generateMatrix(matrix, start, end);
    }

    /**Read and create matrix from file.
     * @param path -path to file with matrix.
     * @return matrix.*/
    public Matrix ctreateMatrixFromFile(final String path) {
        FileService fileService = serviceFactory.getFileService();
        List<String> matrixInList;
        Matrix matrix = null;
        try {
            matrixInList = fileService.read(path);
        } catch (ServiceException e) {
            LOGGER.log(Level.WARN, "Problems with file: " + e.getMessage());
            return null; //TODO fix
        }
        MatrixService matrixService = serviceFactory.getMatrixService();
        try {
            matrix = matrixService.createMatrix(matrixInList);
        } catch (ServiceException e) {
            LOGGER.log(Level.WARN, "Problem in creating matrix: "
                    + e.getMessage());
        }
        return matrix; //TODO fix null
    }

    /**Multiplication in one thread.
     * @param matrixA -first matrix.
     * @param matrixB -second matrix.*/
    public Matrix multiplicationInOnThread(final Optional<Matrix> matrixA,
                                           final Optional<Matrix> matrixB) {
        if (!matrixA.isPresent() || !matrixB.isPresent()) {
            LOGGER.log(Level.WARN, "Function arguments must be not null.");
            throw new IllegalArgumentException("Function arguments must be " +
                    "not null.");
        }
        int countRows = matrixA.get().getHorizontalSize();
        int countColumns = matrixB.get().getVerticalSize();
        Matrix matrixResult = new Matrix(countRows, countColumns);

        MatrixService matrixService = serviceFactory.getMatrixService();
        try {
            matrixResult = matrixService.multiplicateMatrix(matrixA.get(),
                    matrixB.get(), 1);
        } catch (ServiceException e) {
            LOGGER.log(Level.WARN, e.getMessage());
        }
        return matrixResult;
    }
}
