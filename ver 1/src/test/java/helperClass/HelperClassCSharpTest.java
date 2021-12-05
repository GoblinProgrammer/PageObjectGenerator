package helperClass;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class HelperClassCSharpTest {
    private static List<String> othersTest;
    private static String customElementTypeName1;
    private static String customElementTypeName2;

    private static HelperClassCSharp helperClassCSharp;

    @BeforeAll
    public static void createHelperClassObject(){
        customElementTypeName1 = new Faker().ancient().primordial();
        customElementTypeName2 = new Faker().ancient().hero();
        othersTest = Arrays.asList(customElementTypeName1,customElementTypeName2);

        helperClassCSharp = new HelperClassCSharp(othersTest);
    }

    @Test
    public void printClassTest(){
        Assertions.assertTrue(helperClassCSharp.printClass().contains(helperClassCSharp.printHandleInput()));
        Assertions.assertTrue(helperClassCSharp.printClass().contains(helperClassCSharp.printHandleSelect()));
        Assertions.assertTrue(helperClassCSharp.printClass().contains(helperClassCSharp.printHandleOther(customElementTypeName1)));
        Assertions.assertTrue(helperClassCSharp.printClass().contains(helperClassCSharp.printHandleOther(customElementTypeName2)));
    }

    @Test
    public void printHandleInputTest(){
        Assertions.assertEquals(
                "\tpublic static void setInput(IWebElement element,string value){\n" +
                "\t\telement.clear();\n" +
                "\t\telement.sendKeys(value);\n"+
                "\t}\n\n",
                helperClassCSharp.printHandleInput());
    }

    @Test
    public void printHandleSelectTest(){
        Assertions.assertEquals(
                "\tpublic static void selectValue(IWebDriver driver, IWebElement element, string value) {\n" +
                "\t\ttry {\n" +
                "\t\t\tif(elementIsVisible(driver, element, 5)){\n" +
                "\t\t\t\tdriver.selectElementByText(element, value);\n" +
                "\t\t\t}\n" +
                "\t\t} catch (NoSuchElementException ex) {\n" +
                "\t\t\tthrow new Exception(\"ERROR: Error during selecting '\" + element.getText() + \"' in \" + element + \" \\n\" + ex);\n" +
                "\t\t}\n" +
                "\t}\n\n",
                helperClassCSharp.printHandleSelect());
    }

    @Test
    public void printHandleOtherTest(){
        Assertions.assertEquals(
                "\tpublic static void set" + customElementTypeName1 + "(IWebElement element){\n" +
                "\t\t // method body \n" +
                "\t}\n\n",
                helperClassCSharp.printHandleOther(customElementTypeName1));
    }
}
