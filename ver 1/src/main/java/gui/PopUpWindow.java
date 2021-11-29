package gui;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public abstract class PopUpWindow {
    public static void display(String message){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        Label label = new Label(message);
        Button button = new Button("Ok");
        button.setOnAction(e -> stage.close());

        VBox layout = new VBox(10);

        layout.getChildren().addAll(label,button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 250);


        stage.setScene(scene);
        stage.showAndWait();
    }
}
