package com.qa.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.BasePage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.util.Constants;

public class LoginPageTest extends BasePage{
	
	public LoginPage loginPage;
	public HomePage homePage;
	
	public LoginPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage=new LoginPage();
	}
	
	@Test(priority=1)
	public void LoginPageTitleTest() {
		String title=loginPage.validateLoginPageTitle();
		System.out.println(title);
		
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);//varifying if we are on login page or not
	}
	
	@Test(priority=2)
	public void loginTest() {
		homePage=loginPage.login(prop.getProperty("userID"), prop.getProperty("password"));
		String actual=driver.getTitle();//getting the title of the home page
		System.out.println(actual);
		Assert.assertEquals(actual, Constants.HOME_PAGE_TITLE);//varifying the home page title
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();//quit the browser
	}

}
