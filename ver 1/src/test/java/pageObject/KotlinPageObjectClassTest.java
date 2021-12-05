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

public class KotlinPageObjectClassTest {
    private static KotlinPageObjectClass kotlinPageObjectClass;
    private static String className;
    private static String pageUrl;

    private static KotlinPageObjectElement javaPageObjectElement1;

    @BeforeAll
    public static void createPageObjectClass(){
        className = new Faker().ancient().god();
        pageUrl = new Faker().ancient().hero();

        LocatorType locatorType = LocatorType.values()[(int) (Math.random() * LocatorType.values().length)];
        String elementType = ElementType.values()[(int) (Math.random() * ElementType.values().length)].toString();
        String locator = new Faker().ancient().god();
        String name = new Faker().ancient().hero();
        boolean generateMethod = true;
        javaPageObjectElement1 = new KotlinPageObjectElement(name,locator,locatorType,elementType,generateMethod);

        List<Element> pageElements = Arrays.asList(
                new Element(locatorType,locator,name,generateMethod,elementType));

        kotlinPageObjectClass = new KotlinPageObjectClass(className,pageUrl, pageElements);
    }


    @Test
    public void printImportsTest(){
        Assertions.assertEquals(
                "import org.openqa.selenium.WebDriver\n" +
                        "import org.openqa.selenium.WebElement\n" +
                        "import org.openqa.selenium.support.FindBy\n" +
                        "import org.openqa.selenium.support.PageFactory\n\n"
                , kotlinPageObjectClass.printImports());
    }

    @Test
    public void  printConstructorTest(){
        Assertions.assertEquals(
                "\tinit {PageFactory.initElements(driver, this)}\n\n"
                , kotlinPageObjectClass.printConstructor()
        );
    }

    @Test
    public void printGetTest(){
        Assertions.assertEquals(
                "\tfun get(driver: WebDriver){\n" +
                        "\t\tdriver?.get(\"" + pageUrl + "\");\n" +
                        "\t}\n\n"
                , kotlinPageObjectClass.printGet()
        );
    }

    @Test
    public void printElemnentsLocatorsAttributesTest(){
        Assertions.assertTrue(kotlinPageObjectClass.printElementsLocatorsAttributes().contains(javaPageObjectElement1.printElementLocator()));
    }

    @Test
    public void printElementsFindBysTest(){
        Assertions.assertTrue(kotlinPageObjectClass.printElementsFindBys().contains(javaPageObjectElement1.printElementFindBy()));
    }

    @Test
    public void printElementsMethodsTest(){
        Assertions.assertTrue(kotlinPageObjectClass.printElementsMethods().contains(javaPageObjectElement1.printElementHandleMethod()));
    }

    @Test
    public void printClassTest(){
        Assertions.assertTrue(kotlinPageObjectClass.printClass().contains(kotlinPageObjectClass.printImports()));
        Assertions.assertTrue(kotlinPageObjectClass.printClass().contains(kotlinPageObjectClass.printGet()));
        Assertions.assertTrue(kotlinPageObjectClass.printClass().contains(kotlinPageObjectClass.printConstructor()));
        Assertions.assertTrue(kotlinPageObjectClass.printClass().contains(kotlinPageObjectClass.printElementsLocatorsAttributes()));
        Assertions.assertTrue(kotlinPageObjectClass.printClass().contains(kotlinPageObjectClass.printElementsFindBys()));
        Assertions.assertTrue(kotlinPageObjectClass.printClass().contains(kotlinPageObjectClass.printElementsMethods()));
        Assertions.assertTrue(kotlinPageObjectClass.printClass().contains("class " + className + "(private val driver: WebElement){"));
    }
}
