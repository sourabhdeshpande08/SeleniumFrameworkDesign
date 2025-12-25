package SourabhAutomationProject.PageObjectModel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class yourOrdersPage extends AbstractComponents{

	WebDriver driver;
	
	public yourOrdersPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
		
	}
	
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productName;
	
	public Boolean verifyOrdersDetails(String itemToBeAdded) {
		
		
		Boolean match=  productName.stream().anyMatch(x -> x.getText().equalsIgnoreCase(itemToBeAdded));
		return match;
		
		
	}
	
	

}
