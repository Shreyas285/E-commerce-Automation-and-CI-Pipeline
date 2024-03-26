package EcomAutomation.SeleniumFD.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import EcomAutomation.SeleniumFD.abstarctcomponents.AbstractComponent;

public class LoginPage extends AbstractComponent{

	protected WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement Email = driver.findElement(By.id("userEmail"));
	@FindBy(id = "userEmail")
	WebElement Email;

	// WebElement Password = driver.findElement(By.id("userPassword"));
	@FindBy(id = "userPassword")
	WebElement Password;

	// WebElement Login = driver.findElement(By.id("login"));
	@FindBy(id = "login")
	WebElement Login;
	
	@FindBy(css = "div[aria-label='Incorrect email or password.']")
	WebElement errorMessage;
	
	public void getLoginPage() {
		driver.get("https://rahulshettyacademy.com/client/");
	}

	public ProductCatlogPage loginAction(String email, String password) {
		Email.sendKeys(email);
		Password.sendKeys(password);
		Login.click();
		
		// Creating productCatlogPage object
		ProductCatlogPage catlogPage = new ProductCatlogPage (driver);
		return catlogPage;
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

}
