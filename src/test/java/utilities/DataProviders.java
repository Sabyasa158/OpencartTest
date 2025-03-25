package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException {
		
		String path = ".\\testData\\Opencart_TestData.xlsx"; // taking excel file from testData
		ExcelUtility xlutil = new ExcelUtility(path); // creating an object from ExcelUtility
		
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1",1);
		
		String logindata[][] = new String[totalrows][totalcols]; // created for two dimensional array which can store rows and cols
		
		for(int i=1;i<=totalrows;i++) { // read the data from excel storing in tow dimensional array
			for(int j=0;j<totalcols;j++) { 
				logindata[i-1][j] = xlutil.getCellData("Sheet1", i, j);
			}
		}
		return logindata; // returning two dimensional array
	}

}
