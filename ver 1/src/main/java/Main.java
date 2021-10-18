import gui.CustomContextMenu;
import gui.RightPanel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import org.w3c.dom.html.HTMLElement;
import util.AttributeFinder;

import java.util.Optional;

import static util.Cast.cast;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private RightPanel rp;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/GUI.fxml"));

        Scene scene = new Scene(root, 800, 800);

        stage.setTitle("FXML Welcome");
        stage.setScene(scene);

        CustomContextMenu.disableDefaultContextMenu(stage);

        stage.show();
    }
}

//poszukaj po id
//poszukaj po klasie
//poszukaj po css - opcjonalnie
// wpisz css/xpath z palca
// pozwol userowi wybrać opcje, ale zaproponuj domyślne na podstawie prostego algorytmu id/klasa,
// wystarczy isc do gory po elementach DOM