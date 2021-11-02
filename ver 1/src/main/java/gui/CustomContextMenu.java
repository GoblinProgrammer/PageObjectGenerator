package gui;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CustomContextMenu {

    public static void disableDefaultContextMenu(Stage stage){
        stage.getScene().addEventFilter(MouseEvent.MOUSE_RELEASED,
                new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.SECONDARY) {
                            event.consume();
                        }

                    }
                });
    }
}
