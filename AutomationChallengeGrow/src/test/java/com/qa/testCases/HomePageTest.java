package com.qa.testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.BasePage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;

public class HomePageTest extends BasePage{
	
	public HomePage homePage;
	public LoginPage loginPage;
		
	public HomePageTest() {
		super();//calling the super class constructor for initializing the properties file
	}
	
	
	@BeforeMethod
	public void setUp() {   //initializing the browser and login into system
		initialization();
		homePage=new HomePage();//creating the object of home page
		loginPage=new LoginPage();//creating the object of login page class
		loginPage.login(prop.getProperty("userID"), prop.getProperty("password"));
		
	}
	@Test(priority=1)  //will execute first
	public void validateSalesByMonthMetricNotExpendedTest() {
		String dealAmount= homePage.validateSalesByMonthMetricNotExpended();
		
		Assert.assertEquals(dealAmount, "3.48","Actual deal amount is not as expected");
	}
	
	
	
	@Test(priority=2)
	public void validateCloseExpendedViewTest() {
		String exploreLabelOnMetrics=homePage.validateSalesByMonthMetricsOptions();
		Assert.assertEquals(exploreLabelOnMetrics, "Explore");//asserting expanded view has explore label or not 
		
		driver.findElement(By.cssSelector("[viewBox='0 0 9 7'][width='9px']")).click();//closing the expanded view
		System.out.println("Sales By Month Metric Tile expanded view  has been closed");
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();//close the browser
	}

}
