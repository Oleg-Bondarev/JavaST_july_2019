package by.training.task07_synchrWrite;

import java.io.FileWriter;
import java.io.IOException;

/**Writing to resource.*/
public class Resource {
    /**file obj.*/
    private FileWriter fileWriter;
    /**@param file  -file.
     * @exception IOException - exception with file.*/
    public Resource(final String file) throws IOException {
        fileWriter = new FileWriter(file, true);
    }
    /**@param str - str to append.
     * @param i - number to append.*/
    public synchronized void writing(final String str, final int i) {
        try {
            fileWriter.append(str + i);
            System.out.print(str + i);
            Thread.sleep((long) (Math.random() * 50));
            fileWriter.append("->" + i + " ");
            System.out.print("->" + i + " ");
        } catch (IOException e) {
            System.err.print("ошибка файла: " + e);
        } catch (InterruptedException e) {
            System.err.print("ошибка потока: " + e);
        }
    }
    /**Close all files.*/
    public void close() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            System.err.print("ошибка закрытия файла: " + e);
        }
    }
}
