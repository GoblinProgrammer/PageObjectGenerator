package pageObject;

import element.Element;
import element.ElementType;
import element.LocatorType;

public class JavaPageObjectElement extends PageObjectElement implements IPageObjectElement{

    public JavaPageObjectElement(String name, String locator, LocatorType locatorType, ElementType elementType){
        super(name,locator,locatorType,elementType);

        elementLocatorName = name + "Locator";

        setElementLocatorAttribute();
        setElementFindBy();
    }

    @Override
    public void setElementLocatorAttribute(){
        elementLocatorAttribute = "private static final String " + elementLocatorName + " = \"" + locator + "\";\n";
    }

    @Override
    public void setElementFindBy(){
        elementFindBy = "@FindBy(" + locatorType + " = " + elementLocatorName + ")\n" +
                        "private WebElement " + name + ";\n";
    }
}
