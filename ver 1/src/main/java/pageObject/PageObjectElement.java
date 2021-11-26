package pageObject;

import element.ElementType;
import element.LocatorType;

public class PageObjectElement implements IPageObjectElement{
    String elementName;
    String locator;
    LocatorType locatorType;

    ElementType elementType;

    String elementLocatorName;

    String elementLocatorAttribute;
    String elementFindBy;
    String elementHandleMethod;

    public PageObjectElement(String elementName, String locator, LocatorType locatorType, ElementType elementType){
        this.elementName = elementName;
        this.locator = locator;
        this.locatorType = locatorType;
        this.elementType = elementType;

        elementLocatorName = elementName + "Locator";
    }

    @Override
    public void setElementLocatorAttribute() {

    }

    @Override
    public void setElementFindBy() {

    }

    @Override
    public void setElementHandleMethod(){}

    @Override
    public String printElementLocator(){
        return elementLocatorAttribute;
    }

    @Override
    public String printElementFindBy(){
        return elementFindBy;
    }

    @Override
    public String printElementHandleMethod() { return elementHandleMethod; }
}
