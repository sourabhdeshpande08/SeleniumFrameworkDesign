package SourabhAutomationProjects.EndToEndAutomation;


import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ECommerceWebSiteAutomation {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		JavascriptExecutor js = (JavascriptExecutor) driver;

		List<String> itemsToBeAdded = Arrays.asList("ZARA COAT 3", "ADIDAS ORIGINAL");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.manage().window().maximize();

		driver.get("https://rahulshettyacademy.com/client/");

		driver.manage().deleteAllCookies();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		ECommerceWebSiteAutomation object = new ECommerceWebSiteAutomation();

		object.login(driver, wait);
		object.addTocart(driver, itemsToBeAdded, wait);
		object.cart(driver, itemsToBeAdded, wait, js);
		object.autoSuggestiveDropdown(driver, wait);

	}

	public void login(WebDriver driver, WebDriverWait wait) {

		driver.findElement(By.id("userEmail")).sendKeys("sourabhdeshpande@gmail.com");

		driver.findElement(By.id("userPassword")).sendKeys("S@uRabh@1990");

		driver.findElement(By.cssSelector("input.login-btn")).click();

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("toast-container"))));

	}

	public void addTocart(WebDriver driver, List<String> itemsToBeAdded, WebDriverWait wait) {

		WebElement textElement = driver.findElement(By.id("toast-container"));

		List<WebElement> itemsList = driver.findElements(By.cssSelector("div[class*='col-lg-4']"));

		itemsList.stream().filter(x -> itemsToBeAdded.contains(x.findElement(By.cssSelector("h5")).getText()))
				.forEach(x -> {

					WebElement addToCartBtn = x.findElement(By.cssSelector("button:last-of-type"));

					addToCartBtn.click();

					wait.until(ExpectedConditions.visibilityOf(textElement));

					String text = textElement.getText();

					System.out.println(text);

					wait.until(ExpectedConditions.invisibilityOf(textElement));

				});

		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();

	}

	public void cart(WebDriver driver, List<String> itemsToBeAdded, WebDriverWait wait, JavascriptExecutor js)
			throws InterruptedException {

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("toast-container")));

		List<WebElement> cartItems = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));

		Boolean match = cartItems.stream().anyMatch(x -> itemsToBeAdded.contains(x.getText()));

		Assert.assertTrue(match);

		js.executeScript("window.scrollBy(0,1000)");

		driver.findElement(By.xpath("(//button[@class='btn btn-primary'])[4]")).click();

	}

	public void autoSuggestiveDropdown(WebDriver driver, WebDriverWait wait) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Select Country']")));

		driver.findElement(By.cssSelector("[class='form-group'] input")).sendKeys("ind");

		List<WebElement> countries = driver.findElements(By.cssSelector("span.ng-star-inserted"));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.ng-star-inserted")));

		WebElement country = countries.stream().filter(x -> x.getText().equalsIgnoreCase("india")).findFirst()
				.orElse(null);

		country.click();

		wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath("//div[@class='form-group']/input"),
				"India"));

		driver.findElement(By.cssSelector("a[class*='action__submit']")).click();

		String orderID = driver
				.findElement(
						By.xpath("(//table/tbody/tr/td[@class='box']/table/tbody/tr/td/table/tbody/tr/td/label)[2]"))
				.getText();

		System.out.println(orderID);

		String actualMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();

		Assert.assertEquals(actualMessage, "THANKYOU FOR THE ORDER.");
		
		driver.close();

	}

}
