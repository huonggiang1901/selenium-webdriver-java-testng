package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_Command_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	By myAccountLnk = By.xpath("//div[@class='footer']//a[@title='My Account']");
	By createAccountBtn = By.xpath("//a[@title='Create an Account']");

	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Verify_Url() {

		System.out.println("TC 01 - Verify Url");

		System.out.println("	Step 01: Truy cập vào trang: http://live.techpanda.org/index.php/");
		driver.get("http://live.techpanda.org/index.php/");

		System.out.println("	Step 02: Click MY ACCOUNT link tại footer");
		driver.findElement(myAccountLnk).click();

		System.out.println(
				"	Step 03: Verify url của Login Page = http:http://live.techpanda.org/index.php/customer/account/login/");
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");

		System.out.println("	Step 04: Click CREATE AN ACCOUNT button");
		driver.findElement(createAccountBtn).click();

		System.out.println(
				"	Step 05: Verify url của Register Page = http://live.techpanda.org/index.php/customer/account/create/");
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
	}

	@Test
	public void TC_02_Verify_Title() {

		System.out.println("TC 02 - Verify Title");

		System.out.println("	Step 01: Truy cập vào trang: http://live.techpanda.org/index.php/");
		driver.get("http://live.techpanda.org/index.php/");

		System.out.println("	Step 02: Click MY ACCOUNT link tại footer");
		driver.findElement(myAccountLnk).click();

		System.out.println("	Step 03: Verify title của Login Page = Customer Login");
		Assert.assertEquals(driver.getTitle(), "Customer Login");

		System.out.println("	Step 04: Click CREATE AN ACCOUNT button");
		driver.findElement(createAccountBtn).click();

		System.out.println("	Step 05: Verify title của Register Page = Create New Customer Account");
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}

	@Test
	public void TC_03_Navigate_function() {

		System.out.println("TC 03 - Navigate function");

		System.out.println("	Step 01: Truy cập vào trang: http://live.techpanda.org/index.php/");
		driver.get("http://live.techpanda.org/index.php/");

		System.out.println("	Step 02: Click MY ACCOUNT link tại footer");
		driver.findElement(myAccountLnk).click();

		System.out.println("	Step 03: Click CREATE AN ACCOUNT button");
		driver.findElement(createAccountBtn).click();

		System.out.println(
				"	Step 04: Verify url của Register Page = http://live.techpanda.org/index.php/customer/account/create/");
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");

		System.out.println("	Step 05: Back lại trang Login Page");
		driver.navigate().back();

		System.out.println(
				"	Step 06: Verify url của Login Page = http://live.techpanda.org/index.php/customer/account/login/");
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");

		System.out.println("	Step 07: Forward tới trang Register Page");
		driver.navigate().forward();

		System.out.println("	Step 08: Verify title của Register Page = Create New Customer Account");
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}

	@Test
	public void TC_04_Get_Page_Source_Code() {

		System.out.println("TC 04 - Get Page Source Code");

		System.out.println("	Step 01: Truy cập vào trang: http://live.techpanda.org/index.php/");
		driver.get("http://live.techpanda.org/index.php/");

		System.out.println("	Step 02: Click MY ACCOUNT link tại footer");
		driver.findElement(myAccountLnk).click();

		System.out.println("	Step 03: Verify Login Page chứa text Login or Create an Account");
		String loginPageSource = driver.getPageSource();
		Assert.assertTrue(loginPageSource.contains("Login or Create an Account"));

		System.out.println("	Step 04: Click CREATE AN ACCOUNT button");
		driver.findElement(createAccountBtn).click();

		System.out.println("	Step 05: Verify Register Page chứa text Create an Account");
		String registerPageSource = driver.getPageSource();
		Assert.assertTrue(registerPageSource.contains("Create an Account"));
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
