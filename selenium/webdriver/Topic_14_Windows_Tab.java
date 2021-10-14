package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Windows_Tab {
	WebDriver driver;
	JavascriptExecutor js;

	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void BeforeClass() {

		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		js = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

//	@Test
	public void TC_08_Windows_Tab() {

		System.out.println("TC 08 - Windows Tab");

		System.out.println("	Step 01: Truy cập vào trang: https://automationfc.github.io/basic-form/index.html");
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String parentWindow = driver.getWindowHandle();

		System.out.println("	Step 02: Click \"GOOGLE\" link -> Switch qua tab mới");
		clickElement(By.xpath("//a[text()='GOOGLE']"));
		switchWindowByTitle("Google");

		System.out.println("	Step 03: Kiểm tra title của window mới = Google");
		Assert.assertEquals(driver.getTitle(), "Google");

		System.out.println("	Step 04: Switch về parent window");
		switchToParentWindow(parentWindow);

		System.out.println("	Step 05: Click \"FACEBOOK\" link -> Switch qua tab mới");
		clickElement(By.xpath("//a[text()='FACEBOOK']"));
		switchWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");

		System.out.println("	Step 06: Kiểm tra title của window mới = Facebook - Đăng nhập hoặc đăng ký");
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");

		System.out.println("	Step 07: Switch về parent window");
		switchToParentWindow(parentWindow);

		System.out.println("	Step 08: Click \"TIKI\" link -> Switch qua tab mới");
		clickElement(By.xpath("//a[text()='TIKI']"));
		switchWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

		System.out.println(
				"	Step 09: Kiểm tra title của window mới = Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		Assert.assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

		System.out.println("	Step 10: Close tất cả cửa sổ/ tab ngoại trừ parent window");
		closeWindowsExceptParentWindow(parentWindow);

		System.out.println("	Step 11: Kiểm tra đã quay về parent window thành công (title/ url)");
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
	}

//	@Test
	public void TC_09_Kyna_Windows_Tab() {

		System.out.println("\nTC 09 - Kyna Windows Tab");

		System.out.println("	Step 01: Truy cập vào trang: https://kyna.vn/");
		driver.get("https://kyna.vn/");
		String parentWindow = driver.getWindowHandle();

		System.out.println("	Step 02: Close popup nếu có xuất hiện");
		System.out.println(
				"	Step 03: Click lần lượt vào các link sau tại Footer: Facebook/ Youtube/ Kyna.vn/ ĐÃ THÔNG BÁO BỘ CÔNG THƯƠNG/ ĐÃ ĐĂNG KÝ BỘ CÔNG THƯƠNG");
		System.out.println(
				"	Step 04: Kiểm tra đã chuyển qua trang tab mới thành công: Kiểm tra Url hoặc Title hoặc bất kì 1 phần tử nào để định danh được page đó");

		clickElement(By.xpath("//div[@class='hotline']//img[@alt='facebook']"));
		clickElement(By.xpath("//div[@class='hotline']//img[@alt='youtube']"));

		switchWindowByTitle("Kyna.vn - Trang chủ | Facebook");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Trang chủ | Facebook");
		driver.close();

		switchWindowByTitle("Kyna.vn - YouTube");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - YouTube");

		switchToParentWindow(parentWindow);

		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'kyna.vn')]")));
		clickElement(By.xpath("//a[@title='Kyna.vn']"));
		driver.switchTo().defaultContent();

		clickElement(By.xpath("//img[contains(@src,'dathongbao')]"));

		switchWindowByTitle("Kyna.vn - Trang chủ | Facebook");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Trang chủ | Facebook");

		switchWindowByTitle("Thông tin website thương mại điện tử - Online.Gov.VN");
		Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logoSaleNoti.png')]")).isDisplayed());
		driver.close();
		switchToParentWindow(parentWindow);

		clickElement(By.xpath("//img[contains(@src,'logoCCDV')]"));
		switchWindowByTitle("Thông tin website thương mại điện tử - Online.Gov.VN");
		Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logoCCDV.png')]")).isDisplayed());

		System.out.println("	Step 05: Quay lại parent page và đóng tất cả các tab còn lại");
		switchToParentWindow(parentWindow);
		closeWindowsExceptParentWindow(parentWindow);
	}

	@Test
	public void TC_10_Demoguru_Windows_Tab() {

		System.out.println("\nTC 10 - Demoguru Windows Tab");

		System.out.println("	Step 01: Truy cập vào trang: http://live.demoguru99.com/index.php/");
		driver.get("http://live.demoguru99.com/index.php/");
		
		System.out.println("	Step 02: Click vào Mobile tab");

		System.out.println(
				"	Step 03: Add sản phẩm Sony Xperia vào để COmpare (Add to Compare). Verify text hiển thị: The product Sony Xperia has been added to comparision list.");

		System.out.println(
				"	Step 04: Add sản phẩm Samsung Galaxy vào để Compare (Add to Compare). Verify text hiển thị: The product Samsung Galaxy has been added tp comparision list.");

		System.out.println("	Step 05: Click to Compare button");

		System.out.println("	Step 06: Switch qua cửa sổ mới");

		System.out.println("	Step 07: Verify title cửa sổ mới bằng: Products Comparision List - Magento Commerce");

		System.out.println("	Step 08: Clocse tab và chuyển về Parent Window");

		System.out.println("	Step 09: Click 'Clear All' link và accept alert");

		System.out.println("	Step 10: Verify message xuất hiện: The comparision list was cleared.");

	}

	@AfterClass
	public void AfterClass() {
//		driver.quit();
	}

	public void clickElement(By element) {
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(element));
		driver.findElement(element).click();
	}

	public void switchWindowByID(String parentWindow) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String childWindow : allWindows) {
			if (!childWindow.equals(parentWindow))
				driver.switchTo().window(childWindow);
		}
	}

	public void switchWindowByTitle(String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String childWindow : allWindows) {
			driver.switchTo().window(childWindow);
			String windowTitle = driver.getTitle();
			if (windowTitle.equals(title))
				break;
		}
	}

	public void switchToParentWindow(String parentWindow) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String childWindow : allWindows) {
			if (childWindow.equals(parentWindow))
				driver.switchTo().window(childWindow);
			break;
		}
	}

	public void closeWindowsExceptParentWindow(String parentWindow) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String childWindow : allWindows) {
			if (!childWindow.equals(parentWindow)) {
				driver.switchTo().window(childWindow);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
	}

}
