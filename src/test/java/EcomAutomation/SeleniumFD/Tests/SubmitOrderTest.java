package EcomAutomation.SeleniumFD.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import EcomAutomation.SeleniumFD.TestComponents.BaseTest;
import EcomAutomation.SeleniumFD.pageobjects.ConfirmationPage;
import EcomAutomation.SeleniumFD.pageobjects.PaymentDashboardPage;
import EcomAutomation.SeleniumFD.pageobjects.ProductCatlogPage;

public class SubmitOrderTest extends BaseTest {

	@Test(dataProvider="getHashData", groups = {"purchase"})
	public void SubmitOrder(HashMap<String,String> input) throws IOException, InterruptedException {

		// Calling method to do login action.
		ProductCatlogPage catlogPage = loginPage.loginAction(input.get("email"), input.get("password"));

		// Calling method to add ADDIDAS ORIGINAL product to cart.
		catlogPage.addToCartAction(input.get("productName"));

		// Calling method to click on Cart button
		catlogPage.cartClickAction();

		// Calling method to click on Cart button
		PaymentDashboardPage paymentdashboard = catlogPage.ChecoutClickAction();

		// Calling method to select India from drop down
		paymentdashboard.selectCountry("Ind");

		// Calling method to click on check out button.
		ConfirmationPage confirmpage = paymentdashboard.placeOrder();

		String confirmMessage = confirmpage.getConfirmMessage();
		Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");
	}

	@DataProvider
	public Object[][] getData() {
		return new Object[][] { { "demoshreyas39@mail.com", "Demo123!", "ADIDAS ORIGINAL" },
			{ "demoshreyas69@mail.com", "Demo1234!", "IPHONE 13 PRO" } };
	}
	
	@DataProvider
	public Object[][] getHashData() {
		HashMap <String, String> map1 = new HashMap <String, String>();
		map1.put("email","demoshreyas39@mail.com");
		map1.put("password","Demo123!");
		map1.put("productName","ADIDAS ORIGINAL");
		
		HashMap <String, String> map2 = new HashMap <String, String>();
		map2.put("email","demoshreyas69@mail.com");
		map2.put("password","Demo1234!");
		map2.put("productName","IPHONE 13 PRO");
		
		return new Object [][] {{map1},{map2}};
	}
	
	@DataProvider
	public Object[][] getJsonData() throws IOException {
	List <HashMap<String,String>> data = getJsonToMap(System.getProperty("user.dir")
			+ "src\\test\\java\\EcomAutomation\\SeleniumFD\\Data\\PurchaseOrder.json");
		
		return new Object [][] {{data.get(0)},{data.get(1)}};
	}
}
