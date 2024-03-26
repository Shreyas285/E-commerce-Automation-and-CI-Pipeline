package EcomAutomation.SeleniumFD.StepDefinations;

import java.io.IOException;

import org.testng.Assert;

import EcomAutomation.SeleniumFD.TestComponents.BaseTest;
import EcomAutomation.SeleniumFD.pageobjects.ConfirmationPage;
import EcomAutomation.SeleniumFD.pageobjects.LoginPage;
import EcomAutomation.SeleniumFD.pageobjects.PaymentDashboardPage;
import EcomAutomation.SeleniumFD.pageobjects.ProductCatlogPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationImplementation extends BaseTest {

	public LoginPage logingpage;
	public ProductCatlogPage catlogPage;
	public PaymentDashboardPage paymentdashboard;
	public ConfirmationPage confirmpage;

	@Given("I have landed on Ecommerce Page")
	public void I_have_landed_on_Ecommerce_Page() throws IOException {
		logingpage = lauchApplication();
	}

	@Given("^Log in with valid name (.+) and password (.+)$")
	public void Log_in_with_valid_name_and_password(String username, String password) {
		catlogPage = loginPage.loginAction(username, password);
	}

	@When("^I add product (.+) to the cart$")
	public void I_add_product_to_the_cart(String productName) throws InterruptedException {
		catlogPage.addToCartAction(productName);
		// Calling method to click on Cart button
		catlogPage.cartClickAction();
	}

	
	@When("^Checkout (.+) and submit the order$")
	public void Checkout_addida_original_and_submit_the_order(String productName) {

		// Calling method to click on Cart button
		paymentdashboard = catlogPage.ChecoutClickAction();
		// Calling method to select India from drop down
		paymentdashboard.selectCountry("Ind");
		// Calling method to click on check out button.
		confirmpage = paymentdashboard.placeOrder();
	}

	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String message) {
		String confirmMessage = confirmpage.getConfirmMessage();
		Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");
	}
}
