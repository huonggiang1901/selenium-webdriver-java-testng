package testng;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic_06_Parameter {
	
	WebDriver driver;
	Select select;
	String projectPath = System.getProperty("user.dir");

	By countryDropdown = By.xpath("//select[@id='where_country']");

	By femaleGenderRadioBtn = By.xpath("//input[@id='gender-female']");
	By firstNameTextbox = By.xpath("//input[@id='FirstName']");
	By lastNameTextbox = By.xpath("//input[@id='LastName']");
	By dayOfBirthDropdown = By.xpath("//select[@name='DateOfBirthDay']");
	By monthOfBirthDropdown = By.xpath("//select[@name='DateOfBirthMonth']");
	By yearOfBirthDropdown = By.xpath("//select[@name='DateOfBirthYear']");
	By emailTextbox = By.xpath("//input[@id='Email']");
	By companyNameTextbox = By.xpath("//input[@id='Company']");
	By passwordTextbox = By.xpath("//input[@id='Password']");
	By confirmPasswordTextbox = By.xpath("//input[@id='ConfirmPassword']");
	By registerBtn = By.xpath("//button[@id='register-button']");

	String selectedCountry = "Vietnam";
	String resultCount = "29";

	String firstName = "Jan";
	String lastName = "Kane";
	String dayOfBirth = "1";
	String monthOfBirth = "May";
	String yearOfBirth = "1980";
	String companyName = "Automation VN";
	String password = "12345678";

	String dayTotalOptions = "32";
	String monthTotalOptions = "13";
	String yearTotalOptions = "112";

	@Parameters({ "browser", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, @Optional("LOCAL") String environmentName) {
		switch (browserName) {
		case "firefox":
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "chrome":
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		default:
			throw new RuntimeException("Browser not supported");
		}

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Parameters("url")
	@Test
	public void TC_NopCommerce() {

		System.out.println("\nTC - Nop Commerce");

		System.out.println("	Step 01: Truy cập trang: https://demo.nopcommerce.com/register");
		driver.get("https://demo.nopcommerce.com/register");

		System.out.println("	Step 02: Click Register link trên Header");
		driver.findElement(By.xpath("//a[text()='Register']")).click();

		System.out.println("	Step 03: Input các thông tin hợp lệ vào form");
		driver.findElement(femaleGenderRadioBtn).click();
		sendKeysToElement(firstNameTextbox, firstName);
		sendKeysToElement(lastNameTextbox, lastName);

		// Chọn các giá trị trong dropdown Date of birth:
		// Day = 1 (Kiểm tra dropdown có 32 items)
		select = new Select(driver.findElement(dayOfBirthDropdown));
		Assert.assertEquals(String.valueOf(select.getOptions().size()), dayTotalOptions);
		select.selectByVisibleText(dayOfBirth);

		// Month = May (Kiểm tra dropdown có 13 items)
		select = new Select(driver.findElement(monthOfBirthDropdown));
		Assert.assertEquals(String.valueOf(select.getOptions().size()), monthTotalOptions);
		select.selectByVisibleText(monthOfBirth);

		// Year = 1980 (Kiểm tra dropdown có 112 items)
		select = new Select(driver.findElement(yearOfBirthDropdown));
		Assert.assertEquals(String.valueOf(select.getOptions().size()), yearTotalOptions);
		select.selectByVisibleText(yearOfBirth);

		String email = "kane" + getStringCurrentDateAndTime() + "@gmail.net";

		sendKeysToElement(emailTextbox, email);
		sendKeysToElement(companyNameTextbox, companyName);
		sendKeysToElement(passwordTextbox, password);
		sendKeysToElement(confirmPasswordTextbox, password);

		System.out.println("	Step 04: Click REGISTER button");
		driver.findElement(registerBtn).click();

		System.out.println("	Step 05: Verify đã vào trang Home Page sau khi đăng kí thành công");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='result']")).isDisplayed());

		System.out.println("	Step 06: Click vào trang My account và kiểm tra ngày/ tháng/ năm nhập vào là đúng");
		driver.findElement(By.xpath("//a[@class='ico-account']")).click();

		select = new Select(driver.findElement(dayOfBirthDropdown));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), dayOfBirth);

		select = new Select(driver.findElement(monthOfBirthDropdown));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), monthOfBirth);

		select = new Select(driver.findElement(yearOfBirthDropdown));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), yearOfBirth);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public String getStringCurrentDateAndTime() {
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyHH:mm:ss");
		return dateFormat.format(currentDate).toString().replace(":", "");
	}

	public void sendKeysToElement(By locator, String value) {
		WebElement element = driver.findElement(locator);
		element.clear();
		element.sendKeys(value);
	}
	
}
