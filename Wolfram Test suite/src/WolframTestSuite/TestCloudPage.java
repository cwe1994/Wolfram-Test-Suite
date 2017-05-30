package WolframTestSuite;

//Wolfram Cloud Page object
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;
import java.util.NoSuchElementException;

public class TestCloudPage {
	private WebDriver driver;
	
	public TestCloudPage(WebDriver driver){
		this.driver = driver;
		//verify the Programming Cloud icon is present
		if (this.driver.findElements(By.id("Wolfram Cloud")).size() == 0){
			throw new NoSuchElementException("CloudPage: Programming Cloud icon is not present");
		}
	}
	
	public TestLoginPage getLoginPage(){
		driver.findElement(By.id("development-tile")).click(); // clicks development platform icon
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //what if timeout
		return new TestLoginPage(driver); //returns a LoginPage object
	}
}
