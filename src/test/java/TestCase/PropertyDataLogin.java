package TestCase;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import Utilities.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class PropertyDataLogin {
	
	WebDriver driver ;
		
		@Test
		public void login() {
			String browser = ConfigReader.getproperty("Browser");
			String url = ConfigReader.getproperty("URL");
			String username = ConfigReader.getproperty("Username");
			String password = ConfigReader.getproperty("Password");
			
			//Initialize Browser 
			if(browser.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			} else if ( browser.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			}  else {
				throw new RuntimeException ("Unsupported  browser " +browser);
			}
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.get(url);
			
			driver.findElement(By.id("emailOrUsername")).sendKeys(username);
			driver.findElement(By.id("password")).sendKeys(password);
			driver.findElement(By.xpath("//button[contains(text(),' Sign In ')]")).click();
		}
				
	

}
