package pageObject;

import element.ElementType;
import element.LocatorType;

public class CSharpPageObjectElement extends PageObjectElement implements IPageObjectElement{
    public CSharpPageObjectElement(String name, String locator, LocatorType locatorType, ElementType elementType) {
        super(name, locator, locatorType, elementType);

        setElementLocatorAttribute();
        setElementFindBy();
    }

    @Override
    public void setElementLocatorAttribute(){
        elementLocatorAttribute = "   private static string " + elementLocatorName + " = \"" + locator + "\";\n";
    }

    @Override
    public void setElementFindBy(){
        elementFindBy = "   @FindBy(" + locatorType + " = " + elementLocatorName + ")\n" +
                "   private WebElement " + elementName + ";\n";
    }

    @Override
    public void setElementHandleMethod(){
        elementHandleMethod = "   private void set" + elementLocatorName + elementType + "(string value){\n" +
                "      System.out.println(\"INFO: Seting input \" + " + elementLocatorName + " + \" with value\" + value);" +
                "      ";
    }
}
