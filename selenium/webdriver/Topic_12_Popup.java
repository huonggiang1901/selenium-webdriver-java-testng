package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Popup {
	WebDriver driver;
	WebDriverWait expliciteWait;

	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void BeforeClass() {

		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		expliciteWait = new WebDriverWait(driver, 15);

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Fixed_Popup() {

		System.out.println("TC 01 - Fixed Popup");

		System.out.println("	Step 01: Truy cập vào trang: https://ngoaingu24h.vn/");
		driver.get("https://ngoaingu24h.vn/");

		System.out.println("	Step 02: Verify login popup is not displayed");
		WebElement loginPopup = driver
				.findElement(By.xpath("//div[@id='modal-login-v1']//div[@class='modal-content']"));
		Assert.assertFalse(loginPopup.isDisplayed());

		System.out.println("	Step 03 Click to Đăng nhập button");
		driver.findElement(By.xpath("//button[@class='login_ icon-before']")).click();
		expliciteWait.until(ExpectedConditions.visibilityOf(loginPopup));

		System.out.println("	Step 04: Verify login popup is displayed");
		Assert.assertTrue(loginPopup.isDisplayed());

		System.out.println("	Step 05: Nhập username/ password: automationfc/ automationfc");
		driver.findElement(By.xpath("//input[@id='account-input']")).sendKeys("automationfc");
		driver.findElement(By.xpath("//input[@id='password-input']")).sendKeys("automationfc");

		System.out.println("	Step 06: Click to Đăng nhập button");
		driver.findElement(By.xpath("//button[@class='btn-v1 btn-login-v1 buttonLoading']")).click();
		expliciteWait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//div[@id='modal-login-v1']//div[@class='row error-login-panel']"))));

		System.out.println("	Step 07: Verify message hiển thị: Tài khoản không tồn tại");
		Assert.assertEquals(driver
				.findElement(By.xpath("//div[@id='modal-login-v1']//div[@class='row error-login-panel']")).getText(),
				"Tài khoản không tồn tại!");
	}

	@Test
	public void TC_02_Testproject_Random_Popup_In_DOM() {

		System.out.println("\nTC 02 - Testproject Random Popup In DOM");

		System.out.println("	Step 01: Truy cập vào trang: https://blog.testproject.io/");
		driver.get("https://blog.testproject.io/");

		System.out.println(
				"	Step 02: Kiềm tra popup trong 2 trường hợp: Có xuất hiện - đóng popup - chuyển qua step 03/ Không xuất hiện - chuyển qua step 03 (Element của popup vẫn còn trong DOM");
		if (driver.findElement(By.xpath("//div[@class='mailch-wrap']")).isDisplayed()) {
			driver.findElement(By.xpath("//div[@id='close-mailch']")).click();
			Assert.assertFalse(driver.findElement(By.xpath("//div[@class='mailch-wrap']")).isDisplayed());
		}

		System.out.println("	Step 03: Nhập dữ liệu vào Search textbox với từ khóa Selenium");
		driver.findElement(By.xpath("//section[@id='search-2']//input[@class='search-field']")).sendKeys("Selenium");
		driver.findElement(By.xpath("//section[@id='search-2']//span[@class='glass']")).click();
		expliciteWait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//h2[@class='page-title']/span[text()='\"Selenium\":']"))));

		System.out.println("	Step 04: Kiểm tra các title có xuất hiện đều chứa từ khóa Selenium");
		List<WebElement> postTitles = driver.findElements(By.xpath("//h3[@class='post-title']//a"));

		for (WebElement postTitle : postTitles) {
			Assert.assertTrue(postTitle.getText().contains("Selenium"));
		}
	}

	@Test
	public void TC_05_Shopee_Random_Popup_Not_In_DOM() {

		System.out.println("\nTC 05 - Shopee Random Popup Not In DOM");

		System.out.println("	Step 01: Truy cập vào trang: https://shopee.vn/");
		driver.get("https://shopee.vn/");

		System.out.println(
				"	Step 02: Kiềm tra popup trong 2 trường hợp: Có xuất hiện - đóng popup - chuyển qua step 03/ Không xuất hiện - chuyển qua step 03 (Element của popup không còn trong DOM");
		List<WebElement> popup = driver.findElements(By.xpath("//div[@class='shopee-popup__container']"));
		if (popup.size() > 0) {
			driver.findElement(By.xpath("//div[@class='shopee-popup__close-btn']")).click();
		}

		System.out.println("	Step 03: Nhập tìm kiếm với từ khóa là Macbook Pro");
		driver.findElement(By.xpath("//input[@class='shopee-searchbar-input__input']")).sendKeys("Macbook Pro");
		driver.findElement(By.xpath("//button[@type='button']")).click();

		expliciteWait.until(ExpectedConditions.visibilityOf(driver.findElement(
				By.xpath("//div[@class='shopee-header-section__header__title']//span[text()='macbook pro']"))));

		System.out.println("	Step 04: Verify product name chứa từ khóa vừa search");
		List<WebElement> productNames = driver.findElements(By.xpath("//div[@class='_10Wbs- _5SSWfi UjjMrh']"));

		for (WebElement name : productNames) {
			Assert.assertTrue(name.getText().toUpperCase().contains("MACBOOK"));
			Assert.assertTrue(name.getText().toUpperCase().contains("PRO"));
		}
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
