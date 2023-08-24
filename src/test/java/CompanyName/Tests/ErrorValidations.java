package CompanyName.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import CompanyName.TestComponents.BaseTest;
import companyName.PageObjects.CartPage;
import companyName.PageObjects.CheckoutPage;
import companyName.PageObjects.ConfirmationPage;
import companyName.PageObjects.ProductCatalogue;

public class ErrorValidations extends BaseTest{

	@Test(groups= {"ErrorHandling"},retryAnalyzer=CompanyName.TestComponents.Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException{
		// TODO Auto-generated method stub
		String productName="ZARA COAT 3";
		String countryName="India";
		String expectedConfirmMessage="THANKYOU FOR THE ORDER.";
		landingPage.loginApplication("ab@t.com", "Ab@123456");
		Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException{
		// TODO Auto-generated method stub
		String productName="ZARA COAT 3";
		String countryName="India";
		String expectedConfirmMessage="THANKYOU FOR THE ORDER.";
		ProductCatalogue productCatalogue=landingPage.loginApplication("ab@t.com", "Ab@12345");
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage= productCatalogue.goToCartPage();
		Boolean match=cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}

}
