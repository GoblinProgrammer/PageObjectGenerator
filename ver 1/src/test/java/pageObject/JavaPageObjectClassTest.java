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

public class JavaPageObjectClassTest {
    private static JavaPageObjectClass javaPageObjectClass;
    private static String className;
    private static String pageUrl;

    private static JavaPageObjectElement javaPageObjectElement1;

    @BeforeAll
    public static void createPageObjectClass(){
        className = new Faker().ancient().god();
        pageUrl = new Faker().ancient().hero();

        LocatorType locatorType = LocatorType.values()[(int) (Math.random() * LocatorType.values().length)];
        String elementType = ElementType.values()[(int) (Math.random() * ElementType.values().length)].toString();
        String locator = new Faker().ancient().god();
        String name = new Faker().ancient().hero();
        boolean generateMethod = true;
        javaPageObjectElement1 = new JavaPageObjectElement(name,locator,locatorType,elementType,generateMethod);

        List<Element> pageElements = Arrays.asList(
                new Element(locatorType,locator,name,generateMethod,elementType));

        javaPageObjectClass = new JavaPageObjectClass(className,pageUrl, pageElements);
    }


    @Test
    public void printImportsTest(){
        Assertions.assertEquals(
                "import org.openqa.selenium.WebDriver;\n" +
                        "import org.openqa.selenium.WebElement;\n" +
                        "import org.openqa.selenium.support.FindBy;\n\n"
                , javaPageObjectClass.printImports());
    }

    @Test
    public void  printConstructorTest(){
        Assertions.assertEquals(
                "\tWebDriver driver;\n\n" +
                        "\tpublic " + className + "(WebDriver driver){\n" +
                        "\t\tthis.driver = driver;\n" +
                        "\t\tPageFactory.initElements(driver, this);\n" +
                        "\t}\n\n"
                , javaPageObjectClass.printConstructor()
        );
    }

    @Test
    public void printGetTest(){
        Assertions.assertEquals(
                "\tpublic void get(){\n" +
                        "\t\tdriver.get(\"" + pageUrl + "\");\n" +
                        "\t}\n\n"
                , javaPageObjectClass.printGet()
        );
    }

    @Test
    public void printElemnentsLocatorsAttributesTest(){
        Assertions.assertTrue(javaPageObjectClass.printElementsLocatorsAttributes().contains(javaPageObjectElement1.printElementLocator()));
    }

    @Test
    public void printElementsFindBysTest(){
        Assertions.assertTrue(javaPageObjectClass.printElementsFindBys().contains(javaPageObjectElement1.printElementFindBy()));
    }

    @Test
    public void printElementsMethodsTest(){
        Assertions.assertTrue(javaPageObjectClass.printElementsMethods().contains(javaPageObjectElement1.printElementHandleMethod()));
    }

    @Test
    public void printClassTest(){
        Assertions.assertTrue(javaPageObjectClass.printClass().contains(javaPageObjectClass.printImports()));
        Assertions.assertTrue(javaPageObjectClass.printClass().contains(javaPageObjectClass.printGet()));
        Assertions.assertTrue(javaPageObjectClass.printClass().contains(javaPageObjectClass.printConstructor()));
        Assertions.assertTrue(javaPageObjectClass.printClass().contains(javaPageObjectClass.printElementsLocatorsAttributes()));
        Assertions.assertTrue(javaPageObjectClass.printClass().contains(javaPageObjectClass.printElementsFindBys()));
        Assertions.assertTrue(javaPageObjectClass.printClass().contains(javaPageObjectClass.printElementsMethods()));
        Assertions.assertTrue(javaPageObjectClass.printClass().contains("public class " + className + "{"));
    }
}
