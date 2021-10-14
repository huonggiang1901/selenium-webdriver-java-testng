package webdriver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Javascript_Executor {
	WebDriver driver;
	JavascriptExecutor js;

	String projectPath = System.getProperty("user.dir");

	String email = "kane" + getcurrentDateAndTime() + "@gmail.com";

	@BeforeClass
	public void BeforeClass() {

		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		js = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

//	@Test
	public void TC_01_Javascript_Executor() {

		System.out.println("TC 01 - Javascript Executor");

		System.out.println("	Step 01: Truy cập vào trang: http://live.demoguru99.com/ (Sử dụng JE)");
		js.executeScript("window.location = 'http://live.demoguru99.com/'");

		System.out.println("	Step 02: Sử dụng JE để get domain của page. Verify domain = live.demoguru99.com");
		String domain = js.executeScript("return documan.domain").toString();
		Assert.assertEquals(domain, "live.demoguru99.com");

		System.out.println("	Step 03: Sử dụng JE để get URL của page. Verify URL = http://live.demoguru99.com/");
		String URL = js.executeAsyncScript("return document.URL").toString();
		Assert.assertEquals(URL, "http://live.demoguru99.com/");

		System.out.println("	Step 04: Open MOBILE page bằng JE");

		System.out.println("	Step 05: Add sản phẩm SAMSUNG GALAXY vào Cart (click vào ADD TO CART button bằng JE");

		System.out.println(
				"	Step 06: Verify message được hiển thị: Samsung Galaxy was added to your shopping cart. (Sử dụng JE - Get inner text)");
		String innerText = js.executeScript("return document.documentElement.innerText").toString();
		Assert.assertTrue(innerText.contains("Samsung Galaxy was added to your shopping cart."));

		System.out.println(
				"	Step 07: Open Customer Service page (Sử dụng JE). Verify title của page = Customer Service (Sử dụng JE)");

		System.out.println("	Step 08: Scroll tới element Newsletter textbox nằm ở cuối page (Sử dụng JE)");

		System.out.println("	Step 09: Input emaail hợp lệ vào Newslatter textbox (Sử dụng JE)");

		System.out.println("	Step 10: Click vào Subscribe button (Sử dụng JE)");

		System.out.println(
				"	Step 11: Verify text có hiển thị (Sử dụng JE - Get innet text): Thank you for your subscription.");
		innerText = js.executeScript("return document.documentElement.innerText").toString();
		Assert.assertTrue(innerText.contains("Thank you for your subscription."));

		System.out.println(
				"	Step 12: Navigate tới domain: http://demo.guru99.com/v4/ (Sử dụng JE). Verify domain sau khi navigate = demo.guru99.com");
		js.executeScript("window.location = 'http://demo.guru99.com/v4/'");
	}

//	@Test
	public void TC_02_Adutomationfc_Verify_HTML5_validation_message() {

		By nameTextbox = By.xpath("//input[@id='fname']");
		By passwordTextbox = By.xpath("//input[@id='pass']");
		By emailTextbox = By.xpath("//input[@id='em']");
		By addressDropdown = By.xpath("//b[text()='✱ ADDRESS ']/parent::label/following-sibling::select");
		By submitBtn = By.xpath("//input[@name='submit-btn']");

		System.out.println("\nTC 02 - Adutomationfc Verify HTML5 validation message");

		System.out.println("	Step 01: Access vào trang: https://automationfc.github.io/html5/index.html");
		js.executeScript("window.location = 'https://automationfc.github.io/html5/index.html'");

		System.out.println("	Step 02: Click Submit và verify mesage hiển thị tại field Name textbox");
		js.executeScript("arguments[0].click()", driver.findElement(submitBtn));

		String nameValidatetionMgs = js
				.executeScript("return arguments[0].validationMessage", driver.findElement(nameTextbox)).toString();
		Assert.assertEquals(nameValidatetionMgs, "Please fill out this field.");

		System.out.println(
				"	Step 03: Input dữ liệu vào field Name và click Submit - Verify message hiển thị tại field Password textbox");
		driver.findElement(nameTextbox).sendKeys("Jan Kane");
		js.executeScript("arguments[0].click()", driver.findElement(submitBtn));
		String passwordValidatetionMgs = js
				.executeScript("return arguments[0].validationMessage", driver.findElement(passwordTextbox)).toString();
		Assert.assertEquals(passwordValidatetionMgs, "Please fill out this field.");

		System.out.println(
				"	Step 04: Input dữ liệu vào field Password và click Submit - Verify message hiển thị tại field Email textbox");
		driver.findElement(passwordTextbox).sendKeys("12345678");
		js.executeScript("arguments[0].click()", driver.findElement(submitBtn));
		String emailValidatetionMgs = js
				.executeScript("return arguments[0].validationMessage", driver.findElement(emailTextbox)).toString();
		Assert.assertEquals(emailValidatetionMgs, "Please fill out this field.");

		System.out.println(
				"	Step 05: Input dữ liệu không hợp lệ vào field Email và click Submit - Verify message hiển thị tại field Email textbox");
		driver.findElement(emailTextbox).sendKeys("123!@#$");
		js.executeScript("arguments[0].click()", driver.findElement(submitBtn));
		emailValidatetionMgs = js
				.executeScript("return arguments[0].validationMessage", driver.findElement(emailTextbox)).toString();
		Assert.assertEquals(emailValidatetionMgs, "A part following '@' should not contain the symbol '#'.");

		System.out.println(
				"	Step 06: Input dữ liệu không hợp lệ vào field Email và click Submit - Verify message hiển thị tại field Email textbox");
		driver.findElement(emailTextbox).clear();
		driver.findElement(emailTextbox).sendKeys("123@456");
		js.executeScript("arguments[0].click()", driver.findElement(submitBtn));
		emailValidatetionMgs = js
				.executeScript("return arguments[0].validationMessage", driver.findElement(emailTextbox)).toString();
		Assert.assertEquals(emailValidatetionMgs, "Please match the requested format.");

		System.out.println(
				"	Step 07: Input dữ liệu hợp lệ vào field Email và click Submit - Verify message hiển thị tại field Address");
		driver.findElement(emailTextbox).clear();
		driver.findElement(emailTextbox).sendKeys(email);
		js.executeScript("arguments[0].click()", driver.findElement(submitBtn));
		String addressValidatetionMgs = js
				.executeScript("return arguments[0].validationMessage", driver.findElement(addressDropdown)).toString();
		Assert.assertEquals(addressValidatetionMgs, "Please select an item in the list.");
	}

	@Test
	public void TC_03_Verify_HTML5_validation_message() {

		System.out.println("\nTC 03 - Ubuntu Verify HTML5 validation message");

		System.out.println("	Step 01: Access vào trang: https://login.ubuntu.com/");

		System.out.println("	Step 02: Access vào trang: https://sieuthimaymocthietbi.com/account/register");

		System.out.println("	Step 03: Access vào trang: https://warranty.rode.com/");

		System.out.println("	Step 04: Access vào trang: https://www.pexels.com/vi-vn/join-contributor/");

	}

	@Test
	public void TC_04_Remove_attribute() throws ParseException {

		String name = "Jan Kane";
		String dateOfBirth = "01/19/1995";
		String address = "921 Monster";
		String city = "Konoha";
		String state = "HK";
		String pin = "123456";
		String phone = "0909009009";
		String password = "Abc/1234";

		System.out.println("\nTC 04 - Remove attribute");

		System.out.println("	Step 01: Access vào trang: http://demo.guru99.com/v4");
		System.out.println(
				"	Step 02: Đăng nhập với thông tin User/ Pass (Lấy thông tin User/ Pass tại: http://demo.guru99.com/)");

		js.executeScript("window.location = 'http://demo.guru99.com/'");
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
		js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//input[@name='btnLogin']")));

		String userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		String pass = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

		js.executeScript("window.location = 'http://demo.guru99.com/v4'");

		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userID);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pass);
		js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//input[@name='btnLogin']")));

		System.out.println("	Step 03: Chọn menu New Customer");
		js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//a[text()='New Customer']")));

		System.out.println("	Step 04: Nhập toàn bộ dữ liệu đúng > Click Submit");
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys(name);
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@value='m']")));
		js.executeScript("arguments[0].removeAttribute('type')", driver.findElement(By.xpath("//input[@id='dob']")));
		driver.findElement(By.xpath("//input[@id='dob']")).sendKeys(dateOfBirth);
		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(address);
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city);
		driver.findElement(By.xpath("//input[@name='state']")).sendKeys(state);
		driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys(pin);
		driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(phone);
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//input[@name='sub']")));

		System.out.println("	Step 05: Verify tạo mới Customer thành công");
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),
				"male");
		Assert.assertEquals(formatDateFromString(
				driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), "yyyy-MM-dd",
				"MM/dd/yyyy"), dateOfBirth);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
				address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
				state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),
				phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),
				email);

	}

