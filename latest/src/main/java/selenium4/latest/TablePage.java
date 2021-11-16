package selenium4.latest;

import java.io.File;
import java.nio.file.Files;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TablePage {

	@FindBy(xpath="//li[@class='dropdown']//following::a")
	WebElement selenium;
	@FindBy(xpath="//a[text()='Table Demo']")
	WebElement tabel_demo;
	Actions act;
	
	public TablePage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	public void mouseHover(WebDriver driver){
		try{
			selenium.click();
			act = new Actions(driver);
			File img = tabel_demo.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(img, new File(System.getProperty("user.dir")+"\\images\\tableelement.jpg"));
			act.moveToElement(tabel_demo).click().build().perform();
		}
		catch(Exception e){e.printStackTrace();}
	}
}
