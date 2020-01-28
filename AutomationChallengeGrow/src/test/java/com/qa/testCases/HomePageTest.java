package com.qa.testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.BasePage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;

public class HomePageTest extends BasePage {

	public HomePage homePage;
	public LoginPage loginPage;

	public HomePageTest() {
		// calling the super class constructor for initializing the properties file
		super();
	}

	@BeforeMethod
	public void setUp() {
		// initializing the browser and login into system
		initialization();
		// creating the object of home page
		homePage = new HomePage();
		// creating the object of login page class
		loginPage = new LoginPage();
		loginPage.login(prop.getProperty("userID"), prop.getProperty("password"));

	}

	// will execute first
	@Test(priority = 1)
	public void validateSalesByMonthMetricNotExpendedTest() {
		String dealAmount = homePage.validateSalesByMonthMetricNotExpended();
		Assert.assertEquals(dealAmount, "3.48", "Actual deal amount is not as expected");
	}

	@Test(priority = 2)
	public void validateCloseExpendedViewTest() throws InterruptedException {
		String exploreLabelOnMetrics = homePage.validateSalesByMonthMetricsOptions();
		// asserting expanded view has explore label or not
		Assert.assertEquals(exploreLabelOnMetrics, "Explore");
		Thread.sleep(5000);
		// closing the expanded view
		driver.findElement(By.cssSelector("[viewBox='0 0 9 7'][width='9px']")).click();
		System.out.println("Sales By Month Metric Tile expanded view  has been closed");
	}

	@AfterMethod
	public void tearDown() {
		// close the browser
		driver.quit();
	}
}
