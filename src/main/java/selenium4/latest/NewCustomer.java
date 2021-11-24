package selenium4.latest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewCustomer {

	@FindBy(name="name")
	WebElement customername;
	@FindBy(name="dob")
	WebElement DOB;
	@FindBy(name="addr")
	WebElement address;
	@FindBy(name="city")
	WebElement city;
	@FindBy(name="state")
	WebElement state;
	@FindBy(name="pinno")
	WebElement pin;		
	@FindBy(name="telephoneno")		
	WebElement telephone;
	@FindBy(name="emailid")
	WebElement mailid;		
	@FindBy(name="sub")		
	WebElement submitbutton;
	
	public NewCustomer(WebDriver driver){
		PageFactory.initElements(driver, this);
	}

	public void setCustomername(String name) {
		customername.sendKeys(name);
	}

	public void setDOB(String dOB) {
		DOB.sendKeys(dOB);
	}

	public void setAddress(String Address) {
		address.sendKeys(Address);
	}

	public void setCity(String City) {
		city.sendKeys(City);
	}

	public void setState(String State) {
		state.sendKeys(State);
	}

	public void setPin(String Pin) {
		pin.sendKeys(Pin);
	}

	public void setTelephone(String Telephone) {
		telephone.sendKeys(Telephone);
	}

	public void setMailid(String Mailid) {
		mailid.sendKeys(Mailid);
	}
	
	public void submit(){
		submitbutton.click();
	}
}
