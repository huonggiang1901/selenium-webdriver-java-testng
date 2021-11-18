package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_02_Run_On_Broswer {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@Test
	public void TC_01_Firefox_Lastest() {
		// Firefox lastest: 89
		// Selenum 3.141.59
		// TestNG 6.14.3
		// Gecko Driver
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
	
		driver.quit();
	}
	
	//@Test
	public void TC_02_Firefox_Old() {
		// Firefox 47.0.2
		// Selenium 2.53.1
		// Ko TestNG
		// Ko Gecko Driver
		
		driver = new FirefoxDriver();
		
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		
		driver.quit();
	}

}
