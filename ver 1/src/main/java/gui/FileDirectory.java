package gui;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public abstract class FileDirectory {
    public static String getDirectory(){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("src"));

        File selectedDirectory = directoryChooser.showDialog(new Stage());

        return selectedDirectory.getAbsolutePath();
    }
}
