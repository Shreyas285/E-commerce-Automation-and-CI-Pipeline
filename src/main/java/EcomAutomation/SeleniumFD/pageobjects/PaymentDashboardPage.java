package EcomAutomation.SeleniumFD.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import EcomAutomation.SeleniumFD.abstarctcomponents.AbstractComponent;

public class PaymentDashboardPage extends AbstractComponent {

	protected WebDriver driver;

	public PaymentDashboardPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".user__name.mt-5 div div")
	WebElement Country;
	
	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement SelectCountry;
	
	@FindBy(css = ".btnn.action__submit.ng-star-inserted")
	WebElement PlaceOrder;
	
	By displayItemLOC = By.cssSelector(".ta-results.list-group.ng-star-inserted button");
	By placeOrder = By.cssSelector(".btnn.action__submit.ng-star-inserted");
	
	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(Country, countryName).build().perform();
		waitForElementToAppear(displayItemLOC);
		SelectCountry.click();
	}
	
	public ConfirmationPage placeOrder() {
		//waitForElementToAppear(placeOrder);
		PlaceOrder.click();
		ConfirmationPage confirmpage = new ConfirmationPage(driver);
		return confirmpage;
	}

}
