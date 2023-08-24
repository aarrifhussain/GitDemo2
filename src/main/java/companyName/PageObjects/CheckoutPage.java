package companyName.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import companyName.AbstractComponenets.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement countryField;
	
	@FindBy(xpath="//a[contains(@class,'action__submit')]")
	WebElement submitButton;
	
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	WebElement selectCountry;
	
	By results=By.cssSelector(".ta-results ");
	
	public void selectCountry(String countryName) throws InterruptedException {
		Actions a= new Actions(driver);
		a.sendKeys(countryField,countryName).build().perform();
		waitforElementToAppear(results);
		selectCountry.click();
		scrollDown();
	}
	
	public ConfirmationPage submitOrder() {
		submitButton.click();
		return new ConfirmationPage(driver);
	}
}
