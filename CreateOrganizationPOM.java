package Base;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import CommonUtils.ExcelUtils;
import CommonUtils.JavaUtils;
import CommonUtils.PropertyFileUtils;
import CommonUtils.WebDriverUtils;
import POM.HomePage;
import POM.LoginPage;
import POM.OrganizationPage;

public class CreateOrganizationPOM {

	@Test
	public void oRG1() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		final WebDriver driver;
		//object Creation
		PropertyFileUtils fu= new PropertyFileUtils();
		ExcelUtils excel = new ExcelUtils();
		WebDriverUtils wd = new WebDriverUtils();
		JavaUtils ju= new JavaUtils();
//		System.out.println(ju.getRandomNumber());
	
		//TO read data from Properties file	
		String Browser = fu.getDataFromPropertyFile("browser");
		String url = fu.getDataFromPropertyFile("URL");
		String un = fu.getDataFromPropertyFile("UN");
		String pw = fu.getDataFromPropertyFile("PW");
		
		// To read data from excel
		String ORG = excel.getDataFromExcelFile("Sheet1", 1, 0);
        String Drop1 = excel.getDataFromExcelFile("Sheet1", 1, 1);
		
        
        
		if (Browser.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		} else if (Browser.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
		}else {
			driver = new FirefoxDriver();
		}
		
		// Login page
		wd.maximize(driver);
		wd.implicitWait(driver);
		//login credential
        driver.get(url);
        LoginPage lp = new LoginPage(driver);
        lp.Login(un, pw);
        
        //Home Page
        
        HomePage hp = new HomePage(driver);
        hp.createORG();
        
        //Add Organization
        OrganizationPage op= new OrganizationPage(driver);
        op.addORG(ORG+ju.getRandomNumber());
        Thread.sleep(3000);
        //handle dropdown
        wd.handleDropDown(op.getDropDown(), Drop1);;
        //Save ORG 
        op.saveORG();
        
        Thread.sleep(3000);
       
     // TO handle pop ups If ORG already Present and Sign out
        String s = "Organization Name Already Exists!";
       
        try {
			
        	if (driver.switchTo().alert().getText().equalsIgnoreCase(s)) {
    			wd.handelAlert(driver);
    			op.signout();
    		} else {
    			op.signout();					
    		}
		} catch (NoAlertPresentException e) {
			// TODO: handle exception
			
			op.signout();
			
		}
        
	}

}
