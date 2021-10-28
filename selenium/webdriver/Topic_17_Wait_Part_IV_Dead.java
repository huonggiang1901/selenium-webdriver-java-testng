package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_Part_IV_Dead {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_03_StaticWait() {

		System.out.println("TC 03 - Static Wait");

		System.out.println("	Step 01: Truy cập vào trang: https://automationfc.github.io/dynamic-loading/");
		driver.get("https://automationfc.github.io/dynamic-loading/");

		System.out.println("	Step 02: Click Start button");
		driver.findElement(By.xpath("//div[@id='start']/button")).click();

		System.out.println("	Step 03: Define a static wait (Thread.sleep)");
		sleepInSeconds(6);

		System.out.println("	Step 04: Wait result text appear");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4")).isDisplayed());

		System.out.println("	Step 05: Check result text is \"Hello World!\"");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}

	public void sleepInSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
