package helperClass;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class HelperClassJavaTest {
    private static List<String> othersTest;
    private static String customElementTypeName1;
    private static String customElementTypeName2;

    private static HelperClassJava helperClassJava;

    @BeforeAll
    public static void createHelperClassObject(){
        customElementTypeName1 = new Faker().ancient().primordial();
        customElementTypeName2 = new Faker().ancient().hero();
        othersTest = Arrays.asList(customElementTypeName1,customElementTypeName2);

        helperClassJava = new HelperClassJava(othersTest);
    }

    @Test
    public void printClassTest(){
        Assertions.assertTrue(helperClassJava.printClass().contains(helperClassJava.printHandleInput()));
        Assertions.assertTrue(helperClassJava.printClass().contains(helperClassJava.printHandleSelect()));
        Assertions.assertTrue(helperClassJava.printClass().contains(helperClassJava.printHandleOther(customElementTypeName1)));
        Assertions.assertTrue(helperClassJava.printClass().contains(helperClassJava.printHandleOther(customElementTypeName2)));
    }

    @Test
    public void printHandleInputTest(){
        Assertions.assertEquals(
                "\tpublic static void setInput(WebElement element,String value){\n" +
                        "\t\telement.clear();\n" +
                        "\t\telement.sendKeys(value);\n"+
                        "\t}\n\n",
                helperClassJava.printHandleInput());
    }

    @Test
    public void printHandleSelectTest(){
        Assertions.assertEquals(
                "\tpublic static void selectValue(Browser driver, WebElement element, String value) {\n" +
                        "\t\ttry {\n" +
                        "\t\t\tif(elementIsVisible(driver, element, 5)){\n" +
                        "\t\t\t\tdriver.selectElementByText(element, value);\n" +
                        "\t\t\t}\n" +
                        "\t\t} catch (NoSuchElementException ex) {\n" +
                        "\t\t\tthrow new Error(\"ERROR: Error during selecting '\" + element.getText() + \"' in \" + element + \" \\n\" + ex);\n" +
                        "\t\t}\n" +
                        "\t}\n\n",
                helperClassJava.printHandleSelect());
    }

    @Test
    public void printHandleOtherTest(){
        Assertions.assertEquals(
                "\tpublic static void set" + customElementTypeName1 + "(WebElement element){\n" +
                        "\t\t // method body \n" +
                        "\t}\n\n",
                helperClassJava.printHandleOther(customElementTypeName1));
    }
}
