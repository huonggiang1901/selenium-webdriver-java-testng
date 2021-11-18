package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_Command_II {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	By emailTextbox = By.xpath("//input[@id='mail']");
	By eduTextbox = By.xpath("//textarea[@id='edu']");
	By ageUnder18Radiobutton = By.xpath("//input[@id='under_18']");
	By user5Text = By.xpath("//h5[text()='Name: User5']");
	By jobRole01Dropdown = By.xpath("//select[@id='job1']");
	By jobRole02Dropdown = By.xpath("//select[@id='job2']");
	By jobRole03Dropdown = By.xpath("//select[@id='job3']");
	By developmentCheckbox = By.xpath("//input[@id='development']");
	By disabledPasswordTextbox = By.xpath("//input[@id='password' and @disabled]");
	By radioDisabledRadiobutton = By.xpath("//input[@id='radio-disabled']");
	By biographyTextarea = By.xpath("//textarea[@id='bio']");
	By checkDisabledCheckbox = By.xpath("//input[@id='check-disbaled']");
	By slider01 = By.xpath("//input[@id='slider-1']");
	By slider02 = By.xpath("//input[@id='slider-2']");
	By javaCheckbox = By.xpath("//input[@id='java']");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Kiểm_tra_phần_tử_displayed() {

		System.out.println("TC 01 - Kiểm tra phần tử displayed");

		System.out.println("	Step 01: Truy cập vào trang: https://automationfc.github.io/basic-form/index.html");
		driver.get("https://automationfc.github.io/basic-form/index.html");

		System.out
				.println("	Step 02: Kiểm tra các phần tử sau hiển thị trên trang: Email, Age (Under 18), Education");
		// Nếu có hiển thị thì nhập giá trị
		// Autonation Testing vào 2 field Email/ Education
		// Click chọn Age = Under 18
		if (isElementDisplayed(emailTextbox) == true)
			sendKeysToElement(emailTextbox, "Automation Testing");

		if (isElementDisplayed(eduTextbox) == true)
			sendKeysToElement(eduTextbox, "Automation Testing");

		if (isElementDisplayed(ageUnder18Radiobutton) == true)
			driver.findElement(ageUnder18Radiobutton).click();

		System.out.println("	Step 03: Kiểm tra các phần tử sau không hiển thị trên trang: Name: User5");
		isElementDisplayed(user5Text);
	}

	@Test
	public void TC_02_Kiểm_tra_phần_tử_enabled_hay_disabled() {

		System.out.println("\nTC 02 - Kiểm tra phần tử enabled hay disabled");

		System.out.println("	Step 01: Truy cập vào trang: https://automationfc.github.io/basic-form/index.html");
		driver.get("https://automationfc.github.io/basic-form/index.html");

		System.out.println(
				"	Step 02: Kiểm tra các phần tử sau enable trên trang: Email, Age (under 18), Education, Job Role 01/ Job Role 02, Interest (Development) Checkbox, Slider 01");
		Assert.assertTrue(isElementEnabled(emailTextbox));
		Assert.assertTrue(isElementEnabled(ageUnder18Radiobutton));
		Assert.assertTrue(isElementEnabled(eduTextbox));
		Assert.assertTrue(isElementEnabled(jobRole01Dropdown));
		Assert.assertTrue(isElementEnabled(jobRole02Dropdown));
		Assert.assertTrue(isElementEnabled(developmentCheckbox));
		Assert.assertTrue(isElementEnabled(slider01));

		System.out.println(
				"	Step 03: Kiểm tra các phần tử sau disable trên trang: Password, Age (Radiobutton is disable), Biography, Job Role 03, Interests (Checkbox is disabled), Slider 02 (Disabled)");
		Assert.assertFalse(isElementEnabled(disabledPasswordTextbox));
		Assert.assertFalse(isElementEnabled(radioDisabledRadiobutton));
		Assert.assertFalse(isElementEnabled(biographyTextarea));
		Assert.assertFalse(isElementEnabled(jobRole03Dropdown));
		Assert.assertFalse(isElementEnabled(checkDisabledCheckbox));
		Assert.assertFalse(isElementEnabled(slider02));
	}

	@Test
	public void TC_03_Kiểm_tra_phần_tử_selected() {

		System.out.println("\nTC 03 - Kiểm tra phần tử selected");

		System.out.println("	Step 01: Truy cập vào trang: https://automationfc.github.io/basic-form/index.html");
		driver.get("https://automationfc.github.io/basic-form/index.html");

		System.out.println("	Step 02: Click chọn: Age (Undoer 18) radio button, \"Languages: Java\" checkbox");
		clickElement(ageUnder18Radiobutton);
		clickElement(javaCheckbox);

		System.out.println("	Step 03: Kiểm tra các phần tử tại Step 02 đã được chọn");
		Assert.assertTrue(isElementSelected(ageUnder18Radiobutton));
		Assert.assertTrue(isElementSelected(javaCheckbox));

		System.out.println("	Step 04: Click để bỏ chọn \"Languages: Java\" checkbox");
		clickElement(javaCheckbox);

		System.out.println("	Step 05:  Kiểm tra các phần tữ \"Landguages: Java\" đã được bỏ chọn");
		Assert.assertFalse(isElementSelected(javaCheckbox));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public boolean isElementDisplayed(By locator) {
		if (driver.findElement(locator).isDisplayed()) {
			System.out.println(locator + " is displayed ");
			return true;
		} else
			System.out.println(locator + " is not displayed ");
		return false;
	}

	public boolean isElementEnabled(By locator) {
		if (driver.findElement(locator).isEnabled()) {
			System.out.println(locator + " is enabled ");
			return true;
		} else
			System.out.println(locator + " is disabled ");
		return false;
	}

	public boolean isElementSelected(By locator) {
		if (driver.findElement(locator).isSelected()) {
			System.out.println(locator + " is selected ");
			return true;
		} else
			System.out.println(locator + " is deselected ");
		return false;
	}

	public void sendKeysToElement(By locator, String value) {
		WebElement element = driver.findElement(locator);
		element.clear();
		element.sendKeys(value);
	}

	public void clickElement(By locator) {
		driver.findElement(locator).click();
	}
}
