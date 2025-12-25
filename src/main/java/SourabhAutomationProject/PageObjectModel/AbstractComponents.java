package SourabhAutomationProject.PageObjectModel;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {
	
	WebDriver driver;
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	
	public AbstractComponents(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	private WebElement cartBtn;
	
	@FindBy(css="button[routerlink*='myorders']")
	private WebElement ordersBtn;
	
	@FindBy(id="toast-container")
	private WebElement loginText;
	
	@FindBy(xpath="//h1[text()='Your Orders']")
	private WebElement yourOrdersText;
	
	
	public void visibleElement(WebElement FindBy)
	{
		
		
		wait.until(ExpectedConditions.visibilityOf(FindBy));
		
		
	}
	
	
	public void invisibleElement(WebElement FindBy)
	{
		
		wait.until(ExpectedConditions.invisibilityOf(FindBy));
		
		
	}
	
	public void visibleElement(List<WebElement> FindBy)
	{
		
		
		wait.until(ExpectedConditions.visibilityOfAllElements(FindBy));
		
		
	}
	
	
	public CartPage goToCartBtn() {
		
		cartBtn.click();
		
		return new CartPage(driver);
		
		
	}
	
	public yourOrdersPage goToOrdersBtn() {
		
		
		visibleElement(loginText);
		ordersBtn.click();
		visibleElement(yourOrdersText);
		return new yourOrdersPage(driver);
	}
	

}
