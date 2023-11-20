package DataRead;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Readdata3 {

	public static WebDriver driver;
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		FileInputStream fis = new FileInputStream("src\\test\\resources\\Data.properties");
		Properties p = new Properties();
		
		p.load(fis);
		
		String BROWSER = p.getProperty("browser");
		if (BROWSER.equalsIgnoreCase("Chrome")) {
			driver= new ChromeDriver();
		} else if(BROWSER.equalsIgnoreCase("Edge")){
			driver= new EdgeDriver();
		} else {
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		String URL = p.getProperty("ur");
		String USERNAME = p.getProperty("username");
		String PASSWORD = p.getProperty("username");
		
		driver.get(URL);
		driver.findElement(By.linkText("LOGIN")).click();;
		Thread.sleep(2000);
		driver.findElement(By.id("email")).sendKeys(USERNAME);
		driver.findElement(By.id("password")).sendKeys(PASSWORD);
		
		driver.findElement(By.id("last")).click();		

	}

}
