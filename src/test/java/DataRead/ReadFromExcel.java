package DataRead;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ReadFromExcel {

    public static WebDriver driver;

    public static void main(String[] args) throws IOException, InterruptedException {
        // Load properties from a file
        FileInputStream fis = new FileInputStream("src\\test\\resources\\Data.properties");
        Properties p = new Properties();
        p.load(fis);

        // Read browser property from Data.properties
        String BROWSER = p.getProperty("browser");

        // Initialize WebDriver based on the specified browser
        if (BROWSER.equalsIgnoreCase("Chrome")) {
            driver = new ChromeDriver();
        } else if (BROWSER.equalsIgnoreCase("Edge")) {
            driver = new EdgeDriver();
        } else {
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Read Excel data
        String excelFilePath = "src\\test\\resources\\Data1.xlsx"; // Update with your Excel file path
        String sheetName = "Sheet1"; // Update with your sheet name

        FileInputStream excelFile = new FileInputStream(excelFilePath);
        Workbook workbook = WorkbookFactory.create(excelFile);
        Sheet sheet = workbook.getSheet(sheetName);

        // Read data from Excel and perform actions
        String URL = p.getProperty("url");
        driver.get(URL);
        Thread.sleep(2000);

        // Assuming your Excel sheet has a header row
        int rowCount = sheet.getLastRowNum();
        for (int i = 1; i <= rowCount; i++) {
            Row row = sheet.getRow(i);
            String USERNAME = row.getCell(0).getStringCellValue();
            System.out.println();
            String PASSWORD = row.getCell(1).getStringCellValue();

            driver.findElement(By.id("username")).sendKeys(USERNAME);
            driver.findElement(By.name("pwd")).sendKeys(PASSWORD);

            driver.findElement(By.id("loginButton")).click();
            
        }

        
    }
}

