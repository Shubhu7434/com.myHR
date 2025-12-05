package TestCase;

import java.awt.Desktop;
import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Pages.LoginPage;
import base.BaseTest;

public class Loginpage extends BaseTest {
	
	Logger log = LogManager.getLogger(Loginpage.class);

	LoginPage LP;
	ExtentReports extent;
	ExtentTest test;
	String reportpath = "C:\\Reports\\myHR\\LoginPage.html";

	@BeforeSuite
	public void setupReport() {
		ExtentSparkReporter report = new ExtentSparkReporter(reportpath);
		extent = new ExtentReports();
		extent.attachReporter(report);
	}

	@BeforeMethod
	public void setup() {
		Setup();
		LP = new LoginPage(driver);
		driver.get("http://192.168.21.94:8023/");
	}

	@Test()
	public void Validlogin() {
		log.info("Stariting test : Login test");
		test = extent.createTest("Verfy User Able To Login With Valid Credentails");
		LP.Login("24118" , "62abce");
		
		WebElement Header = driver.findElement(By.xpath("//h1[contains(text(),'Welcome back')]"));
		
		try {
			Assert.assertTrue(Header.isDisplayed() , "After Login User Should Not be Navigate Home Page");
			test.pass("User Should Be Successfully Navigate Home Page After Login");
			log.info("Login Test Pass");
		} catch (AssertionError e) {
			log.error("Login test Fail");
			test.fail("After Login User Should Not be Navigate Home Page");
			throw e;
		}
		

	}
	
	@Test()
	public void LoginwithInvalidUsernameValidPasswrd() {
	     test = extent.createTest("Verify Login With Invalid username And Valid Passwrod");
	     LP.Login("27000", "62abce");
	     
	    WebElement errormsg = driver.findElement(By.xpath("//span[contains(text(),'User is Inactive ')]"));
	    
	    try {
			Assert.assertTrue(errormsg.isDisplayed() , "Error Message User Should Not Exist Not Display ");
			test.pass("Error Message User Should Not Exist Is  Display");
		} catch (AssertionError e) {
			test.fail("Error Message User Should Not Exist Not Display");
			throw e;
		}
	}
	
	@Test()
	public void LoginWithValidEmployeeecodeandinvalidpasssword() {
		test = extent.createTest("Verify Login With Valid Employee Code And Invalid Password");
		LP.Login("24738", "6215454ssd");
		
		WebElement errormsg = driver.findElement(By.xpath("//span[contains(text(),'Please enter a valid password.')]"));
		
		try {
			Assert.assertTrue(errormsg.isDisplayed() , "Error Message For Invalid Password Not Display ");
			test.pass("Error Message For Invalid Password Has Been  Display");
		} catch (AssertionError e) {
			test.fail("Error Message For Invalid Password Not Display");
			throw e;
		}
		
	}

	@AfterSuite
	public void teardownReport() {
		extent.flush();

		
		  try { File reportfile = new File(reportpath); if (reportfile.exists()) {
		  Desktop.getDesktop().browse(reportfile.toURI());
		  System.out.println("Extent Report Open Automatically On Defualt Browser"); }
		  else {
		  System.out.println("Extent Report Not Open Automatically On Defualt Browser"
		  ); } } catch (Exception e) { System.out.
		  println("Extent Report Not Open Automatically On Defualt Browser , Open Manually"
		  ); }
		 

	}

}
