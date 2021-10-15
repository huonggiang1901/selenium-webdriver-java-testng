package webdriver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Xpath_Css_III {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	By myAccountLnk = By.xpath("//div[@class='footer']//a[@title='My Account']");
	By firstNameTextbox = By.xpath("//input[@id='firstname']");
	By lastNameTextbox = By.xpath("//input[@id='lastname']");
	By emailTextbox = By.xpath("//input[@id='email_address']");
	By passwordTextbox = By.xpath("//input[@id='password']");
	By confirmPasswordTextbox = By.xpath("//input[@id='confirmation']");
	By registerButton = By.xpath("//button[@title='Register']");
	By contactInfo = By
			.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p");

	String firstName = "Automation";
	String lastName = "Testing";
	String fullName = firstName + " " + lastName;
	String email = "automationtest" + getCurrentDateAndTime() + "@gmail.net";
	String password = "123456";

	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_05_Create_a_new_account() {

		System.out.println("TC 05 - Create a new account");

		System.out.println("	Step 01: Truy cập vào trang: http://live.guru99.com/");
		driver.get("http://live.guru99.com/");

		System.out.println("	Step 02: Click vào link \"My Account\" để tới trang đăng nhập");
		driver.findElement(myAccountLnk).click();

		System.out.println("	Step 03: Click CREATE AN ACCOUNT button để tớ trang đăng kí tào khoản");
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		System.out.println(
				"	Step 04: Nhập thông tin hợp lệ vào tất cả các field: First Name/ Last Name/ Email Address/ Password/ Confirm Password");
		driver.findElement(firstNameTextbox).sendKeys(firstName);
		driver.findElement(lastNameTextbox).sendKeys(lastName);
		driver.findElement(emailTextbox).sendKeys(email);
		System.out.println(email);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(confirmPasswordTextbox).sendKeys(password);

		System.out.println("	Step 05: Click REGISTER button");
		driver.findElement(registerButton).click();

		System.out.println(
				"	Step 06: Verify message xuất hiện khi đăng kí thành công: Thank you for registering with Main Website Store.");
		Assert.assertTrue(driver.findElement(By
				.xpath("//li[@class='success-msg']//span[text()='Thank you for registering with Main Website Store.']"))
				.isDisplayed());

		System.out.println(
				"	Step 07: Verify User được tạo mới với thông tin: Firstname/ Lastname/ Email hiển thị ở trang My Dashboard");
		// Cách 1
		String info = driver.findElement(contactInfo).getText();
		Assert.assertTrue(info.contains(fullName));
		Assert.assertTrue(info.contains(email));

		// Cách 2
		Assert.assertTrue(driver.findElement(By.xpath(
				"//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p[contains(text(),'"
						+ fullName + "')]"))
				.isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath(
				"//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p[contains(string(),'"
						+ email + "')]"))
				.isDisplayed());

		System.out.println("	Step 08: Logout khỏi hệ thống");
		driver.findElement(By.xpath("//a/span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		sleepInSeconds(6);

		System.out.println("	Step 09: Kiểm tra hệ thống navigate về Home page sau khi logout thành công");
		Assert.assertEquals(driver.getTitle(), "Home page");

	}

	@Test
	public void TC_06_Login_with_valid_Email_and_Password() {

		System.out.println("\nTC 06 - Login with valid Email and Password");

		System.out.println("	Step 01: Truy cập vào trang: http://live.guru99.com/");
		driver.get("http://live.guru99.com/");

		System.out.println("	Step 02: Click vào link \"My Account\" để tới trang đăng nhập");
		driver.findElement(myAccountLnk).click();

		System.out.println("	Step 03: Login với thông tin đã đăng kí ở testcase số 5 trên");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(password);

		System.out.println("	Step 04: Click Login button");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		sleepInSeconds(1);

		System.out.println("	Step 05: Verify các thông tin sau được hiển thị:");
		System.out.println("- My dashboard");
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());

		System.out.println("- Hello, " + fullName);
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Hello, " + fullName + "!']")).isDisplayed());

		System.out.println("- User full name");
		String info = driver.findElement(contactInfo).getText();
		Assert.assertTrue(info.contains(fullName));

		System.out.println("- Email");
		Assert.assertTrue(info.contains(email));
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

	public String getCurrentDateAndTime() {
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyHH:mm:ss");
		return dateFormat.format(currentDate).toString().replace(":", "");
	}

}
