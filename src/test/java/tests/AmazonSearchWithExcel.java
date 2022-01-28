package tests;

import org.testng.annotations.Test;

import pages.AmazonSearchPage;
import utilities.Driver;
import utilities.ExcelUtils;
import utilities.PropertiesReader;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class AmazonSearchWithExcel {

	AmazonSearchPage amazonPage;
	WebDriverWait wait;

	@Test(dataProvider = "searchData")
	public void amazonSearchWithExcel(String data) {
		amazonPage = new AmazonSearchPage();
		wait = new WebDriverWait(Driver.getDriver(), 10);
		Driver.getDriver().get(PropertiesReader.getProperty("amazonURL"));

		String actualTitle = Driver.getDriver().getTitle();
		Assert.assertEquals(actualTitle, "Amazon.com. Spend less. Smile more.");
		amazonPage.searchBox.sendKeys(data);
		amazonPage.searchBtn.click();
		wait.until(ExpectedConditions.visibilityOf(amazonPage.searchResultText));
		String actualText = amazonPage.searchResultText.getText();
		String trimmedText = actualText.substring(1, actualText.length() - 1);
		System.out.println("Actual text: " + actualText);
		System.out.println("Trimmed Text: " + trimmedText);
		System.out.println("Searched text: " + data);
		Assert.assertEquals(trimmedText, data);

	}

	@DataProvider
	public Object[] searchData() {//                      
		Object[] obj = ExcelUtils.getExcelDataInAColumn("./src/test/resources/amazonTestData.xlsx", "Sheet1");
		return obj;
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
