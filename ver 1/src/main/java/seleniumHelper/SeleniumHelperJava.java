package seleniumHelper;

public class SeleniumHelperJava implements ISeleniumHelper{
    @Override
    public String printClass() {
        return "import org.openqa.selenium.WebDriver;\n" +
                "import org.openqa.selenium.WebElement;\n" +
                "\n" +
                "public class SeleniumHelper {\n" +
                "\n" +
                "\tpublic static void setInput(WebElement element,String value){}\n" +
                "\tpublic static void selectValue(WebElement element, String value){}\n" +
                "\tpublic static void selectCheckbox(WebElement element){}\n" +
                "\tpublic static void selectRadioButton(WebElement element){}\n" +
                "\n" +
                "}";
    }
}
