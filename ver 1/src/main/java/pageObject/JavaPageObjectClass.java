package pageObject;


import element.Element;
import element.ElementType;

import java.util.List;

public class JavaPageObjectClass extends PageObjectClass implements IPageObjectClass {

    public JavaPageObjectClass(String className, String pageUrl){
        super(className,pageUrl);
    }

    @Override
    public void setPageObjectElements(List<Element> pageElements){
        this.pageObjectElements = null;
        for (Element element : pageElements){
            pageObjectElements.add(new JavaPageObjectElement(element.getName(),element.getLocator(),element.getLocatorType(),element.getElementType()));
        }
    }

    @Override
    public String printConstructor(){
        String constructor;
        constructor = "public " + className + "(){}\n";

        return constructor;
    }

    @Override
    public String printGet(){
        String get;
        get = "public void get(WebDriver driver){\n" +
                    "driver.get(" + pageUrl + ");\n" +
                "}\n";

        return get;
    }

    @Override
    public String printElementsLocatorsAttributes(){
        String elementsLocators = "";
        for(PageObjectElement element : pageObjectElements){
            elementsLocators += element.printElementLocator();
        }
        return elementsLocators;
    }

    @Override
    public String printElementsFindBys(){
        String elementsFindBys = "";
        for(PageObjectElement element : pageObjectElements){
            elementsFindBys += element.printElementFindBy();
        }
        return elementsFindBys;
    }

    @Override
    public String printClass(){
        return null;
    }
}
