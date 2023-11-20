package DataRead;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Readdata2 {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		FileInputStream fis = new FileInputStream("src\\test\\resources\\Data.properties");
		
		
		Properties p = new Properties();
		
		p.load(fis);
		
		String URL = p.getProperty("ur");
		String USERNAME = p.getProperty("username");
//		String PASSWORD = p.getProperty("ps");
		
		driver.get(URL);
		driver.findElement(By.linkText("LOGIN")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("email")).sendKeys(USERNAME);
		driver.findElement(By.id("password")).sendKeys(USERNAME);
//		driver.findElement(By.id("last")).click();

	}

}
