package pageObject;

import com.github.javafaker.Faker;
import element.ElementType;
import element.LocatorType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class JavaPageObjectElementTest {
    private static JavaPageObjectElement javaPageObjectElement;
    private static JavaPageObjectElement javaPageObjectElement1;
    private static String name;
    private static String locator;
    private static LocatorType locatorType;
    private static String elementType;
    private static boolean generateMethods;

    @BeforeAll
    public static void createPageObjectElement(){
        name = new Faker().ancient().god();
        locator = new Faker().ancient().primordial();
        locatorType = LocatorType.values()[(int) (Math.random() * LocatorType.values().length)];
        elementType = ElementType.values()[(int) (Math.random() * ElementType.values().length)].toString();
        generateMethods = true;

        javaPageObjectElement = new JavaPageObjectElement(name,locator,locatorType,elementType,generateMethods);
        javaPageObjectElement1 = new JavaPageObjectElement(name + 1,locator + 1,locatorType,elementType,false);
    }


    @Test
    public void printElementLocatorTest(){
        Assertions.assertEquals(
                "\tprivate static final String " + name + "Locator = \"" + locator + "\";\n",
                javaPageObjectElement.printElementLocator()
        );
    }

    @Test
    public void printElementFindByTest(){
        Assertions.assertEquals(
                "\t@FindBy(" + locatorType + " = " + name + "Locator)\n" +
                        "\tprivate WebElement " + name + ";\n\n",
                javaPageObjectElement.printElementFindBy()
        );
    }

    @Test
    public void printElementHandleMethodTest(){
        Assertions.assertEquals(
                "\tprivate void set" + name + elementType + "(String value){\n" +
                        "\t\tSystem.out.println(\"INFO: Setting " + name + " with value\" + value);\n" +
                        "\t\tSeleniumHelper.set" + elementType + "(" + name + ",value);\n" +
                        "\t}\n\n",
                javaPageObjectElement.printElementHandleMethod()
        );
        Assertions.assertNull(javaPageObjectElement1.printElementHandleMethod());
    }
}
