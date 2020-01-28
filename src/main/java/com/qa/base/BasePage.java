package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.qa.util.TestUtil;
import com.qa.util.WebEventListener;

public class BasePage {
	
	public static WebDriver driver;    //referenece to webdriver
	public static Properties prop;   //properties file reference
	static FileInputStream fis;      //fileinputstream  reference
	public static EventFiringWebDriver edriver;//reference to the web events occuring
	public static WebEventListener elistener;//reference of the class which implements WebDriverEventListener
	
	public BasePage() {
         prop=new Properties();
		
		try {
			fis=new FileInputStream("C:\\Users\\parmod.kumar\\eclipse-workspace\\AutomationChallengeGrow\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(fis);//configuration data initialization
		} catch (FileNotFoundException e) {    //catching the exceptions
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
		
	}
	
	
	public static void initialization() {
		String browserName=prop.getProperty("browser");//fetches the browser name from config.properties file initialized above
		//browser name provided in properties file ,launches the browser
		
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "C:\\mytools\\chromedriver_win32 (1)\\chromedriver.exe");
			driver=new ChromeDriver();
			
			}
		else if(browserName.equals("firefox")){
			System.setProperty("webdriver.gecko.driver", "C:\\mytools\\geckodriver-v0.25.0-win64\\geckodriver.exe");
			driver=new FirefoxDriver();
			
		}
		
		edriver=new EventFiringWebDriver(driver);
		elistener=new WebEventListener();
		edriver.register(elistener);//registering the driver reference to events listener so that it will listenes to the actions happening on web page
		driver=edriver;
		
		driver.manage().deleteAllCookies();//before launching delete all the cookies
		driver.manage().window().maximize();//maximizing the window
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICITLY_WAIT, TimeUnit.SECONDS);//global wait for finding the web elements
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);//setting the page load time out
		
		driver.get(prop.getProperty("baseURL"));//launches the url provided in the config.properties file
	}

}
