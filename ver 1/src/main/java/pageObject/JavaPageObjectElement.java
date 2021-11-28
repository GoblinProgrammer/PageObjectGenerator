package pageObject;

import element.ElementType;
import element.LocatorType;

public class JavaPageObjectElement extends PageObjectElement implements IPageObjectElement{

    public JavaPageObjectElement(String name, String locator, LocatorType locatorType, ElementType elementType, boolean generateMethod){
        super(name,locator,locatorType,elementType,generateMethod);
    }

    @Override
    public void setElementLocatorAttribute(){
        elementLocatorAttribute = "\tprivate static final String " + elementLocatorName + " = \"" + locator + "\";\n";
    }

    @Override
    public void setElementFindBy(){
        elementFindBy = "\t@FindBy(" + locatorType + " = " + elementLocatorName + ")\n" +
                        "\tprivate WebElement " + elementName + ";\n";
    }

    @Override
    public void setElementHandleMethod(){
        elementHandleMethod = "\tprivate void set" + elementLocatorName + elementType + "(String value){\n" +
                              "\t\tSystem.out.println(\"INFO: Setting " + elementName + " with value\" + value);\n" +
                              "\t\tSeleniumHelper.set" + elementType + "(" + elementName + ",value);\n" +
                              "\t}\n";
    }
}
