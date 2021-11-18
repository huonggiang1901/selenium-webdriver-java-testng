package webdriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_17_Wait_VII_Fluent {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	FluentWait<WebDriver> fluentWaitDriver;
	FluentWait<WebElement> fluentWaitElement;

	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().window().maximize();
	}

	@Test
	public void TC_08_Fluent_Wait_Countdown() {
		System.out.println("TC 08 - Fluent Wait Countdown");

		System.out.println("	Step 01 - Truy cập vào trang: https://automationfc.github.io/fluent-wait/");
		driver.get("https://automationfc.github.io/fluent-wait/");

		System.out.println("	Step 02 - Wait cho đến khi countdown time được visible (visibility)");

		WebElement countdown = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
		fluentWaitElement = new FluentWait<WebElement>(countdown);

		fluentWaitElement.withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofMillis(500))
				.ignoring(NoSuchElementException.class).until(new Function<WebElement, Boolean>() {
					@Override
					public Boolean apply(WebElement element) {
						return element.isDisplayed();
					}
				});

		System.out.println(
				"	Step 03 - Sử dụng Fluent wait để: Mỗi 1/10s kiểm tra countdown = 00 được xuất hiện trên page hay chưa. Nếu thỏa mãn điều kiện là về tới số 00 rồi thì ko cần phải chờ hết timeout (tổng time = 15s)");

		fluentWaitElement.withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofMillis(100))
				.ignoring(NoSuchElementException.class).until(new Function<WebElement, Boolean>() {
					@Override
					public Boolean apply(WebElement element) {
						return element.getText().endsWith("00");
					}
				});
	}

	@Test
	public void TC_09_Fluent_Wait_Dynamic_loading() {
		System.out.println("\nTC 09 - Fluent Wait Dynamic loading");

		System.out.println("	Step 01 - Truy cập vào trang: https://automationfc.github.io/dynamic-loading/");
		driver.get("https://automationfc.github.io/dynamic-loading/");

		System.out.println("	Step 02 - Click the start button");
		driver.findElement(By.xpath("//div[@id='start']/button")).click();

		System.out.println(
				"	Step 03 - Apply fluent wait v=để mỗi 0.1s kiểm tra 1 lần xem Hello World text đã được hiển thị hay chưa");
		fluentWaitDriver = new FluentWait<WebDriver>(driver);

		fluentWaitDriver.withTimeout(Duration.ofSeconds(6)).pollingEvery(Duration.ofMillis(100))
				.ignoring(NoSuchElementException.class).until(new Function<WebDriver, Boolean>() {
					@Override
					public Boolean apply(WebDriver arg) {
						return driver.findElement(By.xpath("//div[@id='finish']/h4")).getText().equals("Hello World!");
					}
				});
	}

	@Test
	public void TC_10_Page_Ready_Ajax_Loading() {
		System.out.println("\nTC 10 - Page Ready Ajax Loading");

		System.out.println("	Step 01 - Truy cập vào trang: https://opensource-demo.orangehrmlive.com");
		driver.get("https://opensource-demo.orangehrmlive.com");

		System.out.println("	Step 02 - Login với thông tin hợp lệ: Admin/ admin123");
		getWebElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin");
		getWebElement(By.xpath("//input[@id='txtPassword']")).sendKeys("admin123");
		waitForElementAndCLick(By.xpath("//input[@id='btnLogin']"));

		System.out.println("	Step 03 - Verify text hiển thị tại màn hình Dashboard: 3 months");
		Assert.assertTrue(waitForElementAndDisplayed(By.xpath("//table[@class='table']//span")));

		System.out.println("	Step 04 - Vào màn hình PIM");
		waitForElementAndCLick(By.xpath("//a[@id='menu_pim_viewPimModule']"));

		System.out.println(
				"	Step 05 - Nhập dữ liệu vào field Employee Name và click search button: Employee Name: Peter Mac");
		getWebElement(By.xpath("//input[@id='empsearch_employee_name_empName']")).sendKeys("Peter Mac");
		waitForElementAndCLick(By.xpath("//input[@id='searchBtn']"));

		System.out.println("	Step 06 - Verify dữ liệu trả về đúng thông tin");
		Assert.assertTrue(waitForElementAndDisplayed(By.xpath("//a[text()='Peter Mac']")));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public WebElement getWebElement(By locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(15))
				.pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);

		WebElement element = wait.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver arg0) {
				return driver.findElement(locator);
			}
		});
		return element;
	}

	public void waitForElementAndCLick(By locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(15))
				.pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);

		WebElement element = wait.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver arg0) {
				return driver.findElement(locator);
			}
		});
		element.click();
	}

	public boolean waitForElementAndDisplayed(By locator) {
		WebElement element = getWebElement(locator);
		FluentWait<WebElement> wait = new FluentWait<WebElement>(element).withTimeout(Duration.ofSeconds(15))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);

		boolean isDisplayed = wait.until(new Function<WebElement, Boolean>() {
			@Override
			public Boolean apply(WebElement arg0) {
				return element.isDisplayed();
			}
		});
		return isDisplayed;
	}

	public boolean isJQueryLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		return explicitWait.until(jQueryLoad);
	}

	public boolean isJQueryAndAjaxIconLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}

			}
		};

		ExpectedCondition<Boolean> ajaxIconLoading = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return $('.raDiv').is('.visible')").toString().equals("false");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(ajaxIconLoading);
	}

	public boolean isJQueyAndPageLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}

			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
	
}
