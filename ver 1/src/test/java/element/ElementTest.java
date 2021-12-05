package element;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class ElementTest {

    private static Element element;

    private static String locator;
    private static LocatorType locatorType;
    private static String  elementType;
    private static String name;
    private static boolean generateMethod;

    @BeforeAll
    public static void createElement(){
        locatorType = LocatorType.values()[(int) (Math.random() * LocatorType.values().length)];
        elementType = ElementType.values()[(int) (Math.random() * ElementType.values().length)].toString();
        locator = new Faker().ancient().god();
        name = new Faker().ancient().hero();
        generateMethod = new Random().nextBoolean();

        element = new Element(locatorType,locator,name,generateMethod,elementType);
    }

    @Test
    public void ElementAttributesTest(){
        Assertions.assertEquals(element.getName(), name);
        Assertions.assertEquals(element.getElementType(),elementType);
        Assertions.assertEquals(element.getGenerateMethod(),generateMethod);
        Assertions.assertEquals(element.getLocator(),locator);
        Assertions.assertEquals(element.getLocatorType(),locatorType);
        Assertions.assertEquals(element.getElementType(),elementType);
    }
}
