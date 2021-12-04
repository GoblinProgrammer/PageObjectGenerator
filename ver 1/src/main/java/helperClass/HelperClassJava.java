package helperClass;

import java.util.List;

public class HelperClassJava implements HelperClassFactory{
    List<String> others;

    public HelperClassJava(List<String> others){
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
        return "\tpublic static void setInput(WebElement element,String value){\n" +
                "\t\telement.clear();\n" +
                "\t\telement.sendKeys(value);\n"+
                "\t}\n\n";
    }

    @Override
    public String printHandleSelect() {
        return "\tpublic static void selectValue(Browser driver, WebElement element, String value) {\n" +
                "\t\ttry {\n" +
                "\t\t\tif(elementIsVisible(driver, element, 5)){\n" +
                "\t\t\t\tdriver.selectElementByText(element, value);\n" +
                "\t\t\t}\n" +
                "\t\t} catch (NoSuchElementException ex) {\n" +
                "\t\t\tthrow new Error(\"ERROR: Error during selecting '\" + element.getText() + \"' in \" + element + \" \\n\" + ex);\n" +
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
        return "\tpublic static void set" + other + "(WebElement element){\n" +
                "\t\t // method body \n" +
                "\t}\n\n";
    }
}
