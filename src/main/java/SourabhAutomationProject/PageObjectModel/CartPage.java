package SourabhAutomationProject.PageObjectModel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends AbstractComponents{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		
		super(driver);
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
		
	}
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> productTitle;
	
	@FindBy(css=".totalRow button")
	private WebElement chkBtn;
	
	
	
	
	public Boolean verifyProductDisplay(String itemToBeAdded) {
		
		visibleElement(chkBtn);
		
		Boolean match = productTitle.stream().anyMatch(x -> x.getText().equalsIgnoreCase(itemToBeAdded));
		
		return match;
		
	}
	
	public PaymentPage checkout() {
		
		
		
		chkBtn.click();
		return new PaymentPage(driver);
		
	}

}
