package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Selenium_Locator {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://live.demoguru99.com/index.php/customer/account/login");
	}

	// @Test
	public void TC_01_FindElement() {

		// Single element: WebElement
		driver.findElement(By.className("")).click();

		driver.findElement(By.className("")).getText();

		// findElement: tìm element
		// By.xxx: vs locator nào
		// Action gì lên element đó: click()/ sendKeys()/ getText()/ ...

		// Multiple element: List<WebElement>
		List<WebElement> buttons = driver.findElements(By.className(""));
		buttons.get(0).click();
	}

	@Test
	public void TC_02_ID() {

		// Selenium Locator
		driver.findElement(By.id("send2")).click();

		// Verify email error message xuất hiện
		Assert.assertTrue(driver.findElement(By.id("advice-required-entry-email")).isDisplayed());
	}

	@Test
	public void TC_03_Class() {
		driver.navigate().refresh();

		driver.findElement(By.className("validate-password")).sendKeys("123456789");
	}

	@Test
	public void TC_04_Name() {
		driver.navigate().refresh();

		driver.findElement(By.id("send2")).click();

		// Verify email error message xuất hiện
		Assert.assertTrue(driver.findElement(By.id("advice-required-entry-email")).isDisplayed());
	}

	@Test
	public void TC_05_Tagname() {
		driver.navigate().refresh();

		// Hiển thị hết tất cả đường link tại màn hình nay sau đó getText() ra
		List<WebElement> loginPageLinks = driver.findElements(By.tagName("a"));

		for (WebElement webElement : loginPageLinks) {
			System.out.println(webElement.getText());
		}
	}

	@Test
	public void TC_06_LinkText() {
		driver.navigate().refresh();

		driver.findElement(By.linkText("Forgot Your Password?")).click();

		Assert.assertTrue(driver.findElement(By.id("email_address")).isDisplayed());
	}

	@Test
	public void TC_07_PartialLinkText() {
		driver.findElement(By.partialLinkText("Back to")).click();

		Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed());
	}

	@Test
	public void TC_08_Css() {
		driver.findElement(By.cssSelector("#email")).sendKeys("giang@gmail.com");
		driver.findElement(By.cssSelector("input[name='login[password]']")).sendKeys("12345");
	}

	@Test
	public void TC_09_Xpath() {
		driver.navigate().refresh();

		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("giang@gmail.com");
		driver.findElement(By.xpath("//label[contains(text(),'Password')]/following-sibling::div/input"))
				.sendKeys("12345");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}
