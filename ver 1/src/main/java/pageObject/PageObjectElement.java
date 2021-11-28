package pageObject;

import element.ElementType;
import element.LocatorType;

public abstract class PageObjectElement implements IPageObjectElement{
    String elementName;
    String locator;
    LocatorType locatorType;
    ElementType elementType;
    boolean generateMethod;

    String elementLocatorName;

    String elementLocatorAttribute;
    String elementFindBy;
    String elementHandleMethod;

    public PageObjectElement(String elementName, String locator, LocatorType locatorType, ElementType elementType, boolean generateMethod){
        this.elementName = elementName;
        this.locator = locator;
        this.locatorType = locatorType;
        this.elementType = elementType;
        this.generateMethod = generateMethod;

        elementLocatorName = elementName + "Locator";


        setElementLocatorAttribute();
        setElementFindBy();
        if(generateMethod){
            setElementHandleMethod();
        }
    }

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

    @Override
    public boolean isMethodNeeded(){ return generateMethod; }
}
