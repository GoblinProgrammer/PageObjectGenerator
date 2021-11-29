package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainWindow {
    Stage stage = new Stage();

    public MainWindow() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/GUI/GUI.fxml")));

        Scene scene = new Scene(root, 600, 600);

        stage.setTitle("PageObject Generator");
        stage.setScene(scene);
    }

    public Stage getStage(){return stage;}
}
