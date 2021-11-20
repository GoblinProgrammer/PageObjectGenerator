package pageObject;

import element.Element;
import element.ElementType;

public interface IPageObjectElement {
    void setElementLocatorAttribute();
    void setElementFindBy();

    String printElementLocator();
    String printElementFindBy();
}
