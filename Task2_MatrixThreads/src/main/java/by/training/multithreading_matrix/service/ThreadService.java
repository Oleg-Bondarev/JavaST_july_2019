package by.training.multithreading_matrix.service;

import by.training.multithreading_matrix.entity.MatrixThread;
import by.training.multithreading_matrix.property.MatrixProperty;
import by.training.multithreading_matrix.service.interfaces.FileService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Thread service for working with matrix diagonal.
 */
final class ThreadService {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Executor.
     */
    private ExecutorService executor;

    /**
     * Instance.
     */
    private static class InstanceHolder {
        /**
         * Instance for singleton.
         */
        static final ThreadService INSTANCE =
                new ThreadService(MatrixProperty.PATH_TRANSFORMATION_NUMBERS);
    }

    /**
     * Initialize threads.
     *
     * @param path -path to file with numbers for threads.
     */
    private ThreadService(final String path) {
        Iterator<Integer> iterator;
        List<String> list = readingByPath(path);
        List<Integer> numbersForThreads =
                validateCountOfThreads(createIntegerList(list));
        int countOfThreads = numbersForThreads.size();

        iterator = numbersForThreads.iterator();
        ThreadFactory threadFactory = runnable -> new MatrixThread(runnable,
                iterator.next());
        executor = Executors.newFixedThreadPool(countOfThreads,
                threadFactory);
    }

    /**
     * Getter.
     *
     * @return instance.
     */
    static ThreadService getInstance() {
        return InstanceHolder.INSTANCE;
    }

    /**
     * Getter.
     *
     * @return executor.
     */
    ExecutorService getExecutor() {
        return executor;
    }
    /**
     * Reading list.
     * @param path -path to file.
     * @return list with information.
     * */
    private List<String> readingByPath(final String path) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        FileService fileService = serviceFactory.getFileService();
        List<String> list;
        try {
            list = fileService.read(path);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Problems with reading numbers for "
                    + "threads. " + e.getMessage());
            throw new RuntimeException("Problems with reading numbers for "
                    + "threads. " + e.getMessage());
        }
        return list;
    }
    /**
     * Parsing for integer list.
     * @param list -string list.
     * @return integer list.
     * */
    private List<Integer> createIntegerList(final List<String> list) {
        String tempStr;
        List<Integer> numbersForThreads = new ArrayList<>();
        int len = list.size();
        int tempInt;
        for (String str : list) {
            tempStr = str;
            tempInt = Integer.parseInt(tempStr);
            if (tempStr.matches("\\d+")
                    && !numbersForThreads.contains(tempInt)) {
                numbersForThreads.add(tempInt);
            }
        }
        /*for (int i = 0; i < len; i++) {
            tempStr = list.get(i);
            tempInt = Integer.parseInt(tempStr);
            if (tempStr.matches("\\d+")
                    && !numbersForThreads.contains(tempInt)) {
                numbersForThreads.add(tempInt);
            }
        }*/
        return numbersForThreads;
    }
    /**
     * Checking count of threads. If count of threads less than minimum count
     * of threads, generate unique numbers for threads while their count not
     * equals minimum count of threads.
     *
     * @param numbers -list of thread numbers.
     * @return list of numbers.
     * */
    private List<Integer> validateCountOfThreads(final List<Integer> numbers) {
        int countOfThreads = numbers.size();
        if (countOfThreads < MatrixProperty.MINIMUM_COUNT_OF_THREADS) {
            Random random = new SecureRandom();
            int temp;
            final int bound = 100;
            int difference =
                    MatrixProperty.MINIMUM_COUNT_OF_THREADS - countOfThreads;
            while (difference > 0) {
                temp = random.nextInt(bound);
                while (numbers.contains(temp)) {
                    temp = random.nextInt(bound);
                }
                numbers.add(temp);
                difference--;
            }
        }
        if (countOfThreads > MatrixProperty.MAXIMUM_COUNT_OF_THREADS) {
            for (int i = MatrixProperty.MAXIMUM_COUNT_OF_THREADS;
                 i < countOfThreads; i++) {
                numbers.remove(i);
            }
        }
        return numbers;
    }
}
