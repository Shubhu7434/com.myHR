package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GatepassPage {

	private WebDriver driver;
	private WebDriverWait wait;

	public GatepassPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	}

	// ===Locators======
	@FindBy(xpath = "//span[text()='Home']")
	private WebElement Homemenu;

	@FindBy(xpath = "//span[text()='WFH OD & GatePass']")
	private WebElement Gatepassmenu;

	@FindBy(id = "typeReason")
	private WebElement Reasondropdown;

	@FindBy(id = "fromTime")
	private WebElement Fromtime;

	@FindBy(id = "toTime")
	private WebElement Totime;

	@FindBy(id = "reason")
	private WebElement Reasonfield;

	@FindBy(xpath = "//button[normalize-space()='Submit ']")
	private WebElement Submitbutton;

	@FindBy(xpath = "//button[normalize-space()='Clear ']")
	private WebElement Clearbutton;
	
	@FindBy(xpath = "(//span[text()='Gate Pass Request'])[2]")
	private WebElement gatpassPageTitle;

	// ========utility methods=====
	private void Click(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	private void type(WebElement element, String text) {
		wait.until(ExpectedConditions.visibilityOf(element)).clear();
		element.sendKeys(text);
	}
	
	private void typeandenter(WebElement element, String text) {
		wait.until(ExpectedConditions.visibilityOf(element)).clear();
		element.sendKeys(text ,Keys.ENTER);
	}
	
	public void selectDate(WebElement datePicker, String day, String month, String year) {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click();", datePicker);

	    // Wait for the calendar that is open
	    WebElement calendar = wait.until(ExpectedConditions.visibilityOfElementLocated(
	        By.xpath("//div[contains(@class,'flatpickr-calendar') and contains(@class,'open')]")
	    ));

	    WebElement yearField = calendar.findElement(By.xpath(".//input[contains(@class,'cur-year')]"));
	    WebElement monthDropdown = calendar.findElement(By.xpath(".//select[contains(@class,'flatpickr-monthDropdown-months')]"));

	    // Adjust year only if different
	    String displayedYear = yearField.getAttribute("value");
	    if (!displayedYear.equals(year)) {
	        // Only find arrows if needed
	        WebElement nextMonth = calendar.findElement(By.xpath(".//span[contains(@class,'flatpickr-next-month')]"));
	        WebElement prevMonth = calendar.findElement(By.xpath(".//span[contains(@class,'flatpickr-prev-month')]"));

	        int displayed = Integer.parseInt(displayedYear);
	        int target = Integer.parseInt(year);

	        while (!displayedYear.equals(year)) {
	            if (displayed < target) {
	                nextMonth.click();
	            } else {
	                prevMonth.click();
	            }
	            displayedYear = yearField.getAttribute("value");
	        }
	    }

	    // Select month
	    Select select = new Select(monthDropdown);
	    select.selectByVisibleText(month);

	    // Select day
	    WebElement dayElement = wait.until(ExpectedConditions.elementToBeClickable(
	        calendar.findElement(By.xpath(".//span[contains(@class,'flatpickr-day') and normalize-space(text())='" + day + "']"))
	    ));
	    dayElement.click();
	}
	

	// ===Action =====
	public GatepassPage Clickhomemenu() {
		Click(Homemenu);
		return this;
	}

	public GatepassPage Clickgatepassmenu() {
		Click(Gatepassmenu);
		return this;
	}
	
	public boolean isGatePassPageDisplayed() {
		return wait.until(ExpectedConditions.visibilityOf(gatpassPageTitle)).isDisplayed();
	}
	
	public GatepassPage Selecttype(String text) {
		Click(Reasondropdown);
		typeandenter(Reasondropdown, text);
		return this;
	}
	
	
	

	// =======Methods============
	public GatepassPage Navigategatepasspage() {
    	 Clickhomemenu();
    	Clickgatepassmenu();
    	return this;
    }
	
	public GatepassPage Selectreason(String text) {
		return Selecttype(text);
	}
	
	
	
	
	
	

}
