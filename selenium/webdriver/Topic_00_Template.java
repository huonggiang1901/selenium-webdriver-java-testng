package webdriver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_00_Template {
	
	WebDriver driver;
	JavascriptExecutor js;
	Calendar calendar;
	WebDriverWait explicitWait;

	String projectPath = System.getProperty("user.dir");
	
	
	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		explicitWait = new WebDriverWait(driver, 15);
		
		js = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC() {
		WebElement element = driver.findElement(By.xpath(""));
		js.executeScript("arguments[0].scrollIntoView();", element);
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

	public String getStringCurrentDateAndTime() {
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyHH:mm:ss");
		return dateFormat.format(currentDate).toString().replace(":", "");
	}
	
	public int getCurrentDay() {
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	public WebElement scrollToTop(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		return element;
	}
	
	public boolean isElementDisplayed(By locator) {
		if (driver.findElement(locator).isDisplayed()) {
			System.out.println(locator + " is displayed ");
			return true;
		} else
			System.out.println(locator + " is not displayed ");
		return false;
	}

	public boolean isElementEnabled(By locator) {
		if (driver.findElement(locator).isEnabled()) {
			System.out.println(locator + " is enabled ");
			return true;
		} else
			System.out.println(locator + " is disabled ");
		return false;
	}

	public boolean isElementSelected(By locator) {
		if (driver.findElement(locator).isSelected()) {
			System.out.println(locator + " is selected ");
			return true;
		} else
			System.out.println(locator + " is deselected ");
		return false;
	}

	public void sendKeysToElement(By locator, String value) {
		WebElement element = driver.findElement(locator);
		element.clear();
		element.sendKeys(value);
	}

	public void clickElement(By locator) {
		driver.findElement(locator).click();
	}
	
	public String formatDateFromString(String date, String oldFormat, String newFormat) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(oldFormat);
		Date d = sdf.parse(date);
		sdf.applyPattern(newFormat);		
		String newDateString = sdf.format(d);
		return newDateString;
	}
	
	public void clickAcceptCookiesBtn(By acceptCookies) {
		List<WebElement> acceptCookiesBtn = driver.findElements(acceptCookies);
		if (acceptCookiesBtn.size() > 0) {
			driver.findElement(acceptCookies).click();
			explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(acceptCookies));
		}
	}
	
}
