import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/GUI/GUI.fxml")));

        Scene scene = new Scene(root, 600, 600);

        stage.setTitle("PageObject Generator");
        stage.setScene(scene);

        stage.show();
    }
}

//poszukaj po id
//poszukaj po klasie
//poszukaj po css - opcjonalnie
// wpisz css/xpath z palca
// pozwol userowi wybrać opcje, ale zaproponuj domyślne na podstawie prostego algorytmu id/klasa,
// wystarczy isc do gory po elementach DOM