package webdriver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Javascript_Executor {
	
	WebDriver driver;
	JavascriptExecutor js;

	String projectPath = System.getProperty("user.dir");

	String email = "kane" + getStringCurrentDateAndTime() + "@gmail.net";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		js = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Javascript_Executor() {

		System.out.println("TC 01 - Javascript Executor");

		System.out.println("	Step 01: Truy cập vào trang: http://live.guru99.com/ (Sử dụng JE)");
		js.executeScript("window.location = 'http://live.guru99.com/'");

		System.out.println("	Step 02: Sử dụng JE để get domain của page. Verify domain = live.guru99.com");
		String domain = js.executeScript("return document.domain").toString();
		Assert.assertEquals(domain, "live.techpanda.org");

		System.out.println("	Step 03: Sử dụng JE để get URL của page. Verify URL = http://live.guru99.com/");
		String URL = js.executeScript("return document.URL").toString();
		Assert.assertEquals(URL, "http://live.techpanda.org/");

		System.out.println("	Step 04: Open MOBILE page bằng JE");
		js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//a[text()='Mobile']")));

		System.out.println("	Step 05: Add sản phẩm SAMSUNG GALAXY vào Cart (click vào ADD TO CART button bằng JE");
		js.executeScript("arguments[0].click()", driver.findElement(By.xpath(
				"//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//button[@title='Add to Cart']")));

		System.out.println(
				"	Step 06: Verify message được hiển thị: Samsung Galaxy was added to your shopping cart. (Sử dụng JE - Get inner text)");
		String innerText = js.executeScript("return document.documentElement.innerText").toString();

		Assert.assertTrue(innerText.contains("Samsung Galaxy was added to your shopping cart."));

		System.out.println(
				"	Step 07: Open Customer Service page (Sử dụng JE). Verify title của page = Customer Service (Sử dụng JE)");
		js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//a[text()='Customer Service']")));
		Assert.assertEquals(js.executeScript("return document.title").toString(), "Customer Service");

		System.out.println("	Step 08: Scroll tới element Newsletter textbox nằm ở cuối page (Sử dụng JE)");
		js.executeScript("arguments[0].scrollIntoView(true)",
				driver.findElement(By.xpath("//input[@id='newsletter']")));

		System.out.println("	Step 09: Input emaail hợp lệ vào Newslatter textbox (Sử dụng JE)");
		driver.findElement(By.xpath("//input[@id='newsletter']")).sendKeys(email);

		System.out.println("	Step 10: Click vào Subscribe button (Sử dụng JE)");
		js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//button[@title='Subscribe']")));

		System.out.println(
				"	Step 11: Verify text có hiển thị (Sử dụng JE - Get innet text): Thank you for your subscription.");
		innerText = js.executeScript("return document.documentElement.innerText").toString();
		Assert.assertTrue(innerText.contains("Thank you for your subscription."));

		System.out.println(
				"	Step 12: Navigate tới domain: http://demo.guru99.com/v4/ (Sử dụng JE). Verify domain sau khi navigate = demo.guru99.com");
		js.executeScript("window.location = 'http://demo.guru99.com/v4/'");
		domain = js.executeScript("return document.domain").toString();
		Assert.assertEquals(domain, "demo.guru99.com");
	}

	@Test
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
		js.executeScript("window.location = 'https://login.ubuntu.com/'");

		List<WebElement> acceptCookies = driver.findElements(By.xpath("//button[@id='cookie-policy-button-accept']"));
		if (acceptCookies.size() > 0)
			driver.findElement(By.xpath("//button[@id='cookie-policy-button-accept']")).click();

		driver.findElement(By.xpath("//input[@class='textType']")).sendKeys("a");
		driver.findElement(By.xpath("//button[@data-qa-id='login_button']")).click();
		String emailValidatetionMgs = js.executeScript("return arguments[0].validationMessage",
				driver.findElement(By.xpath("//input[@class='textType']"))).toString();
		Assert.assertEquals(emailValidatetionMgs, "Please include an '@' in the email address. 'a' is missing an '@'.");

		System.out.println("	Step 02: Access vào trang: https://sieuthimaymocthietbi.com/account/register");
		js.executeScript("window.location = 'https://sieuthimaymocthietbi.com/account/register'");
		driver.findElement(By.xpath("//button[@value='Đăng ký']")).click();
		String lastNameValidatetionMgs = js.executeScript("return arguments[0].validationMessage",
				driver.findElement(By.xpath("//input[@id='lastName']"))).toString();
		Assert.assertEquals(lastNameValidatetionMgs, "Please fill out this field.");

		System.out.println("	Step 03: Access vào trang: https://warranty.rode.com/");
		js.executeScript("window.location = 'https://warranty.rode.com/'");
		driver.findElement(By.xpath("//button[contains(text(),'Register')]")).click();
		String firstNameValidatetionMgs = js.executeScript("return arguments[0].validationMessage",
				driver.findElement(By.xpath("//input[@id='firstname']"))).toString();
		Assert.assertEquals(firstNameValidatetionMgs, "Please fill out this field.");

		System.out.println("	Step 04: Access vào trang: https://www.pexels.com/vi-vn/join-contributor/");
		js.executeScript("window.location = 'https://www.pexels.com/vi-vn/join-contributor/'");
		js.executeScript("arguments[0].scrollIntoView(true)",
				driver.findElement(By.xpath("//button[@class='rd__button rd__button--full-width']")));
		driver.findElement(By.xpath("//button[@class='rd__button rd__button--full-width']")).click();
		String nameValidatetionMgs = js.executeScript("return arguments[0].validationMessage",
				driver.findElement(By.xpath("//input[@id='user_first_name']"))).toString();
		Assert.assertEquals(nameValidatetionMgs, "Please fill out this field.");
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

	@Test
	public void TC_05_Create_an_Account() {

		System.out.println("\nTC 05 - Create an Account");

		System.out.println("	Step 01: Truy cập vào trang: http://live.guru99.com/ (Sử dụng JE)");
		js.executeScript("window.location = 'http://live.guru99.com/'");

		System.out.println(
				"	Step 02: Click vào link \"My Account\" trên header (ẩn) để tới trang đăng nhập (Sử dụng JE - CLick trực tiếp vào My account, ko click vào ACCOUNT link)");
		js.executeScript("arguments[0].click()",
				driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account']")));

		System.out.println("	Step 03: Click CREATE AN ACCOUNT button để tới trang đăng kí tài khoản (Sử dụng JE)");
		js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//a[@title='Create an Account']")));

		System.out.println(
				"	Step 04: Nhập thông tin hợp lệ vào tất cả các field: First Name/ Last Name/ Email Address/ Password/ Confirm Password (SỬ dụng JE)");
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Jan");
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Kane");
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Abc/123456");
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("Abc/123456");

		System.out.println("	Step 05: Click REGISTER button (Sử dụng JE)");
		js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//button[@title='Register']")));

		System.out.println(
				"	Step 06: Verify message xuất hiện khi đăng kí thành công: Thank you for registering with Main Website Store. (Sử dụng JE)");
		String innerText = js.executeScript("return document.documentElement.innerText").toString();
		Assert.assertTrue(innerText.contains("Thank you for registering with Main Website Store."));

		System.out.println("	Step 07: Logout khỏi hệ thống (Sử dụng JE)");
		js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//a[@title='Log Out']")));

		System.out.println("	Step 08: Kiểm tra hệ thống navigate về Home page sau khi logout thành công");
		sleepInSeconds(6);
		Assert.assertEquals(js.executeScript("return document.title"), "Home page");
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

	public String formatDateFromString(String date, String oldFormat, String newFormat) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(oldFormat);
		Date d = sdf.parse(date);
		sdf.applyPattern(newFormat);
		String newDateString = sdf.format(d);
		return newDateString;
	}

	public void sleepInSeconds(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
