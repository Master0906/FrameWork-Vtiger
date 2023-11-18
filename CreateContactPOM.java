package Base;

import java.io.IOException;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import CommonUtils.ExcelUtils;
import CommonUtils.PropertyFileUtils;
import CommonUtils.WebDriverUtils;
import POM.ContactPage;
import POM.HomePage;
import POM.LoginPage;

public class CreateContactPOM {

	@Test
	public void contac1() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
        final WebDriver driver;
		//Object creation
        PropertyFileUtils fu= new PropertyFileUtils();
		ExcelUtils excel = new ExcelUtils();
		WebDriverUtils wd = new WebDriverUtils();
				
		//TO read data from Properties file	
		String Browser = fu.getDataFromPropertyFile("browser");
		String url = fu.getDataFromPropertyFile("URL");
		String un = fu.getDataFromPropertyFile("UN");
		String pw = fu.getDataFromPropertyFile("PW");
//		Child url
		String churl=fu.getDataFromPropertyFile("childurl");
//		parent url
		String purl=fu.getDataFromPropertyFile("parent");
		
		
		// To read data from excel
		String ORG = excel.getDataFromExcelFile("Sheet1", 1, 0);
		String firstName = excel.getDataFromExcelFile("Sheet1", 1, 3);
        String LastName = excel.getDataFromExcelFile("Sheet1", 1, 4);
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
		
        driver.get(url);
        LoginPage lp = new LoginPage(driver);
        lp.Login(un, pw);
        
        //Home Page
        
        HomePage hp = new HomePage(driver);
        hp.createContact();
        
        //Add Contact
        ContactPage cp = new ContactPage(driver);
        cp.addContact(firstName, LastName);
        
        Thread.sleep(3000);
        //Handle Dropdown
        WebElement drop = cp.getDropDown();
        wd.handleDropDown(drop, Drop1);
        
        //Add Org name to contact
        WebElement addorg = cp.getAddOrg();
        
      //To add ORganiztion name in Contact 
        wd.click(driver, addorg);
      
        Thread.sleep(3000);
        //Switch control to child
        wd.SwitchToWindow(driver,churl);
        
        cp.getSearchtf().sendKeys(ORG); 
        
        cp.getSearchbtn().click();
        Thread.sleep(3000);
        cp.getOrgnamebtn().click();
       
        
        // Switch control back to parent       
        wd.SwitchToWindow(driver,purl);
        Thread.sleep(3000);
        //Scroll down to choose file btn
        wd.scrolltoDown(driver);
        
//        Add IMG to Contact
        WebElement conimg=cp.getAddConImgbtn();
        
//        wd.takeScreenShotWebelement(driver, conimg);
        wd.moveToElement(driver, conimg);
        wd.click(driver, conimg);
        Thread.sleep(3000);
        
        Runtime.getRuntime().exec("C:\\Users\\DELL\\OneDrive\\Desktop\\Createcontact.exe");
        
        
        //SaveContact
       
        cp.saveContact();
        
        
        Thread.sleep(3000);
        
        //TO Logout/ Sign out
        cp.signout();
        
        

	}

}
