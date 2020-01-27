package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.BasePage;

public class LoginPage extends BasePage{
	
	//Login Page objects repository or web elements
	
	@FindBy(xpath="//input[@name='email']")
	WebElement email;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement password;
	
	@FindBy(xpath="//button[@title='Sign in to Grow']")
	WebElement loginButton;
	
	//initializing the page elements
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Login Page methods/actions
	
	public String validateLoginPageTitle() {
		WebDriverWait wait=new WebDriverWait(driver,10); //putting the explicit wait for the appearence of the title 
		wait.until(ExpectedConditions.titleIs("Grow"));//waiting for the title to display 
		String title=driver.getTitle();//getting the title of the page
		return title;
	}
	
	public HomePage login(String emailID,String pass) {
		
		email.sendKeys(emailID);//writing into the email id box
		password.sendKeys(pass);//sending the password
		loginButton.click();//clicking on login button
		return new HomePage(); //once clicked on login button ,landing on Home Page (page chaining)
	}

}
