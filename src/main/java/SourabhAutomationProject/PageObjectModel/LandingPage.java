package SourabhAutomationProject.PageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponents {

	WebDriver driver;

	public LandingPage(WebDriver driver) {

		super(driver);

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	
	
	@FindBy(id = "userEmail")
	private WebElement email;

	@FindBy(id = "userPassword")
	private WebElement password;

	@FindBy(css = "input.login-btn")
	private WebElement loginBtn;
	
	@FindBy(css=".toast-error")
	private WebElement errorMessage;

	

	public void goToURL(String URL) {

		driver.get(URL);

	}

	public ProductCatalogue login(String userName, String pass) {

		email.sendKeys(userName);
		password.sendKeys(pass);
		loginBtn.click();
		
		return new ProductCatalogue(driver);

	}
	
	public String getErrorMessage() {
		
		visibleElement(errorMessage);
		
		String errorText = errorMessage.getText();
		return errorText;
		
	}
	
	
	

}
