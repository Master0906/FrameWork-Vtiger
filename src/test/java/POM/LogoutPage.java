package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage {
	
	public WebDriver driver;
	
	@FindBy(id="logoutLink")
	private WebElement logoutbtn;

	public WebElement getLogoutbtn() {
		return logoutbtn;
	}
	public LogoutPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	public LoginPage Logout() {
		logoutbtn.click();
		return new LoginPage(driver);
	}

}
