package pageObject;

import element.ElementType;
import element.LocatorType;

public class JavaPageObjectElement extends PageObjectElement implements IPageObjectElement{

    public JavaPageObjectElement(String name, String locator, LocatorType locatorType, ElementType elementType){
        super(name,locator,locatorType,elementType);

        setElementLocatorAttribute();
        setElementFindBy();
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
                              "\t\tSystem.out.println(\"INFO: Setting \" + " + elementLocatorName + " + \" with value\" + value);" +
                              "\t\tSeleniumHelper.set" + elementType + "(" + elementName + ",value);" +
                              "\n}";
    }
}
