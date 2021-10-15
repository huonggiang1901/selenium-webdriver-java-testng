package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Xpath_Css_I {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Login_with_empty_Email_and_Password() {

		System.out.println("TC 01 - Login with empty Email and Password");

		System.out.println("	Step 01 - Truy cập vào trang: http://live.guru99.com/");
		driver.get("http://live.guru99.com/");

		System.out.println("	Step 02 - Click vào link \"My Account\" để tới trang đăng nhập");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		System.out.println("	Step 03 - Để trống Username/Password");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");

		System.out.println("	Step 04 - Click Login button");
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		System.out.println("	Step 05 - Verify error message xuất hiện tại 2 field: This is a require field.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText(),
				"This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(),
				"This is a required field.");
	}

	@Test
	public void TC_02_Login_with_invalid_Email() {

		System.out.println("\nTC 02 - Login with invalid Email");

		System.out.println("	Step 01 - Truy cập vào trang: http://live.guru99.com/");
		driver.get("http://live.guru99.com/");

		System.out.println("	Step 02 - Click vào link \"My Account\" để tới trang đăng nhập");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		System.out.println("	Step 03 - Nhập email invalid: 123434234@12312.123123/ password valid: 123456");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123434234@12312.123123");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");

		System.out.println("	Step 04 - Click Login button");
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		System.out.println(
				"	Step 05 - Verify error messege xuất hiện: Please enter a valid email address. For example johndoe@domain.com.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText(),
				"Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void TC_03_Login_with_Password_less_than_6_characters() {

		System.out.println("\nTC 03 - Login with Password less than 6 characters");

		System.out.println("	Step 01 - Truy cập vào trang: http://live.guru99.com/");
		driver.get("http://live.guru99.com/");

		System.out.println("	Step 02 - Click vào link \"My Account\" để tới trang đăng nhập");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		System.out.println("	Step 03 - Nhập email correct and password invalid: automation@gmail.com/ 123");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");

		System.out.println("	Step 04 - Click Login button");
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		System.out.println(
				"	Step 05 - Verify error messege xuất hiện: Please enter 6 or more characters without leading or trailing spaces.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText(),
				"Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void TC_04_Login_with_incorrect_Email_and_Password() {

		System.out.println("\nTC 04 - Login with incorrect Email and Password");

		System.out.println("	Step 01 - Truy cập vào trang: http://live.guru99.com/");
		driver.get("http://live.guru99.com/");

		System.out.println("	Step 02 - Click vào link \"My Account\" để tới trang đăng nhập");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		System.out.println("	Step 03 - Nhập email correct and password incorrect: automation@gmail.com/ 123123123");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123123");

		System.out.println("	Step 04 - Click Login button");
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		System.out.println("	Step 05 - Verify error messege xuất hiện: Invalid login or password.");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),
				"Invalid login or password.");
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
