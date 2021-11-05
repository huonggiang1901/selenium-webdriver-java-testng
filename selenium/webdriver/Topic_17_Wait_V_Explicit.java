package webdriver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_V_Explicit {
	WebDriver driver;
	WebDriverWait explicitWait;
	Calendar calendar = Calendar.getInstance();;
	JavascriptExecutor js;

	String projectPath = System.getProperty("user.dir");

	By acceptCokkiesBtn = By.xpath("//button[@id='onetrust-accept-btn-handler']");

	@BeforeClass
	public void BeforeClass() {

		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		js = (JavascriptExecutor) driver;

		explicitWait = new WebDriverWait(driver, 15);

		driver.manage().window().maximize();
	}

	@Test
	public void TC_04_Explicit_Wait_Invisibility() {

		System.out.println("TC 04 - Explicit Wait Invisibility");

		System.out.println("	Step 01: Truy cập vào trang: https://automationfc.github.io/dynamic-loading/");
		driver.get("https://automationfc.github.io/dynamic-loading/");

		System.out.println("	Step 02: Click Start button");
		driver.findElement(By.xpath("//div[@id='start']/button")).click();

		System.out.println("	Step 03: Wait loading icon invisible");
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));

		System.out.println("	Step 04: Check result text is \"Hello World!\"");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
	}

	@Test
	public void TC_05_Explicit_Wait_Visibility() {

		System.out.println("\nTC 05 - Explicit Wait Visibility");

		System.out.println("	Step 01: Truy cập vào trang: https://automationfc.github.io/dynamic-loading/");
		driver.get("https://automationfc.github.io/dynamic-loading/");

		System.out.println("	Step 02: Click Start button");
		driver.findElement(By.xpath("//div[@id='start']/button")).click();

		System.out.println("	Step 03: Wait Hello World visible");
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='finish']/h4")));

		System.out.println("	Step 04: Check result text is \"Hello World!\"");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
	}

	@Test
	public void TC_06_explicitWait() {

		System.out.println("\nTC 06 - explicitWait");

		System.out.println(
				"	Step 01: Truy cập vào trang: https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		driver.get(
				"https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		clickAcceptCookiesBtn(acceptCokkiesBtn);

		System.out.println("	Step 02: Wait cho \"Date Time Picker\" được hiển thị");
		explicitWait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='calendarContainer']")));
		scrollIntoView(driver.findElement(By.xpath("//div[@class='calendarContainer']")));

		System.out.println(
				"	Step 03: In ra ngày đã chọn (Before AJAX call) -> hiện tại chưa chọn nên in ra = \"No Selected Dates to display.\"");
		System.out.println(
				"--> Selected Dates: " + driver.findElement(By.xpath("//div[@class='RadAjaxPanel']/span")).getText());

		System.out.println("	Step 04: Chọn ngày hiện tại");
		driver.findElement(By.xpath("//a[text()='" + String.valueOf(getCurrentDate()) + "']")).click();

		System.out.println("	Step 05: Wait cho đến khi \"Ajax loading icon\" không còn visible");
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));

		System.out.println("	Step 06: Wait cho selected date được selected");
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.xpath("//td[@class='rcSelected rcHover']/a[text()='" + String.valueOf(getCurrentDate()) + "']")));

		System.out.println("	Step 07: Verify ngày đã chọn");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='RadAjaxPanel']/span")).getText(),
				getToday("EEEE, MMMM d, yyyy"));

	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}

	public void sleepInSeconds(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void clickAcceptCookiesBtn(By acceptCookies) {
		sleepInSeconds(2);
		List<WebElement> acceptCookiesBtn = driver.findElements(acceptCookies);
		if (acceptCookiesBtn.size() > 0) {
			driver.findElement(acceptCookies).click();
			explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(acceptCookies));
		}
	}

	public int getCurrentDate() {
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public String getToday(String format) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	public void scrollIntoView(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView(false);", element);
	}

}
