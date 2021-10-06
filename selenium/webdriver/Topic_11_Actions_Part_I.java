package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Actions_Part_I {
	WebDriver driver;
	Actions action;
	JavascriptExecutor js;

	String projectPath = System.getProperty("user.dir");

	String selectedLnk = "Home & Bath";
	String navigatedPageName = "Kids Home Bath";

	String selectedBookMenu = "Sách Trong Nước";

	@BeforeClass
	public void BeforeClass() {

		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);

		js = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Hover_to_element_Tooltip() {

		System.out.println("TC 01 - Hover to element Tooltip");

		System.out.println("	Step 01: Try cập vào trang: https://automationfc.github.io/jquery-tooltip/");
		driver.get("https://automationfc.github.io/jquery-tooltip/");

		System.out.println("	Step 02: Hover chuột vào textbox");
		action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();

		System.out.println("	Step 03: Kiểm tra tooltip xuất hiện");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).isDisplayed());
	}

	@Test
	public void TC_02_Hover_to_element_Myntra_Page() {

		System.out.println("\nTC 02 - Hover to element Myntra Page");

		System.out.println("	Step 01: Truy cập vào trang: http://www.myntra.com/");
		driver.get("http://www.myntra.com/");

		System.out.println("	Step 02: Hover chuột vào KIDS");
		action.moveToElement(driver.findElement(By.xpath("//a[@data-group='kids']"))).perform();

		System.out.println("	Step 03: Click chọn Home & Bath hoặc bất kì page nào");
		driver.findElement(By.xpath("//a[text()='" + selectedLnk + "']")).click();

		System.out.println("	Step 04: Verify đã chuyển page sau khi click thành công");
		Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='title-title']")).getText(), navigatedPageName);
	}

	@Test
	public void TC_03_Hover_to_element_Fahasa_Page() {

		System.out.println("\nTC 03 - Hover to element Fahasa Page");

		System.out.println("	Step 01: Truy cập vào trang: https://www.fahasa.com/");
		driver.get("https://www.fahasa.com/");

		System.out.println("	Step 02: Hover chuột vào bất kì trang nào trên Menu (trái)");
		action.moveToElement(
				driver.findElement(By.xpath("//div[@class='container']//a[@title='" + selectedBookMenu + "']")))
				.perform();

		System.out.println("	Step 03: Kiểm tra các trang (sub-menu) xuất hiện sau khi hover thành công");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='container']//a[@title='" + selectedBookMenu
				+ "']/following-sibling::div[@class='dropdown-menu']")).isDisplayed());
	}

	@Test
	public void TC_04_Click_and_hold_element_select_multiple_items() {

		System.out.println("\nTC 04 - Click and hold element select multiple items");

		System.out.println("	Step 01: Truy cập vào trang: https://automationfc.github.io/jquery-selectable/");
		driver.get("https://automationfc.github.io/jquery-selectable/");

		System.out.println("	Step 02: Click and hold từ 1 -> 4");
		List<WebElement> allNumbers = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee']"));
		action.clickAndHold(allNumbers.get(0)).moveToElement(allNumbers.get(3)).release().perform();

		System.out.println("	Step 03: Sau khi chọn - kiểm tra có đúng 4 phần tử đã được chọn thành công");
		Assert.assertEquals(
				driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']")).size(), 4);
	}

	@Test
	public void TC_05_Click_and_hold_element_select_random_items() {

		System.out.println("\nTC 05 - Click and hold element select random items");

		System.out.println("	Step 01: Truy cập vào trang: https://automationfc.github.io/jquery-selectable/");
		driver.get("https://automationfc.github.io/jquery-selectable/");

		System.out.println("	Step 02: Click and select random item: 1 - 3 - 6 - 11");
		List<WebElement> allNumbers = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee']"));
		action.keyDown(Keys.CONTROL).click(allNumbers.get(0)).click(allNumbers.get(2)).click(allNumbers.get(5))
				.click(allNumbers.get(10)).keyUp(Keys.CONTROL).perform();

		System.out
				.println("	Step 03: Sau khi chọn kiểm tra rằng có đúng 4 phần tử đã được chọn thành công với xpath");
		Assert.assertEquals(
				driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']")).size(), 4);
	}

	@Test
	public void TC_06_Double_click() {

		System.out.println("\nTC 06 - Double click");

		System.out.println("	Step 01: Truy cập vào trang: https://automationfc.github.io/basic-form/index.html");
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		System.out.println("	Step 02: Double click vào button: Double click me");
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[@ondblclick='doubleClickMe()']")));
		action.doubleClick(driver.findElement(By.xpath("//button[@ondblclick='doubleClickMe()']"))).perform();

		System.out.println("	Step 03: Verify text được hiển thị bên dưới: Hello Automation Guys!");
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
