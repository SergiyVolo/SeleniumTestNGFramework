package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Driver;

public class SauceDemoLoginPage {
	
	public SauceDemoLoginPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	@FindBy(id = "user-name")
	public WebElement loginUserName;

	@FindBy(id = "password")
	public WebElement loginPassword;
	// you can directly put the id as the WebElement method name, this works with
	// only ID locator.

	@FindBy(name = "login-button")
	public WebElement logInBtn;
	
	@FindBy (css = ".inventory_item")
	public List<WebElement> items;
	
	// in page object model, all the elements that belongs to a page
	// should be stored in that same page objects class

	@FindBy (css = ".inventory_item_price")
	public List<WebElement> itemPrices;

}

