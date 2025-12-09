package TestCase;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Pages.LeaveAndAttendacePage;
import Utilities.LoggerUtil;
import base.BaseTest;

public class LeaveandAttendance extends BaseTest {

	Logger log = LoggerUtil.getLogger(LeaveandAttendance.class);

	LeaveAndAttendacePage LA;
	ExtentReports extent;
	ExtentTest test;
	WebDriverWait wait;
	String reportpath = "C:\\Reports\\myHR\\LeaveandAttendance.html";

	@BeforeSuite
	public void setupReport() {
		ExtentSparkReporter report = new ExtentSparkReporter(reportpath);
		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("ProjectName", "myHR");
		extent.setSystemInfo("Module", "LeaveAnd Attendance");
		extent.setSystemInfo("Tester", "Shubham Mohite");
		// extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("Environment", "QA");
	}

	@BeforeMethod
	public void initPage() {
		// Setup();
		Login();
		LA = new LeaveAndAttendacePage(driver);

	}

	@Test()
	public void NavigatePage() throws InterruptedException {
		test = extent.createTest("Verify Leave And Attendance Page Open Successfully");
		LA.NavigatePage();
		LA.SelectLeavetype("Sick");
		LA.selectDate(LA.getFromDatePicker(), "1", "January", "2026");
		Thread.sleep(500);
		LA.selectDate(LA.getToDatePicker(), "2", "February", "2026");

		WebElement pagename = driver.findElement(By.xpath("(//span[text()='Leave Request'])[2]"));

		try {
			Assert.assertTrue(pagename.isDisplayed(), " Leave and Attendace Page NOt Open Successfully");
			test.pass("Leave and Attendace Page Open Successfully");

		} catch (AssertionError e) {
			test.fail("Leave and Attendace Page NOt Open Successfully");
			throw e;
		}
	}

	@Test
	public void CheckLeaveBalace() {
		test = extent.createTest("Check After Select Leave Type Leav Balace Display Correct");
		LA.NavigatePage();
		LA.SelectLeavetype("privilege");

		// Get Leave Balance From Balance Field
		WebElement leavebalance = driver.findElement(By.id("balance"));
		String UiBalance = leavebalance.getAttribute("value").trim();
		log.info("Display Balance After Select PL = " + UiBalance);

		// Get Leave Balance Form Leave Details Table
		WebElement Balance = driver.findElement(By.xpath("//td[text()='PL']/following-sibling::td[3]"));
		String GridBalnace = Balance.getText();
		log.info("Leave Details Table PL Leave Balance = " + GridBalnace);

		try {
			Assert.assertEquals(UiBalance, GridBalnace, "After Select leave type Leave Balance Not Display Correct");
			test.pass("After Select leave type Leave Balance Display Correct");
			log.info("After Select leave type Leave Balance Display Correct");
		} catch (AssertionError e) {
			test.fail("After Select leave type Leave Balance Not Display Correct");
			log.error("After Select leave type Leave Balance Not Display Correct");
			throw e;
		}

	}

	@AfterSuite
	public void teardownreport() {
		extent.flush();
	}

}
