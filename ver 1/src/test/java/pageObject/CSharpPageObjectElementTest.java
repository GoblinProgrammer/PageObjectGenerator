package pageObject;

import com.github.javafaker.Faker;
import element.ElementType;
import element.LocatorType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CSharpPageObjectElementTest {
    private static CSharpPageObjectElement cSharpPageObjectElement;
    private static CSharpPageObjectElement cSharpPageObjectElement1;
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

        cSharpPageObjectElement = new CSharpPageObjectElement(name,locator,locatorType,elementType,generateMethods);
        cSharpPageObjectElement1 = new CSharpPageObjectElement(name + 1,locator + 1,locatorType,elementType,false);
    }


    @Test
    public void printElementLocatorTest(){
        Assertions.assertEquals(
                "\tprivate static string " + name + "Locator = \"" + locator + "\";\n",
                cSharpPageObjectElement.printElementLocator()
                );
    }

    @Test
    public void printElementFindByTest(){
        Assertions.assertEquals(
                "\t[FindBy(How = How." + locatorType + ", Using = " + name + "Locator)\n" +
                "\t[CacheLookup]\n" +
                "\tprivate IWebElement " + name + ";\n\n",
                cSharpPageObjectElement.printElementFindBy()
                );
    }

    @Test
    public void printElementHandleMethodTest(){
        Assertions.assertEquals(
                "\tprivate void set" + name + elementType + "(string value){\n" +
                "\t\tConsole.WriteLine(\"INFO: Seting input \" + " + name + " + \" with value\" + value);\n" +
                "\t\tSeleniumHelper.set" + elementType + "(" + name + ",value);\n" +
                "\t}\n\n",
                cSharpPageObjectElement.printElementHandleMethod()
                );
        Assertions.assertNull(cSharpPageObjectElement1.printElementHandleMethod());
    }
}
