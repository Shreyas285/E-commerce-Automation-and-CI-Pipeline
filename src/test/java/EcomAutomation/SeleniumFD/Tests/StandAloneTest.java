package EcomAutomation.SeleniumFD.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;


public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		
		driver.findElement(By.id("userEmail")).sendKeys("demoshreyas39@mail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Demo123!");
		driver.findElement(By.id("login")).click();
		
        String productName = "ADIDAS ORIGINAL";
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		//Adding ADIDAS ORIGINAL to the cart
		List <WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement AddidasProduct = products.stream().filter(product-> 
        product.findElement(By.cssSelector("b")).getText().equals("ADIDAS ORIGINAL")).findFirst().orElse(null);
		AddidasProduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		//Adding IPHONE 13 PRO to the cart
		WebElement IphoneProduct = products.stream().filter(product-> 
        product.findElement(By.cssSelector("b")).getText().equals("IPHONE 13 PRO")).findFirst().orElse(null);
		IphoneProduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List <WebElement> Cartproducts = driver.findElements(By.cssSelector(".cartWrap.ng-star-inserted h3"));
		
		Boolean match = Cartproducts.stream().anyMatch(Cartproduct -> Cartproduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		WebElement country = driver.findElement(By.cssSelector(".user__name.mt-5 div div"));
		
		Actions a = new Actions(driver);
		a.sendKeys(country, "Ind").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results.list-group.ng-star-inserted button")));
		

        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
        driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
        
        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        System.out.println(confirmMessage);
        Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");
		driver.quit();
	}
}
