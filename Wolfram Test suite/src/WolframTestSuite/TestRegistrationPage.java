package WolframTestSuite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.NoSuchElementException;

public class TestRegistrationPage {
	private WebDriver driver;
	
	public TestRegistrationPage(WebDriver driver){
		this.driver = driver;
		if (!"Sign UP - Wolfram Programming Cloud".equals(this.driver.getTitle()))
		{//checks the page title
			throw new NoSuchElementException("This is not the login page");
		}
		
		//verifies that the required field are there
		String[] elements = {"email", "firstname", "lastname",  "password", "password2", "signIn"};
		for(int i = 0; i <elements.length; i++){
			if(driver.findElements(By.id(elements[i])).size() == 0)
			{
				throw new NoSuchElementException(elements[i] + "field not present");
			}
		}
	}

}
