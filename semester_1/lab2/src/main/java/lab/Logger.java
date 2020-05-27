package lab;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

final class Logger {

    private static boolean sessionStart = true;

    private Logger(){}

    static void log(String data) {
        File file = new File("log.txt");

        try (FileWriter fileWriter = new FileWriter(file, !sessionStart)){
            fileWriter.write(data);
        } catch (IOException ignored) {
            return;
        }
        if (sessionStart)
            sessionStart = false;
    }
}
