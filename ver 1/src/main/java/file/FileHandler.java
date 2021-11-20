package file;

import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
    public static void saveToFile(String fileName, String dataToSaveInFile){
        try {
            FileWriter myWriter = new FileWriter(fileName + ".txt");
            myWriter.write(dataToSaveInFile);
            myWriter.close();
            System.out.println("INFO: Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("ERROR: An error occurred.");
            e.printStackTrace();
        }
    }
}
