package Vtiger;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import CommonUtils.ExcelUtils;
import CommonUtils.JavaUtils;
import CommonUtils.PropertyFileUtils;
import CommonUtils.WebDriverUtils;

public class CreateOrganization {

	@Test
	public void oRG2() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		final WebDriver driver;
			
		PropertyFileUtils fu= new PropertyFileUtils();
		ExcelUtils excel = new ExcelUtils();
		WebDriverUtils wd = new WebDriverUtils();
		JavaUtils ju= new JavaUtils();
	
		//TO read data from Properties file	
		String Browser = fu.getDataFromPropertyFile("browser");
		String url = fu.getDataFromPropertyFile("URL");
		String un = fu.getDataFromPropertyFile("UN");
		String pw = fu.getDataFromPropertyFile("PW");
		
		//TO read data from Excel file
		String ORG = excel.getDataFromExcelFile("Sheet1", 1, 0);
//        String Drop = excel.getDataFromExcelFile("Sheet1", 1, 1);
        String Drop1 = excel.getDataFromExcelFile("Sheet1", 1, 2);
		
		if (Browser.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		} else if (Browser.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
		}else {
			driver = new FirefoxDriver();
		}
		
		//To use WebDriver method
		wd.maximize(driver);
		wd.implicitWait(driver);
        
		// Login page
        driver.get(url);
        driver.findElement(By.name("user_name")).sendKeys(un);
        driver.findElement(By.name("user_password")).sendKeys(pw);
        driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
        
        //Home page
        driver.findElement(By.xpath("//a[text()='Organizations']")).click();
        //Organization Page
        driver.findElement(By.xpath("//img[@title=\"Create Organization...\"]")).click();
        // Add organization page
        driver.findElement(By.name("accountname")).sendKeys(ORG+ju.getRandomNumber());
        driver.findElement(By.xpath("//input[@value='T']")).click();

        WebElement drop = driver.findElement(By.name("assigned_group_id"));
        WebElement drop1 = driver.findElement(By.name("industry"));
        //To Handle the DropDown
        wd.handleDropDown(drop1,Drop1);
        wd.handleDropDown(drop, 2);
        
        WebElement NO = driver.findElement(By.name("notify_owner"));
        //Perform Action class method
        wd.click(driver, NO);
    
        WebElement EO = driver.findElement(By.name("emailoptout"));    
        wd.click(driver, EO);
        
        WebElement Save = driver.findElement(By.xpath("(//input[@value=\"  Save  \"])[1]"));
        wd.click(driver, Save);
         
        Thread.sleep(3000);
        WebElement ADMIN = driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
         
        wd.moveToElement(driver, ADMIN);
         
        WebElement LOGOUT = driver.findElement(By.linkText("Sign Out"));
         
        wd.click(driver, LOGOUT);
        
        driver.quit();
        

	}

}
