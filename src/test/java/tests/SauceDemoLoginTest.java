package tests;

import org.testng.annotations.Test;

import pages.SauceDemoLoginPage;
import utilities.Driver;
import utilities.PropertiesReader;

import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;

public class SauceDemoLoginTest {

	SauceDemoLoginPage logInPage;

	@Test
	public void loginPageTest() {
		logInPage = new SauceDemoLoginPage();
		/*
		 * Test case 1 go to https://saucedemo.com log in with username = standard_user
		 * password = secret_sauce find all the results and get them in a list, and
		 * print the size of the list.
		 */

		Driver.getDriver().get(PropertiesReader.getProperty("sauceURL"));

		logInPage.loginUserName.sendKeys(PropertiesReader.getProperty("sauce_username"));

		logInPage.loginPassword.sendKeys(PropertiesReader.getProperty("sauce_password"));

		logInPage.logInBtn.click();

		System.out.println(logInPage.items.size());

	}

	@Test
	public void inventoryTest() {
		/*
		 * go to https://saucedemo.com log in with username = standard_user password =
		 * secret_sauce find all the results. print the price of each result. Hint: loop
		 * through the list, get index and get text
		 */
		logInPage = new SauceDemoLoginPage();
		Driver.getDriver().get(PropertiesReader.getProperty("sauceURL"));

		logInPage.loginUserName.sendKeys(PropertiesReader.getProperty("sauce_username"));
		logInPage.loginPassword.sendKeys(PropertiesReader.getProperty("sauce_password"));
		logInPage.logInBtn.click();

		for (WebElement item_price : logInPage.itemPrices) {
			System.out.println(item_price.getText().trim());
		}

	}

	@BeforeMethod
	public void beforeMethod() {
		Driver.getDriver().manage().window().maximize();
		Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void afterMethod() {
		Driver.quitDriver();
	}

}
