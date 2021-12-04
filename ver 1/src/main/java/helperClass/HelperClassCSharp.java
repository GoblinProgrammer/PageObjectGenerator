package helperClass;

import java.util.List;

public class HelperClassCSharp implements HelperClassFactory{
    List<String> others;

    public HelperClassCSharp(List<String> others){
        this.others = others;
    }

    @Override
    public String printClass() {
        return "public abstract class SeleniumHelper {\n" +
                printClassBody() +
                "\n}";
    }

    @Override
    public String printClassBody() {
        return printHandleInput() + printHandleSelect() + printHandleAllOthers(others);
    }

    @Override
    public String printHandleInput() {
        return "\tpublic static void setInput(IWebElement element,string value){\n" +
                "\t\telement.clear();\n" +
                "\t\telement.sendKeys(value);\n"+
                "\t}\n\n";
    }

    @Override
    public String printHandleSelect() {
        return "\tpublic static void selectValue(IWebDriver driver, IWebElement element, string value) {\n" +
                "\t\ttry {\n" +
                "\t\t\tif(elementIsVisible(driver, element, 5)){\n" +
                "\t\t\t\tdriver.selectElementByText(element, value);\n" +
                "\t\t\t}\n" +
                "\t\t} catch (NoSuchElementException ex) {\n" +
                "\t\t\tthrow new Exception(\"ERROR: Error during selecting '\" + element.getText() + \"' in \" + element + \" \\n\" + ex);\n" +
                "\t\t}\n" +
                "\t}\n\n";
    }

    @Override
    public String printHandleAllOthers(List<String> others){
        String othersAsCode = "";
        for (String other : others){
            othersAsCode += printHandleOther(other);
        }
        return othersAsCode;
    }

    @Override
    public String printHandleOther(String other) {
        return "\tpublic static void set" + other + "(IWebElement element){\n" +
                "\t\t // method body \n" +
                "\t}\n\n";
    }
}
