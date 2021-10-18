package gui;

import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import org.w3c.dom.html.HTMLElement;
import util.AttributeFinder;

import static util.Cast.cast;

public class CustomContextMenu {

    private final ContextMenu contextMenu;
    private WebView webView;

    public CustomContextMenu(){
        MenuItem addObject = new MenuItem("addObject");
        contextMenu = new ContextMenu(addObject);
    }

    public CustomContextMenu setWebView(WebView webView){
        this.webView = webView;
        return this;
    }

    public void testCreateContextMenu(){
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
                });
                contextMenu.show(webView, e.getScreenX(), e.getScreenY());
            } else {
                contextMenu.hide();
            }
        });
    }

    public static void createContextMenu(WebView webView, RightPanel rp) {

        MenuItem reload = new MenuItem("reload");
        MenuItem addObject = new MenuItem("addObject");
        reload.setOnAction(e -> webView.getEngine().reload());

        ContextMenu contextMenu = new ContextMenu(reload,addObject);
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
                    AttributeFinder.resolveClosesAttribute(htmlElement,"id").ifPresentOrElse(
                            id -> rp.updateAttributeIdText("Najbliższe znalezione id " + id),
                            () -> rp.updateAttributeIdText("Element ani żaden z rodziców nie posiadają id"));
                });
                contextMenu.show(webView, e.getScreenX(), e.getScreenY());
            } else {
                contextMenu.hide();
            }
        });
    }

    public void hideCustomContextMenu(){
        contextMenu.hide();
    }

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
