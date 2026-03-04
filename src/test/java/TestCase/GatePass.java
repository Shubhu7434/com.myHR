package TestCase;

import java.awt.Desktop;
import java.io.File;

import org.jspecify.annotations.Nullable;
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

import Pages.GatepassPage;
import base.BaseTest;

public class GatePass extends BaseTest {

	GatepassPage GP;
	ExtentReports extent;
	ExtentTest test;
	String reportpath = "C:\\Reports\\myHR\\GatePass.html";
	WebDriverWait wait;

	@BeforeSuite
	public void setupreport() {
		ExtentSparkReporter report = new ExtentSparkReporter(reportpath);
		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Projectname", "myHR");
		extent.setSystemInfo("Module", "GatePass");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Tester", "Shubham Mohite");

	}
	
	@BeforeMethod
	public void initpage() {
		Login();
		GP = new GatepassPage(driver);
	}
	
	@Test(enabled =  false)
	public void NavigateGatePassPage() {
		test = extent.createTest("Verify Gate Pass Page Open Successfully");
		GP.Navigategatepasspage();
		@Nullable
		String pagetitle = driver.getTitle();
		org.testng.Reporter.log("Page Title is_-: " +pagetitle , true);
		WebElement Pagename = driver.findElement(By.xpath("(//span[text()='Gate Pass Request'])[2]"));
		org.testng.Reporter.log("Page Name -:" + Pagename.getText() , true );
		
		try {
			Assert.assertTrue(GP.isGatePassPageDisplayed() ," GatePass Page Not Open Successfully");
			test.pass("Gate Pass Page Open Successfully");
		} catch (AssertionError e) {
			test.fail("GatePass Page Not Open Successfully");
			throw e;
		}
		
	 }
	@Test
	public void Selectreasonoption() {
		test = extent.createTest("Verify User Able To Select Gate Pass Option Successfully");
		GP.Navigategatepasspage();
		GP.Selectreason("Personal");
	}
	
	
	@AfterSuite
	public void teardownreport() {
		extent.flush();
		try {
			File reportfile = new File(reportpath);
			if(reportfile.exists()) {
				Desktop.getDesktop().browse(reportfile.toURI());
				System.out.println("Extent Report Open Automatically on defualt browser");
			} else {
				System.out.println("Extent Report Not Open Automatically on defualt browser");
			}
		} catch (Exception e) {
			System.out.println("Extent Report Open Automatically on defualt browser ,Open Manually");
		}
	}
	
	
	
	

}
