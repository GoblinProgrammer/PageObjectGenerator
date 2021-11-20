package pageObject;

import element.Element;
import element.ElementType;
import element.LocatorType;

public class PageObjectElement implements IPageObjectElement{
    String name;
    String locator;
    LocatorType locatorType;

    ElementType elementType;

    String elementLocatorName;

    String elementLocatorAttribute;
    String elementFindBy;

    public PageObjectElement(String name, String locator, LocatorType locatorType,ElementType elementType){
        this.name = name;
        this.locator = locator;
        this.locatorType = locatorType;
        this.elementType = elementType;
    }

    @Override
    public void setElementLocatorAttribute() {

    }

    @Override
    public void setElementFindBy() {

    }

    @Override
    public String printElementLocator(){
        return elementLocatorAttribute;
    }

    @Override
    public String printElementFindBy(){
        return elementFindBy;
    }
}
