import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TestPage{
	private static final String kontrolkazmetodaLocator = "input[id= 'dupa']";
	private static final String bezmetodyLocator = "idelementu";

	@FindBy(css = kontrolkazmetodaLocator)
	private WebElement kontrolkazmetoda;
	@FindBy(id = bezmetodyLocator)
	private WebElement bezmetody;

	public TestPage(){}

	public void get(WebDriver driver){
		driver.get("www/test/pl");
	}

	private void setkontrolkazmetodaLocatorInput(String value){
		System.out.println("INFO: Setting kontrolkazmetoda with value" + value);
		SeleniumHelper.setInput(kontrolkazmetoda,value);
	}


}