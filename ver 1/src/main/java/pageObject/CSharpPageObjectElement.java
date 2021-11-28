package pageObject;

import element.ElementType;
import element.LocatorType;

public class CSharpPageObjectElement extends PageObjectElement implements IPageObjectElement{
    public CSharpPageObjectElement(String name, String locator, LocatorType locatorType, ElementType elementType, boolean generateMethod) {
        super(name, locator, locatorType, elementType,generateMethod);

        setElementLocatorAttribute();
        setElementFindBy();
    }

    @Override
    public void setElementLocatorAttribute(){
        elementLocatorAttribute = "\tprivate static string " + elementLocatorName + " = \"" + locator + "\";\n";
    }

    @Override
    public void setElementFindBy(){
        elementFindBy = "\t[FindBy(How = How." + locatorType + ", Using = " + elementLocatorName + ")\n" +
                "\t[CacheLookup]\n" +
                "\tprivate IWebElement " + elementName + ";\n\n";
    }

    @Override
    public void setElementHandleMethod(){
        elementHandleMethod = "\tprivate void set" + elementLocatorName + elementType + "(string value){\n" +
                "\t\tConsole.WriteLine(\"INFO: Seting input \" + " + elementLocatorName + " + \" with value\" + value);\n" +
                "\t\tSeleniumHelper.set" + elementType + "(" + elementName + ",value);\n" +
                "\t}\n\n";
    }
}
