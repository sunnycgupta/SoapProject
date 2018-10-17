package utility;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class Utility {
	@Test
	public static String getValues(String value) throws Exception {
		
		FileInputStream fis=new FileInputStream("application.properties");
		Properties p=new Properties();
		p.load(fis);
		return p.getProperty(value);
		}
	@Test
	public static ArrayList<String> getExcelData(String valueName) throws Exception {
		
		ArrayList<String> al=new ArrayList<String>();
		FileInputStream fis=new FileInputStream("testData.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet  sheet=wb.getSheetAt(0);
		Iterator<Row> rows=sheet.iterator();
		Row firstRow=rows.next();
		Iterator<Cell> cells=firstRow.cellIterator();
		int k=0;
		int column=0;
		while(cells.hasNext()) {
			Cell cv=cells.next();
			if(cv.getStringCellValue().equalsIgnoreCase("PathParameter")) {
				column=k;
			}
			k++;
		}
		
		while(rows.hasNext()) {
			Row row=rows.next();
			if(row.getCell(column).getStringCellValue().equalsIgnoreCase(valueName)) {
				
				Iterator<Cell> c=row.iterator();
				while(c.hasNext()) {
					Cell value=c.next();
					if(value.getCellTypeEnum()==CellType.STRING) {
						al.add(value.getStringCellValue());
					}
					else {
						al.add(NumberToTextConverter.toText(value.getNumericCellValue()));
					}
				}
			}
			
		}
		return al;
		
	}
}
