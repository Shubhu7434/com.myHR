package TestCase;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderLoginTest {
	
	WebDriver driver ; 
	
	@DataProvider(name = "LoginData")
	public Object[][] getData(){
		return new Object[][] {
			{"24118" , "62abce"},
			{"24738" , "62abce"},
			{"24525" , "62abce"}
		};
	}
	
	@Test(dataProvider = "LoginData")
	public void Logintest(String Username , String Password) {
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.get("http://192.168.21.94:8023/");
		driver.findElement(By.id("emailOrUsername")).sendKeys(Username);
		driver.findElement(By.id("password")).sendKeys(Password);
		driver.findElement(By.xpath("//button[contains(text(),' Sign In ')]")).click();
		
	}
	

}
