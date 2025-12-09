package TestCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utilities.Excelutiles;
import base.BaseTest;

public class ExcelUtilLoginTest extends BaseTest{
	
	
	@BeforeMethod
	public void setup() {
		//Setup();
	}
	
	@DataProvider(name = "ExcelData")
	public Object[][] getData(){
		//String Filepath = "src/test/resources/myHRTestData.xlsx";
		return Excelutiles.getExcelData("src/test/resources/myHRTestData.xlsx", "Sheet1");
	}
	
	@Test(dataProvider = "ExcelData")
	public void Login( String Username , String Password ,String URL ) {
		driver.get(URL);
		driver.findElement(By.id("emailOrUsername")).sendKeys(Username);
		driver.findElement(By.id("password")).sendKeys(Password);
		driver.findElement(By.xpath("//button[contains(text(),' Sign In ')]")).click();
	}

}
