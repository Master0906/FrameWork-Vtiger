package DataRead;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelData1 {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// TODO Auto-generated method stub

		// Create a FileInputStream to read the Excel file "Data1.xlsx"
		FileInputStream fis = new FileInputStream("src\\test\\resources\\Data1.xlsx");

		// Create a Workbook object from the FileInputStream using WorkbookFactory
		Workbook wb = WorkbookFactory.create(fis); 

		// Get a reference to the "Sheet1" sheet in the workbook
		Sheet sh = wb.getSheet("Sheet1");

		// Get the row  from the sheet
		Row rw = sh.getRow(1);

		// Get the  cell  from the row
		Cell cl = rw.getCell(1);

		// Extract the string value from the cell
		String data = cl.getStringCellValue();

		// Print the extracted data to the console
		System.out.println(data);
	}


}
