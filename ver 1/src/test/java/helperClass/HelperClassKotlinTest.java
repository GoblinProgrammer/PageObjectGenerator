package helperClass;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class HelperClassKotlinTest {
    private static List<String> othersTest;
    private static String customElementTypeName1;
    private static String customElementTypeName2;

    private static HelperClassKotlin helperClassKotlin;

    @BeforeAll
    public static void createHelperClassObject(){
        customElementTypeName1 = new Faker().ancient().primordial();
        customElementTypeName2 = new Faker().ancient().hero();
        othersTest = Arrays.asList(customElementTypeName1,customElementTypeName2);

        helperClassKotlin = new HelperClassKotlin(othersTest);
    }

    @Test
    public void printClassTest(){
        Assertions.assertTrue(helperClassKotlin.printClass().contains(helperClassKotlin.printHandleInput()));
        Assertions.assertTrue(helperClassKotlin.printClass().contains(helperClassKotlin.printHandleSelect()));
        Assertions.assertTrue(helperClassKotlin.printClass().contains(helperClassKotlin.printHandleOther(customElementTypeName1)));
        Assertions.assertTrue(helperClassKotlin.printClass().contains(helperClassKotlin.printHandleOther(customElementTypeName2)));
    }

    @Test
    public void printHandleInputTest(){
        Assertions.assertEquals(
                "\tfun setInput(element: WebElement, value: String){\n" +
                        "\t\telement?.clear();\n" +
                        "\t\telement?.sendKeys(value);\n"+
                        "\t}\n\n",
                helperClassKotlin.printHandleInput());
    }

    @Test
    public void printHandleSelectTest(){
        Assertions.assertEquals(
                "\tfun selectValue(driver: Browser, element: WebElement, value: String) {\n" +
                        "\t\ttry {\n" +
                        "\t\t\tif(elementIsVisible(driver, element, 5)){\n" +
                        "\t\t\t\tdriver?.selectElementByText(element, value);\n" +
                        "\t\t\t}\n" +
                        "\t\t} catch (ex: NoSuchElementException) {\n" +
                        "\t\t\tthrow new Error(\"ERROR: Error during selecting '\" + element.getText() + \"' in \" + element + \" \\n\" + ex);\n" +
                        "\t\t}\n" +
                        "\t}\n\n",
                helperClassKotlin.printHandleSelect());
    }

    @Test
    public void printHandleOtherTest(){
        Assertions.assertEquals(
                "\tfun set" + customElementTypeName1 + "(element: WebElement){\n" +
                        "\t\t // method body \n" +
                        "\t}\n\n",
                helperClassKotlin.printHandleOther(customElementTypeName1));
    }
}
