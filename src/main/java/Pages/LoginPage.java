package Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	private WebDriver driver ;
	private WebDriverWait wait ;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver , this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	//=========Locators ==========
	@FindBy(id = "emailOrUsername")
	private WebElement EMPcode;
	
	@FindBy(id =  "password")
	private WebElement Password;
	
	@FindBy(xpath = "//button[contains(text(),' Sign In ')]")
	private WebElement SignInButton;
	
	//=====Actions============
	public LoginPage enterUsername(String username) {
		EMPcode.sendKeys(username);
		return this;
	}
	
	
	public LoginPage enterPassword(String password) {
		Password.sendKeys(password);
		return this;
	}
	
	public LoginPage clickSignin() {
		SignInButton.click();
		return this;
	}
	
	//====Workflow Methods=======
	
	public LoginPage Login(String username , String password ) {
		return enterUsername(username)
				.enterPassword(password)
				.clickSignin();
	}
	
	

}

