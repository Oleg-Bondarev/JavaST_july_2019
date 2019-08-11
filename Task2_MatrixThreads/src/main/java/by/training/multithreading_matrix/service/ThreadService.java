package by.training.multithreading_matrix.service;

import by.training.multithreading_matrix.entity.MatrixThread;
import by.training.multithreading_matrix.property.MatrixProperty;
import by.training.multithreading_matrix.service.interfaces.FileService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Thread service for working with matrix diagonal.
 * */
final class ThreadService {
    /**
     * Logger.
     * */
    private static final Logger LOGGER =
            LogManager.getLogger(ThreadService.class);
    /**
     * Executor.
     * */
    private ExecutorService executor;
    /**
     * Instance.
     * */
    private static class InstanceHolder {
        /**
         * Instance for singleton.
         * */
        static final ThreadService INSTANCE =
                new ThreadService(MatrixProperty.PATH_TRANSFORMATION_NUMBERS);
    }
    /**
     * Initialize threads.
     * @param path -path to file with numbers for threads.
     * */
    private ThreadService(final String path) {
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
        ThreadFactory threadFactory = runnable -> new MatrixThread(runnable,
                iterator.next());
        executor = Executors.newFixedThreadPool(countOfThreads,
                threadFactory);
    }
    /**
     * Getter.
     * @return instance.
     * */
    static ThreadService getInstance() {
        return InstanceHolder.INSTANCE;
    }
    /**
     * Getter.
     * @return executor.
     * */
    ExecutorService getExecutor() {
        return executor;
    }
}
