package companyName.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import companyName.AbstractComponenets.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//li[@class='totalRow']/button")
	WebElement checkoutButton;

	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> productTitles;
	
	public Boolean verifyProductDisplay(String productName) {
		Boolean match=productTitles.stream().anyMatch(productTitle->productTitle.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		checkoutButton.click();
		return new CheckoutPage(driver);
	}
}
