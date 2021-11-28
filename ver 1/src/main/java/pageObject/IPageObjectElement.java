package pageObject;

import element.Element;
import element.ElementType;

public interface IPageObjectElement {
    void setElementLocatorAttribute();
    void setElementFindBy();
    void setElementHandleMethod();

    String printElementLocator();
    String printElementFindBy();
    String printElementHandleMethod();

    boolean isMethodNeeded();
}
