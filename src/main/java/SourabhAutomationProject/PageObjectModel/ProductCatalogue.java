package SourabhAutomationProject.PageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductCatalogue extends AbstractComponents{
	
	WebDriver driver;
	
	
	public ProductCatalogue(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
		
		
	}
	
	
	@FindBy(css="div.mb-3")
	private List<WebElement> products;
	
	@FindBy(css="div.ng-animating")
	private WebElement spinner;
	
	@FindBy(id="toast-container")
	private WebElement text;
	
	
	
	private By cartBtn = By.cssSelector("button:last-of-type");
	
	
	
	
	public List<WebElement> getProductsList() {
		
		visibleElement(products);
		return products;
		
		
	}
	
	public WebElement getProductByName(String itemToBeAdded){
		
		List<WebElement> products = getProductsList();
		
		WebElement prod = products.stream().filter(x -> itemToBeAdded.contains(x.findElement(By.cssSelector("b")).getText())).findFirst().orElse(null);
		
		return prod;
	}
	
	
	public void addToCart(String itemToBeAdded) throws InterruptedException{
		
		WebElement prod =  getProductByName(itemToBeAdded);
		prod.findElement(cartBtn).click();
		visibleElement(text);
		invisibleElement(spinner);
				
	}
	
	
	
	

}
