package WolframTestSuite;
//Login Page object 

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;
import java.util.NoSuchElementException;

public class TestLoginPage {
	private WebDriver driver;
	
	public TestLoginPage(WebDriver driver){
		this.driver = driver;
		//verify "create id" link is present
		if(this.driver.findElements(By.id("signIn")).size() == 0){
			throw new NoSuchElementException();
		}
	}
	
	//returns registration page to create a new account
	public TestRegistrationPage getRegistrationPage(){
		driver.findElement(By.id("createAccount")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return new TestRegistrationPage(driver);
	}
	
}
