package stepDefs;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SourabhAutomationProject.PageObjectModel.CartPage;
import SourabhAutomationProject.PageObjectModel.FinalPage;
import SourabhAutomationProject.PageObjectModel.LandingPage;
import SourabhAutomationProject.PageObjectModel.PaymentPage;
import SourabhAutomationProject.PageObjectModel.ProductCatalogue;
import SourabhAutomationProject.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition extends BaseTest{
	
	WebDriver driver;
	LandingPage landingPage;
	ProductCatalogue prodCat;
	CartPage cart;
	PaymentPage payment;
	FinalPage finalPage;
	
	
	@Given("The user landed on the ECommerce website login page")
	public void The_user_landed_on_the_ECommerce_website_login_page() throws IOException
	{
		
	 	driver =  InitializeDriver();
	 	landingPage = new LandingPage(driver);
	 	landingPage.goToURL("https://rahulshettyacademy.com/client/");
		
	}
	
	
	@Given("the user logged in using the {string} as Email and {string} as password")
	public void the_user_logged_in_using_Email_and_password(String userName, String password)
	{
		
		prodCat = landingPage.login(userName, password);
		
		
	}
	
	@When("the user added a product {string} in the cart")
	public void the_user_added_a_product_in_the_cart(String itemsToBeAdded) throws InterruptedException
	{
		List<WebElement> products = prodCat.getProductsList();
		WebElement prod = prodCat.getProductByName(itemsToBeAdded);
		prodCat.addToCart(itemsToBeAdded);
		
	}
	
	@And("the user verifies the order {string} in My Cart section")
	public void the_user_verifies_the_order_in_My_Cart_section(String itemsToBeAdded)
	{
		cart = prodCat.goToCartBtn();
		Boolean match = cart.verifyProductDisplay(itemsToBeAdded);
		Assert.assertTrue(match);
		
	}
	
	@Then("the user checks out and selects the country")
	public void the_user_checks_out_and_selects_the_country()
	{
		
		payment = cart.checkout();
		payment.selectCountry();
		
	}
	
	
	
	@And("the user clicks the Place Order button")
	public void the_user_clicks_the_Place_Order_button()
	{
		finalPage = payment.placeOrder();
	}
	
	@And("The user verifies the order ID and order confirmation message {string}")
	public void The_user_verifies_the_order_ID_and_order_confirmation_message(String string)
	{
		
		System.out.println(finalPage.verifyOrderID());
		String orderConfirmationText = finalPage.getConfirmationMessage();
		Assert.assertEquals(orderConfirmationText, string);
		driver.close();

	}
	
	
}
