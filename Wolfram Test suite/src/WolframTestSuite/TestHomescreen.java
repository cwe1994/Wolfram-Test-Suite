package WolframTestSuite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

public class TestHomescreen {
	private WebDriver driver;
	
	public TestHomescreen(WebDriver driver){
		this.driver = driver;
	}
	
	
	public TestNotebook createNb(String type){
		TestDocType newType = TestDocType.valueOf(type);
		//clarifies and allows for more general tests
		
		driver.findElement(By.className("newNotebookBtn-dropdown")).click();
		driver.findElement(By.id(newType.getId())).click();
		
		driver.manage().timeouts().implicitlyWait(10,  TimeUnit.SECONDS);
		//switch to a new window
		for(String handle : driver.getWindowHandles()){
			driver.switchTo().window(handle);
		}
		return new TestNotebook(driver);
	}
}
