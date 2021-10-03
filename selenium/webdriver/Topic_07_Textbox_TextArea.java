package webdriver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Textbox_TextArea {
	WebDriver driver;
	JavascriptExecutor js;
	String projectPath = System.getProperty("user.dir");

	By nameTextbox = By.xpath("//input[@name='name']");
	By maleGenderRadioBtn = By.xpath("//input[@value='m']");
	By femaleGenderRadioBtn = By.xpath("//input[@value='f']");
	By dateOfBirthTextbox = By.xpath("//input[@id='dob']");
	By addressTextarea = By.xpath("//textarea[@name='addr']");
	By cityTextbox = By.xpath("//input[@name='city']");
	By stateTextbox = By.xpath("//input[@name='state']");
	By pinTextbox = By.xpath("//input[@name='pinno']");
	By phoneTextbox = By.xpath("//input[@name='telephoneno']");
	By emailTextbox = By.xpath("//input[@name='emailid']");
	By passwordTextbox = By.xpath("//input[@type='password']");
	By submitBtn = By.xpath("//input[@name='sub']");

	By customerIDText = By.xpath("//td[text()='Customer ID']/following-sibling::td");
	By nameText = By.xpath("//td[text()='Customer Name']/following-sibling::td");
	By genderText = By.xpath("//td[text()='Gender']/following-sibling::td");
	By birthDateText = By.xpath("//td[text()='Birthdate']/following-sibling::td");
	By addressText = By.xpath("//td[text()='Address']/following-sibling::td");
	By cityText = By.xpath("//td[text()='City']/following-sibling::td");
	By stateText = By.xpath("//td[text()='State']/following-sibling::td");
	By pinText = By.xpath("//td[text()='Pin']/following-sibling::td");
	By phoneText = By.xpath("//td[text()='Mobile No.']/following-sibling::td");
	By emailText = By.xpath("//td[text()='Email']/following-sibling::td");

	String name = "Kane";
	String gender = "male";
	String dateOfBirth = "01/19/1995";
	String address = "921 Monster";
	String city = "Ho Chi Minh";
	String state = "HK";
	String pin = "123456";
	String phone = "0908001901";
	String email = "kane" + getCurrentDateAndTime() + "@gmail.net";
	String password = "Qwerty/12345678";

	String editedAddress = "988 GH";
	String editedCity = "Hue";
	String editeState = "NY";
	String editedPin = "456789";
	String editedPhone = "0909009009";

	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		js = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Xử_lí_textbox_textarea() throws ParseException {

		System.out.println("TC 01 - Xử lí textbox/textarea");

		System.out.println("	Step 01: Access vào trang: http://demo.guru99.com/v4");
		System.out.println("	Step 02: Đăng nhập với thông tin: User/Pass lấy ở trang http://demo.guru99.com/");

		// Register
		driver.get("http://demo.guru99.com/");
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		String userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		String pass = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

		// Login
		driver.get("http://demo.guru99.com/v4");
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userID);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pass);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

		Assert.assertEquals(driver.getTitle(), "Guru99 Bank Manager HomePage");

		System.out.println("	Step 03: Click chọn link New Customer");
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		System.out.println("	Step 04: Nhập toàn bộ dữ liệu đúng > Click Submit");
		sendKeysToElement(nameTextbox, name);
		clickGender(gender);
		enterDateOfBirthTextbox(dateOfBirth);
		sendKeysToElement(addressTextarea, address);
		sendKeysToElement(cityTextbox, city);
		sendKeysToElement(stateTextbox, state);
		sendKeysToElement(pinTextbox, pin);
		sendKeysToElement(phoneTextbox, phone);
		sendKeysToElement(emailTextbox, email);
		sendKeysToElement(passwordTextbox, password);
		driver.findElement(submitBtn).click();

		System.out
				.println("	Step 05: Sau khi hệ thông tạo mới Customer thành công > Get ra thông tin của Customer ID");
		String customerID = driver.findElement(customerIDText).getText();

		System.out.println("	Step 06: Verify tất cả thông tin được tạo mới thành công");
		Assert.assertEquals(driver.findElement(nameText).getText(), name);
		Assert.assertEquals(driver.findElement(genderText).getText(), gender);
		Assert.assertEquals(driver.findElement(genderText).getText(), gender);
		Assert.assertEquals(
				formatDateFromString(driver.findElement(birthDateText).getText(), "yyyy-MM-dd", "MM/dd/yyyy"),
				dateOfBirth);
		Assert.assertEquals(driver.findElement(addressText).getText(), address);
		Assert.assertEquals(driver.findElement(cityText).getText(), city);
		Assert.assertEquals(driver.findElement(stateText).getText(), state);
		Assert.assertEquals(driver.findElement(pinText).getText(), pin);
		Assert.assertEquals(driver.findElement(phoneText).getText(), phone);
		Assert.assertEquals(driver.findElement(emailText).getText(), email);

		System.out.println("	Step 07: Chọn menu Edit Customer > Nhập Customer ID > Submit");
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();

		System.out.println(
				"	Step 08: Tại màn hình Edit Customer: Verify giá trị 2 field: Customer Name và Address đúng với dữ liệu khi tạo mới New Customer tại Step 04");
		Assert.assertEquals(driver.findElement(nameTextbox).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(addressTextarea).getText(), address);

		System.out.println("	Step 09: Nhập giá trị mới tại tất cả các field (ngoại trừ field bị disable) > Submit");
		sendKeysToElement(addressTextarea, editedAddress);
		sendKeysToElement(cityTextbox, editedCity);
		sendKeysToElement(stateTextbox, editeState);
		sendKeysToElement(pinTextbox, editedPin);
		sendKeysToElement(phoneTextbox, editedPhone);

		String editedEmail = "ranari" + getCurrentDateAndTime() + "@yahoo.com";

		sendKeysToElement(emailTextbox, editedEmail);
		driver.findElement(submitBtn).click();

		System.out.println("	Step 10: Verify giá trị tất cả các field đúng với dữ liệu sau khi đã Edit thành công");
		Assert.assertEquals(driver.findElement(addressText).getText(), editedAddress);
		Assert.assertEquals(driver.findElement(cityText).getText(), editedCity);
		Assert.assertEquals(driver.findElement(stateText).getText(), editeState);
		Assert.assertEquals(driver.findElement(pinText).getText(), editedPin);
		Assert.assertEquals(driver.findElement(phoneText).getText(), editedPhone);
		Assert.assertEquals(driver.findElement(emailText).getText(), editedEmail);
	}

	@AfterClass
	public void AfterClass() {
//		driver.quit();
	}

	public void sendKeysToElement(By locator, String value) {
		WebElement element = driver.findElement(locator);
		element.clear();
		element.sendKeys(value);
	}

	public String getCurrentDateAndTime() {
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyHH:mm:ss");
		return dateFormat.format(currentDate).toString().replace(":", "");
	}

	public void clickGender(String gender) {
		if (gender == "male")
			driver.findElement(maleGenderRadioBtn).click();
		else
			driver.findElement(femaleGenderRadioBtn).click();
	}

	public void enterDateOfBirthTextbox(String date) {
		WebElement element = driver.findElement(dateOfBirthTextbox);
		js.executeScript("arguments[0].removeAttribute('type')", element);
		element.sendKeys(date);
	}

	public String formatDateFromString(String date, String oldFormat, String newFormat) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(oldFormat);
		Date d = sdf.parse(date);
		sdf.applyPattern(newFormat);
		String newDateString = sdf.format(d);
		return newDateString;
	}

	public void sleepInSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
