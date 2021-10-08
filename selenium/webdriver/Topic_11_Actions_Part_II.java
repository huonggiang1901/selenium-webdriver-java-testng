package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Actions_Part_II {
	WebDriver driver;
	JavascriptExecutor js;
	Actions action;

	String projectPath = System.getProperty("user.dir");

	String jsHelperPath = projectPath + "\\dragAndDrop\\drag_and_drop_helper.js";
//	String jQueryHelperPath = projectPath + "\\dragAndDrop\\jquery_load_helper.js";

	@BeforeClass
	public void BeforeClass() {

		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		js = (JavascriptExecutor) driver;

		action = new Actions(driver);

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_07_Right_click_to_element() {

		System.out.println("TC 07 - Right click to element");

		System.out.println("	Step 01: Truy cập vào trang: http://swisnl.github.io/jQuery-contextMenu/demo.html");
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

		System.out.println("	Step 02: Right click vào element: right click me");
		action.contextClick(driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']")))
				.perform();

		System.out.println("	Step 03: Hover chuột vào element: Quit");
		action.moveToElement(driver
				.findElement(By.xpath("//li[@class='context-menu-item context-menu-icon context-menu-icon-quit']")))
				.perform();

		System.out.println("	Step 04: Kiểm tra Quit menu hiển thị");
		System.out.println("	Step 05: Verify element Quit (visible + hover) với xpath");
		Assert.assertTrue(driver.findElement(By.xpath(
				"//li[@class='context-menu-item context-menu-icon context-menu-icon-quit context-menu-hover context-menu-visible']"))
				.isDisplayed());

		System.out.println("	Step 06: Click chọn Quit");
		action.click(driver.findElement(By.xpath(
				"//li[@class='context-menu-item context-menu-icon context-menu-icon-quit context-menu-hover context-menu-visible']")))
				.perform();
		driver.switchTo().alert().accept();

		System.out.println("	Step 07: Kiểm tra Quit menu không có hiển thị");
		Assert.assertFalse(driver
				.findElement(By.xpath("//li[@class='context-menu-item context-menu-icon context-menu-icon-quit']"))
				.isDisplayed());
	}

	@Test
	public void TC_08_Drag_and_drop_element_HTML4() {

		System.out.println("\nTC 08 - Drag and drop element HTML4");

		System.out.println("	Step 01: Truy cập vào trang: https://automationfc.github.io/kendo-drag-drop/");
		driver.get("https://automationfc.github.io/kendo-drag-drop/");

		System.out.println("	Step 02: Kéo hình tròn nhỏ vào hình tròn lớn");
		WebElement smallCircle = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement bigCircle = driver.findElement(By.xpath("//div[@id='droptarget']"));

		action.dragAndDrop(smallCircle, bigCircle).perform();

		System.out.println("	Step 03: Verify message đã thay đổi: You did great!");
		Assert.assertEquals(bigCircle.getText(), "You did great!");

		System.out.println("	Step 04: Verify background color của hình tròn lớn chuyển qua xanh da trời");
		Assert.assertEquals(Color.fromString(bigCircle.getCssValue("background-color")).asHex(), "#03a9f4");
	}

	@Test
	public void TC_09_Drag_and_drop_element_HTML5() throws IOException {

		System.out.println("\nTC 09 - Drag and drop element HTML5");

		System.out.println("	Step 01: Truy cập vào trang: https://automationfc.github.io/drag-drop-html5/");
		driver.get("https://automationfc.github.io/drag-drop-html5/");

		System.out.println("	Step 02: Sử dụng Javascript/ JQuery để kéo thả từ A qua B và ngược lại");
		String jsHelperFileContent = getContentFile(jsHelperPath);

		String sourceCss = "#column-a";
		String targetCss = "#column-b";

		// A to B
		jsHelperFileContent = jsHelperFileContent + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \""
				+ targetCss + "\"});";
		js.executeScript(jsHelperFileContent);
		sleepInSeconds(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='A']")).isDisplayed());

		// B to A
		js.executeScript(jsHelperFileContent);
		sleepInSeconds(3);

		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='A']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='B']")).isDisplayed());
	}

//	@Test
	public void TC_10_DragDrop_HTML5_Offset() throws InterruptedException, IOException, AWTException {

		System.out.println("\nTC 10 - Drag and drop element HTML5 Offset");

		System.out.println("	Step 01: Truy cập vào trang: https://automationfc.github.io/drag-drop-html5/");
		driver.get("https://automationfc.github.io/drag-drop-html5/");

		System.out.println("	Step 02: Kéo thả từ A qua B và ngược lại");

		String sourceXpath = "//div[@id='column-a']";
		String targetXpath = "//div[@id='column-b']";

		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		sleepInSeconds(3);

		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='A']")).isDisplayed());

		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		sleepInSeconds(3);

		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='A']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='B']")).isDisplayed());
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}

	public void sleepInSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getContentFile(String filePath) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(filePath);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}

	public void drag_the_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x,
				((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

}
