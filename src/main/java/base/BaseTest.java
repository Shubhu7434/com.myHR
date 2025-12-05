package base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {
	
	protected WebDriver driver ;
	public WebDriverWait wait ;
	
	public void Setup() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	public void Login() {
		driver.get("http://192.168.21.94:8023/");
		driver.findElement(By.id("emailOrUsername")).sendKeys("24118");
		driver.findElement(By.id("password")).sendKeys("62abce");
		driver.findElement(By.xpath("//button[contains(text(),' Sign In ')]")).click();
	}
	
	
	public WebDriver getDriver() {
		return driver;
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
	
	

}
