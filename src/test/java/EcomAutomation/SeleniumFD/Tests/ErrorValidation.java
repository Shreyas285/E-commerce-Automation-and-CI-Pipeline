package EcomAutomation.SeleniumFD.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import EcomAutomation.SeleniumFD.TestComponents.BaseTest;

public class ErrorValidation extends BaseTest{

     @Test
     public void loginErrorValidation() throws IOException, InterruptedException {
		
		// Calling method to do login action.
		loginPage.loginAction("demoshreyas39@mail.com", "Demo123!!");
		Assert.assertEquals("Incorrect email or password.", loginPage.getErrorMessage());
		
	 }    
}
