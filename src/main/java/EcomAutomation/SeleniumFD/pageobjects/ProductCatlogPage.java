package EcomAutomation.SeleniumFD.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import EcomAutomation.SeleniumFD.abstarctcomponents.AbstractComponent;

public class ProductCatlogPage extends AbstractComponent {

	protected WebDriver driver;

	public ProductCatlogPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// List <WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

	@FindBy(css = ".mb-3")
	List<WebElement> Products;

	@FindBy(css = ".ng-animating")
	WebElement Spinner;

	@FindBy(css = "[routerlink*='cart']")
	WebElement Cart;

	By productsLOC = By.cssSelector(".mb-3");
	By addCartLOC = By.cssSelector(".card-body button:last-of-type");
	By toastLOC = By.cssSelector("#toast-container");
	By loadingLOC = By.cssSelector(".ng-animating");

	public List<WebElement> getProducts() {
		waitForElementToAppear(productsLOC);
		return Products;
	}

	public Boolean verfiyProductsdisplay(String productName) {
		Boolean match = Products.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;

	}

	public WebElement getProduct(String productName) {
		WebElement foundProduct = getProducts().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return foundProduct;
	}

	public void addToCartAction(String productName) throws InterruptedException {
		WebElement foundProduct = getProduct(productName);
		foundProduct.findElement(addCartLOC).click();
		waitForElementToAppear(toastLOC);
		waitForElementToDisappear(Spinner);
	}

	public void cartClickAction() {
		Cart.click();
	}

}
