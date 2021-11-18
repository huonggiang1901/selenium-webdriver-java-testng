package webdriver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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

	String image01 = "Image01.jpg";
	String image02 = "Image02.jpg";
	String image03 = "Image03.jpg";
	String image01Path = projectPath + "\\uploadFiles\\" + image01;
	String image02Path = projectPath + "\\uploadFiles\\" + image02;
	String image03Path = projectPath + "\\uploadFiles\\" + image03;

	By acceptCokkiesBtn = By.xpath("//button[@id='onetrust-accept-btn-handler']");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		js = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 30);

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
		scrollToBottom(driver.findElement(By.xpath("//div[@class='calendarContainer']")));

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

	@Test
	public void TC_07_explicitWait_Upload_file() {

		System.out.println("\nTC 07 - Explicit Wait Upload file");

		System.out.println("	Step 01: Open URL: https://gofile.io/?t=uploadFiles");
		driver.get("https://gofile.io/?t=uploadFiles");
		String parentWindow = driver.getWindowHandle();

		System.out.println("	Step 02: Upload các file và verify file đã được load lên thành công");

		explicitWait.until((ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//button[@class='btn btn-primary btn-lg mb-1 uploadButton']"))));
		driver.findElement(By.xpath("//input[@type='file']"))
				.sendKeys(image01Path + "\n" + image02Path + "\n" + image03Path);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(
				driver.findElements(By.xpath("//div[@class='progress position-relative mt-1']"))));
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='text-center']/i")));

		explicitWait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='callout callout-success']/h5")));

		Assert.assertTrue(driver.findElement(By.xpath("//h5[text()='Your files have been successfully uploaded']"))
				.isDisplayed());

		System.out.println("	Step 03: Click vào Download link");
		driver.findElement(By.xpath("//a[@id='rowUploadSuccess-downloadPage']")).click();

		System.out.println("	Step 04: Chuyển qua Tab/ Window mới - kiểm tra các filename hiển thị");

		Set<String> allWindows = driver.getWindowHandles();
		for (String child : allWindows) {
			if (!child.equals(parentWindow))
				driver.switchTo().window(child);
		}

		explicitWait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='contentName']")));

		Assert.assertTrue(driver.findElement(By.xpath("//span[text() = '" + image01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text() = '" + image02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text() = '" + image03 + "']")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
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

	public WebElement scrollToBottom(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView(false);", element);
		return element;
	}

}
