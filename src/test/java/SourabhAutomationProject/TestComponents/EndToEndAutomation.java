package SourabhAutomationProject.TestComponents;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SourabhAutomationProject.PageObjectModel.CartPage;
import SourabhAutomationProject.PageObjectModel.FinalPage;
import SourabhAutomationProject.PageObjectModel.PaymentPage;
import SourabhAutomationProject.PageObjectModel.ProductCatalogue;
import SourabhAutomationProject.PageObjectModel.yourOrdersPage;

public class EndToEndAutomation extends BaseTest {

	
	
	@Test(dataProvider="getData",groups={"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException {

		ProductCatalogue prodCat = landingPage.login(input.get("email"), input.get("password"));
		List<WebElement> products = prodCat.getProductsList();
		WebElement prod = prodCat.getProductByName(input.get("itemToBeAdded"));
		prodCat.addToCart(input.get("itemToBeAdded"));
		CartPage cart = prodCat.goToCartBtn();
		Boolean match = cart.verifyProductDisplay(input.get("itemToBeAdded"));
		Assert.assertTrue(match);
		PaymentPage payment = cart.checkout();
		payment.selectCountry();
		FinalPage finalPage = payment.placeOrder();
		System.out.println(finalPage.verifyOrderID());

		String orderConfirmationText = finalPage.getConfirmationMessage();
		Assert.assertEquals(orderConfirmationText, input.get("expectedText"));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistory() throws IOException {

		String itemToBeAdded = "ZARA COAT 3";
		
		ProductCatalogue prodCat = landingPage.login("sourabhdeshpande@gmail.com", "S@uRabh@1990");
		yourOrdersPage ordersPage = prodCat.goToOrdersBtn();
		Boolean match = ordersPage.verifyOrdersDetails(itemToBeAdded);
		Assert.assertTrue(match);

	}

	@DataProvider
	public Object[][] getData() throws IOException {
		
		/* Map<String, String> hash = new HashMap<>();
		hash.put("email", "sourabhdeshpande@gmail.com");
		hash.put("password", "S@uRabh@1990");
		hash.put("itemToBeAdded", "ZARA COAT 3");
		hash.put("expectedText", "THANKYOU FOR THE ORDER.");
		
		Map<String, String> hash2 = new HashMap<>();
		hash2.put("email", "aavyan@gmail.com");
		hash2.put("password", "A@vyan@2021");
		hash2.put("itemToBeAdded", "ADIDAS ORIGINAL");
		hash2.put("expectedText", "THANKYOU FOR THE ORDER."); */
		
		//return new Object[][]	{{"sourabhdeshpande@gmail.com","S@uRabh@1990","ZARA COAT 3","THANKYOU FOR THE ORDER."},{"aavyan@gmail.com","A@vyan@2021","ADIDAS ORIGINAL","THANKYOU FOR THE ORDER."}};
		
		List<HashMap<String, String>> data = getJsonDataToMap();
		return new Object[][]	{{data.get(0)},{data.get(1)}};
		
	}
	
	
	

}
