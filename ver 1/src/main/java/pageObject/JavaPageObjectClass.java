package pageObject;


import element.Element;

import java.util.ArrayList;
import java.util.List;

public class JavaPageObjectClass extends PageObjectClass implements IPageObjectClass {

    public JavaPageObjectClass(String className, String pageUrl,List<Element> pageElements){
        super(className,pageUrl,pageElements);
    }

    @Override
    public void setPageObjectElements(List<Element> pageElements){
        this.pageObjectElements = new ArrayList<>();
        for (Element element : pageElements){
            pageObjectElements.add(new JavaPageObjectElement(element.getName(),element.getLocator(),element.getLocatorType(),element.getElementType(),element.getGenerateMethod()));
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
        constructor = "\tWebDriver driver;\n\n" +
                "\tpublic " + className + "(WebDriver driver){\n" +
                "\t\tthis.driver = driver;\n" +
                "\t\tPageFactory.initElements(driver, this);\n" +
                "\t}\n\n";

        return constructor;
    }

    @Override
    public String printGet(){
        String get;
        get = "\tpublic void get(){\n" +
                    "\t\tdriver.get(\"" + pageUrl + "\");\n" +
                "\t}\n\n";

        return get;
    }

    @Override
    public String printClass(){
        return  printImports() +
                "public class " + className + "{\n" +
                printClassBody() +
                "\n}";
    }
}
