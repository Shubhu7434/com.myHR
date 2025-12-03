package Utilities;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excelutiles {

	public static Object[][] getExcelData(String Filepath, String Sheetname) {
		Object[][] data = null;

		try {

			FileInputStream fis = new FileInputStream(Filepath);
			Workbook book = new XSSFWorkbook(fis);
			Sheet sheet = book.getSheet(Sheetname);
			int rowcount = sheet.getPhysicalNumberOfRows();
			int colcount = sheet.getRow(0).getPhysicalNumberOfCells();
			data = new Object[rowcount - 1][colcount];

			DataFormatter formatter = new DataFormatter();

			for (int i = 1; i < rowcount; i++) {
				Row row = sheet.getRow(i);
				for (int j = 0; j < colcount; j++) {
					Cell cell = row.getCell(j);

					if (cell != null) {
						data[i - 1][j] = formatter.formatCellValue(cell);
					} else {
						data[i - 1][j] = "";
					}
				}
			}
			book.close();
			fis.close();

		} catch (Exception e) {

		}
		return data;
	}

}
