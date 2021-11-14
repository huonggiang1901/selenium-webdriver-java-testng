package testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Group {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass(alwaysRun = true)
	public void beforeCLass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		Assert.assertTrue(false);
	}

	@Test(groups = "user")
	public void TC_01() {
		System.out.println("Run TC 01");
	}

	@Test(groups = "user")
	public void TC_02() {
		System.out.println("Run TC 02");
	}

	@Test(groups = { "user", "admin" })
	public void TC_03() {
		System.out.println("Run TC 03");
	}

	@Test(groups = { "user", "supper" })
	public void TC_04() {
		System.out.println("Run TC 04");
	}

	@Test(groups = { "supper" })
	public void TC_05() {
		System.out.println("Run TC 05");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.quit();
	}
}
