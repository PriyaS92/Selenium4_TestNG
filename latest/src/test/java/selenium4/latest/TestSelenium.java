package selenium4.latest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestSelenium {

	WebDriver driver;
	LoginPage log;
	NewCustomer customer;
	TablePage table;
	
	@BeforeTest
	public void setup(){
		ChromeOptions option = new ChromeOptions();
		option.setCapability(CapabilityType.BROWSER_VERSION, "95");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ktpr340\\Documents\\Selenium4\\latest\\chromedriver.exe");
		driver = new ChromeDriver(option);
		driver.navigate().to("http://demo.guru99.com/V1/index.php");
		driver.manage().window().maximize();
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
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("http://demo.guru99.com/V1/html/addcustomerpage.php");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(12));
		customer.setCustomername("AishwaryaRamu");
		customer.setDOB("12011989");
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
}
