package TestCase;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParameterLoginTest  {
	
	WebDriver driver ;
	
	@BeforeMethod
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));	
	}
	
	@Parameters({"Username" , "Password"})
	@Test()
	public void Login(String Username , String Password) {
		driver.get("http://192.168.21.94:8023/");
		driver.findElement(By.id("emailOrUsername")).sendKeys(Username);
		driver.findElement(By.id("password")).sendKeys(Password);
		driver.findElement(By.xpath("//button[contains(text(),' Sign In ')]")).click();
	}
	
	
	
	

}
