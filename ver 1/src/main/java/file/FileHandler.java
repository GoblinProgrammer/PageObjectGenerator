package file;

import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
    public static void saveToFile(String fileName, String dataToSaveInFile, String fileExtention){
        try {
            FileWriter myWriter = new FileWriter(fileName + "." + fileExtention);
            myWriter.write(dataToSaveInFile);
            myWriter.close();
            System.out.println("INFO: Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("ERROR: An error occurred.");
            e.printStackTrace();
        }
    }
}
