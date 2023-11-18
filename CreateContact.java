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
import CommonUtils.PropertyFileUtils;
import CommonUtils.WebDriverUtils;

public class CreateContact {

	@Test
	public void contact() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		final WebDriver driver;
		PropertyFileUtils fu= new PropertyFileUtils();
		ExcelUtils excel = new ExcelUtils();
		WebDriverUtils wd = new WebDriverUtils();
		
		//TO read data from Properties file	
		String Browser = fu.getDataFromPropertyFile("browser");
		String url = fu.getDataFromPropertyFile("URL");
		String un = fu.getDataFromPropertyFile("UN");
		String pw = fu.getDataFromPropertyFile("PW");
		
		
		if (Browser.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		} else if (Browser.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
		}else {
			driver = new FirefoxDriver();
		}
		
		wd.maximize(driver);
		wd.implicitWait(driver);
        
        driver.get(url);
        driver.findElement(By.name("user_name")).sendKeys(un);
        driver.findElement(By.name("user_password")).sendKeys(pw);
        driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();

//        Thread.sleep(3000);
        String first = excel.getDataFromExcelFile("Sheet1", 1, 3);
        String last = excel.getDataFromExcelFile("Sheet1", 1, 4);
        String Drop = excel.getDataFromExcelFile("Sheet1", 1, 1);
        
        
        driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.name("firstname")).sendKeys(first);
		driver.findElement(By.name("lastname")).sendKeys(last);
        driver.findElement(By.xpath("//input[@value=\"T\"]")).click();
		
		WebElement drop = driver.findElement(By.name("assigned_group_id"));
		
		wd.handleDropDown(drop, Drop);
		
		WebElement drop2 = driver.findElement(By.name("salutationtype"));
		
		wd.handleDropDown(drop2, 1);
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
