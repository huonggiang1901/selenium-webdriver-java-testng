package webdriver;

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

public class Topic_16_Upload_Files_I {
	WebDriver driver;
	JavascriptExecutor js;
	WebDriverWait expliciteWait;

	String projectPath = System.getProperty("user.dir");

	String image01 = "Image01.jpg";
	String image02 = "Image02.jpg";
	String image03 = "Image03.jpg";
	String image01Path = projectPath + "\\uploadFiles\\" + image01;
	String image02Path = projectPath + "\\uploadFiles\\" + image02;
	String image03Path = projectPath + "\\uploadFiles\\" + image03;

	@BeforeClass
	public void BeforeClass() {

		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		expliciteWait = new WebDriverWait(driver, 15);

		js = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Upload_file_by_sendkeys() {

		System.out.println("TC 01 - Upload file by sendkeys");

		System.out.println("	Step 01: Truy cập vào trang: https://blueimp.github.io/jQuery-File-Upload/");
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		System.out.println(
				"	Step 02: Sử dụng phương thức sendKeys để upload file chạy cho 2 trình duyệt (Firefox/ Chrome)");
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(image01Path);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(image02Path);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(image03Path);

		System.out.println("	Step 03: Kiểm tra file đã được load lên thành công");
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + image01 + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + image02 + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + image03 + "']")).isDisplayed());

		System.out.println("	Step 04: Click Start button để upload cho cả 3 file");
		List<WebElement> startUploadBtns = driver
				.findElements(By.xpath("//tbody//button[@class='btn btn-primary start']"));
		for (WebElement startBtn : startUploadBtns)
			startBtn.click();

		System.out.println("	Step 05: Sau khi upload thành công, verify cả 3 file đã được upload");
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + image01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + image02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + image03 + "']")).isDisplayed());
	}

	@Test
	public void TC_02_Upload_multiple_files_by_sendkeys() {

		System.out.println("\nTC 02 - Upload multiple files by sendkeys");

		System.out.println("	Step 01: Truy cập vào trang: https://blueimp.github.io/jQuery-File-Upload/");
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		System.out.println(
				"	Step 02: Sử dụng phương thức sendKeys để upload nhiều file cùng lúc chạy cho 2 trình duyệt (Firefox/ Chrome)");
		driver.findElement(By.xpath("//input[@type='file']"))
				.sendKeys(image01Path + "\n" + image02Path + "\n" + image03Path);

		System.out.println("	Step 03: Kiểm tra file đã được load lên thành công");
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + image01 + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + image02 + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + image03 + "']")).isDisplayed());

		System.out.println("	Step 04: Click Start button để upload cho cả 3 file");
		List<WebElement> startUploadBtns = driver
				.findElements(By.xpath("//tbody//button[@class='btn btn-primary start']"));
		for (WebElement startBtn : startUploadBtns)
			startBtn.click();

		System.out.println("	Step 05: Sau khi upload thành công, verify cả 3 file đã được upload");
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + image01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + image02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + image03 + "']")).isDisplayed());
	}

	@Test
	public void TC_04_Upload_file() {

		System.out.println("\nTC 04 - Upload file");

		System.out.println("	Step 01: Open URL: https://gofile.io/?t=uploadFiles");
		driver.get("https://gofile.io/?t=uploadFiles");
		String parentWindow = driver.getWindowHandle();

		System.out.println("	Step 02: Upload các file và verify file đã được load lên thành công");
		driver.findElement(By.xpath("//input[@type='file']"))
				.sendKeys(image01Path + "\n" + image02Path + "\n" + image03Path);
		expliciteWait.until(ExpectedConditions.invisibilityOfAllElements(
				driver.findElement(By.xpath("//div[@class='progress position-relative mt-1']"))));
		expliciteWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='text-center']/i")));

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

		Assert.assertTrue(driver.findElement(By.xpath("//span[text() = '" + image01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text() = '" + image02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text() = '" + image03 + "']")).isDisplayed());
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
