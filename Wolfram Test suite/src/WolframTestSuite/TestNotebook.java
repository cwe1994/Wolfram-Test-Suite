package WolframTestSuite;

//Notboook page object

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.NoSuchElementException;

public class TestNotebook {
private WebDriver driver;
	
	public TestNotebook(WebDriver driver){
		this.driver = driver;
		if(this.driver.findElements(By.id("toolbarRenameInputField")).size() == 0)
			throw new NoSuchElementException("Name input field missing");
		
		//checks the name field initially says "(unnamed)"
		if((this.driver.findElements(By.xpath("//*[contains(text(), (unnamed) )]" ))).size() == 0) 
			throw new NoSuchElementException("Default input name should contain unnamed");
		
	}
	
	//returns the name field  (should say "(unnamed)")
		public String getNameInputVal(){
			String defaultVal = driver.findElement(By.className("rename-title")).getAttribute("innerHTML");
			return defaultVal;
		}
		
	//returns extension of page element for the notebook name
	public String getNameInputExt(){
		
		driver.findElement(By.id("renameButton")).click();

		String defaultExt = driver.findElement(By.id("toolbarRenameInputField")).getAttribute("value");
		return defaultExt;		
	}
}
