package CompanyName.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import CompanyName.TestComponents.BaseTest;
import companyName.PageObjects.CartPage;
import companyName.PageObjects.CheckoutPage;
import companyName.PageObjects.ConfirmationPage;
import companyName.PageObjects.LandingPage;
import companyName.PageObjects.OrderPage;
import companyName.PageObjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest{
	String productName="ZARA COAT 3";
	String countryName="India";
	String expectedConfirmMessage="THANKYOU FOR THE ORDER.";

	@Test(dataProvider="getData",groups="Purchase")
	public void SubmitOrder(HashMap<String,String> input) throws IOException, InterruptedException{
		// TODO Auto-generated method stub
		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage= productCatalogue.goToCartPage();
		Boolean match=cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartPage.goToCheckout();
		checkoutPage.selectCountry(countryName);
		ConfirmationPage confirmationPage=checkoutPage.submitOrder();
		String confirmMessage=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(expectedConfirmMessage));
		
	}
   @Test(dependsOnMethods="SubmitOrder")
   public void orderHistorytest() {
		ProductCatalogue productCatalogue=landingPage.loginApplication("ab@example.com", "Ab@12345");
		OrderPage orderPage=productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
   }
   
   @DataProvider
   public Object[][] getData() throws IOException {
		/*
		 * HashMap<String,String> map= new HashMap<String,String>(); map.put("email",
		 * "ab@example.com"); map.put("password", "Ab@12345"); map.put("productName",
		 * "ZARA COAT 3"); HashMap<String,String> map1= new HashMap<String,String>();
		 * map1.put("email", "ab@t.com"); map1.put("password", "Ab@12345");
		 * map1.put("productName", "ADIDAS ORIGINAL");
		 */
	   List<HashMap<String,String>> data =getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\CompanyName\\data\\PurchaseOrder.json");
	   return new Object[][] {{data.get(0)},{data.get(1)}};
	 //  return new Object[][] {{"ab@example.com","Ab@12345","ZARA COAT 3"},{"ab@t.com","Ab@12345","ADIDAS ORIGINAL"}};
	   
   }
   
}
