package webdriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Alert {
	
	WebDriver driver;
	WebDriverWait expliciteWait;
	Alert alert;

	String projectPath = System.getProperty("user.dir");

	String autenChrome = projectPath + "\\autoIT\\authen_chrome.exe";
	String autenFirefox = projectPath + "\\autoIT\\authen_firefox.exe";

	String fillPromtAlert = "daominhdam";

	String username = "admin";
	String password = "admin";

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		expliciteWait = new WebDriverWait(driver, 15);

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_05_Accept_Alert() {

		System.out.println("TC 05 - Accept Alert");

		System.out.println("	Step 01: Truy cập vào trang: https://automationfc.github.io/basic-form/index.html");
		driver.get("https://automationfc.github.io/basic-form/index.html");

		System.out.println("	Step 02: Click vào button: Click for JS Alert");
		driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();
		alert = expliciteWait.until(ExpectedConditions.alertIsPresent());

		System.out.println("	Step 03: Verify message hiển thị trong alert là: I am a JS Alert");
		Assert.assertEquals(alert.getText(), "I am a JS Alert");

		System.out.println(
				"	Step 04: Accept alert và verify message hiển thị tại Result là: You clicked an alert successfully");
		alert.accept();

		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),
				"You clicked an alert successfully");
	}

	@Test
	public void TC_06_Confirm_Alert() {
		System.out.println("\nTC 06 - Confirm Alert");

		System.out.println("	Step 01: Truy cập vào trang: https://automationfc.github.io/basic-form/index.html");
		driver.get("https://automationfc.github.io/basic-form/index.html");

		System.out.println("	Step 02: Click vào button: Click for JS Confirm");
		driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();
		alert = expliciteWait.until(ExpectedConditions.alertIsPresent());

		System.out.println("	Step 03: Verify message hiển thị trong alert là: I am a JS Confirm");
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");

		System.out.println(
				"	Step 04: Accept alert và verify message hiển thị tại Result là: You clicked an alert successfully");
		alert.dismiss();

		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Cancel");
	}

	@Test
	public void TC_07_Prompt_Alert() {

		System.out.println("\nTC 07 - Prompt Alert");

		System.out.println("	Step 01: Truy cập vào trang: https://automationfc.github.io/basic-form/index.html");
		driver.get("https://automationfc.github.io/basic-form/index.html");

		System.out.println("	Step 02: Click vào button: Click for JS Prompt");
		driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();
		alert = expliciteWait.until(ExpectedConditions.alertIsPresent());

		System.out.println("	Step 03: Verify message hiển thị trong alert là: I am a JS prompt");
		Assert.assertEquals(alert.getText(), "I am a JS prompt");

		System.out.println(
				"	Step 04: Nhập vào text bất kì 9daominhdam) và veify message hiển thị tại Result là: You entered: daominhdam");
		alert.sendKeys(fillPromtAlert);
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),
				"You entered: " + fillPromtAlert);
	}

	@Test
	public void TC_08_Authentication_Alert_Sử_dụng_by_pass_qua_link() {

		System.out.println("TC 08 - Authentication Alert Sử dụng by pass qua link");

		System.out.println("	Step 01: Truy cập vào trang: http://the-internet.herokuapp.com/basic_auth");
		System.out.println("	Step 02: Handle autentication alert vs user/pass: admin/ admin");
		driver.get("http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth");

		System.out.println(
				"	Step 03: Verify message hiển thị sau khi login thành công: Congratulations! You must have the proper credential");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='example']/p")).getText().trim(),
				"Congratulations! You must have the proper credentials.");
	}

	@Test
	public void TC_09_Authentication_Alert_Sử_dụng_AutoIT() throws IOException {

		System.out.println("TC 09 - Authentication Alert Sử dụng AutoIT");

		System.out.println("	Step 01: Truy cập vào trang: http://the-internet.herokuapp.com/basic_auth");
		System.out.println("	Step 02: Handle autentication alert vs user/pass: admin/ admin");
		Runtime.getRuntime().exec(new String[] { autenChrome, username, password });

		driver.get("http://the-internet.herokuapp.com/basic_auth");

		System.out.println(
				"	Step 03: Verify message hiển thị sau khi login thành công: Congratulations! You must have the proper credential");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='example']/p")).getText().trim(),
				"Congratulations! You must have the proper credentials.");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}
