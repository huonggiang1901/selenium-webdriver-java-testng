package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_Part_I_Conditions_Status {
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
	public void TC_01_Visible() {

		System.out.println("TC 01 - Visible");

		driver.get("http://facebook.com/");

		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();

		expliciteWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']")))
				.sendKeys("dam@gmail.com");

		Dimension confirmEmailTextbox = expliciteWait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")))
				.getSize();

		System.out.println("Height of confirmEmailTextbox = " + confirmEmailTextbox.getHeight());
		System.out.println("Width of confirmEmailTextbox = " + confirmEmailTextbox.getWidth());
	}

	@Test
	public void TC_02_Invisible_In_DOM() {

		System.out.println("\nTC 02 - Invisible In DOM");

		driver.get("http://facebook.com/");

		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();

		expliciteWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']")));

		expliciteWait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
	}

	@Test
	public void TC_03_Invisible_Not_in_DOM() {

		System.out.println("\nTC 03 - Invisible Not In DOM");

		driver.get("http://facebook.com/");

		expliciteWait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
	}

	@Test
	public void TC_04_Presence_In_UI() {

		System.out.println("\nTC 04 - Presence In UI");

		driver.get("http://facebook.com/");

		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();

		expliciteWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']")))
				.sendKeys("dam@gmail.com");

		expliciteWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
	}

	@Test
	public void TC_05_Presence_Not_In_UI() {

		System.out.println("\nTC 05 - Presence Not In UI");

		driver.get("http://facebook.com/");

		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();

		expliciteWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
	}

	@Test
	public void TC_06_Staleness() {

		System.out.println("\nTC 06 - Staleness");

		driver.get("http://facebook.com/");

		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		
		expliciteWait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		
		expliciteWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']")))
		.sendKeys("dam@gmail.com");
		
		expliciteWait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		
		WebElement confirmEmailTextbox = driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']"));

		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();

		expliciteWait.until(ExpectedConditions.stalenessOf(confirmEmailTextbox));
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
