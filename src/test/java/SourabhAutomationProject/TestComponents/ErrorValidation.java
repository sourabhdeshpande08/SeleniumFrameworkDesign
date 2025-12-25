package SourabhAutomationProject.TestComponents;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import SourabhAutomationProject.PageObjectModel.CartPage;
import SourabhAutomationProject.PageObjectModel.ProductCatalogue;

public class ErrorValidation extends BaseTest {

	@Test(groups = { "errorHandling" })
	public void loginErrorValidation() throws IOException {

		//This method verifies if the error message is displayed or not.
		
		landingPage.login("sourabhdeshpande08@gmail.com", "S@uRabh@0890");
		String errorText = landingPage.getErrorMessage();
		Assert.assertEquals(errorText, "Incorrect email or password.");
		

	}

	@Test(retryAnalyzer = Listeners.class)
	public void productErrorValidation() throws IOException, InterruptedException {

		String itemToBeAdded = "ZARA COAT 3";

		ProductCatalogue prodCat = landingPage.login("sourabhdeshpande@gmail.com", "S@uRabh@1990");

		prodCat.addToCart(itemToBeAdded);
		CartPage cart = prodCat.goToCartBtn();
		Boolean match = cart.verifyProductDisplay("ZARA COAT 33");
		Assert.assertTrue(match);
		driver.close();

	}

}
