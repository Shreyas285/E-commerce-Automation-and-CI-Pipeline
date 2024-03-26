package EcomAutomation.SeleniumFD.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import EcomAutomation.SeleniumFD.abstarctcomponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
	
	protected WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	@FindBy(css = ".hero-primary")
	WebElement ConfirmMessage;
	
	public String getConfirmMessage() {
		return ConfirmMessage.getText();
	}
	
	

}
