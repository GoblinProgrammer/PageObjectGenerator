package util;

import org.w3c.dom.html.HTMLElement;

import java.util.Optional;

import static util.Cast.cast;

public class AttributeFinder {

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
