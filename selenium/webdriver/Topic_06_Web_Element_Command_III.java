package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_Command_III {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	JavascriptExecutor js;

	By emailTextbox = By.xpath("//input[@id='email']");
	By userNameTextbox = By.xpath("//input[@id='new_username']");
	By passwordTextbox = By.xpath("//input[@id='new_password']");
	By signUpBtn = By.xpath("//button[@id='create-account']");
	By newsLetterCheckbox = By.xpath("//input[@id='marketing_newsletter']");

	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		js = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_04_Register_function_at_MailChimp() {

		System.out.println("TC 04 - Register function at MailChimp");

		System.out.println("	Step 01: Truy cập vào trang: https://login.mailchimp.com/signup/");
		driver.get("https://login.mailchimp.com/signup/");

		System.out.println("	Step 02: Nhập dữ liệu vào 2 trường: Email/ Username");
		sendKeysToElement(emailTextbox, "automation@gmail.net");
		sendKeysToElement(userNameTextbox, "Automation");

		System.out.println(
				"	Step 03: Nhập dữ liệu với các tiêu chí khác nhau để kiểm tra cách validate của trường Password (các dòng text sẽ bị ẩn khi 1 tham số được nhập hợp lệ)");
		System.out.println("	Step 04: Kiểm tra Sign Up button bị disable nếu Password ko hợp lệ");
		// Nhập số
		sendKeysToElement(passwordTextbox, "123");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(signUpBtn).isEnabled());

		// Nhập chữ thường
		sendKeysToElement(passwordTextbox, "abc");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(signUpBtn).isEnabled());

		// Nhập chữ hoa
		sendKeysToElement(passwordTextbox, "ABC");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(signUpBtn).isEnabled());

		// Nhập kí tự đặc biệt
		sendKeysToElement(passwordTextbox, "!@#");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(signUpBtn).isEnabled());

		// Lớn hơn 8 kí tự
		sendKeysToElement(passwordTextbox, "❄️❄️❄️❄️❄️❄️❄️❄️❄️");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(signUpBtn).isEnabled());

		// Full valid data
		sendKeysToElement(passwordTextbox, "Abcd/1234");
		Assert.assertTrue(driver.findElement(signUpBtn).isEnabled());

		System.out.println("	Step 05: Kiểm tra checkbox được chọn sau khi click chọn thành công");
		clickElement(newsLetterCheckbox);
		Assert.assertTrue(driver.findElement(newsLetterCheckbox).isSelected());
	}

	public void sendKeysToElement(By locator, String value) {
		WebElement element = driver.findElement(locator);
		element.clear();
		element.sendKeys(value);
	}

	public void sleepInSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void clickElement(By locator) {
		WebElement element = driver.findElement(locator);
		js.executeScript("arguments[0].scrollIntoView();", element);
		element.click();
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
