package read;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

class Read {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// TODO Auto-generated method stub
		
		FileInputStream files = new FileInputStream("src\\test\\resources\\Book1.xlsx");
		//To read Data from Excel Sheet
		Workbook wb = WorkbookFactory.create(files);
		//to get control of the sheet
		Sheet sh = wb.getSheet("Sheet1");
		/*
		//To get control of Row
		Row rw = sh.getRow(1);
		
		//To get the Value of the cell/Column
		Cell c1 = rw.getCell(1);
		
		// to read value of the cell
		
		String data = c1.getStringCellValue();
		System.out.println(data);
		*/
		int count = sh.getLastRowNum();
		
		for (int i = 1 ; i<count ; i++) 
		{
			Row rw = sh.getRow(i);
			String cl0 = rw.getCell(0).getStringCellValue();
			System.out.println(cl0);
			String cl1 = rw.getCell(1).getStringCellValue();
			System.out.println(cl1);
			//Cell c11 = rw.getCell(1);
			
			
			
		}
		
		

	}

}
