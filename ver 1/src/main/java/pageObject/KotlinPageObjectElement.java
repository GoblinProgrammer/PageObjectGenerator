package pageObject;

import element.ElementType;
import element.LocatorType;

public class KotlinPageObjectElement extends PageObjectElement implements IPageObjectElement{
    public KotlinPageObjectElement(String elementName, String locator, LocatorType locatorType, String elementType, boolean generateMethod) {
        super(elementName, locator, locatorType, elementType, generateMethod);
    }

    @Override
    public void setElementLocatorAttribute(){
        elementLocatorAttribute = "\tprivate static final val " + elementLocatorName + " = \"" + locator + "\";\n";
    }

    @Override
    public void setElementFindBy(){
        elementFindBy = "\t@FindBy(" + locatorType + " = " + elementLocatorName + ")\n" +
                "\tprivate val " + elementName + ": WebElement? = null\n\n";
    }

    @Override
    public void setElementHandleMethod(){
        elementHandleMethod = "\tfun set" + elementName + elementType + "(value: String){\n" +
                "\t\tSystem.out.println(\"INFO: Setting " + elementName + " with value\" + value);\n" +
                "\t\tSeleniumHelper?.set" + elementType + "(" + elementName + ",value);\n" +
                "\t}\n\n";
    }
}
