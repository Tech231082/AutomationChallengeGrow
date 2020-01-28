package com.qa.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.BasePage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.util.Constants;

public class LoginPageTest extends BasePage {

	public LoginPage loginPage;
	public HomePage homePage;

	public LoginPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
	}

	@Test(priority = 1,enabled=false)
	public void LoginPageTitleTest() {
		String title = loginPage.validateLoginPageTitle();
		System.out.println(title);
		// varifying if we are on login page or not
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void loginTest() {
		homePage = loginPage.login(prop.getProperty("userID"), prop.getProperty("password"));
		// getting the title of the home page
		String actual = driver.getTitle();
		System.out.println(actual);
		// varifying the home page title
		Assert.assertEquals(actual, Constants.HOME_PAGE_TITLE);
	}

	@AfterMethod
	public void tearDown() {
		driver.close();// quit the browser
	}

}
