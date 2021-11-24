package selenium4.latest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

@FindBy(name="uid")
WebElement username;	
@FindBy(name="password")
WebElement password;
@FindBy(name="btnLogin")
WebElement loginbutton;

public LoginPage(WebDriver driver){
	PageFactory.initElements(driver, this);
}

public void setuserdetails(String user,String Password){
	username.sendKeys(user);
	password.sendKeys(Password);
}

public void clicklogin(){
	loginbutton.click();
}

}
