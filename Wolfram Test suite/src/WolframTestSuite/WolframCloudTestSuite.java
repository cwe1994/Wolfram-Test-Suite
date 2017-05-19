package WolframTestSuite;

/*This suite tests the following
 * 1. Launch of Wolfram Cloud
 * 2. Create new Wolfram account and checks error responses
 * 3. Access Homescreen with new account
 * 4. Creation of new Notebook file with unnamed title
 */

import org.testng.annotations.*;
import org.testng.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;
import java.util.NoSuchElementException;
import java.util.Random;

public class WolframCloudTestSuite {
	protected WebDriver driver;
	protected String baseURL;
	private TestCloudPage myCloudPage;
	private TestLoginPage myLoginPage;
	private TestRegistrationPage myRegistrationPage;
	private TestHomescreen myHomescreen;
	private TestNotebook myNotebook;
	
	@BeforeSuite
	public void setUp() throws Exception{
		driver = new ChromeDriver();
		baseURL = "https://www.wolframcloud.com/";
		System.out.println("Before Suite.");
		driver.manage().timeouts().implicitlyWait(5,  TimeUnit.SECONDS);
	}
	
	@AfterSuite
	public void cleanUp() throws Exception{
		driver.quit();
		System.out.println("After Suite.");
		
	}
	
	//Tests the launch of the Wolfram Cloud Site
	@Test(groups={"functest", "usrexptest"}, priority=1)
	public void testLaunch(){
		driver.get(baseURL); //waits for page to load
		try{
			this.myCloudPage = new TestCloudPage(driver);
		}catch(NoSuchElementException e){
			System.out.println("CloudPage did not load properly");
		}

		Assert.assertTrue(driver.getTitle().contains("Wolfram Cloud"));
		
	}
	
	//tests the login page's functionality and user experience
	@Test(groups={"functest", "usrexptest"}, priority=2)
	public void tryLogin(){
		try{
			this.myLoginPage = myCloudPage.getLoginPage();
		}
		catch(NoSuchElementException e){
			System.out.println("Login page could not be created");
		}
		Assert.assertTrue(driver.getTitle().contains("Sign In - Wolfram Programming Cloud"));
	}
	
	//Tests the account registration page
	@Test(groups={"functest")
	
}
