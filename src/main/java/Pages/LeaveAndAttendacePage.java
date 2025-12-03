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

public class LeaveAndAttendacePage {
	
	private WebDriver driver ;
	private WebDriverWait wait ;
	
	public LeaveAndAttendacePage ( WebDriver driver) {
		this.driver = driver ;
		PageFactory.initElements( driver , this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	}
	
	//=======Locators==========
	@FindBy(xpath = "//span[text()='Home']")
	private WebElement Homemenu;
	
	@FindBy(xpath = "//span[contains(text(),'My Leaves & Attendance')] ")
	private WebElement LeaveandAttendancemenu;
	
	@FindBy(xpath =  "//div[@class='ng-select-container']")
	private WebElement Leavetypedropdown;
	                                                                                         
	@FindBy(xpath =  "//div[@class='ng-input']//input[@type='text']")
	private WebElement SearchLeavetype;
	
	@FindBy(id = "fromDate")
	private WebElement FromDatePicker;
	
	@FindBy(id = "toDate")
	private WebElement ToDatePicker;
	
	@FindBy(xpath = "//input[@class='numInput cur-year']")
	private WebElement CurrentYear;
	
	@FindBy(xpath = "//span[@class='flatpickr-next-month']")
	private WebElement NextMonthButton;
	
	@FindBy(xpath = "//span[@class='flatpickr-prev-month']")
	private WebElement PreviousMonthButton;
	
	@FindBy(xpath = "//div[contains(@class,'flatpickr-calendar')]//select[contains(@class,'flatpickr-monthDropdown-months')]")
	private WebElement Monthdropdown;
	
	
	//====Utility Methods ==========
	
	private void Click(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	
	}
	
	private void type(WebElement element , String text) {
		wait.until(ExpectedConditions.visibilityOf(element)).clear();
			element.sendKeys(text);	
	}
	
	private void typeandenter(WebElement element ,String text) {
		wait.until(ExpectedConditions.visibilityOf(element)).clear();
		element.sendKeys(text , Keys.ENTER);	
	}
	
	//====== Actions===========
	
	public LeaveAndAttendacePage ClickHomemenu() {
		Click(Homemenu);
		return this;
	}
	
	public LeaveAndAttendacePage Clickleaveandattendance() {
		Click(LeaveandAttendancemenu);
		return this;
	}
	
	public LeaveAndAttendacePage ClickLeavedropdown() {
		Click(Leavetypedropdown);
		return this;
	}
	
	public LeaveAndAttendacePage Selectleavetype(String text) {
		typeandenter(SearchLeavetype, text);
		return this;
	}
	
	public LeaveAndAttendacePage ClickFromcalendar() {
		Click(FromDatePicker);
		return this;
	}
	
	public LeaveAndAttendacePage ClickTocalendar() {
		Click(ToDatePicker);
		return this;
	}
	
	  //  Date Picker Reusable Method from Date 
	
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
	
	//==============Methods==============
	public LeaveAndAttendacePage NavigatePage() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		ClickHomemenu();
		Clickleaveandattendance();
		return this;
	}
	
	public LeaveAndAttendacePage SelectLeavetype(String text) {
		Click(Leavetypedropdown);
		typeandenter(SearchLeavetype, text);
		return this;
	}
	
	// Getter Method For Date 
	public WebElement getFromDatePicker() {
	    return FromDatePicker;
	}

	public WebElement getToDatePicker() {
	    return ToDatePicker;
	}
	
	
	
}
	
	
	

	

