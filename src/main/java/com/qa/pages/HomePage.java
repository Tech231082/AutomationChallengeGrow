package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.BasePage;

public class HomePage extends BasePage {

	// HomePage objects repository or web elements on home page
	@FindBy(xpath = "//*[@id=\"metricTiles\"]/div[1]/div[1]")
	WebElement salesByMonth;

	// home page elements /objects initialization
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	// home page methods/actions
	public String validateSalesByMonthMetricNotExpended() {
		String dealAmount = driver.findElement(By.cssSelector(".focusValues---primaryValueValue---1zneM")).getText();
		System.out.println("Deal Amount present : " + dealAmount);
		return dealAmount;
	}

	public String validateSalesByMonthMetricsOptions() throws InterruptedException {
		// actions class object with the driver instance
		Actions action = new Actions(driver);
		// mouse hover to the expand icon
		action.moveToElement(salesByMonth).build().perform();
		// clicking on the expand icon
		driver.findElement(By.xpath("//*[@id=\"metricTiles\"]/div[1]/div[2]")).click();

		System.out.println("Sales By Month Metric Dashboard has been expended");
		WebElement explore = driver.findElement(By.xpath("//div[contains(text(),'Explore')]"));
		Thread.sleep(5000);
		// getting the text from expanded view
		String exploreLabel = explore.getText();
		// return the text to calling method
		return exploreLabel;

	}

}
