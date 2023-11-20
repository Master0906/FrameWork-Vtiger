package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	public WebDriver driver;
		@FindBy(id="username")
		private WebElement usernametf;
		
		@FindBy(name="pwd")
		private WebElement passwordtf;
		
		@FindBy(id="keepLoggedInCheckBox")
		private WebElement checkbox;
		
		@FindBy(xpath="//div[text()='Login ']")
		private WebElement loginbtn;

		public WebElement getUsernametf() {
			return usernametf;
		}

		public WebElement getPasswordtf() {
			return passwordtf;
		}

		public WebElement getCheckbox() {
			return checkbox;
		}

		public WebElement getLoginbtn() {
			return loginbtn;
		}
		
		public LoginPage(WebDriver driver){
			PageFactory.initElements(driver, this);
		}
		
		public LogoutPage Login(String usernamedata, String passworddata) {
			usernametf.sendKeys(usernamedata);
			passwordtf.sendKeys(passworddata);
			checkbox.click();
			loginbtn.click();
			return new LogoutPage(driver);
		}
		
		

}
