package testng;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Topic_05_DataProvider {

	WebDriver driver;
	
	String projectPath = System.getProperty("user.dir");

	By emailTextbox = By.xpath("//*[@id='email']");
	By passwordTextbox = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test(dataProvider = "register")
	public void TC_01_Register_To_System(String username, String password) {

	}

	@Test(dataProvider = "login")
	public void TC_02_Login_To_System(String username, String password) {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");

		driver.findElement(emailTextbox).sendKeys(username);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(loginButton).click();

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(username));

		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log out']")).click();
	}

	@DataProvider(name = "login")
	public Object[][] loginData(Method method) {
		Object[][] obj = null;
		if (method.getName().contains("Resister_To_System")) {
			obj = new Object[][] { { "selenium" + getCurrentDateAndTime() + "@gmail.com", "111111" },
					{ "selenium" + getCurrentDateAndTime() + "@gmail.com", "111111" },
					{ "selenium" + getCurrentDateAndTime() + "@gmail.com", "111111" }, };
		} else if (method.getName().contains("Login_To_System")) {
			obj = new Object[][] { { "selenium_11_01@gmail.com", "111111" }, { "selenium_11_02@gmail.com", "111111" },
					{ "selenium_11_03@gmail.com", "111111" }, };
		}

		return obj;
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public String getCurrentDateAndTime() {
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyHH:mm:ss");
		return dateFormat.format(currentDate).toString().replace(":", "");
	}
}
