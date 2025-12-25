package SourabhAutomationProject.PageObjectModel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage extends AbstractComponents{
	
	WebDriver driver;
	
	public PaymentPage(WebDriver driver) {
		
		super(driver);
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="[placeholder='Select Country']")
	private WebElement inputField;
	
	@FindBy(css="span.ng-star-inserted")
	private List<WebElement> countries;
	
	@FindBy(css=".action__submit")
	private WebElement placeOrderBtn;
	
	
	
	public void selectCountry() {
		
		inputField.sendKeys("ind");
		
		visibleElement(countries);
		WebElement country = countries.stream().filter(x -> x.getText().equalsIgnoreCase("india")).findFirst().orElse(null);
		
		country.click();
		
		visibleElement(placeOrderBtn);
	}
	
	public FinalPage placeOrder() {
		
		placeOrderBtn.click();
		
		return new FinalPage(driver);
		
		
	}
	
	
		
	

}
