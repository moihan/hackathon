package Util;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class TestDataProvider {

	public static Object[][] getTestData(String DataFileName, String SheetName) {
		

		ExcelUtil readdata = new ExcelUtil("C:\\Users\\ELCOT\\git\\hackathon\\cabBooking\\src\\test\\java\\excelsheet\\"+DataFileName);
		String sheetName = SheetName;

		int row = readdata.getRowCount(sheetName);

		int col = readdata.getColumnCount(sheetName);

		Object[][] dataset = new Object[row][col];
		int rowNum = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				dataset[rowNum][j] = readdata.getCellData(sheetName, j, rowNum);
			}
			rowNum++;
		}

		return dataset;

	}

}
