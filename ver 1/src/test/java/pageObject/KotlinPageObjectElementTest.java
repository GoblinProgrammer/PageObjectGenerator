package pageObject;

import com.github.javafaker.Faker;
import element.ElementType;
import element.LocatorType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class KotlinPageObjectElementTest {
    private static KotlinPageObjectElement kotlinPageObjectElement;
    private static KotlinPageObjectElement kotlinPageObjectElement1;
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

        kotlinPageObjectElement = new KotlinPageObjectElement(name,locator,locatorType,elementType,generateMethods);
        kotlinPageObjectElement1 = new KotlinPageObjectElement(name + 1,locator + 1,locatorType,elementType,false);
    }


    @Test
    public void printElementLocatorTest(){
        Assertions.assertEquals(
                "\tprivate static final val " + name + "Locator = \"" + locator + "\";\n",
                kotlinPageObjectElement.printElementLocator()
        );
    }

    @Test
    public void printElementFindByTest(){
        Assertions.assertEquals(
                "\t@FindBy(" + locatorType + " = " + name + "Locator)\n" +
                        "\tprivate val " + name + ": WebElement? = null\n\n",
                kotlinPageObjectElement.printElementFindBy()
        );
    }

    @Test
    public void printElementHandleMethodTest(){
        Assertions.assertEquals(
                "\tfun set" + name + elementType + "(value: String){\n" +
                        "\t\tSystem.out.println(\"INFO: Setting " + name + " with value\" + value);\n" +
                        "\t\tSeleniumHelper?.set" + elementType + "(" + name + ",value);\n" +
                        "\t}\n\n",
                kotlinPageObjectElement.printElementHandleMethod()
        );
        Assertions.assertNull(kotlinPageObjectElement1.printElementHandleMethod());
    }
}
