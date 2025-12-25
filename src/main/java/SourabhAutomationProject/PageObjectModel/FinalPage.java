package SourabhAutomationProject.PageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FinalPage extends AbstractComponents{
	
WebDriver driver;
	
	public FinalPage(WebDriver driver) {
		
		super(driver);
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="label.ng-star-inserted")
	private WebElement orderID;
	
	@FindBy(css=".hero-primary")
	private WebElement orderConfirmText;
	
	public String verifyOrderID() {
		
		visibleElement(orderConfirmText);
		
		String orderIdText =  orderID.getText();
		return orderIdText;
		
		
		
	}
	
	public String getConfirmationMessage() {
		
		String orderConfirmationText = orderConfirmText.getText();
		return orderConfirmationText;
		
	}

}
