package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Frame_IFrame {
	
	WebDriver driver;
	WebDriverWait expliciteWait;
	Select select;

	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		expliciteWait = new WebDriverWait(driver, 15);

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_06_Iframe() {

		System.out.println("TC 06 - Iframe");

		System.out.println("	Step 01: Truy cập vào trang: https://kyna.vn/");
		driver.get("https://kyna.vn/");

		System.out.println("	Step 02: Verify Facebook iframe hiển thị");
		WebElement facebookIframe = driver.findElement(By.xpath("//iframe[contains(@src,'www.facebook.com')]"));
		Assert.assertTrue(facebookIframe.isDisplayed());

		System.out.println("	Step 03: Verify số lượng like của Facebook page là 167K likes");
		driver.switchTo().frame(facebookIframe);
		Assert.assertEquals(
				driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText(),
				"167K lượt thích");

		System.out.println("	Step 04: Click vào chat iframe");
		driver.switchTo().defaultContent();

		WebElement chatIframe = driver.findElement(By.xpath("//iframe[@id='cs_chat_iframe']"));
		driver.switchTo().frame(chatIframe);

		driver.findElement(By.xpath("//div[@class='border_overlay meshim_widget_widgets_BorderOverlay']")).click();

		System.out.println("	Step 05: Nhập dữ liệu vào các field");

		driver.findElement(By.xpath("//input[@ng-model='login.username']")).sendKeys("John Wick");
		driver.findElement(By.xpath("//input[@ng-model='login.phone']")).sendKeys("0909444555");

		select = new Select(driver.findElement(By.xpath("//select[@id='serviceSelect']")));
		select.selectByVisibleText("TƯ VẤN TUYỂN SINH");

		driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("Java Bootcamp");

		driver.findElement(By.xpath("//input[@ng-click=\"sendOffline()\"]")).click();

		System.out.println("	Step 06: Verify dữ liệu được gửi thành công");
		Assert.assertEquals(driver
				.findElement(By.xpath("//div[@class='floater']//label[@class='logged_in_name ng-binding']")).getText(),
				"John Wick");
		Assert.assertEquals(driver
				.findElement(By.xpath("//div[@class='floater']//label[@class='logged_in_phone ng-binding']")).getText(),
				"0909444555");
		Assert.assertEquals(driver.findElement(By.xpath("//textarea[@name='message']")).getText(), "Java Bootcamp");

		System.out.println("	Step 07: Sendkey với từ khóa là \"Excel\" và click Search icon");
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//input[@id='live-search-bar']")).sendKeys("Excel");
		driver.findElement(By.xpath("//button[@aria-label='search']")).click();
		expliciteWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[text()=\"'Excel'\"]"))));

		System.out.println("	Step 08: Verify chuyển qua page danh sách khóa học chứa từ khóa Excel");
		List<WebElement> courses = driver.findElements(By.xpath("//div[@class='content']/h4"));

		for (WebElement course : courses) {
			Assert.assertTrue(course.getText().contains("Excel"));
		}
	}

	@Test
	public void TC_07_Frame() {

		System.out.println("\nTC 07 - Frame");

		System.out.println("	Step 01: Truy cập vào trang: https://netbanking.hdfcbank.com/netbanking/");
		driver.get("https://netbanking.hdfcbank.com/netbanking/");

		System.out.println("	Step 02: Input vào Customer ID và click Continue");
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='login_page']")));
		driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("0909009009");
		driver.findElement(By.xpath("//a[@class='btn btn-primary login-btn']")).click();

		System.out.println("	Step 03: Verify Password textbox hiển thị");
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='fldPasswordDispId']")).isDisplayed());

		System.out.println("	Step 04: Click vào Terms and Conditions dưới footer");
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='login_page']")));
		driver.findElement(By.xpath("//div[@class='footer-btm']//a[text()='Terms and Conditions']")).click();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}
