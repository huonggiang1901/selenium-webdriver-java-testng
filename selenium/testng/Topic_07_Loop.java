package testng;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Loop {

	WebDriver driver;

	String projectPath = System.getProperty("user.dir");

	By emailTextbox = By.xpath("//*[@id='email']");
	By passwordTextbox = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");

	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//		driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test(invocationCount = 100)
	public void TC_01_Register_To_System() {
		System.out.println("abc" + randomNumber(99999) + "@gmail.com");
		System.out.println("abcdef");
	}

	@AfterClass
	public void afterClass() {
//		driver.quit();
	}

	public static int randomNumber(int number) {
		return new Random().nextInt();
	}
}
