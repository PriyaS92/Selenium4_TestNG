package selenium4.latest;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.browserstack.local.Local;

public class TestSelenium {

	WebDriver driver;
	LoginPage log;
	NewCustomer customer;
	TablePage table;
	
	ProjectUtility util = new ProjectUtility();
	DesiredCapabilities caps = new DesiredCapabilities();
	Local bsLocal = new Local();
	
	
	@BeforeSuite
	public void configBrowserstack(){
		try{
			HashMap<String, String> bsLocalArgs = new HashMap<String, String>();
			bsLocalArgs.put("key", util.retrieveProperty("access_key"));
			bsLocal.start(bsLocalArgs);
			System.out.println("Browser stack Running Status: "+bsLocal.isRunning());
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@BeforeTest
	@Parameters({ "browserversion", "browser" })
	public void setup(String browserversion,String browser){
		try{

			caps.setCapability("os", "Windows");
			caps.setCapability("os_version", "10");
			caps.setCapability("browserstack.selenium_version", "4.0.0");
			caps.setCapability("browserstack.local", "true");
			caps.setCapability("name", "BStack-[Java] Selenium Test - Cross Browser"); // test name
			caps.setCapability("build", "BStack Build Number 3.1");
			caps.setCapability("browser", browser);
			caps.setCapability("browser_version", browserversion);
			driver = new RemoteWebDriver(new java.net.URL("https://" + util.retrieveProperty("browserstack_username")+ ":" +util.retrieveProperty("access_key")+ "@hub-cloud.browserstack.com/wd/hub"),caps);
			driver.navigate().to("http://demo.guru99.com/V1/index.php");
			driver.manage().window().maximize();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test(priority=0)
	public void loginbanking(){
		log = new LoginPage(driver);
		log.setuserdetails("mngr367306", "nenAzYr");
		log.clicklogin();
	}
	
	@Test(priority=1)
	public void addnewCustomer(){
		customer = new NewCustomer(driver);
		String mainWind = driver.getWindowHandle();
		
		// New method for window handling, if user wants to open 2 URLs in two different tabs at the same time
		driver.switchTo().newWindow(WindowType.TAB);
		
		
		driver.get("http://demo.guru99.com/V1/html/addcustomerpage.php");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(12));
		customer.setCustomername("AishwaryaRamu");
		customer.setDOB("12011989");
		
		//to find out the element which is present nearby to other web element or, 
		//we can say that it can find the web elements based on GUI location.
		
		WebElement radiobutton = driver.findElement(RelativeLocator.with(By.tagName("input")).toRightOf(By.xpath("//input[@type='radio'][2]")));
		radiobutton.click();
		
		customer.setAddress("Chrompet");
		customer.setCity("Chennai");
		customer.setState("TN");
		customer.setPin("600020");
		customer.setTelephone("9786514325");
		customer.setMailid("ramu89@yahoo.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		customer.submit();
		driver.close();
		driver.switchTo().window(mainWind);
	}
	
	@Test(priority=2)
	public void opentabledemoPage(){
		table = new TablePage(driver);
		table.mouseHover(driver);
	}
	
	@AfterMethod
    public void markTestStatus(ITestResult result)
    {
        String status;
        if(result.getStatus() == ITestResult.SUCCESS)
            status = "passed";
        else if(result.getStatus() == ITestResult.SKIP)
            status = "error";
        else
            status = "failed";
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \""+status+"\"}}");
    }
	
	@AfterTest
	public void teardown() throws Exception{
		driver.quit();
		bsLocal.stop();
	}
}
