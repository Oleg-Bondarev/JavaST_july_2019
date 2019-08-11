package by.training.multithreading_matrix.service;

import by.training.multithreading_matrix.property.MatrixProperty;
import by.training.multithreading_matrix.service.interfaces.FileService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Thread service for working with matrix diagonal with using semaphores.
 * */
public final class ThreadServiceSemaphore {
    /**
     * Logger.
     * */
    private static final Logger LOGGER =
            LogManager.getLogger(ThreadServiceSemaphore.class);
    /**
     * Instance.
     * */
    private static class InstanceHolder {
        /**
         * Instance for singleton.
         * */
        static final ThreadServiceSemaphore INSTANCE =
            new ThreadServiceSemaphore(MatrixProperty
                    .PATH_TRANSFORMATION_NUMBERS);
    }
    /**
     * Initialize threads.
     * @param path -path to file with numbers for threads.
     * */
    private ThreadServiceSemaphore(final String path) {
        Semaphore semaphore = new Semaphore(1);
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        FileService fileService = serviceFactory.getFileService();
        List<String> list;
        List<Integer> numbersForThreads = new ArrayList<>();
        Iterator<Integer> iterator;
        int len;
        int countOfThreads;
        String tempStr;

        try {
            list = fileService.read(path);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Problems with reading numbers for "
                    + "threads. " + e.getMessage());
            throw new RuntimeException("Problems with reading numbers for "
                    + "threads. " + e.getMessage());
        }
        len = list.size();
        for (int i = 0; i < len; i++) {
            tempStr = list.get(i);
            if (tempStr.matches("\\d+")) {
                numbersForThreads.add(Integer.parseInt(tempStr));
            }
        }
        countOfThreads = numbersForThreads.size();
        if (countOfThreads < MatrixProperty.MINIMUM_COUNT_OF_THREADS) {
            countOfThreads = MatrixProperty.MAXIMUM_COUNT_OF_THREADS;
        }
        if (countOfThreads > MatrixProperty.MAXIMUM_COUNT_OF_THREADS) {
            countOfThreads = MatrixProperty.MAXIMUM_COUNT_OF_THREADS;
        }
        iterator = numbersForThreads.iterator();
        for (int i = 0; i < countOfThreads; i++) {
            new WorkWithSemaphore(semaphore, iterator.next());
        }
    }
    /**
     * Getter.
     * @return instance.
     * */
    public static ThreadServiceSemaphore getInstance() {
        return ThreadServiceSemaphore.InstanceHolder.INSTANCE;
    }
}
