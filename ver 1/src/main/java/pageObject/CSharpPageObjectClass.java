package pageObject;

import element.Element;

import java.util.ArrayList;
import java.util.List;

public class CSharpPageObjectClass extends PageObjectClass implements IPageObjectClass{

    public CSharpPageObjectClass(String className, String pageUrl,List<Element> pageElements) {
        super(className, pageUrl,pageElements);
    }

    @Override
    public void setPageObjectElements(List<Element> pageElements){
        this.pageObjectElements = new ArrayList<>();
        for (Element element : pageElements){
            pageObjectElements.add(new CSharpPageObjectElement(element.getName(),element.getLocator(),element.getLocatorType(),element.getElementType(),element.getGenerateMethod()));
        }
    }

    @Override
    public String printImports(){
        return "using System;\n" +
                "using OpenQA.Selenium;\n" +
                "using OpenQA.Selenium.Remote;\n" +
                "using NUnit.Framework;\n" +
                "using OpenQA.Selenium.Support.UI;\n" +
                "// For supporting Page Object Model\n" +
                "// Obsolete - using OpenQA.Selenium.Support.PageObjects;\n" +
                "using SeleniumExtras.PageObjects;\n\n";
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
        get = "\tpublic void get(IWebDriver driver){\n" +
                "\t\tdriver.get(\"" + pageUrl + "\");\n" +
                "\t}\n\n";

        return get;
    }

    @Override
    public String printClass(){
        return  printImports() +
                "class " + className + "{\n" +
                printClassBody() +
                "\n}";
    }
}