//	@Test
	public void TC_05_Create_an_Account() {

		System.out.println("\nTC 05 - Create an Account");

		System.out.println("	Step 01: Truy cập vào trang: http://live.demoguru99.com/ (Sử dụng JE)");
		js.executeScript("window.location = 'http://live.demoguru99.com/'");

		System.out.println(
				"	Step 02: Click vào link \"My Account\" trên header (ẩn) để tới trang đăng nhập (Sử dụng JE - CLick trực tiếp vào My account, ko click vào ACCOUNT link)");

		System.out.println("	Step 03: Click CREATE AN ACCOUNT button để tới trang đăng kí tài khoản (Sử dụng JE)");

		System.out.println(
				"	Step 04: Nhập thông tin hợp lệ vào tất cả các field: First Name/ Last Name/ Email Address/ Password/ Confirm Password (SỬ dụng JE)");

		System.out.println("	Step 05: Click REGISTER button (Sử dụng JE)");

		System.out.println(
				"	Step 06: Verify message xuất hiện khi đăng kí thành công: Thank you for registering with Main Website Store. (Sử dụng JE)");

		System.out.println("	Step 07: Logout khỏi hệ thống (Sử dụng JE)");

		System.out.println(
				"	Step 08: Kiểm tra hệ thống navigate về Home page sau khi logout thành công (Sử dụng hàm isDisplayed để check wait");

	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}

	public String getcurrentDateAndTime() {
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyHH:mm:ss");
		return dateFormat.format(currentDate).toString().replace(":", "");
	}

	public String formatDateFromString(String date, String oldFormat, String newFormat) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(oldFormat);
		Date d = sdf.parse(date);
		sdf.applyPattern(newFormat);
		String newDateString = sdf.format(d);
		return newDateString;
	}

}
