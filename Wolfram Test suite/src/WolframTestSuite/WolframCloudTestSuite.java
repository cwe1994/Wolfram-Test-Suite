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
import java.io.File;
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
		File file = new File("C:/Selenium/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
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
	public void suiteLaunch(){
		driver.get(baseURL); //waits for page to load
		System.out.println("URL gotten.");
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
	@Test(groups={"functest",  "usrexptest"}, priority=3)
	public void tryRegistration(){
		try{
			this.myRegistrationPage = myLoginPage.getRegistrationPage();
		}
		catch(NoSuchElementException e){
			System.out.println("Registration page failed to load");
		}
		
		Assert.assertTrue(driver.getTitle().contains("Sign Up - Wolfram Programming Cloud"));
		
		//uses random number generator to create login name and password
		Random rand = new Random();
		//six character password is required
		int randInt = rand.nextInt(899999) + 100000;
		String email = Integer.toString(randInt) + "@test.com";
		String firstname = "test";
		String lastname = "suite";
		String password = Integer.toString(randInt);
		
		String[] info = {email, firstname, lastname, password};
		
		//checks that various invalid input produces errors
		String[] info2 = new String[4];
		System.arraycopy(info,  0,  info2,  0,  info.length);
		
		//missing entries should generate errors
		for(int i = 0; i < 4; i++){
			//replace value with empty string
			info2[i] = info2[i].replace(info[i],  "");
			myRegistrationPage.register(info2);
			Assert.assertTrue(driver.findElement(By.className("error")).isDisplayed());
			System.arraycopy(info,  0,  info2,  0,  info.length);
		}
		
		//less than 6 characters for password should generate an error
		info2[info.length-1] = info2[info.length-1].replace(info[info.length-1], Integer.toString(randInt/10));
		myRegistrationPage.register(info2);
		Assert.assertTrue(driver.findElement(By.className("error")).isDisplayed());
		
		//check the valid entries
		try{
			this.myHomescreen = myRegistrationPage.register(info);
		}
		catch(NoSuchElementException e){
			System.out.println("Could not register properly");
		}
		//check that the form entries created account successfully
		Assert.assertEquals(driver.getTitle(),"Home - Wolfram Programming Cloud");
	}
	
	//tests generation of Homescreen and contents
	@Test(groups={"usrexptest"}, priority=4)
	public void tryHomescreen(){
		//Double check we are on homescreen
		Assert.assertEquals(driver.getTitle(), "Home - Wolfram Programming Cloud");
		
		//checks for upload button
		//checks for other elements similarly
		Assert.assertTrue(driver.findElements(By.id("uploadBtn")).size() > 0);
	}
	
	//tests the notebook document interface
	@Test(groups={"functest", "usrexptest"}, priority=5)
	public void tryNotebook(){
		//specify what type of document to create
		//can change to other DocTypes for more tests
		String newDocType = "Notebook";
		
		try{
			this.myNotebook = myHomescreen.createNb(newDocType);
		}
		catch(NoSuchElementException e){
			System.out.println(newDocType + "cannot be created");
		}
		
		Assert.assertTrue(driver.getTitle().contains("(unnamed) - Wolfram Programming Cloud"));
		
		//checks that the name field reads "(unnamed)"
		Assert.assertEquals(myNotebook.getNameInputVal(), "(unnamed)");
		
		//checks that the value after click has the right extension
		Assert.assertEquals(myNotebook.getNameInputExt(), "."+ TestDocType.valueOf(newDocType).getId());
	}
	
}
