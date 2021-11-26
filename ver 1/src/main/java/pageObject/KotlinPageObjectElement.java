package pageObject;

import element.ElementType;
import element.LocatorType;

public class KotlinPageObjectElement extends PageObjectElement implements IPageObjectElement{
    public KotlinPageObjectElement(String name, String locator, LocatorType locatorType, ElementType elementType) {
        super(name, locator, locatorType, elementType);
    }
}
