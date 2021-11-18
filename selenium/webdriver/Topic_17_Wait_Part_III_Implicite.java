package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_Part_III_Implicite {
	
	WebDriver driver;	
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");		
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_02_ImplicitlyWait() {
		
		System.out.println("TC 02 - Implicitly Wait");
		
		System.out.println("	Step 01: Truy cập vào trang: https://automationfc.github.io/dynamic-loading/");	
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		System.out.println("	Step 02: Define an implicit wait");	
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		System.out.println("	Step 03: Click Start button");	
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		System.out.println("	Step 04: Wait result text appear");	
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4")).isDisplayed());		
	
		System.out.println("	Step 05: Check result text is \"Hello World!\"");	
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}	
	
}
