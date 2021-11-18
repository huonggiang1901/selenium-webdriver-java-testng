package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Upload_Files_II {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	String chromeUploadOneTime = projectPath + "\\autoIT\\chromeUploadOneTime.exe";
	String firefoxUploadOneTime = projectPath + "\\autoIT\\firefoxUploadOneTime.exe";

	String image01 = "Image01.jpg";
	String image01Path = projectPath + "\\uploadFiles\\" + image01;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_02_Upload_file_by_AutoIT() throws IOException {

		System.out.println("TC 02 - Upload files by AutoIT");

		System.out.println("	Step 01: Truy cập vào trang: https://blueimp.github.io/jQuery-File-Upload/");
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		System.out.println("	Step 02: Sử dụng AutoIT để upload file chạy cho 2 trình duyệt (Firefox/ Chrome)");
		driver.findElement(By.xpath("//span[@class='btn btn-success fileinput-button']")).click();

		Runtime.getRuntime().exec(new String[] { chromeUploadOneTime, image01Path });

		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + image01 + "']")).isDisplayed());

		driver.findElement(By.xpath("//tbody//button[@class='btn btn-primary start']")).click();

		System.out.println("	Step 03: Kiểm tra file đã được tải lên thành công");
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + image01 + "']")).isDisplayed());
	}

	@Test
	public void TC_03_Upload_file_by_Robot_class() throws AWTException {

		System.out.println("\nTC 03 - Upload files by Robot class");

		System.out.println("	Step 01: Truy cập vào trang: https://blueimp.github.io/jQuery-File-Upload/");
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		System.out.println("	Step 02: Sử dụng Robot để upload file chạy cho 2 trình duyệt (Firefox/ Chrome)");
		driver.findElement(By.xpath("//span[@class='btn btn-success fileinput-button']")).click();

		// Specify the file location with extension
		StringSelection select = new StringSelection(image01Path);

		// Copy to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

		Robot robot = new Robot();
		sleepInSeconds(1);

		// Nhan phim enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		// Nhan xuong Ctrl - V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		// Nha Ctrl - V
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		sleepInSeconds(1);

		// Nhan Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		driver.findElement(By.xpath("//tbody//button[@class='btn btn-primary start']")).click();

		System.out.println("	Step 03: Kiểm tra file đã được tải lên thành công");
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + image01 + "']")).isDisplayed());

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
	
}
