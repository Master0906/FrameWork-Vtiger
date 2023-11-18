package read;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class CreateSheet {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// TODO Auto-generated method stub
		
		FileInputStream files = new FileInputStream("src\\test\\resources\\Book1.xlsx");
		//To read Data from Excel Sheet
		Workbook wb = WorkbookFactory.create(files);
		//to get control of the sheet
		Sheet sh = wb.createSheet("Pune");
		
		
		//To Write Data in Excel SHEET
		Row row = sh.createRow(0);
		Cell cell = row.createCell(5);
		
		cell.setCellValue("Admin");
		
		FileOutputStream opFile = new FileOutputStream("src\\test\\resources\\Book1.xlsx");
		
		wb.write(opFile);

	}

}
