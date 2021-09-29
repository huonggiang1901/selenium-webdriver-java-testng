package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Xpath_Css_II {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	By nameTextbox = By.xpath("//input[@id='txtFirstname']");
	By emailTextbox = By.xpath("//input[@id='txtEmail']");
	By confirmEmailTextbox = By.xpath("//input[@id='txtCEmail']");
	By passwordTextbox = By.xpath("//input[@id='txtPassword']");
	By confirmPaswordTextbox = By.xpath("//input[@id='txtCPassword']");
	By phoneTextbox = By.xpath("//input[@id='txtPhone']");
	By submitButton = By.xpath("//button[@type='submit']");

	By nameErrorMsg = By.xpath("//label[@id='txtFirstname-error']");
	By emailErrorMsg = By.xpath("//label[@id='txtEmail-error']");
	By confirmEmailErrorMsg = By.xpath("//label[@id='txtCEmail-error']");
	By passwordErrorMsg = By.xpath("//label[@id='txtPassword-error']");
	By confirmPaswordErrorMsg = By.xpath("//label[@id='txtCPassword-error']");
	By phoneErrorMsg = By.xpath("//label[@id='txtPhone-error']");

	String name = "John Wick";
	String email = "johnwick@gmail.net";
	String password = "123456";
	String phoneNumber = "0987654321";

	@BeforeClass
	public void BeforeCLass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Register_with_empty_data() {

		System.out.println("TC 01 - Register with empty data");

		System.out.println("	Step 01 - Truy cập vào trang: https://alada.vn/tai-khoan/dang-ky.html");
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		System.out.println("	Step 02 - Click vào button \"ĐĂNG KÝ\"");
		driver.findElement(submitButton).click();

		System.out.println("	Step 03 - Kiểm tra các error message hiển thị tại form đăng kí");
		Assert.assertEquals(driver.findElement(nameErrorMsg).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(emailErrorMsg).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(confirmEmailErrorMsg).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(passwordErrorMsg).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(confirmPaswordErrorMsg).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(phoneErrorMsg).getText(), "Vui lòng nhập số điện thoại.");
	}

	@Test
	public void TC_02_Register_with_invalid_Email() {

		System.out.println("\nTC 02 - Register with invalid Email");

		System.out.println("	Step 01 - Truy cập vào trang: https://alada.vn/tai-khoan/dang-ky.html");
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		System.out.println("	Step 02 - Nhập dữ kiệu hợp lệ vào các field ngoại trừ Email và Confirm Email");
		driver.findElement(nameTextbox).sendKeys(name);
		driver.findElement(emailTextbox).sendKeys("123@123.234@");
		driver.findElement(confirmEmailTextbox).sendKeys("123@123.234@");
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(confirmPaswordTextbox).sendKeys(password);
		driver.findElement(phoneTextbox).sendKeys(phoneNumber);

		System.out.println("	Step 03 - Click vào button \"ĐĂNG KÝ\"");
		driver.findElement(submitButton).click();

		System.out.println("	Step 04 - Kiểm tra các error message hiển thị Email và Confirm Email");
		Assert.assertEquals(driver.findElement(emailErrorMsg).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(confirmEmailErrorMsg).getText(), "Email nhập lại không đúng");
	}

	@Test
	public void TC_03_Register_with_incorrect_Confirm_Email() {

		System.out.println("\nTC 03 - Register with incorrect Confirm Email");

		System.out.println("	Step 01 - Truy cập vào trang: https://alada.vn/tai-khoan/dang-ky.html");
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		System.out.println("	Step 02 - Nhập dữ liệu hợp lệ vào các field ngoại trừ Confirm Email");
		driver.findElement(nameTextbox).sendKeys(name);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(confirmEmailTextbox).sendKeys("johnwick@gmail.com");
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(confirmPaswordTextbox).sendKeys(password);
		driver.findElement(phoneTextbox).sendKeys(phoneNumber);

		System.out.println("	Step 03 - Click vào button \"ĐĂNG KÝ\"");
		driver.findElement(submitButton).click();

		System.out.println("	Step 04 - Kiểm tra error message hiển thị tại Confirm Email");
		Assert.assertEquals(driver.findElement(confirmEmailErrorMsg).getText(), "Email nhập lại không đúng");
	}

	@Test
	public void TC_04_Register_with_Password_less_than_6_characters() {

		System.out.println("\nTC 04 - Register with Password less than 6 characters");

		System.out.println("	Step 01 - Truy cập vào trang: https://alada.vn/tai-khoan/dang-ky.html");
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		System.out.println("	Step 02 - Nhập dữ liệu hợp lệ vào các field ngoại trừ Password và Confirm Password");
		driver.findElement(nameTextbox).sendKeys(name);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(confirmEmailTextbox).sendKeys(email);
		driver.findElement(passwordTextbox).sendKeys("123");
		driver.findElement(confirmPaswordTextbox).sendKeys("123");
		driver.findElement(phoneTextbox).sendKeys(phoneNumber);

		System.out.println("	Step 03 - Click vào button \"ĐĂNG KÝ\"");
		driver.findElement(submitButton).click();

		System.out.println("	Step 04 - Kiểm tra error message hiển thị tại Password và Confirm Password");
		Assert.assertEquals(driver.findElement(passwordErrorMsg).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(confirmPaswordErrorMsg).getText(), "Mật khẩu phải có ít nhất 6 ký tự");

	}

	@Test
	public void TC_05_Register_with_incorrect_Confirm_Password() {

		System.out.println("\nTC 05 - Register with incorrect Confirm Password");

		System.out.println("	Step 01 - Truy cập vào trang: https://alada.vn/tai-khoan/dang-ky.html");
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		System.out.println("	Step 02 - Nhập dữ liệu hợp lệ vào các field ngoại trừ Confirm Password");
		driver.findElement(nameTextbox).sendKeys(name);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(confirmEmailTextbox).sendKeys(email);
		driver.findElement(passwordTextbox).sendKeys("password");
		driver.findElement(confirmPaswordTextbox).sendKeys(password + "789");
		driver.findElement(phoneTextbox).sendKeys(phoneNumber);

		System.out.println("	Step 03 - Click vào button \"ĐĂNG KÝ\"");
		driver.findElement(submitButton).click();

		System.out.println("	Step 04 - Kiểm tra error message hiển thị tại Confirm Password");
		Assert.assertEquals(driver.findElement(confirmPaswordErrorMsg).getText(), "Mật khẩu bạn nhập không khớp");
	}

	@Test
	public void TC_06_Register_with_invalid_Phone_number() {

		System.out.println("\nTC 06 - Register with invalid Phone number");

		System.out.println("	Step 01 - Truy cập vào trang: https://alada.vn/tai-khoan/dang-ky.html");
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		System.out.println("	Step 02 - Nhập dữ liệu hợp lệ vào các field ngoại trừ Phone number");
		// Nhập vào Phone dạng email (chữ)
		driver.findElement(nameTextbox).sendKeys(name);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(confirmEmailTextbox).sendKeys(email);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(confirmPaswordTextbox).sendKeys("234567");
		driver.findElement(phoneTextbox).sendKeys("johnwick@gmail.net");

		System.out.println("	Step 03 - Click vào button \"ĐĂNG KÝ\"");
		driver.findElement(submitButton).click();

		System.out.println("	Step 04 - Kiểm tra error message hi63n thị tại Phone number");
		Assert.assertEquals(driver.findElement(phoneErrorMsg).getText(), "Vui lòng nhập con số");

		System.out.println("	Step 05 - Nhập dữ liệu hợp lệ vào các field ngoại trừ Phone number");
		// Nhập vào Phone ít hơn 10 kí tự hoặc lớn hơn 11 kí tự
		driver.findElement(phoneTextbox).clear();
		driver.findElement(phoneTextbox).sendKeys("098712345");

		System.out.println("	Step 06 - Click vào button \"ĐĂNG KÝ\"");
		driver.findElement(submitButton).click();

		System.out.println("	Step 07 - Kiểm tra error message hiển thị tại Phone number");
		Assert.assertEquals(driver.findElement(phoneErrorMsg).getText(), "Số điện thoại phải từ 10-11 số.");

		System.out.println("	Step 08 - Nhập dữ liệu hợp lệ vào các field ngoại trừ Phone number");
		// Nhập vào Phone bắt đầu không phải đầu số của các nhà mạng
		driver.findElement(phoneTextbox).clear();
		driver.findElement(phoneTextbox).sendKeys("123456");

		System.out.println("	Step 09 - Click vào button \"ĐĂNG KÝ\"");
		driver.findElement(submitButton).click();

		System.out.println("	Step 10 - Kiểm tra error message hiển thị tại Phone number");
		Assert.assertEquals(driver.findElement(phoneErrorMsg).getText(),
				"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
