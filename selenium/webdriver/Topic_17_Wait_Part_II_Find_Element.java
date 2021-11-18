package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_Part_II_Find_Element {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_findElement() {
		
		System.out.println("TC 01 - findElement");
		
		driver.get("https://www.facebook.com/");

		// Ko tìm thấy element
		System.out.println("No element");
//		driver.findElement(By.xpath("//input[@id='selenium']"));

		// Có 1 element
		System.out.println("Found 1 element");
		driver.findElement(By.xpath("//input[@id='email']"));

		// Nhiều hơn 1 element
		System.out.println("Found more than 1 element");
		driver.findElement(By.xpath("//input[@id='email' or @id='pass']")).sendKeys("selenium@gmail.com");

	}

	@Test
	public void TC_02_findElements() {
		
		System.out.println("\nTC 02 - findElements");
		
		driver.get("https://www.facebook.com/");

		// Ko tìm thấy element
		System.out.println("No element");
		List<WebElement> element = driver.findElements(By.xpath("//input[@id='selenium']"));
		System.out.println(" - Size = " + element.size());

		// Có 1 element
		System.out.println("Found 1 element");
		element = driver.findElements(By.xpath("//input[@id='email']"));
		System.out.println(" - Size = " + element.size());

		// Nhiều hơn 1 element
		System.out.println("Found more than 1 element");
		element = driver.findElements(By.xpath("//input[@id='email' or @id='pass']"));
		System.out.println(" - Size = " + element.size());

		element.get(0).clear();
		element.get(0).sendKeys("selenium@gmail.com");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}
