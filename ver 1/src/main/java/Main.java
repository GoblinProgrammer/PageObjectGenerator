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
        stage.show();
    }


    private void createContextMenu(WebView webView) {

        MenuItem reload = new MenuItem("reload");
        reload.setOnAction(e -> webView.getEngine().reload());

        ContextMenu contextMenu = new ContextMenu(reload);
        webView.setOnMousePressed(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                System.out.println(webView.getEngine().executeScript("document.elementFromPoint("
                        + e.getX()
                        + "," + e.getY() + ").tagName;"));
                JSObject object = (JSObject) webView.getEngine().executeScript("document.elementFromPoint("
                        + e.getX()
                        + "," + e.getY() + ");");
                cast(object, HTMLElement.class).ifPresent(htmlElement -> {
                    System.out.println(htmlElement.getClassName());
                    resolveClosesAttribute(htmlElement,"id").ifPresentOrElse(
                            id -> rp.updateAttributeIdText("Najbliższe znalezione id " + id),
                            () -> rp.updateAttributeIdText("Element ani żaden z rodziców nie posiadają id"));
                });
                contextMenu.show(webView, e.getScreenX(), e.getScreenY());
            } else {
                contextMenu.hide();
            }
        });
    }

    private Optional<String> resolveClosesId(HTMLElement element) {
        if (getId(element) != null) return Optional.of(getId(element));
        else if (parentExistsAndIsHtmlElement(element)) return resolveClosesId((HTMLElement) element.getParentNode());
        else return Optional.empty();
    }

    private String getId(HTMLElement element) {
        return element.getAttribute("id");
    }

    private Optional<String> resolveClosesAttribute(HTMLElement element,String attribute) {
        if (getId(element) != null) return Optional.of(getAttribute(element,attribute));
        else if (parentExistsAndIsHtmlElement(element)) return resolveClosesAttribute((HTMLElement) element.getParentNode(),attribute);
        else return Optional.empty();
    }

    private String getAttribute(HTMLElement element,String attribute) {
        return element.getAttribute(attribute);
    }

    private boolean parentExistsAndIsHtmlElement(HTMLElement child) {
        return cast(child.getParentNode(), HTMLElement.class).isPresent();
    }
}

//poszukaj po id
//poszukaj po klasie
//poszukaj po css - opcjonalnie
// wpisz css/xpath z palca
// pozwol userowi wybrać opcje, ale zaproponuj domyślne na podstawie prostego algorytmu id/klasa,
// wystarczy isc do gory po elementach DOM