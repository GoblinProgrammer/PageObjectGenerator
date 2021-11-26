package pageObject;


import element.Element;

import java.util.ArrayList;
import java.util.List;

public class JavaPageObjectClass extends PageObjectClass implements IPageObjectClass {

    public JavaPageObjectClass(String className, String pageUrl,boolean generateMethods){
        super(className,pageUrl,generateMethods);
    }

    @Override
    public void setPageObjectElements(List<Element> pageElements){
        this.pageObjectElements = new ArrayList<>();
        for (Element element : pageElements){
            pageObjectElements.add(new JavaPageObjectElement(element.getName(),element.getLocator(),element.getLocatorType(),element.getElementType()));
        }
    }

    @Override
    public String printImports(){
        return "import org.openqa.selenium.WebDriver;\n" +
                "import org.openqa.selenium.WebElement;\n" +
                "import org.openqa.selenium.support.FindBy;\n\n";
    }

    @Override
    public String printConstructor(){
        String constructor;
        constructor = "\tpublic " + className + "(){}\n\n";

        return constructor;
    }

    @Override
    public String printGet(){
        String get;
        get = "\tpublic void get(WebDriver driver){\n" +
                    "\t\tdriver.get(\"" + pageUrl + "\");\n" +
                "\t}\n";

        return get;
    }

    @Override
    public String printElementsLocatorsAttributes(){
        String elementsLocators = "";
        for(PageObjectElement element : pageObjectElements){
            elementsLocators += element.printElementLocator();
        }
        return elementsLocators + "\n";
    }

    @Override
    public String printElementsFindBys(){
        String elementsFindBys = "";
        for(PageObjectElement element : pageObjectElements){
            elementsFindBys += element.printElementFindBy();
        }

        return elementsFindBys + "\n";
    }

    @Override
    public String printElementsMethods(){
        String elementsMethods = "";
        for(PageObjectElement element : pageObjectElements){
            elementsMethods += element.printElementHandleMethod();
        }
        return elementsMethods;
    }

    @Override
    public String printMethods(){

        return printGet() + "\n";
    }

    @Override
    public String printClassBody(){
        return printElementsLocatorsAttributes() + printElementsFindBys() + printConstructor() + printGet() + printElementsMethods() + "\n";
    }

    @Override
    public String printClass(){
        return  printImports() +
                "public class " + className + "{\n" +
                printClassBody() +
                "\n}";
    }
}
