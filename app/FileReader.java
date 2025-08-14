package app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

public class FileReader {

    public static LinkedList<String> fileIPs = new LinkedList<>();

    static BufferedReader reader = null;


    public static void fillFileIps() throws IOException {
        {
            try {
                reader = new BufferedReader(new java.io.FileReader("C:/Users/rulko/Desktop/OSI2/src/app/ipList.txt"));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        String line = null;
        while ((line = reader.readLine())!= null){
            fileIPs.add(line);
        }
    }


}
