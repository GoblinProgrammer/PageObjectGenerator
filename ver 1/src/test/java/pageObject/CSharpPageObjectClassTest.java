package pageObject;

import com.github.javafaker.Faker;
import element.Element;
import element.ElementType;
import element.LocatorType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class CSharpPageObjectClassTest {
    private static CSharpPageObjectClass cSharpPageObjectClass;
    private static String className;
    private static String pageUrl;

    private static CSharpPageObjectElement cSharpPageObjectElement1;

    @BeforeAll
    public static void createPageObjectClass(){
        className = new Faker().ancient().god();
        pageUrl = new Faker().ancient().hero();

        LocatorType locatorType = LocatorType.values()[(int) (Math.random() * LocatorType.values().length)];
        String elementType = ElementType.values()[(int) (Math.random() * ElementType.values().length)].toString();
        String locator = new Faker().ancient().god();
        String name = new Faker().ancient().hero();
        boolean generateMethod = true;
        cSharpPageObjectElement1 = new CSharpPageObjectElement(name,locator,locatorType,elementType,generateMethod);

        List<Element> pageElements = Arrays.asList(
                new Element(locatorType,locator,name,generateMethod,elementType));

        cSharpPageObjectClass = new CSharpPageObjectClass(className,pageUrl, pageElements);
    }


    @Test
    public void printImportsTest(){
        Assertions.assertEquals(
                "using System;\n" +
                        "using OpenQA.Selenium;\n" +
                        "using OpenQA.Selenium.Remote;\n" +
                        "using NUnit.Framework;\n" +
                        "using OpenQA.Selenium.Support.UI;\n" +
                        "// For supporting Page Object Model\n" +
                        "// Obsolete - using OpenQA.Selenium.Support.PageObjects;\n" +
                        "using SeleniumExtras.PageObjects;\n\n"
                ,cSharpPageObjectClass.printImports());
    }

    @Test
    public void  printConstructorTest(){
        Assertions.assertEquals(
                "\t private IWebDriver driver;\n" +
                "\tpublic " + className + "(IWebDriver driver){\n" +
                "\t\t this.driver = driver;\n" +
                "\t\t PageFactory.InitElements(driver, this);\n" +
                "\t}\n\n"
                ,cSharpPageObjectClass.printConstructor()
                );
    }

    @Test
    public void printGetTest(){
        Assertions.assertEquals(
                "\tpublic void get(IWebDriver driver){\n" +
                "\t\tdriver.Navigate.GoToUrl(\"" + pageUrl + "\");\n" +
                "\t}\n\n"
                ,cSharpPageObjectClass.printGet()
                );
    }

    @Test
    public void printElemnentsLocatorsAttributesTest(){
        Assertions.assertTrue(cSharpPageObjectClass.printElementsLocatorsAttributes().contains(cSharpPageObjectElement1.printElementLocator()));
    }

    @Test
    public void printElementsFindBysTest(){
        Assertions.assertTrue(cSharpPageObjectClass.printElementsFindBys().contains(cSharpPageObjectElement1.printElementFindBy()));
    }

    @Test
    public void printElementsMethodsTest(){
        Assertions.assertTrue(cSharpPageObjectClass.printElementsMethods().contains(cSharpPageObjectElement1.printElementHandleMethod()));
    }

    @Test
    public void printClassTest(){
        Assertions.assertTrue(cSharpPageObjectClass.printClass().contains(cSharpPageObjectClass.printImports()));
        Assertions.assertTrue(cSharpPageObjectClass.printClass().contains(cSharpPageObjectClass.printGet()));
        Assertions.assertTrue(cSharpPageObjectClass.printClass().contains(cSharpPageObjectClass.printConstructor()));
        Assertions.assertTrue(cSharpPageObjectClass.printClass().contains(cSharpPageObjectClass.printElementsLocatorsAttributes()));
        Assertions.assertTrue(cSharpPageObjectClass.printClass().contains(cSharpPageObjectClass.printElementsFindBys()));
        Assertions.assertTrue(cSharpPageObjectClass.printClass().contains(cSharpPageObjectClass.printElementsMethods()));
        Assertions.assertTrue(cSharpPageObjectClass.printClass().contains("class " + className + "{"));
    }

}
