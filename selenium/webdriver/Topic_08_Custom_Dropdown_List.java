package webdriver;

import java.util.List;
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

public class Topic_08_Custom_Dropdown_List {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor js;

	String projectPath = System.getProperty("user.dir");

	String selectedNumber = "19";
	String selectedName = "Justen Kitsune";
	String selectedOption = "Third Option";
	String selectedGame = "Tennis";
	String selectedCarBrand = "Volvo";
	String selectedCountry = "Benin";

	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 15);
		js = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Xử_lí_JQuery_Custom_Dropdown_List() {

		System.out.println("TC 01 - Xử lí JQuery Custom Dropdown List");

		System.out
				.println("	Step 01: Truy cập vào trang: http://jqueryui.com/resources/demos/selectmenu/default.html");
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

		System.out.println("	Step 02: Chọn item cuối cùng: số 19");
		By customDropdown = By.xpath("//span[@id='number-button']");
		By options = By.xpath("//ul[@id='number-menu']//div[@role='option']");
		selectItemInCustomDropdown(customDropdown, options, selectedNumber);

		System.out.println("	Step 03: Kiểm tra item đã dược chọn thành công");
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(),
				selectedNumber);
	}

	@Test
	public void TC_02_Xử_lí_ReactJS_Custom_Dropdown_List() {
		System.out.println("\nTC 02 - Xử lí ReactJS Custom Dropdown List");

		System.out.println(
				"	Step 01: Truy cập vào trang: https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		System.out.println("	Step 02: Chọn item cuối cùng: Justen Kitsune");
		By customDropdown = By.xpath("//div[@role='listbox']");
		By options = By.xpath("//div[@class='visible menu transition']/div[@role='option']");
		selectItemInCustomDropdown(customDropdown, options, selectedName);

		System.out.println("	Step 03: Kiểm tra item đã dược chọn thành công");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), selectedName);
	}

	@Test
	public void TC_03_Xử_lí_VueJS_Custom_Dropdown_List() {
		System.out.println("\nTC 03 - Xử lí VueJS Custom Dropdown List");

		System.out.println("	Step 01: Truy cập vào trang: https://mikerodham.github.io/vue-dropdowns/");
		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		System.out.println("	Step 02: Chọn item cuối cùng: Third Option");
		By customDropdown = By.xpath("//li[@class='dropdown-toggle']");
		By options = By.xpath("//ul[@class='dropdown-menu']//a");
		selectItemInCustomDropdown(customDropdown, options, selectedOption);

		System.out.println("	Step 03: Kiểm tra item đã dược chọn thành công");
		Assert.assertEquals(driver.findElement(customDropdown).getText(), selectedOption);
	}

	@Test
	public void TC_04_Xử_lí_Angular_Custom_Dropdown_List() {
		System.out.println("\nTC 04 - Xử lí Angular Custom Dropdown List");

		System.out.println(
				"	Step 01: Truy cập vào trang: https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		driver.get(
				"https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");

		System.out.println("	Step 02: Chọn item cuối cùng: Third Option");
		By customDropdown = By.xpath("//span[@aria-owns='games_options']");
		By options = By.xpath("//ul[@id='games_options']/li");
		selectItemInCustomDropdown(customDropdown, options, selectedGame);

		System.out.println("	Step 03: Kiểm tra item đã dược chọn thành công");
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[@aria-owns='games_options']//input")).getAttribute("aria-label"),
				selectedGame);
	}

	@Test
	public void TC_05_Xử_lí_JQuery_Editable_Custom_Dropdown_List() {
		System.out.println("\nTC 05 - Xử lí JQuery Editable Custom Dropdown List");

		System.out.println("	Step 01: Truy cập vào trang: http://indrimuska.github.io/jquery-editable-select/");
		driver.get("http://indrimuska.github.io/jquery-editable-select/");

		System.out.println("	Step 02: Chọn item cuối cùng: Volvo");
		By customDropdown = By.xpath("//div[@id='default-place']/input");
		By options = By.xpath("//ul[@class='es-list' and @style]/li");
		selectItemInEditableDropdown(customDropdown, options, selectedCarBrand);

		System.out.println("	Step 03: Kiểm tra item đã dược chọn thành công");
		Assert.assertEquals(
				driver.findElement(By.xpath("//div[@id='default-place']//li[@class='es-visible']")).getText(),
				selectedCarBrand);
	}

	@Test
	public void TC_06_Xử_lí_React_Editable_Custom_Dropdown_List() {
		System.out.println("\nTC 06 - Xử lí React Editable Custom Dropdown List");

		System.out.println(
				"	Step 01: Truy cập vào trang: https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

		System.out.println("	Step 02: Chọn item cuối cùng: Benin");
		By customDropdown = By.xpath("//input[@class='search']");
		By options = By.xpath("//div[@role='listbox']/div");
		selectItemInEditableDropdown(customDropdown, options, selectedCountry);

		System.out.println("	Step 03: Kiểm tra item đã dược chọn thành công");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), selectedCountry);
	}

	@Test
	public void TC_07_Xử_lí_Multiple_Custom_Dropdown_List() {
		System.out.println("\nTC 06 - Xử lí React Editable Custom Dropdown List");

		System.out.println(
				"	Step 01: Truy cập vào trang: https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
		driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");

		System.out.println("	Step 02: Chọn item cuối cùng: Benin");
		By customDropdown = By.xpath("(//button[@class='ms-choice'])[1]");
		By options = By.xpath("(//button[@class='ms-choice'])[1]/following-sibling::div//span");
		By selectedItem = By.xpath("//li[@class='selected']//input");
		String[] months = { "January", "May", "October" };
		selectItemsInMultipleDropdown(customDropdown, options, selectedItem, months);

		System.out.println("	Step 03: Kiểm tra item đã dược chọn thành công");
		areItemsSelected(months);
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}

	public void selectItemInCustomDropdown(By customDropdown, By options, String expectedItem) {
		// Click vào 1 element cho nó xổ ra tất cả option
		driver.findElement(customDropdown).click();
		// Wait cho tất cả option được load ra (có trong HTML/DOM)
		// Store lại tất cả option (item của dropdown)
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(options));
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				// Nếu item mình cần chọn nằm trong view thì click vào
				if (item.isDisplayed())
					item.click();
				// Nếu item cần chọn ko nằm trong view thì scroll xuống rồi click vào
				else {
					js.executeScript("arguments[0].scrollIntoView(true)", item);
					item.click();
				}
				break;
			}
		}
	}

	public void selectItemInEditableDropdown(By customDropdown, By options, String expectedItem) {
		// Truyền vào editable dropdown item ->dropdown phải là textbox (thẻ input)
		driver.findElement(customDropdown).clear();
		driver.findElement(customDropdown).sendKeys(expectedItem);
		// Wait cho tất cả option được load ra (có trong HTML/DOM)
		// Store lại tất cả option (item của dropdown)
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(options));
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				// Nếu item mình cần chọn nằm trong view thì click vào
				if (item.isDisplayed())
					item.click();
				// Nếu item cần chọn ko nằm trong view thì scroll xuống rồi click vào
				else {
					js.executeScript("arguments[0].scrollIntoView(true)", item);
					item.click();
				}
				break;
			}
		}
	}

	public void selectItemsInMultipleDropdown(By customDropdown, By options, By selectedItem, String[] expectedItem) {
		driver.findElement(customDropdown).click();

		List<WebElement> allOptions = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(options));

		// Duyệt qua hết tất cả option
		for (WebElement option : allOptions) {

			// Duyệt qua tất cả item trong mảng String[] expectedItem
			for (String item : expectedItem) {
				if (option.getText().equals(item)) {
					js.executeScript("arguments[0].scrollIntoView(true)", option);
					option.click();

					List<WebElement> selectedOption = driver.findElements(selectedItem);
					if (expectedItem.length == selectedOption.size())
						break;
				}
			}
		}
	}

	public boolean areItemsSelected(String[] months) {
		List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
		int numberItemSelected = itemSelected.size();

		String allItemSelectedText = driver.findElement(By.xpath("(//button[@class='ms-choice']/span)[1]")).getText();

		if (numberItemSelected <= 3 && numberItemSelected > 0) {
			boolean status = true;
			for (String item : months) {
				if (!allItemSelectedText.contains(item)) {
					status = false;
					return status;
				}
			}
			return status;
		} else if (numberItemSelected == 12) {
			return driver.findElement(By.xpath("//button[@class='ms-choice'/spac[text()='All selected']"))
					.isDisplayed();
		} else if (numberItemSelected > 3 && numberItemSelected < 12) {
			return driver
					.findElement(By.xpath(
							"//button[@class='ms-choice'/spac[text()='" + numberItemSelected + " of 12 selected']"))
					.isDisplayed();
		} else {
			return false;
		}
	}

	public void sleepInSeconds(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
