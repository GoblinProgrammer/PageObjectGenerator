package util;

import controller.Controller;
import javafx.scene.input.MouseButton;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import org.w3c.dom.html.HTMLElement;

import java.util.Optional;

import static util.Cast.cast;

public class AttributeFinder {
    
    public static void getDataByCoordinates(Controller controller, WebView webView){
        webView.setOnMousePressed(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                System.out.println("LOG: tagName: " + webView.getEngine().executeScript("document.elementFromPoint("
                        + e.getX()
                        + "," + e.getY() + ").tagName;"));
                JSObject object = (JSObject) webView.getEngine().executeScript("document.elementFromPoint("
                        + e.getX()
                        + "," + e.getY() + ");");
                cast(object, HTMLElement.class).ifPresent(htmlElement -> {
                    //htmlElement.setAttribute();
                    System.out.println("LOG: Html element class name: " + htmlElement.getClassName());
                    controller.setByClassInputValue(htmlElement.getClassName());
                });
                cast(object, HTMLElement.class).ifPresent(htmlElement -> {
                    System.out.println("LOG: Html element id: " + htmlElement.getId());
                    controller.setByIdInputValue(htmlElement.getId());
                });
            }
        });
    }

    public Optional<String> resolveClosesId(HTMLElement element) {
        if (getId(element) != null) return Optional.of(getId(element));
        else if (parentExistsAndIsHtmlElement(element)) return resolveClosesId((HTMLElement) element.getParentNode());
        else return Optional.empty();
    }

    private static String getId(HTMLElement element) {
        return element.getAttribute("id");
    }

    public static Optional<String> resolveClosesAttribute(HTMLElement element,String attribute) {
        if (getId(element) != null) return Optional.of(getAttribute(element,attribute));
        else if (parentExistsAndIsHtmlElement(element)) return resolveClosesAttribute((HTMLElement) element.getParentNode(),attribute);
        else return Optional.empty();
    }

    private static String getAttribute(HTMLElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    private static boolean parentExistsAndIsHtmlElement(HTMLElement child) {
        return cast(child.getParentNode(), HTMLElement.class).isPresent();
    }
}
